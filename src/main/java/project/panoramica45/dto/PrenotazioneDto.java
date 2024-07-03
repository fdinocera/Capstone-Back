package project.panoramica45.dto;

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

    private String problemaOspite;
    private String soluzioneOspite;
    private Boolean comunicazioneDatiPs;
    private Boolean riversamentoSomme;
}
