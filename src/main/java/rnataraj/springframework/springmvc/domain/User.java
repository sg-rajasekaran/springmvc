package rnataraj.springframework.springmvc.domain;

import rnataraj.springframework.springmvc.domain.security.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements DomainObject{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;
    private String userName;
    @Transient
    private String password;
    private String encryptedPassword;
    private Boolean enabled=true;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    //~defaults to @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id)
    // inverseJoinColumns = @joinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.setUser(this);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if(!this.roles.contains(role)) {
            this.roles.add(role);
        }

        if(!role.getUsers().contains(this)) {
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}
