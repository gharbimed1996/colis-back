package com.example.gauth.controller;

import com.example.gauth.config.KeycklockConfig;
import com.example.gauth.entity.ClientDTO;
import com.example.gauth.service.ClientService;
import io.phasetwo.client.openapi.model.UserRepresentation;
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
@RequestMapping("/clients")

public class ClientControlleur {

        @Autowired
        private ClientService clientService;
        @Autowired
        private KeycklockConfig config;

        private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(com.example.gauth.controller.ClientControlleur.class);



        @PostMapping("/create")
        public String createclient(@RequestBody ClientDTO clientDTO) {

            return clientService.createKeycloakUser(clientDTO);
        }
        @PostMapping("/login")

        public ResponseEntity<AccessTokenResponse> login( @RequestBody ClientDTO loginRequest) {
            Keycloak keycloak = config.newKeycloakBuilderWithPasswordCredentialsClient(loginRequest).build();

            AccessTokenResponse accessTokenResponse = null;
            try {
                accessTokenResponse = keycloak.tokenManager().getAccessToken();
                return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
            } catch (BadRequestException ex) {
                LOG.warn("invalid account.", ex);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
            }

        }
   




    }



