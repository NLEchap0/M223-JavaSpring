package ch.samt.clockcollection.controllers;

import ch.samt.clockcollection.models.Clock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class NewIndexController {/*
    private final HashMap<Clock, Integer> clockMap = new HashMap<>();


    @GetMapping("/newclocklist")
    public String newClocksList(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("clocks", clockMap);
        }else{
            List<Clock> listClocks = new ArrayList<Clock>(clockMap.values().size());
            if(!listClocks.isEmpty()){
                Clock clock = listClocks.stream()
                        .filter(c -> c.getId().equals(id))
                        .findFirst()
                        .orElse(null);
                model.addAttribute("clocks", clock);
            }else {
                model.addAttribute("clocks", clockMap);
            }
        }
        return "listClocks";
    }*/
}
