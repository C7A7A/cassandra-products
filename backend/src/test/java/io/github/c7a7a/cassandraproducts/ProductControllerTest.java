package io.github.c7a7a.cassandraproducts;

import io.github.c7a7a.cassandraproducts.configuration.TestSecurityConfig;
import io.github.c7a7a.cassandraproducts.controllers.ProductController;
import io.github.c7a7a.cassandraproducts.data.Product;
import io.github.c7a7a.cassandraproducts.data.ProductDTO;
import io.github.c7a7a.cassandraproducts.services.ProductService;
import io.github.c7a7a.cassandraproducts.utils.TestProductEntities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import(TestSecurityConfig.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ProductService productService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Product product = TestProductEntities.defaultProduct();
    private final ProductDTO productDTO = TestProductEntities.defaultProductDTO();

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void createProduct_shouldReturnSuccessMessage() throws Exception {
        when(productService.createProduct(any(ProductDTO.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Product created successfully"))
                .andExpect(jsonPath("$.data.name").value("Test Product"));
    }

    @Test
    @WithMockUser(username = "user")
    void getProduct_shouldReturnSuccessMessage() throws Exception {
        when(productService.getProduct("123")).thenReturn(product);

        mockMvc.perform(get("/products/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Product retrieved successfully"))
                .andExpect(jsonPath("$.data.name").value("Test Product"));
    }

    @Test
    @WithMockUser(username = "user")
    void getAllProducts_shouldReturnSuccessMessage() throws Exception {
        when(productService.getProducts()).thenReturn(List.of(product, product));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Products retrieved successfully"))
                .andExpect(jsonPath("$.data[0].name").value("Test Product"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void updateProduct_shouldReturnSuccessMessage() throws Exception {
        when(productService.updateProduct(eq("123"), any(ProductDTO.class)))
                .thenReturn(product);

        mockMvc.perform(put("/products/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Product updated successfully"))
                .andExpect(jsonPath("$.data.name").value("Test Product"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deleteProduct_shouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(delete("/products/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Product deleted successfully"))
                .andExpect(jsonPath("$.data").value("123"));
    }
}
