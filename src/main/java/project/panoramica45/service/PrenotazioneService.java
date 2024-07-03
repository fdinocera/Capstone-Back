package project.panoramica45.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.panoramica45.dto.PrenotazioneDto;

import project.panoramica45.entities.Prenotazione;
import project.panoramica45.exceptions.NotFoundException;
import project.panoramica45.repository.PrenotazioneRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public Prenotazione savePrenotazione(PrenotazioneDto prenotazioneDto) {

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setAdulti(prenotazioneDto.getAdulti());
        prenotazione.setBambini(prenotazioneDto.getBambini());
        prenotazione.setCheckIn((prenotazioneDto.getCheckIn()));
        prenotazione.setCheckOut(prenotazioneDto.getCheckOut());
        prenotazione.setPiattaforma((prenotazioneDto.getPiattaforma()));
        prenotazione.setCostoSoggiorno((prenotazioneDto.getCostoSoggiorno()));
        prenotazione.setNomeCliente(prenotazioneDto.getNomeCliente());

        prenotazione.setComunicazioneDatiPs(false);
        prenotazione.setRiversamentoSomme(false);
        prenotazione.setProblemaOspite("");
        prenotazione.setSoluzioneOspite("");

        prenotazioneRepository.save(prenotazione);
        return prenotazione;
    }

    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public Optional<Prenotazione> getPrenotazioneById(int id) {
        return prenotazioneRepository.findById(id);
    }

    public Prenotazione updatePrenotazione(int id, PrenotazioneDto prenotazioneDto) {

        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(id);
        if (optionalPrenotazione.isPresent()) {
            Prenotazione prenotazione = optionalPrenotazione.get();
            prenotazione.setNomeCliente(prenotazioneDto.getNomeCliente());
            prenotazione.setPiattaforma(prenotazioneDto.getPiattaforma());
            prenotazione.setAdulti(prenotazioneDto.getAdulti());
            prenotazione.setBambini(prenotazioneDto.getBambini());
            prenotazione.setCheckOut(prenotazioneDto.getCheckOut());
            prenotazione.setCheckIn(prenotazioneDto.getCheckIn());
            prenotazione.setCostoSoggiorno(prenotazioneDto.getCostoSoggiorno());

            prenotazione.setComunicazioneDatiPs(prenotazioneDto.getComunicazioneDatiPs());
            prenotazione.setRiversamentoSomme(prenotazioneDto.getRiversamentoSomme());
            prenotazione.setProblemaOspite(prenotazioneDto.getProblemaOspite());
            prenotazione.setSoluzioneOspite(prenotazioneDto.getSoluzioneOspite());


            prenotazioneRepository.save(prenotazione);
            return prenotazione;
        } else {
            throw new NotFoundException("Prenotazione con id=" + id + " non trovata.");
        }
    }

    public Prenotazione deletePrenotazione(int id) {

        Optional<Prenotazione> optionalPrenotazione = prenotazioneRepository.findById(id);
        if (optionalPrenotazione.isPresent()) {
            prenotazioneRepository.delete(optionalPrenotazione.get());
            return optionalPrenotazione.get();
        } else {
            throw new NotFoundException("Prenotazione con id=" + id + " non trovata.");
        }
    }

    public List<Prenotazione> getPrenotazioniByYear(int year) {
        return prenotazioneRepository.findByCheckInYear(year);
    }

    public List<Prenotazione> findFromCurrent() {
        return prenotazioneRepository.findFromCurrent();
    }

    public Prenotazione findCurrent(){
        return prenotazioneRepository.findCurrent();
    }
}