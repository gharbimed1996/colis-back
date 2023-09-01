package com.example.gauth.controller;


import com.example.gauth.config.KeycklockConfig;
import com.example.gauth.entity.EntrepriseDTO;
import com.example.gauth.entity.LivreurDTO;
import com.example.gauth.service.EntrepriseService;
import io.smallrye.common.constraint.NotNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.BadRequestException;


@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/AdminOrg")
public class EntrepriseController {


    @Autowired
    private EntrepriseService userService;
    @Autowired
    private KeycklockConfig config;

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(EntrepriseController.class);



    @PostMapping("/create")
    public String createuser(@RequestBody EntrepriseDTO entrepriseDTO) {

        return userService.addAdminOrg(entrepriseDTO);
    }
    @PostMapping("/login")

    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody LivreurDTO userDTO) {
        Keycloak keycloak = config.newKeycloakBuilderWithPasswordCredentials(userDTO).build();

        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            LOG.warn("invalid account. User probably hasn't verified email.", ex);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }

    }




}

