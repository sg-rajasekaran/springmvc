package rnataraj.springframework.springmvc.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rnataraj.springframework.springmvc.commands.CustomerForm;
import rnataraj.springframework.springmvc.commands.validators.CustomerFormValidator;
import rnataraj.springframework.springmvc.convertors.CustomerToCustomerForm;
import rnataraj.springframework.springmvc.domain.Address;
import rnataraj.springframework.springmvc.domain.Customer;
import rnataraj.springframework.springmvc.domain.User;
import rnataraj.springframework.springmvc.services.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {
    @Mock //Mockito Mock Object
    private CustomerService customerService;

    @InjectMocks //sets up controller, and injects mock objects into it.
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); //initializes controller and mocks

        customerController.setCustomerFormValidator(new CustomerFormValidator());
        customerController.setCustomerToCustomerForm(new CustomerToCustomerForm());
        mockMvc= MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        //specific Mockito interaction, tell stub to return of products
        when(customerService.listAll()).thenReturn((List) customers); //need to strip generics to keep Mockito happy

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers",hasSize(2)));

    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        //Tell Mockito stub to return new product for ID 1
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer",instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;
        //tell Mockito stub to return new product product for ID 1
        User user = new User();
        Customer customer = new Customer();
        customer.setUser(user);

        when(customerService.getById(id)).thenReturn(customer);

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customerForm",instanceOf(CustomerForm.class)));

    }

    @Test
    public void testNewProduct() throws Exception {
        Integer id =1;

        //should not call service
        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customerForm", instanceOf(CustomerForm.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String firstName = "Santhana";
        String lastName = "Bharathi";
        String email = "sbhrathi@cinema.com";
        String phoneNumber = "1-510-234-6789";
        String addressLine1 = "Blk 40, Kovan Rise";
        String addressLine2 = "02-20";
        String city ="Singapore";
        String state = "NA";
        String zipCode = "544728";
        String username ="sbhatathi";
        String password = "password";


        Customer returnCustomer = new Customer();
        returnCustomer.setId(id);
        returnCustomer.setFirstName(firstName);
        returnCustomer.setLastName(lastName);
        returnCustomer.setEmail(email);
        returnCustomer.setPhoneNumber(phoneNumber);
        returnCustomer.setBillingAddress(new Address());
        returnCustomer.getBillingAddress().setAddressLine1(addressLine1);
        returnCustomer.getBillingAddress().setAddressLine2(addressLine2);
        returnCustomer.getBillingAddress().setCity(city);
        returnCustomer.getBillingAddress().setState(state);
        returnCustomer.getBillingAddress().setZipCode(zipCode);
        returnCustomer.setUser(new User());
        returnCustomer.getUser().setUserName(username);
        returnCustomer.getUser().setPassword(password);


        when(customerService.saveOrUpdateCustomerForm(Matchers.<CustomerForm>any())).thenReturn(returnCustomer);
        when(customerService.getById(Matchers.<Integer>any())).thenReturn(returnCustomer);

        mockMvc.perform(post("/customer")
                .param("customerId","1")
                .param("firstName",firstName)
                .param("lastName", lastName)
                    .param("userName",username)
                    .param("passwordText", password)
                    .param("passwordTextConf",password)
                .param("email",email)
                .param("phoneNumber",phoneNumber)
                .param("billingAddress.addressLine1",addressLine1)
                .param("billingAddress.addressLine2",addressLine2)
                .param("billingAddress.city",city)
                .param("billingAddress.state",state)
                .param("billingAddress.zipCode",zipCode))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:customer/show/1"));
//                .andExpect(model().attribute("customer",instanceOf(Customer.class)))
//                .andExpect(model().attribute("customer",hasProperty("id",is(id))))
//                .andExpect(model().attribute("customer",hasProperty("firstName",is(firstName))))
//                .andExpect(model().attribute("customer",hasProperty("lastName",is(lastName))))
//                .andExpect(model().attribute("customer", hasProperty("email",is(email))))
//                .andExpect(model().attribute("customer",hasProperty("phoneNumber",is(phoneNumber))))
//                .andExpect(model().attribute("customer",hasProperty("billingAddress",hasProperty("addressLine1",is(addressLine1)))))
//                .andExpect(model().attribute("customer", hasProperty("billingAddress",hasProperty("addressLine2",is(addressLine2)))))
//                .andExpect(model().attribute("customer", hasProperty("billingAddress",hasProperty("city",is(city)))))
//                .andExpect(model().attribute("customer",hasProperty("billingAddress",hasProperty("state",is(state)))))
//                .andExpect(model().attribute("customer",hasProperty("billingAddress",hasProperty("zipCode",is(zipCode)))));

        //verify properties of bound object
        ArgumentCaptor<CustomerForm> customerCopter = ArgumentCaptor.forClass(CustomerForm.class);
        verify(customerService).saveOrUpdateCustomerForm(customerCopter.capture());

        CustomerForm boundCustomer = customerCopter.getValue();

        assertEquals(id, boundCustomer.getCustomerId());
        assertEquals(firstName,boundCustomer.getFirstName());
        assertEquals(lastName,boundCustomer.getLastName());
        assertEquals(email,boundCustomer.getEmail());
        assertEquals(phoneNumber,boundCustomer.getPhoneNumber());
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService,times(1)).delete(id);
    }
}
