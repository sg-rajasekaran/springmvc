package rnataraj.springframework.springmvc.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rnataraj.springframework.springmvc.domain.User;
import rnataraj.springframework.springmvc.services.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testList() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userService.listAll()).thenReturn((List) users);

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attribute("users",hasSize(2)));
    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        when(userService.getById(id)).thenReturn(new User());

        mockMvc.perform(get("/user/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/show"))
                .andExpect(model().attribute("user",instanceOf(User.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id =1;

        when(userService.getById(id)).thenReturn(new User());

        mockMvc.perform(get("/user/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/userform"))
                .andExpect(model().attribute("user",instanceOf(User.class)));

    }

    @Test
    public void testNewProduct() throws Exception {
        Integer id = 1;
        verifyZeroInteractions(userService);

        mockMvc.perform(get("/user/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/userform"))
                .andExpect(model().attribute("user",instanceOf(User.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String userName = "someuser";
        String password = "password";

        User returnUser = new User();
        returnUser.setId(id);
        returnUser.setUserName(userName);
        returnUser.setPassword(password);

        when(userService.saveOrUpdate(Matchers.<User>any())).thenReturn(returnUser);

        mockMvc.perform(post("/user")
                .param("id","1")
                .param("userName",userName)
                .param("password",password))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:user/show/1"))
                    .andExpect(model().attribute("user",instanceOf(User.class)))
                    .andExpect(model().attribute("user",hasProperty("id",is(id))))
                            .andExpect(model().attribute("user",hasProperty("userName",is(userName))))
                            .andExpect(model().attribute("user",hasProperty("password",is(password))));
        ArgumentCaptor<User> boundUser = ArgumentCaptor.forClass(User.class);
        verify(userService).saveOrUpdate(boundUser.capture());

        assertEquals(id,boundUser.getValue().getId());
        assertEquals(userName,boundUser.getValue().getUserName());
        assertEquals(password,boundUser.getValue().getPassword());

    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/user/delete/1")).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));
        verify(userService,times(1)).delete(id);
    }
}
