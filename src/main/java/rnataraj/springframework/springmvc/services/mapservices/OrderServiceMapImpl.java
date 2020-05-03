package rnataraj.springframework.springmvc.services.mapservices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rnataraj.springframework.springmvc.domain.DomainObject;
import rnataraj.springframework.springmvc.domain.Order;
import rnataraj.springframework.springmvc.services.OrderService;

import java.util.List;
@Service
@Profile("map")

public class OrderServiceMapImpl extends AbstractMapService implements OrderService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Order getById(Integer id) {
        return (Order) super.getById(id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        return (Order) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    protected void loadDomainObjects() {

    }
}
