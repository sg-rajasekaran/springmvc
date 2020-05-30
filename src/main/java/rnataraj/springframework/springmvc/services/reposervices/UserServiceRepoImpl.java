package rnataraj.springframework.springmvc.services.reposervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rnataraj.springframework.springmvc.domain.User;
import rnataraj.springframework.springmvc.repositories.UserRepository;
import rnataraj.springframework.springmvc.services.UserService;

import java.util.ArrayList;
import java.util.List;
@Service
@Profile({"springdatajpa","jpadao"})
public class UserServiceRepoImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return userRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);

    }
}
