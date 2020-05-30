package rnataraj.springframework.springmvc.services;

import rnataraj.springframework.springmvc.commands.CustomerForm;
import rnataraj.springframework.springmvc.domain.Customer;

import java.util.List;

public interface CustomerService  extends CRUDService<Customer>{
    Customer saveOrUpdateCustomerForm(CustomerForm customerForm);
}
