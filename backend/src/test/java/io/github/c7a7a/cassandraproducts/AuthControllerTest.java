package io.github.c7a7a.cassandraproducts;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.c7a7a.cassandraproducts.configuration.TestSecurityConfig;
import io.github.c7a7a.cassandraproducts.controllers.AuthController;
import io.github.c7a7a.cassandraproducts.data.Role;
import io.github.c7a7a.cassandraproducts.data.User;
import io.github.c7a7a.cassandraproducts.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthController.class)
@Import(TestSecurityConfig.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private UserService userService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void register_shouldReturnSuccessMessage() throws Exception {
        User user = new User("name", "password", Role.USER);

        mockMvc.perform(post("/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(user))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }
    @Test
    void login_shouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "name")
                        .param("password", "secret")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));
    }
    @Test
    void login_badCredentials_shouldReturn401() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "wrongName")
                        .param("password", "wrongPassword")
                )
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Login failed: Bad credentials"));
    }
    @Test
    void logout_shouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(post("/auth/logout"))
                .andExpect(status().isOk())
                .andExpect(content().string("Logout successful"));
    }
}
