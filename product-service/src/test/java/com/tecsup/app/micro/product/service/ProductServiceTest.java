package com.tecsup.app.micro.product.service;

import com.tecsup.app.micro.product.dto.Product;
import com.tecsup.app.micro.product.entity.ProductEntity;
import com.tecsup.app.micro.product.mapper.ProductMapper;
import com.tecsup.app.micro.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductService productService;

    private ProductEntity productEntity;
    private Product productDto;

    @BeforeEach
    void setUp() {
        productEntity = new ProductEntity(1L, "Laptop", "High end laptop", new BigDecimal("1500.00"), 10, "Electronics", 1L);
        productDto = new Product(1L, "Laptop", "High end laptop", new BigDecimal("1500.00"), 10, "Electronics", 1L);
    }

    @Test
    void getProductById_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(mapper.toDomain(productEntity)).thenReturn(productDto);

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(productDto.getId(), result.getId());
        assertEquals(productDto.getName(), result.getName());
        verify(productRepository).findById(1L);
    }

    @Test
    void getProductById_NotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(99L);
        });

        assertEquals("Product not found with id: 99", exception.getMessage());
    }
}

