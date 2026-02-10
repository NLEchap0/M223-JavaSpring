package ch.samt.esercizio2.controller;

import ch.samt.esercizio2.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ProductController {
    static ArrayList<Product> products = new ArrayList<Product>();
    static {

        products.add(new Product(
                1,
                "Nicola Cappels",
                67.104,
                LocalDate.now())
        );
        products.add(new Product(
                2,
                "Re-lay Bianco",
                104.69,
                LocalDate.now())
        );
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", products);
        return "products";
    }
}
