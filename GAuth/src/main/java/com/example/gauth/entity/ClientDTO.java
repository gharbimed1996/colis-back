package com.example.gauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.PrivateKey;


@Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    public class ClientDTO {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private String id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String telephone;



    }


