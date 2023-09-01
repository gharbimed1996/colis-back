package com.covoiturage.user.query.services;
import com.covoiturage.user.KeycloakProvider;
import com.covoiturage.user.commonApi.queries.GetAcountByIdQuery;
import com.covoiturage.user.query.entities.Account;
import com.covoiturage.user.query.repositories.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class KeycloakAdminClientService {
    @Value("${keycloak.realm}")
    public String realm;

    private final KeycloakProvider kcProvider;
    private  final AccountRepository accountRepository;


    public KeycloakAdminClientService(KeycloakProvider keycloakProvider, AccountRepository accountRepository) {
        this.kcProvider = keycloakProvider;
        this.accountRepository = accountRepository;
    }


    public UserRepresentation getUserById(String id) {
        UserResource userResource = kcProvider.getInstance().realm(realm).users().get(id);
   return userResource.toRepresentation();

    }

    public Account getAccountById(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }


}