package rnataraj.springframework.springmvc.services.mapservices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rnataraj.springframework.springmvc.domain.Address;
import rnataraj.springframework.springmvc.domain.Customer;
import rnataraj.springframework.springmvc.domain.DomainObject;
import rnataraj.springframework.springmvc.services.CustomerService;
import rnataraj.springframework.springmvc.services.mapservices.AbstractMapService;

import java.util.*;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer)super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer)super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    protected void loadDomainObjects() {
        domainMap = new HashMap<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
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
        domainMap.put(1,customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Donald");
        customer2.setLastName("Trump");
        customer2.setEmail("djt@domain.com");
        customer2.getBillingAddress().setAddressLine1("The White House");
        customer2.getBillingAddress().setAddressLine2("#20-20");
        customer2.getBillingAddress().setCity("Washington");
        customer2.setPhoneNumber("111-111-111");
        customer2.getBillingAddress().setState("DC");
        customer2.getBillingAddress().setZipCode("111111");
        domainMap.put(2,customer2);

        Customer customer3 = new Customer();
        customer3.setId(3);
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
        domainMap.put(3,customer3);

        Customer customer4 = new Customer();
        customer4.setId(4);
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
        domainMap.put(4,customer4);
    }
}
