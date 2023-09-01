package com.example.gauth.controller;

import com.example.gauth.entity.DemandeEntreprise;
import com.example.gauth.service.DemandeEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController

@RequestMapping("/demande")

public class DemandeEntrepriseControlleur {


    private DemandeEntrepriseService demandeEntrepriseService;
    @Autowired
    public DemandeEntrepriseControlleur(DemandeEntrepriseService demandeEntrepriseService) {
        this.demandeEntrepriseService = demandeEntrepriseService;
    }
    @PostMapping
    public DemandeEntreprise ajout(@RequestBody DemandeEntreprise demandeEntreprise) {
        return demandeEntrepriseService.ajout(demandeEntreprise);
    }
    @GetMapping
    public List<DemandeEntreprise> findAll(){return demandeEntrepriseService.findAll();}
}
