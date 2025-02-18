package com.project.backend.service.impl.product;


import com.project.backend.dto.product.ProductDTO;
import com.project.backend.repository.product.ProductRepository;
import com.project.backend.service.product.ProductService;
import com.project.backend.entity.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getSlug(),
                        product.getDescription(), product.getPrice(), product.getCategory(), product.getBrand()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id).map(product ->
                new ProductDTO(product.getId(), product.getName(), product.getSlug(),
                        product.getDescription(), product.getPrice(), product.getCategory(), product.getBrand()));
    }

    @Override
    public Optional<ProductDTO> getProductBySlug(String slug) {
        return productRepository.findBySlug(slug).map(product ->
                new ProductDTO(product.getId(), product.getName(), product.getSlug(),
                        product.getDescription(), product.getPrice(), product.getCategory(), product.getBrand()));
    }

    @Override
    public ProductDTO createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return new ProductDTO(savedProduct.getId(), savedProduct.getName(), savedProduct.getSlug(),
                savedProduct.getDescription(), savedProduct.getPrice(), savedProduct.getCategory(), savedProduct.getBrand());
    }

    @Override
    public ProductDTO updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(updatedProduct.getName());
        product.setSlug(updatedProduct.getSlug());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(updatedProduct.getCategory());
        product.setBrand(updatedProduct.getBrand());

        Product savedProduct = productRepository.save(product);
        return new ProductDTO(savedProduct.getId(), savedProduct.getName(), savedProduct.getSlug(),
                savedProduct.getDescription(), savedProduct.getPrice(), savedProduct.getCategory(), savedProduct.getBrand());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

