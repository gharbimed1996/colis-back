package com.example.gauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EntrepriseDTO {
    private String username;

    private String email;
    private String password;
    private String id;
    private String orgId;
    private String realm;
}
