package project.panoramica45.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue
    private int id;

    private String nomeCliente;
    private Integer adulti;
    private Integer bambini;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String piattaforma;
    private Double costoSoggiorno;

    private String problemaOspite;
    private String soluzioneOspite;
    private Boolean comunicazioneDatiPs;
    private Boolean riversamentoSomme;
}
