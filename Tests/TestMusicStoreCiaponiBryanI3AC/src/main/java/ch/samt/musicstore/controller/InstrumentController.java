package ch.samt.musicstore.controller;

import ch.samt.musicstore.model.MusicInstrument;
import ch.samt.musicstore.service.InstrumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sound.midi.Instrument;
import java.util.List;

@Controller
public class InstrumentController {
    private InstrumentService instrumentService;

    @Autowired
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping("/instruments")
    public String instrumentsByTypeOrBrand(Model model, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "brand", required = false) String brand) {
        List<MusicInstrument> filteredInstruments = List.of();

        if(type == null && brand == null) {
            filteredInstruments = instrumentService.findAll();
        }
        if(type != null && brand == null) {
            filteredInstruments = instrumentService.getInstrumentByType(type);
        }
        if(brand != null && type == null) {
            filteredInstruments = instrumentService.getInstrumentByBrand(brand);
        }
        if(type != null && brand != null) {
            filteredInstruments = instrumentService.getInstrumentByTypeAndBrand(type, brand);
        }

        model.addAttribute("instruments", filteredInstruments);
        return "instrumentList";
    }

    @GetMapping("/instruments/insert")
    public String insertInstrument(Model model) {
        model.addAttribute("instrument", new MusicInstrument());
        return "insertInstrument";
    }

    @PostMapping("/saveInstrument")
    public String saveInstrument(Model model, @Valid MusicInstrument instrument, Errors errors) {
        if(errors.hasErrors()) {
            model.addAttribute("instrument", new MusicInstrument());
            return "insertInstrument";
        }
        instrumentService.saveInstrument(instrument);

        return "redirect:/instruments";
    }
}
