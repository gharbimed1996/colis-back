package com.example.gauth.config;

import com.example.gauth.entity.ClientDTO;
import com.example.gauth.entity.EntrepriseDTO;
import com.example.gauth.entity.LivreurDTO;
import lombok.Data;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data

public class KeycklockConfig {
    public static Keycloak keycloak = null;


    @Value("${keycloak.server-url}")
    public  String SERVER_URL;

    @Value("${keycloak.realm}")
    public  String REALM;

    @Value("${keycloak.ressource}")
    public  String CLIENT_ID;

    @Value("${keycloak.username}")
    public  String USERNAME;
    @Value("${keycloak.password}")
    public  String PASSWORD;
    @Value("${keycloak.secret}")
    public  String SECRET;

    public  Keycloak getInstance() {

        return KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .grantType(OAuth2Constants.PASSWORD)
                .username(USERNAME)
                .password(PASSWORD)
                .clientSecret(SECRET)
                .clientId(CLIENT_ID)
                .build();
    }

    public  Keycloak getInstanceclient() {

        return  keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .clientId(CLIENT_ID)
                .username("admin")
                .password("admin")
                .build();
    }

    public KeycloakBuilder newKeycloakBuilderWithPasswordCredentials(LivreurDTO userDTO) {
        return KeycloakBuilder.builder()
                .realm(REALM)
                .serverUrl(SERVER_URL)
                .clientId(CLIENT_ID)
                .clientSecret(SECRET)
                .username(userDTO.getUsername())
                .password(userDTO.getPassword());
    }
    public KeycloakBuilder newKeycloakBuilderWithPasswordCredentialsClient(ClientDTO userDTO) {
        return KeycloakBuilder.builder()
                .realm(REALM)
                .serverUrl(SERVER_URL)
                .clientId(CLIENT_ID)
                .clientSecret(SECRET)
                .username(userDTO.getUsername())
                .password(userDTO.getPassword());
    }
    public KeycloakBuilder newKeycloakBuilderWithPasswordCredentialsAdminORG(EntrepriseDTO entrepriseDTO) {
        return KeycloakBuilder.builder()
                .realm(REALM)
                .serverUrl(SERVER_URL)
                .clientId(CLIENT_ID)
                .clientSecret(SECRET)
                .username(entrepriseDTO.getUsername())
                .password(entrepriseDTO.getPassword());
    }



}


     /*   // Initialize PhaseTwo client
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, serverUrl);
        UserRepresentation userRepresentation = new UserRepresentation();
        OrganizationRepresentation organization = new OrganizationRepresentation();
        organization.setName("My Organization");
        organization.getId();




        String organizationId = phaseTwo.organizations(realm).create(organization);

        organization.setName("Updated Organization Name");
        phaseTwo.organizations(realm).organization(organizationId).update(organization);

        List<OrganizationRepresentation> organizations = phaseTwo.organizations(realm).get();
        for (OrganizationRepresentation org : organizations) {
            System.out.println("Organization Name: " + org.getName());
        }

        // Delete organization
        phaseTwo.organizations(realm).organization(organizationId).delete();
    }
*/

