package com.soat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idCandidate;
	String firstName;
	String lastName;
	String email;
	Date birthDate;
	@ManyToOne
	@JoinColumn(name = "id_talent_advocate")
	TalentAdvocate talentAdvocate;
	// Image image;

}
