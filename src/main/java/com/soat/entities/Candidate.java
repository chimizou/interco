package com.soat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Candidate implements Serializable {
	@Id
	@GeneratedValue
	Long idCandidate;
	String firstName;
	String lastName;
	Date birthday;
	String imageURL;

}
