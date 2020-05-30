package rnataraj.springframework.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import rnataraj.springframework.springmvc.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
