package project.epic_energy_back.dto;

import lombok.Data;
import project.epic_energy_back.entities.Prenotazione;

import java.time.LocalDate;

@Data
public class SoggiornoCorrenteDto {

    private LocalDate dataInizio;
    private Boolean comunicazioneDatiPS;
    private Boolean riversamentoSomme;
    private String problemaOspite;
    private String soluzioneOspite;
}



