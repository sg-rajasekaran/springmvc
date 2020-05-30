package rnataraj.springframework.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import rnataraj.springframework.springmvc.domain.Order;

public interface OrderRepository extends CrudRepository<Order,Integer> {
}
