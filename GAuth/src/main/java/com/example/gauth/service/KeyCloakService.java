package com.example.gauth.service;


import com.example.gauth.config.KeycklockConfig;
import com.example.gauth.entity.OrgDTO;
import io.phasetwo.client.OrganizationsResource;
import io.phasetwo.client.PhaseTwo;
import io.phasetwo.client.openapi.model.OrganizationRepresentation;
import io.phasetwo.client.openapi.model.UserRepresentation;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;


@Service
public class KeyCloakService  {
    @Autowired
    KeycklockConfig keycklockConfig;




    public String addOrg(OrgDTO orgEntity) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());
        OrganizationRepresentation organizationRepresentation = new OrganizationRepresentation().name(orgEntity.getName());
        OrganizationsResource orgs = phaseTwo.organizations(keycklockConfig.getREALM());
        String orgId = orgs.create(organizationRepresentation);

        return orgId;
    }



    private static String extractCreatedId(Response response) {
        String[] location = response.getLocation().getPath().split("/");
        return location[location.length - 1];
    }




    public List<OrganizationRepresentation >gettOrg() {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());

        List<OrganizationRepresentation> organizations = phaseTwo.organizations(keycklockConfig.REALM).get();

        return  organizations;
    }
    public List<UserRepresentation> gettMemberOrg(String id) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());

        List<UserRepresentation> organizations = phaseTwo.organizations(keycklockConfig.REALM).organization(id).memberships().members();

        return  organizations;
    }

    }











