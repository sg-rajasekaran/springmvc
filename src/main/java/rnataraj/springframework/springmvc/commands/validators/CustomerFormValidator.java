package rnataraj.springframework.springmvc.commands.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rnataraj.springframework.springmvc.commands.CustomerForm;

@Component
public class CustomerFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerForm.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CustomerForm customerForm = (CustomerForm) target;

        if(!customerForm.getPasswordText().equals(customerForm.getPasswordTextConf())) {
            errors.rejectValue("passwordText","PasswordsDontMatch.customerForm.passwordText","Passwords don't match");
            errors.rejectValue("passwordTextConf","PasswordsDontMatch.customerForm.passwordTextConf","Passwords don't Match");
        }

    }
}
