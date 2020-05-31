package rnataraj.springframework.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rnataraj.springframework.springmvc.commands.CustomerForm;
import rnataraj.springframework.springmvc.convertors.CustomerFormToCustomer;
import rnataraj.springframework.springmvc.convertors.CustomerToCustomerForm;
import rnataraj.springframework.springmvc.domain.Customer;
import rnataraj.springframework.springmvc.services.CustomerService;

import javax.validation.Valid;

@RequestMapping("/customer")
@Controller
public class CustomerController {
    private CustomerService customerService;
    private Validator customerFormValidator;
    private CustomerToCustomerForm customerToCustomerForm;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    @Qualifier("customerFormValidator")
    public void setCustomerFormValidator(Validator customerFormValidator) {
        this.customerFormValidator = customerFormValidator;
    }

    @Autowired
    public void setCustomerToCustomerForm(CustomerToCustomerForm customerToCustomerForm) {
        this.customerToCustomerForm = customerToCustomerForm;
    }

    @RequestMapping({"/list", "/"})
    public String listCustomers(Model model) {
        model.addAttribute("customers",customerService.listAll());
        return "customer/list";
    }

    @RequestMapping("/show/{id}")
    public String getCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "customer/show";
    }

    @RequestMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute("customerForm",new CustomerForm());
        return "customer/customerform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(@Valid CustomerForm customerForm, BindingResult bindingResult) {

        customerFormValidator.validate(customerForm, bindingResult);

        if(bindingResult.hasErrors()) {
            return "customer/customerForm";
        }
        Customer newCustomer = customerService.saveOrUpdateCustomerForm(customerForm);
        return "redirect:customer/show/"+newCustomer.getId();
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        Customer customer = customerService.getById(id);
//
//        CustomerForm customerForm = new CustomerForm();
//
//        customerForm.setCustomerId(customer.getId());
//        customerForm.setCustomerVersion(customer.getVersion());
//        customerForm.setEmail(customer.getEmail());
//        customerForm.setFirstName(customer.getFirstName());
//        customerForm.setLastName(customer.getLastName());
//        customerForm.setPhoneNumber(customer.getPhoneNumber());
//        customerForm.setUserId(customer.getUser().getId());
//        customerForm.setUserName(customer.getUser().getUserName());
//        customerForm.setUserVersion(customer.getUser().getVersion());
        model.addAttribute("customerForm",customerToCustomerForm.convert(customer));
        return "customer/customerform";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }
}
