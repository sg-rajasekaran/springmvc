package rnataraj.springframework.springmvc.services.mapservices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rnataraj.springframework.springmvc.domain.DomainObject;
import rnataraj.springframework.springmvc.domain.User;
import rnataraj.springframework.springmvc.domain.security.Role;
import rnataraj.springframework.springmvc.services.RoleService;

import java.util.List;

@Service
@Profile("map")
public class RoleServiceMapImpl extends AbstractMapService implements RoleService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Role getById(Integer id) {
        return (Role)super.getById(id);
    }

    @Override
    public Role saveOrUpdate(Role domainobject) {
        return (Role) super.saveOrUpdate(domainobject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    protected void loadDomainObjects() {

    }
}
