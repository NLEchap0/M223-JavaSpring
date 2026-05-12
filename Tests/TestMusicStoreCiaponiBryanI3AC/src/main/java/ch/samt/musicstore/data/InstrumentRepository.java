package ch.samt.musicstore.data;

import ch.samt.musicstore.model.MusicInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepository extends JpaRepository<MusicInstrument, Long> {
    List<MusicInstrument> findAllByTypeIgnoreCase(String type);

    List<MusicInstrument> findAllByBrandIgnoreCase(String brand);

    List<MusicInstrument> findAllByTypeIgnoreCaseAndBrandIgnoreCase(String type, String brand);
}
