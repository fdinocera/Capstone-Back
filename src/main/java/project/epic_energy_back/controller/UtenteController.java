package project.epic_energy_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.epic_energy_back.dto.UtenteDTO;
import project.epic_energy_back.entities.Utente;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.service.UtenteService;

import java.util.Optional;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createUtente(@Validated @RequestBody UtenteDTO utenteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Invalid input data");
        }
        return utenteService.saveUtente(utenteDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isSelf(#id)")
    public Utente getUtenteById(@PathVariable int id) {
        Optional<Utente> utente = utenteService.getUtenteById(id);
        return utente.orElseThrow(() -> new BadRequestException("L' utente con id " + id + " non è stato trovato"));
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public Utente getUtenteByEmail(@PathVariable String email) {
        Optional<Utente> utente = utenteService.getUtenteByEmail(email);
        return utente.orElseThrow(() -> new BadRequestException("L' utente con email " + email + " non è stato trovato"));
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public Page<Utente> getAllUtenti(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        return utenteService.getAllUtenti(page, size, sortBy);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isSelf(#id)")
    public Utente updateUtente(@PathVariable int id, @Validated @RequestBody UtenteDTO utenteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Invalid input data");
        }
        return utenteService.updateUtente(id, utenteDTO);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or @securityService.isSelf(#id)")
    public String deleteUtente(@PathVariable int id) {
        return utenteService.deleteUtente(id);
    }
}
