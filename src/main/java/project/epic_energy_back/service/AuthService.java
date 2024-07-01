package project.epic_energy_back.service;


import project.epic_energy_back.dto.AuthDataDto;
import project.epic_energy_back.dto.UtenteLoginDTO;
import project.epic_energy_back.entities.Utente;
import project.epic_energy_back.exceptions.NotFoundException;
import project.epic_energy_back.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.epic_energy_back.security.JwtTool;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthDataDto authenticateUtenteAndCreateToken(UtenteLoginDTO utenteLoginDto) {
        Optional<Utente> utenteOptional = utenteService.getUtenteByEmail(utenteLoginDto.getEmail());

        if (utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();
            if (passwordEncoder.matches(utenteLoginDto.getPassword(), utente.getPassword())) {
                jwtTool.createToken(utente);
                AuthDataDto authDataDto = new AuthDataDto();
                authDataDto.setAccessToken(jwtTool.createToken(utente));
                authDataDto.setRuolo(utente.getRuolo());
                authDataDto.setNome(utente.getNome());
                authDataDto.setCognome(utente.getCognome());
                authDataDto.setEmail(utente.getEmail());
                authDataDto.setUsername(utente.getUsername());
                authDataDto.setId(utente.getId());
                //authDataDto.setAvatar(utente.getAvatar());
                return authDataDto;

            } else {
                throw new UnauthorizedException("Errore nel login, riloggarsi");
            }

        } else {
            throw new NotFoundException("Utente con email " + utenteLoginDto.getEmail() + "non trovato ");
        }
    }
}