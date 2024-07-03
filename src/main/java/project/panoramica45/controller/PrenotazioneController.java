package project.panoramica45.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.panoramica45.dto.PrenotazioneDto;


import project.panoramica45.entities.Prenotazione;

import project.panoramica45.exceptions.BadRequestException;
import project.panoramica45.exceptions.NotFoundException;
import project.panoramica45.service.PrenotazioneService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @RequestMapping(value = "/prenotazioni", method = RequestMethod.OPTIONS)
    public void options() {
        // Risponde alle richieste OPTIONS
    }


    @PostMapping("/prenotazioni")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Prenotazione savePrenotazione(@RequestBody PrenotazioneDto prenotazioneDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return prenotazioneService.savePrenotazione(prenotazioneDto);
    }

    @GetMapping("/prenotazioni")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneService.getAllPrenotazioni();
    }

    @GetMapping("/prenotazioni/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Prenotazione getPrenotazioneById(@PathVariable int id) {

        Optional<Prenotazione> optionalPrenotazione = prenotazioneService.getPrenotazioneById(id);

        if (optionalPrenotazione.isPresent()) {
            return optionalPrenotazione.get();
        } else {
            throw new NotFoundException("Prenotazione con id=" + id + " non trovata");
        }
    }

    @PutMapping("/prenotazioni/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Prenotazione updatePrenotazione(@PathVariable int id, @RequestBody @Validated PrenotazioneDto prenotazioneDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return prenotazioneService.updatePrenotazione(id, prenotazioneDto);
    }

    @DeleteMapping("/prenotazioni/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Prenotazione deletePrenotazione(@PathVariable int id) {

        return prenotazioneService.deletePrenotazione(id);
    }


    @GetMapping("/prenotazioni/byYear/{year}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Prenotazione> getAllPrenotazioniPerAnno(@PathVariable int year) {
        return prenotazioneService.getPrenotazioniByYear(year);
    }

    @GetMapping("/prenotazioni/FromCurrent")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Prenotazione> findFromCheckInCurrent() {
        return prenotazioneService.findFromCurrent();
    }

    @GetMapping("/prenotazioni/current")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Prenotazione findCurrent() {
        return prenotazioneService.findCurrent();
    }
}
