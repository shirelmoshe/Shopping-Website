package com.shopping.shopping_cart.service.implementation;

import com.shopping.shopping_cart.dao.ProductRepository;
import com.shopping.shopping_cart.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int productId) {
        return productRepository.findById(productId);
    }

    public Product addProduct(Product product) {
        // Perform necessary validation or business logic
        if (product == null) {
            throw new IllegalArgumentException("Invalid product");
        }
        // Save the product in the repository
        Product addedProduct = productRepository.save(product);
        // Add logs
        System.out.println("Product added: " + addedProduct);
        return addedProduct;
    }

    public Product updateProduct(int productId, Product updatedProduct) {
        // Get the existing product from the repository
        Product product = getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        // Update product properties
        product.setSku(updatedProduct.getSku());
        product.setImageUrl(updatedProduct.getImageUrl());
        product.setActive(updatedProduct.isActive());
        // Update other properties as needed

        // Save the updated product in the repository
        Product updated = productRepository.save(product);
        // Add logs
        System.out.println("Product updated: " + updated);
        return updated;
    }

    public void deleteProduct(int productId) {
        // Perform necessary validation or business logic
        Optional<Product> productOptional = getProductById(productId);
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
        // Delete the product from the repository
        productRepository.deleteById(productId);
        // Add logs
        System.out.println("Product deleted with ID: " + productId);
    }
}
