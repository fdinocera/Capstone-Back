package project.panoramica45.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import project.panoramica45.dto.UtenteDTO;
import project.panoramica45.entities.Utente;
import project.panoramica45.enums.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.panoramica45.exceptions.BadRequestException;
import project.panoramica45.repository.UtenteRepository;

import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String saveUtente(UtenteDTO utenteDTO) {
        Utente utente = new Utente();
        utente.setUsername(utenteDTO.getUsername());
        utente.setNome((utenteDTO.getNome()));
        utente.setCognome((utenteDTO.getCognome()));
        utente.setEmail((utenteDTO.getEmail()));

        utente.setRuolo(Ruolo.ADMIN);

        utente.setPassword(passwordEncoder.encode(utenteDTO.getPassword()));

        utenteRepository.save(utente);
        return "User with id=" + utente.getId() + " correctly saved";
    }

    public Optional<Utente> getUtenteById(int id) {
        return utenteRepository.findById(id);
    }

    public Optional<Utente> getUtenteByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public Page<Utente> getAllUtenti(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente updateUtente(int id, UtenteDTO utenteDto) {
        Optional<Utente> utenteOptional = getUtenteById(id);
        if (utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();
            utente.setNome(utenteDto.getNome());
            utente.setCognome(utenteDto.getCognome());
            utente.setUsername(utenteDto.getUsername());
            utente.setEmail(utenteDto.getEmail());
            utente.setPassword(passwordEncoder.encode(utenteDto.getPassword()));
            utenteRepository.save(utente);
            return utente;
        } else {
            throw new BadRequestException("L' utente con id " + id + " non è stato trovato");
        }
    }

    public String deleteUtente(int id) {
        Optional<Utente> utenteOptional = getUtenteById(id);
        if (utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();
            utenteRepository.delete(utenteOptional.get());
            return "L' utente con id " + id + " è stato eliminato con successo.";
        } else {
            throw new BadRequestException("L' utente con id " + id + " non è stato trovato");
        }
    }
}


