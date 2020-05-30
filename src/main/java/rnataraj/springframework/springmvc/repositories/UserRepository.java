package rnataraj.springframework.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import rnataraj.springframework.springmvc.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
