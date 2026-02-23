package ch.samt.esercizio2.controller;

import ch.samt.esercizio2.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ProductController {
    private static Map<Integer, Product> productsMap = new HashMap<>();

    static {
        productsMap.put(1, new Product(1, "Hoodie", 49.90, LocalDate.now().plusMonths(6), "Blue cotton hoodie"));
        productsMap.put(2, new Product(2, "Backpack", 35.00, LocalDate.now().plusYears(1), "Waterproof 20L backpack"));
        productsMap.put(3, new Product(3, "Hoodie", 55.00, LocalDate.now().plusMonths(3), "Red hooded sweatshirt"));
    }

    @GetMapping("/products")
    public String listProducts(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Double pricelessthan,
                               Model model) {

        var list = productsMap.values().stream().collect(Collectors.toList());

        if (name != null && !name.isEmpty()) {
            list = list.stream()
                    .filter(p -> p.getName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        }

        if (pricelessthan != null) {
            list = list.stream()
                    .filter(p -> p.getPrice() < pricelessthan)
                    .collect(Collectors.toList());
        }

        model.addAttribute("products", list);
        return "productList";
    }

    @GetMapping("/products/show/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        Product p = productsMap.get(id);
        if (p == null) {
            return "error/404";
        }
        model.addAttribute("product", p);
        return "productDetail";
    }

    @GetMapping("/newproduct")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "productForm";
    }

    @PostMapping("/newproduct")
    public String saveProduct(@Valid @ModelAttribute Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "productForm";
        }
        productsMap.put(product.getId(), product);
        return "redirect:/products";
    }
}