package com.example.dev.api;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_EMPLOYEES")
@Setter
@Getter
public class Employees {
    @Id
    Long id;
    String firstName;
    String lastName;
    String email;
}
