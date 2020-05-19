package com.soat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_candidate")
	private Long idCandidate;
	private String firstName;
	private String lastName;
	private String email;
	private Date birthDate;
	private LocalDate creationDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_talent_advocate")
	private TalentAdvocate talentAdvocate;
	// Image image;

}
