package com.example.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.products.Repository.ProductRepository;
import com.example.products.model.Product;

@Service 
public class ProductService {

    private final ProductRepository repo;


    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }
    public List<Product> findAll() {
        return repo.findAll();


    }

    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    public Product save(Product product) {
        return repo.save(product);
    }

    public Optional<Product> update(Long id, Product product) {
        Optional<Product> optionalProduct = repo.findById(id);
        if (optionalProduct.isEmpty()) {
            return Optional.empty();
        }
        Product productBd = optionalProduct.get();
        productBd.setName(product.getName());
        productBd.setPrice(product.getPrice());
        productBd.setStock(product.getStock());
        return Optional.of(repo.save(productBd));
    }

    public boolean delete(long id) {
       
        if (!repo.existsById(id)) {
            return false;
         
        }
        repo.deleteById(id);
        return true;
    }
}
