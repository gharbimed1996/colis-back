package com.example.gauth.repository;

import com.example.gauth.entity.ClientDTO;
import com.example.gauth.entity.DemandeEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientDTO,String> {

}

