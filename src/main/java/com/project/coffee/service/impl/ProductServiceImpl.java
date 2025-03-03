package com.project.coffee.service.impl;

import com.project.coffee.dto.ProductDTO;
import com.project.coffee.entity.Product;
import com.project.coffee.exception.BadRequestException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.ProductRepository;
import com.project.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieves a list of all products from the repository.
     *
     * @return a list of ProductDTO objects representing all products
     */
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a product by its ID from the repository.
     *
     * @param productId the ID of the product to retrieve
     * @return a ProductDTO object representing the product with the given ID
     * @throws ResourceNotFoundException if no product is found with the given ID
     */
    public ProductDTO getProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
        return convertToDTO(product);
    }

    /**
     * Creates a new product and stores it in the repository.
     *
     * @param productDTO the ProductDTO to create a product from
     * @return a ProductDTO representing the newly created product
     * @throws BadRequestException if the product creation fails due to invalid input
     */
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productDTO.getName() == null || productDTO.getName().trim().isEmpty()) {
            throw new BadRequestException("Product name is required");
        }
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    /**
     * Updates an existing product in the repository with the provided details.
     *
     * @param productId  the ID of the product to update
     * @param productDTO the ProductDTO containing the updated product details
     * @return a ProductDTO representing the updated product
     * @throws ResourceNotFoundException if no product is found with the given ID
     */
    public ProductDTO updateProduct(Integer productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setCategoryId(productDTO.getCategoryId());
        product.setIsActive(productDTO.getIsActive());
        Product updatedProduct = productRepository.save(product);
        return convertToDTO(updatedProduct);
    }

    /**
     * Deletes a product from the repository by its ID.
     *
     * @param productId the ID of the product to delete
     * @throws ResourceNotFoundException if no product is found with the given ID
     */
    public void deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
        productRepository.delete(product);
    }

    /**
     * Converts a Product entity to a ProductDTO.
     *
     * @param product the Product entity to convert
     * @return a ProductDTO containing the data from the given Product entity
     */
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setStoreId(product.getStoreId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategoryId(product.getCategoryId());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setIsActive(product.getIsActive());
        return dto;
    }

    /**
     * Converts a ProductDTO into a Product entity.
     *
     * @param dto The ProductDTO to convert
     * @return The converted Product entity
     */
    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setStoreId(dto.getStoreId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategoryId(dto.getCategoryId());
        product.setIsActive(dto.getIsActive());
        return product;
    }
}