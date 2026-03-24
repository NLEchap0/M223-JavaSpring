package ch.samt.customers;

import ch.samt.customers.data.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testLoadCustomers() throws Exception {
        // Percorso aggiornato a /loadCustomers come nel tuo controller
        mockMvc.perform(get("/loadCustomers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerList"))
                // Il tuo controller usa l'attributo "customers" (plurale)
                .andExpect(model().attribute("customers", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    public void testLoadInsertPage() throws Exception {
        // La tua pagina di inserimento (home) è mappata su "/"
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("customer"));
    }

    @Test
    public void testSaveCustomer_Success() throws Exception {
        // Percorso aggiornato a /addCustomer
        mockMvc.perform(post("/addCustomer")
                        .param("name", "Gino")
                        .param("surname", "Bartali")
                        .param("age", "70")
                        .param("city", "Firenze")
                        .param("ccNumber", "4242424242424242")
                        .param("ccExpiration", "12/25")
                        .param("ccCVV", "123"))
                .andExpect(status().is3xxRedirection())
                // Il tuo controller fa redirect su /loadCustomers
                .andExpect(redirectedUrl("/loadCustomers"));

        assert !customerRepository.findBySurnameIgnoreCase("Bartali").isEmpty();
    }

    @Test
    public void testSaveCustomer_WithErrors() throws Exception {
        // Percorso /addCustomer, ma senza il parametro 'age' per forzare l'errore
        mockMvc.perform(post("/addCustomer")
                        .param("name", "Fausto")
                        .param("surname", "Coppi"))
                .andExpect(status().isOk())
                // In caso di errore il tuo controller ritorna la vista "home"
                .andExpect(view().name("home"))
                .andExpect(model().attributeHasFieldErrors("customer", "age"));

        assert customerRepository.findBySurnameIgnoreCase("Coppi").isEmpty();
    }

    @Test
    public void testLoadCustomersBySurname() throws Exception {
        // Percorso aggiornato a /loadCustomers/{surnameToFilter}
        mockMvc.perform(get("/loadCustomers/Rossi"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerList"))
                .andExpect(model().attributeExists("customers"));
    }

    @Test
    public void testLoadCustomersByCity() throws Exception {
        // 3. Implementare un test per la pagina customersbycity
        mockMvc.perform(get("/customersbycity")
                        .param("city", "Lugano"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerList"))
                .andExpect(model().attributeExists("customers"));
    }
}