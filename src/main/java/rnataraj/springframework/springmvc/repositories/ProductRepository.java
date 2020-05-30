package rnataraj.springframework.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import rnataraj.springframework.springmvc.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
