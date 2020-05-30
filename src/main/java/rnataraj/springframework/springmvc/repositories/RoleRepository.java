package rnataraj.springframework.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import rnataraj.springframework.springmvc.domain.User;
import rnataraj.springframework.springmvc.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
