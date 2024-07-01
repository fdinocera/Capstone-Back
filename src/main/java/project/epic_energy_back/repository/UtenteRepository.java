package project.epic_energy_back.repository;

import project.epic_energy_back.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Integer> {

    public Optional<Utente> findByEmail(String email);
}
