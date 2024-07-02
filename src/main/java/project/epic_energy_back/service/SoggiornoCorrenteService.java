package project.epic_energy_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.epic_energy_back.dto.PrenotazioneDto;
import project.epic_energy_back.dto.SoggiornoCorrenteDto;
import project.epic_energy_back.entities.Prenotazione;
import project.epic_energy_back.entities.SoggiornoCorrente;
import project.epic_energy_back.exceptions.NotFoundException;
import project.epic_energy_back.repository.SoggiornoCorrenteRepository;

import java.util.List;
import java.util.Optional;


@Service
public class SoggiornoCorrenteService {

    @Autowired
    private SoggiornoCorrenteRepository soggiornoCorrenteRepository;

    public SoggiornoCorrente saveSoggiornoCorrente(SoggiornoCorrenteDto soggiornoCorrenteDto) {

        SoggiornoCorrente soggiornoCorrente = new SoggiornoCorrente();
        soggiornoCorrente.setDataInizio(soggiornoCorrenteDto.getDataInizio());
        soggiornoCorrente.setProblemaOspite(soggiornoCorrenteDto.getProblemaOspite());
        soggiornoCorrente.setSoluzioneOspite(soggiornoCorrenteDto.getSoluzioneOspite());
        soggiornoCorrente.setRiversamentoSomme(soggiornoCorrenteDto.getRiversamentoSomme());
        soggiornoCorrente.setComunicazioneDatiPS(soggiornoCorrenteDto.getComunicazioneDatiPS());

        soggiornoCorrenteRepository.save(soggiornoCorrente);
        return soggiornoCorrente;
    }
    public SoggiornoCorrente getSoggiornoCorrente(Integer id) {
        Optional<SoggiornoCorrente> soggiornoCorrenteOptional = soggiornoCorrenteRepository.findById(id);
        if (soggiornoCorrenteOptional.isPresent()) {
            //SoggiornoCorrente soggiornoCorrente = soggiornoCorrenteOptional.get();
            return soggiornoCorrenteOptional.get();
        } else {
            return null;
        }
    }

    public SoggiornoCorrente updateSoggiornoCorrente(int id, SoggiornoCorrenteDto soggiornoCorrenteDto) {

        Optional<SoggiornoCorrente> soggiornoCorrenteOptional = soggiornoCorrenteRepository.findById(id);
        if (soggiornoCorrenteOptional.isPresent()) {
            SoggiornoCorrente soggiornoCorrente = soggiornoCorrenteOptional.get();

            soggiornoCorrente.setRiversamentoSomme(soggiornoCorrenteDto.getRiversamentoSomme());
            soggiornoCorrente.setDataInizio(soggiornoCorrenteDto.getDataInizio());
            soggiornoCorrente.setProblemaOspite(soggiornoCorrenteDto.getProblemaOspite());
            soggiornoCorrente.setSoluzioneOspite(soggiornoCorrenteDto.getSoluzioneOspite());
            soggiornoCorrente.setComunicazioneDatiPS(soggiornoCorrenteDto.getComunicazioneDatiPS());

            soggiornoCorrenteRepository.save(soggiornoCorrente);
            return soggiornoCorrente;
        } else {
            throw new NotFoundException("Dati soggiorno corrente con id=" + id + " non trovati.");
        }
    }
}
