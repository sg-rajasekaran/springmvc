package rnataraj.springframework.springmvc.services.reposervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rnataraj.springframework.springmvc.commands.CustomerForm;
import rnataraj.springframework.springmvc.convertors.CustomerFormToCustomer;
import rnataraj.springframework.springmvc.domain.Customer;
import rnataraj.springframework.springmvc.repositories.CustomerRepository;
import rnataraj.springframework.springmvc.repositories.UserRepository;
import rnataraj.springframework.springmvc.services.CustomerService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Profile({"springdatajpa","jpadao"})
public class CustomerServiceRepoImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private CustomerFormToCustomer customerFormToCustomer;


    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }

    @Override
    public List<?> listAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer getById(Integer id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return customerRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Customer customer = customerRepository.findById(id).get();
        userRepository.delete(customer.getUser());
        customerRepository.deleteById(id);
    }

    @Override
    public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
        Customer newCustomer = customerFormToCustomer.convert(customerForm);

        if(newCustomer.getUser().getId() != null) {
            Customer existingCustomer = getById(newCustomer.getId());
            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }
        return saveOrUpdate(newCustomer);
    }


}
