package project.panoramica45.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SoggiornoCorrenteDto {

    private LocalDate dataInizio;
    private Boolean comunicazioneDatiPS;
    private Boolean riversamentoSomme;
    private String problemaOspite;
    private String soluzioneOspite;
}



