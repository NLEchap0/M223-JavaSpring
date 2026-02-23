package ch.samt.esercizio2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Test for HTTP-GET /products (Step 6)
    @Test
    void testListEndpoint() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"));
    }

    // Test for Step 7: POST new product and redirect
    @Test
    void testAddProductRedirect() throws Exception {
        mockMvc.perform(post("/newproduct")
                        .param("id", "10")
                        .param("name", "Test Item")
                        .param("price", "20.0")
                        .param("expirationDate", "2027-01-01")
                        .param("description", "Valid test description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }

    // Test for Step 11: Filtering by name via Query Param
    @Test
    void testFilterByName() throws Exception {
        mockMvc.perform(get("/products").param("name", "Hoodie"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products"));
    }

    // Test for Step 4 & 7: Validation failure (should not redirect)
    @Test
    void testValidationFailure() throws Exception {
        mockMvc.perform(post("/newproduct")
                        .param("id", "-5") // Invalid ID
                        .param("name", "A") // Too short
                        .param("price", "-10.0")) // Negative price
                .andExpect(status().isOk())
                .andExpect(view().name("productForm"))
                .andExpect(model().hasErrors());
    }
}