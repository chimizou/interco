package com.soat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {
    private Long idCandidate;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private TalentAdvocateDTO talentAdvocate;
    private LocalDate creationDate;
}
