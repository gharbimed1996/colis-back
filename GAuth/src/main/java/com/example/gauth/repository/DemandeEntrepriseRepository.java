package com.example.gauth.repository;
import com.example.gauth.entity.DemandeEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public interface DemandeEntrepriseRepository extends JpaRepository<DemandeEntreprise,String> {

    }


