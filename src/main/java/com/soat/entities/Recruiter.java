package com.soat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Recruiter implements Serializable {
    @Id
    @GeneratedValue
    Long idRecruiter;
    String name;
    String description;
    String technologies;
    String address;
    String webSiteLink;
}
