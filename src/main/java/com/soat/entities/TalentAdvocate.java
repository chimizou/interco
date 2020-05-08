package com.soat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalentAdvocate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idTalentAdvocate;
    String firstName;
    String lastName;
    String email;
    String telephone;
    @OneToMany(mappedBy = "talentAdvocate")
    Set<Candidate> candidates;
    // Image image;

}

