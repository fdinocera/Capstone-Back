package project.panoramica45.dto;

import lombok.Data;
import project.panoramica45.enums.Ruolo;

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
