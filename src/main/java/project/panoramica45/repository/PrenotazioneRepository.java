package project.panoramica45.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.panoramica45.entities.Prenotazione;

import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

    @Query("SELECT p FROM Prenotazione p WHERE YEAR(p.checkIn) = :year")
    List<Prenotazione> findByCheckInYear(Integer year);

    @Query("SELECT p FROM Prenotazione p WHERE (p.checkIn >= CURRENT_DATE) OR " +
            "(p.checkIn <= CURRENT_DATE) AND (p.checkOut >= CURRENT_DATE)")
    List<Prenotazione> findFromCurrent();

    @Query("SELECT p FROM Prenotazione p WHERE (p.checkIn <= CURRENT_DATE) AND (p.checkOut >= CURRENT_DATE)")
    Prenotazione findCurrent();
}
