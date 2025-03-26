package io.github.c7a7a.cassandraproducts;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.c7a7a.cassandraproducts.configuration.TestSecurityConfig;
import io.github.c7a7a.cassandraproducts.controllers.ProductController;
import io.github.c7a7a.cassandraproducts.data.Category;
import io.github.c7a7a.cassandraproducts.data.Product;
import io.github.c7a7a.cassandraproducts.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import(TestSecurityConfig.class)
public class AuthorizationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ProductService productService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void adminCanCreateProduct() throws Exception {
        Product product = new Product("name", Category.SPORT, "Desc",  new BigDecimal("59.99"));

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product))
            )
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void userCannotCreateProduct() throws Exception {
        Product product = new Product("name", Category.SPORT, "Desc",  new BigDecimal("59.99"));

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product))
                )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void userCanGetProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }
}
