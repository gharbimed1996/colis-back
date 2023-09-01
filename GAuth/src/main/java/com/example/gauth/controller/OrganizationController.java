package com.example.gauth.controller;


import com.example.gauth.config.KeycklockConfig;
import com.example.gauth.entity.OrgDTO;
import com.example.gauth.service.KeyCloakService;
import com.example.gauth.service.LivreurService;
import io.phasetwo.client.PhaseTwo;
import io.phasetwo.client.openapi.model.OrganizationRepresentation;
import io.phasetwo.client.openapi.model.UserRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.gauth.config.KeycklockConfig.keycloak;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private KeyCloakService keycl;
    @Autowired
    private KeycklockConfig config;
    @Autowired
    LivreurService use ;



    @PostMapping("/create")
    public String createOrg(@RequestBody OrgDTO orgDTO) {


        return keycl.addOrg(orgDTO);
    }



   /* @PostMapping
    public ResponseEntity<String> createOrganization(@RequestBody OrganizationRepresentation organization) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/

    @GetMapping("/orgs")
    public ResponseEntity <List<OrganizationRepresentation>> getOrganization() {
        return new ResponseEntity<>(keycl.gettOrg(),HttpStatus.OK);
    }
    @GetMapping("/member/{id}")
    public List<UserRepresentation> getOrganizationMembers(@PathVariable("id") String organizationId) {
        return keycl.gettMemberOrg(organizationId);
    }

    /*

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrganization(@PathVariable("id") String id, @RequestBody OrganizationRepresentation organization) {
        phaseTwo.organizations(keycloak.getRealm()).organization(id).update(organization);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable("id") String id) {
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, config.getSERVER_URL());
         phaseTwo.organizations(config.getREALM()).organization(id).delete();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
