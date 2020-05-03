package rnataraj.springframework.springmvc.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rnataraj.springframework.springmvc.config.JpaIntegrationConfig;
import rnataraj.springframework.springmvc.domain.Address;
import rnataraj.springframework.springmvc.domain.Customer;
import rnataraj.springframework.springmvc.domain.Product;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class CustomerServiceJpaImplTest {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testListMethod() throws Exception {
        List<Customer> customers = (List<Customer>)customerService.listAll();
        System.out.println("size : "+customers.size());
        assert customers.size() == 5;
    }

    @Test
    public void testGetByIdMethod() throws Exception {
        Integer id = 8;
        Customer customer = customerService.getById(id);
        assert customer.getFirstName() == "Donald";
    }

    @Test
    public void testSaveOrUpdateMethod() throws Exception {
        Customer customer = new Customer();
        Integer id=11;
        System.out.println("Id : "+ id);
        customer.setId(id);
        customer.setFirstName("Palki");
        customer.setLastName("Sharma");
        customer.setBillingAddress(new Address());
        customer.getBillingAddress().setAddressLine1("2020, West Foster Avenue");
        customer.getBillingAddress().setAddressLine2("#02-05");
        customer.getBillingAddress().setCity("Fort Collins");
        customer.getBillingAddress().setState("Colorado");
        customer.getBillingAddress().setZipCode("534213");
        customer.setPhoneNumber("333-234-430");
        customerService.saveOrUpdate(customer);

        Customer customer1 = customerService.getById(id);
        System.out.println("First Name1: "+customer1.getFirstName());
        System.out.println("First Name: "+customer.getFirstName());
        assert customer1.getFirstName() == "Palki";
    }

    @Test
    public void testDeleteMethod() throws Exception {
        Integer id = 9;
        Customer customer = customerService.getById(id);
        customerService.delete(id);
        assertNull(customerService.getById(id));

    }


}
