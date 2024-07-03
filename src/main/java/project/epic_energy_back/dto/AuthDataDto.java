package project.epic_energy_back.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import project.epic_energy_back.entities.Utente;
import project.epic_energy_back.enums.Ruolo;

@Data
public class AuthDataDto {
    private int id;
    private String accessToken;
    private String username;
    private String email;
    private String nome;
    private String cognome;
    private Ruolo ruolo;
}
