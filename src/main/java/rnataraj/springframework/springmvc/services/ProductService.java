package rnataraj.springframework.springmvc.services;

import rnataraj.springframework.springmvc.commands.ProductForm;
import rnataraj.springframework.springmvc.domain.Product;

import java.util.List;

public interface ProductService extends CRUDService<Product>{

    Product saveOrUpdateProductForm(ProductForm productForm);
}
