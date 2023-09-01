package com.example.gauth.service;

import com.example.gauth.config.KeycklockConfig;
import com.example.gauth.entity.EntrepriseDTO;
import com.example.gauth.entity.OrgDTO;
import io.phasetwo.client.OrganizationsResource;
import io.phasetwo.client.PhaseTwo;
import io.phasetwo.client.openapi.model.OrganizationRoleRepresentation;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Service
public class EntrepriseService {

    @Autowired
    KeycklockConfig keycklockConfig;




    public EntrepriseService(KeycklockConfig keycklockConfig) {
        this.keycklockConfig = keycklockConfig;
    }

    public String addAdminOrg(EntrepriseDTO entrepriseDTO) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());
        UsersResource usersResource = keycloak.realm(keycklockConfig.getREALM()).users();
        CredentialRepresentation pass = new CredentialRepresentation();
        pass.setType(CredentialRepresentation.PASSWORD);
        pass.setValue(entrepriseDTO.getPassword());
        pass.setTemporary(false);
        UserRepresentation user = new UserRepresentation();
        user.setUsername(entrepriseDTO.getUsername());
        user.setEmail(entrepriseDTO.getEmail());
        user.setEnabled(true);
        user.setCredentials(Arrays.asList(pass));
        Response response = usersResource.create(user);
        String userId = CreatedResponseUtil.getCreatedId(response);
        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getREALM());
        String orgId = entrepriseDTO.getOrgId();
        orgsResource.organization(orgId).memberships().add(userId);
        String roleName = "Organization Admin";
        RoleRepresentation roleid = keycloak.realm("Auth").roles().get(roleName).toRepresentation();
        List<RoleRepresentation> roles = Arrays.asList(roleid);
        keycloak.realm("Auth").users().get(userId).roles().realmLevel().add(roles);
        return userId;
}


}



