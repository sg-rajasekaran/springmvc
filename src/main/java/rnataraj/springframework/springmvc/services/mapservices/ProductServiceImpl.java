package rnataraj.springframework.springmvc.services.mapservices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rnataraj.springframework.springmvc.domain.DomainObject;
import rnataraj.springframework.springmvc.domain.Product;
import rnataraj.springframework.springmvc.services.ProductService;
import rnataraj.springframework.springmvc.services.mapservices.AbstractMapService;

import java.math.BigDecimal;
import java.util.*;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getById(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return (Product) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }


    protected void loadDomainObjects() {
        domainMap = new HashMap<>();

        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("Product1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");
        domainMap.put(1,product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setDescription("Product2");
        product2.setPrice(new BigDecimal("13.99"));
        product2.setImageUrl("http://example.com/product2");
        domainMap.put(2,product2);

        Product product3 = new Product();
        product3.setId(3);
        product3.setDescription("Product3");
        product3.setPrice(new BigDecimal("14.99"));
        product3.setImageUrl("http://example.com/product3");
        domainMap.put(3,product3);

        Product product4 = new Product();
        product4.setId(4);
        product4.setDescription("Product4");
        product4.setPrice(new BigDecimal("15.99"));
        product4.setImageUrl("http://example.com/product4");
        domainMap.put(4,product4);

        Product product5 = new Product();
        product5.setId(5);
        product5.setDescription("Product5");
        product5.setPrice(new BigDecimal("16.99"));
        product5.setImageUrl("http://example.com/product5");
        domainMap.put(5,product5);

        Product product6 = new Product();
        product6.setId(6);
        product6.setDescription("Product6");
        product6.setPrice(new BigDecimal("17.99"));
        product6.setImageUrl("http://example.com/product6");
        domainMap.put(6,product6);

    }


}
