package rnataraj.springframework.springmvc.bootstrap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import rnataraj.springframework.springmvc.domain.*;
import rnataraj.springframework.springmvc.domain.security.Role;
import rnataraj.springframework.springmvc.enums.OrderStatus;
import rnataraj.springframework.springmvc.services.CustomerService;
import rnataraj.springframework.springmvc.services.ProductService;
import rnataraj.springframework.springmvc.services.RoleService;
import rnataraj.springframework.springmvc.services.UserService;

import java.math.BigDecimal;
import java.util.List;


@Component
public class SpringJPABootStrap implements ApplicationListener<ContextRefreshedEvent> {
    private ProductService productService;
    private CustomerService customerService;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();
        loadRoles();
        assignUsersToDefaultRole();
    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);
    }

    private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role ->{
            if(role.getRole().equalsIgnoreCase("CUSTOMER")) {
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }

    private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>)productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.saveOrUpdate(user);
        });
    }

    private void loadOrderHistory() {
        List<User> users = (List<User>)userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setOrderStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
            });
        });
    }


    public void loadUsersAndCustomers() {

        User user1 = new User();
        user1.setUserName("rcharan");
        user1.setPassword("password");

        Customer customer1 = new Customer();
        customer1.setFirstName("Ram");
        customer1.setLastName("Charan");
        customer1.setEmail("abc@domain.com");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("40, Kovan Rise");
        customer1.getBillingAddress().setAddressLine2("#20-20");
        customer1.getBillingAddress().setCity("Singapore");
        customer1.setPhoneNumber("65585640");
        customer1.getBillingAddress().setState("NA");
        customer1.getBillingAddress().setZipCode("544778");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUserName("dtrump");
        user2.setPassword("password");


        Customer customer2 = new Customer();
        customer2.setFirstName("Donald");
        customer2.setLastName("Trump");
        customer2.setEmail("djt@domain.com");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLine1("The White House");
        customer2.getBillingAddress().setAddressLine2("#20-20");
        customer2.getBillingAddress().setCity("Washington");
        customer2.setPhoneNumber("111-111-111");
        customer2.getBillingAddress().setState("DC");
        customer2.getBillingAddress().setZipCode("111111");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);

        User user3= new User();
        user3.setUserName("nmodi");
        user3.setPassword("password");

        Customer customer3 = new Customer();
        customer3.setFirstName("Narendra");
        customer3.setLastName("Modi");
        customer3.setEmail("ndm@domain.com");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLine1("Janpath");
        customer3.getBillingAddress().setAddressLine2("10");
        customer3.getBillingAddress().setCity("New Delhi");
        customer3.setPhoneNumber("9888666222");
        customer3.getBillingAddress().setState("NCR");
        customer3.getBillingAddress().setZipCode("333333");
        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);

        User user4 = new User();
        user4.setUserName("aghani");
        user4.setPassword("password");

        Customer customer4 = new Customer();
        customer4.setFirstName("Ashraf");
        customer4.setLastName("Ghani");
        customer4.setEmail("ag@domain.com");
        customer4.setBillingAddress(new Address());
        customer4.getBillingAddress().setAddressLine1("Dar-ul-Amaan");
        customer4.getBillingAddress().setAddressLine2("10");
        customer4.getBillingAddress().setCity("Kabul");
        customer4.setPhoneNumber("435-234-111");
        customer4.getBillingAddress().setState("Kabul");
        customer4.getBillingAddress().setZipCode("334533");
        user4.setCustomer(customer4);
        userService.saveOrUpdate(user4);
    }

    public void loadProducts() {
        Product product1 = new Product();
        product1.setDescription("Product1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product2");
        product2.setPrice(new BigDecimal("13.99"));
        product2.setImageUrl("http://example.com/product2");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product3");
        product3.setPrice(new BigDecimal("14.99"));
        product3.setImageUrl("http://example.com/product3");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product4");
        product4.setPrice(new BigDecimal("15.99"));
        product4.setImageUrl("http://example.com/product4");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product5");
        product5.setPrice(new BigDecimal("16.99"));
        product5.setImageUrl("http://example.com/product5");
        productService.saveOrUpdate(product5);

        Product product6 = new Product();
        product6.setDescription("Product6");
        product6.setPrice(new BigDecimal("17.99"));
        product6.setImageUrl("http://example.com/product6");
        productService.saveOrUpdate(product6);
    }
}
