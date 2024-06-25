package project.panoramica45.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class SoggiornoCorrente {

    @Id
    @GeneratedValue
    private int id;

    private LocalDate dataInizio;
    private Boolean comunicazioneDatiPS;
    private Boolean riversamentoSomme;
    private String problemaOspite;
    private String soluzioneOspite;
}
