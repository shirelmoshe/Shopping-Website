package com.shopping.shopping_cart.rest;

import com.shopping.shopping_cart.entity.Product;
import com.shopping.shopping_cart.service.implementation.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Retrieve all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        System.out.println("Retrieving all products...");
        List<Product> products = productService.getAllProducts();
        System.out.println("Total products found: " + products.size());
        return ResponseEntity.ok(products);
    }

    // Retrieve a product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        System.out.println("Retrieving product with ID: " + productId);
        Optional<Product> product = productService.getProductById(productId);
        if (product.isPresent()) {
            System.out.println("Product found: " + product.get());
            return ResponseEntity.ok(product.get());
        } else {
            System.out.println("Product not found.");
            return ResponseEntity.notFound().build();
        }
    }

    // Add a new product
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, Principal principal) {
        System.out.println("Adding a new product: " + product);

        // Get the username of the currently authenticated user
        String username = principal.getName();

        // Check if the user has admin privileges
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("admin"));

        if (isAdmin) {
            Product addedProduct = productService.addProduct(product);
            System.out.println("New product added with ID: " + addedProduct.getProductId());
            return ResponseEntity.ok(addedProduct);
        } else {
            System.out.println("Access denied. User does not have admin privileges.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    // Update a product
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product updatedProduct) {
        System.out.println("Updating product with ID: " + productId);
        Product updated = productService.updateProduct(productId, updatedProduct);
        System.out.println("Product updated: " + updated);
        return ResponseEntity.ok(updated);
    }

    // Delete a product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        System.out.println("Deleting product with ID: " + productId);
        productService.deleteProduct(productId);
        System.out.println("Product deleted successfully.");
        return ResponseEntity.noContent().build();
    }

    // View all products (alternative endpoint)
    @GetMapping("/view")
    public ResponseEntity<List<Product>> viewProducts() {
        System.out.println("Viewing all products...");
        List<Product> products = productService.getAllProducts();
        System.out.println("Total products found: " + products.size());
        return ResponseEntity.ok(products);
    }
}
