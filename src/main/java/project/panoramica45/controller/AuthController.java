package project.panoramica45.controller;

import org.springframework.web.bind.annotation.*;
import project.panoramica45.dto.AuthDataDto;
import project.panoramica45.dto.UtenteLoginDTO;
import project.panoramica45.dto.UtenteDTO;
import project.panoramica45.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import project.panoramica45.service.AuthService;
import project.panoramica45.service.UtenteService;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/auth/register")
    public String register(@RequestBody @Validated UtenteDTO utenteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return utenteService.saveUtente(utenteDTO);
    }

    @PostMapping("/auth/login")
    public AuthDataDto login(@RequestBody @Validated UtenteLoginDTO utenteLoginDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return authService.authenticateUtenteAndCreateToken(utenteLoginDTO);
    }
}
