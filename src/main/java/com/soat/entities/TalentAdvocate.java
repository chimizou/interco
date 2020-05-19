package com.soat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TalentAdvocate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_talent_advocate")
    private Long idTalentAdvocate;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    @OneToMany(mappedBy = "talentAdvocate", cascade = CascadeType.ALL)
    private Set<Candidate> candidates;
    // Image image;

}

