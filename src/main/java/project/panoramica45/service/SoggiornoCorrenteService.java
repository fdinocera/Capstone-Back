package project.panoramica45.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.panoramica45.dto.SoggiornoCorrenteDto;
import project.panoramica45.entities.SoggiornoCorrente;
import project.panoramica45.exceptions.NotFoundException;
import project.panoramica45.repository.SoggiornoCorrenteRepository;

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

    public List<SoggiornoCorrente> getSoggiornoCorrente(){
       return soggiornoCorrenteRepository.findAll();
    }

    public SoggiornoCorrente updateSoggiornoCorrente(int id, SoggiornoCorrenteDto soggiornoCorrenteDto){

        Optional<SoggiornoCorrente> soggiornoCorrenteOptional = soggiornoCorrenteRepository.findById(id);
        if (soggiornoCorrenteOptional.isPresent()) {
            SoggiornoCorrente soggiornoCorrente= soggiornoCorrenteOptional.get();

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
