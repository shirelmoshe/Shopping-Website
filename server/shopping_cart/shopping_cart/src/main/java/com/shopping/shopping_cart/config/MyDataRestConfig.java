package com.shopping.shopping_cart.config;

import com.shopping.shopping_cart.entity.Order;
import com.shopping.shopping_cart.entity.Product;
import com.shopping.shopping_cart.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private final EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Expose entity IDs in the REST API
        config.exposeIdsFor(Product.class, ProductCategory.class, Order.class);

        // Set the default media type to JSON
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);

        // Use annotated repository detection strategy
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);

        // Enable CORS for all endpoints and allow all HTTP methods
        cors.addMapping("/**").allowedMethods("*");

        // Log the base path of the Order repository
        String basePath = config.getBasePath().getPath();
        System.out.println("Order base path: " + basePath);
    }
}
