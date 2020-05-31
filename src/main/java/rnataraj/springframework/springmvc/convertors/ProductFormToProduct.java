package rnataraj.springframework.springmvc.convertors;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import rnataraj.springframework.springmvc.commands.ProductForm;
import rnataraj.springframework.springmvc.domain.Product;

@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {
    @Override
    public Product convert(ProductForm productForm) {
        Product product = new Product();
        product.setId(productForm.getId());
        product.setVersion(productForm.getVersion());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setImageUrl(productForm.getImageUrl());
        return product;
    }
}
