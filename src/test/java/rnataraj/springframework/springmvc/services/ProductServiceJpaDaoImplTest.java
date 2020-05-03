package rnataraj.springframework.springmvc.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rnataraj.springframework.springmvc.config.JpaIntegrationConfig;
import rnataraj.springframework.springmvc.domain.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ProductServiceJpaDaoImplTest {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testListMethod() throws Exception {
        List<Product> products = (List<Product>)productService.listAll();
        assert products.size() == 6;
    }

   @Test
    public void testGetIdMethod() throws Exception {
        Integer id = 2;
        Product product = productService.getById(id);
        assert product.getDescription() == "Product2";
    }
    
    @Test
    public void testSaveOrUpdate() throws Exception {
        Product product = new Product();
        product.setId(8);
        product.setDescription("Product8");
        product.setPrice(BigDecimal.valueOf(48.99));
        product.setImageUrl("http://example.com/product8");
        productService.saveOrUpdate(product);
        Product product1 = productService.getById(8);
        assert product.getDescription() == "Product8";

    }

    @Test
    public void testDelete() throws Exception {
        Integer id =3;
        Product product = productService.getById(id);
        productService.delete(id);
        assertNull(productService.getById(id));

    }
}
