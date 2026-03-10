package ch.samt.clockcollection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListEndpoint() throws Exception {
        mockMvc.perform(get("/clocks/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("listClocks"))
                .andExpect(model().attributeExists("clocks"));
    }

    @Test
    void testAddProductRedirect() throws Exception {
        mockMvc.perform(post("/clocks/add")
                        .param("id", "10")
                        .param("brand", "Rolex")
                        .param("model", "F340")
                        .param("price", "-20203"))
                .andExpect(status().is4xxClientError());
    }


    @Test
    void testFilterByName() throws Exception {
        mockMvc.perform(get("/clocks/list").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("clocks"));
    }
}
