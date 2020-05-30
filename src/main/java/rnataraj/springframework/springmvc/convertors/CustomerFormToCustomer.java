package rnataraj.springframework.springmvc.convertors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import rnataraj.springframework.springmvc.commands.CustomerForm;
import rnataraj.springframework.springmvc.domain.Address;
import rnataraj.springframework.springmvc.domain.Customer;
import rnataraj.springframework.springmvc.domain.User;

@Component
public class CustomerFormToCustomer implements Converter<CustomerForm, Customer> {
    @Override
    public Customer convert(CustomerForm customerForm) {
        Customer customer = new Customer();
        customer.setUser(new User());
        customer.setBillingAddress(new Address());
        customer.getUser().setId(customerForm.getUserId());
        customer.getUser().setVersion(customerForm.getUserVersion());
        customer.setId(customerForm.getCustomerId());
        customer.setVersion(customerForm.getCustomerVersion());
        customer.getUser().setUserName(customerForm.getUserName());
        customer.getUser().setPassword(customerForm.getPasswordText());
        customer.setFirstName(customerForm.getFirstName());
        customer.setLastName(customerForm.getLastName());
        customer.setEmail(customerForm.getEmail());
        customer.setPhoneNumber(customerForm.getPhoneNumber());

        return customer;

    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
