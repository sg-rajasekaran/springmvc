package rnataraj.springframework.springmvc.services.reposervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rnataraj.springframework.springmvc.domain.security.Role;
import rnataraj.springframework.springmvc.repositories.RoleRepository;
import rnataraj.springframework.springmvc.services.RoleService;

import java.util.ArrayList;
import java.util.List;
@Service
@Profile({"springdatajpa","jpadao"})
public class RoleServiceRepoImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        return roleRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}
