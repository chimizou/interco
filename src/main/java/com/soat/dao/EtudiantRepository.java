package com.soat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soat.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
