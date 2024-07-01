package project.epic_energy_back.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.epic_energy_back.dto.AuthDataDto;
import project.epic_energy_back.dto.UtenteLoginDTO;
import project.epic_energy_back.dto.UtenteDTO;
import project.epic_energy_back.entities.Prenotazione;
import project.epic_energy_back.entities.Utente;
import project.epic_energy_back.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import project.epic_energy_back.service.AuthService;
import project.epic_energy_back.service.UtenteService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/auth/register")
    public String register(@RequestBody @Validated UtenteDTO utenteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return utenteService.saveUtente(utenteDTO);
    }

    @PostMapping("/auth/login")
    public AuthDataDto login(@RequestBody @Validated UtenteLoginDTO utenteLoginDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return authService.authenticateUtenteAndCreateToken(utenteLoginDTO);
    }

    @GetMapping("/utenti")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Optional<Utente> getUtenteByEmail() {
        return utenteService.getUtenteByEmail("finocera@libero.it");
    }
}
