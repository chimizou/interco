package com.soat.dto;

import com.soat.entities.TalentAdvocate;
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
    private TalentAdvocate talentAdvocate;
    private LocalDate creationDate;
}
