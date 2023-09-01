package com.example.gauth.service;

import com.example.gauth.config.KeycklockConfig;
import com.example.gauth.entity.ClientDTO;
import com.example.gauth.repository.ClientRepository;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {
    @Value("${keycloak.realm}")
    public String realm;

    private final KeycklockConfig kcProvider;


    private final ClientRepository clientRepository;



    public ClientService(KeycklockConfig kcProvider ,ClientRepository clientRepository) {
        this.kcProvider = kcProvider;
        this.clientRepository=clientRepository;
    }

    public String createKeycloakUser(ClientDTO user) {
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(user.getPassword());
        credentialRepresentation.setTemporary(false);
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(Arrays.asList(credentialRepresentation));

// Set required user attributes
        Map<String, List<String>> userAttributes = new HashMap<>();
        userAttributes.put("attribute_name", Arrays.asList("attribute_value"));
        userRepresentation.setAttributes(userAttributes);

// Create the user
        Response response = usersResource.create(userRepresentation);


        if (response.getStatus() == 201) {
            //If you want to save the user to your other database, do it here, for example:
//            User localUser = new User();
//            localUser.setFirstName(kcUser.getFirstName());
//            localUser.setLastName(kcUser.getLastName());
//            localUser.setEmail(user.getEmail());
//            localUser.setCreatedDate(Timestamp.from(Instant.now()));
//            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
//            usersResource.get(userId).sendVerifyEmail();
//            userRepository.save(localUser);
        }
        String userId = CreatedResponseUtil.getCreatedId(response);
        
        user.getId();
        user.getUsername();
        user.getPassword();
        user.getEmail();
        user.getFirstName();
        user.getLastName();
        user.getTelephone();
        clientRepository.save(user);
        return user.getUsername();

    }
//    public String getUserInfo(String userName) {
//        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
//        UserRepresentation userRepresentation = new UserRepresentation();
//        userRepresentation.getUsername();
//        userRepresentation.getEmail();
//        userRepresentation.getFirstName();
//        userRepresentation.getLastName();
//        return usersResource.toString();
//    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}

