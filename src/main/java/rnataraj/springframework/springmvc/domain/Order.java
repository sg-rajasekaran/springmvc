package rnataraj.springframework.springmvc.domain;

import rnataraj.springframework.springmvc.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDER_HEADER")
public class Order extends AbstractDomainClass {

    private OrderStatus orderStatus;
    private Date dateShipped;

    @OneToOne
    private Customer customer;
    @Embedded
    private Address shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order",orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addToOrderDetails(OrderDetail orderDetail) {
        orderDetail.setOrder(this);
        orderDetails.add(orderDetail);
    }

    public void removeOrderDetails(OrderDetail orderDetail) {
        orderDetail.setOrder(null);
        orderDetails.remove(orderDetail);
    }
}
