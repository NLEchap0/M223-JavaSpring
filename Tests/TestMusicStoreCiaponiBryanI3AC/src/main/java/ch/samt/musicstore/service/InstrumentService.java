package ch.samt.musicstore.service;

import ch.samt.musicstore.data.InstrumentRepository;
import ch.samt.musicstore.model.MusicInstrument;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentService {
    private final InstrumentRepository instrumentRepository;

    @Autowired
    public InstrumentService(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    public List<MusicInstrument> findAll() {
        return instrumentRepository.findAll();
    }

    public List<MusicInstrument> getInstrumentByType(String type) {
        return instrumentRepository.findAllByTypeIgnoreCase(type);
    }

    public List<MusicInstrument> getInstrumentByBrand(String brand) {
        return instrumentRepository.findAllByBrandIgnoreCase(brand);
    }

    public List<MusicInstrument> getInstrumentByTypeAndBrand(String type, String brand) {
        return instrumentRepository.findAllByTypeIgnoreCaseAndBrandIgnoreCase(type, brand);
    }

    public void saveInstrument(@Valid MusicInstrument instrument) {
        instrumentRepository.save(instrument);
    }
}
