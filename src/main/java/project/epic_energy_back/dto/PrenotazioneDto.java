package project.epic_energy_back.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDto {

    private String nomeCliente;
    private int adulti;
    private int bambini;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String piattaforma;
    private double costoSoggiorno;
}
