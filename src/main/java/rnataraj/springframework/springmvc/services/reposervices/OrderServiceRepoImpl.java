package rnataraj.springframework.springmvc.services.reposervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rnataraj.springframework.springmvc.domain.Order;
import rnataraj.springframework.springmvc.repositories.OrderRepository;
import rnataraj.springframework.springmvc.services.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"springdatajpa","jpadao"})
public class OrderServiceRepoImpl implements OrderService {
    private OrderRepository orderRepository;

     @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<?> listAll() {
         List<Order> orders = new ArrayList<>();
         orderRepository.findAll().forEach(orders::add);
         return orders;
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        return orderRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
