package project.panoramica45.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.panoramica45.dto.SoggiornoCorrenteDto;
import project.panoramica45.entities.SoggiornoCorrente;
import project.panoramica45.exceptions.BadRequestException;
import project.panoramica45.service.SoggiornoCorrenteService;

import java.util.List;


@RestController
//@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
public class SoggiornoCorrenteController {

    @Autowired
    private SoggiornoCorrenteService soggiornoCorrenteService;

    @PostMapping("/prenotazioni/soggiornoCorrente")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SoggiornoCorrente saveSoggiornoCorrente(@RequestBody SoggiornoCorrenteDto soggiornoCorrenteDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return soggiornoCorrenteService.saveSoggiornoCorrente(soggiornoCorrenteDto);
    }

    @GetMapping("/prenotazioni/soggiornoCorrente")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<SoggiornoCorrente> getSoggiornoCorrente() {
        return soggiornoCorrenteService.getSoggiornoCorrente();
    }

    @PutMapping("/prenotazioni/soggiornoCorrente/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SoggiornoCorrente updateSoggiornoCorrente(@PathVariable int id, @RequestBody SoggiornoCorrenteDto soggiornoCorrenteDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return soggiornoCorrenteService.updateSoggiornoCorrente(id, soggiornoCorrenteDto);
    }
}