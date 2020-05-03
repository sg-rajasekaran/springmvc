package rnataraj.springframework.springmvc.services.mapservices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rnataraj.springframework.springmvc.domain.DomainObject;
import rnataraj.springframework.springmvc.domain.User;
import rnataraj.springframework.springmvc.services.UserService;

import java.util.List;
@Service
@Profile("map")

public class UserServiceMapImpl extends AbstractMapService implements UserService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User)super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainobject) {
        return (User) super.saveOrUpdate(domainobject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    protected void loadDomainObjects() {

    }

}

