package ch.samt.gardenwarehouse.controller;


import ch.samt.gardenwarehouse.model.Item;
import ch.samt.gardenwarehouse.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ItemsController {
    private ItemService itemService;

    @Autowired
    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String items(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "items";
    }

    @GetMapping("/items/{code}")
    public String searchItems(Model model, @PathVariable String code) {
        List<Item> items = itemService.findByCode(code);
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/items/sell")
    public String removeItem(Model model,
                              @RequestParam(value = "code", required = false)
                              String code) {
        itemService.removeByCode(code);
        List<Item> items = itemService.findByCode(code);
        model.addAttribute("items", items);
        return "redirect:/items/"+code;
    }
}
