package com.soat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalentAdvocateDTO {
    private Long idTalentAdvocate;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;


}
