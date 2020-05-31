package rnataraj.springframework.springmvc.services.reposervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rnataraj.springframework.springmvc.commands.ProductForm;
import rnataraj.springframework.springmvc.convertors.ProductFormToProduct;
import rnataraj.springframework.springmvc.domain.Product;
import rnataraj.springframework.springmvc.repositories.ProductRepository;
import rnataraj.springframework.springmvc.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"springdatajpa","jpadao"})
public class ProductServiceRepoImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductFormToProduct productFormToProduct;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductFormToProduct(ProductFormToProduct productFormToProduct) {
        this.productFormToProduct = productFormToProduct;
    }

    @Override
    public List<?> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product saveOrUpdateProductForm(ProductForm productForm) {
        return saveOrUpdate(productFormToProduct.convert(productForm));
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return productRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
