package com.barbosa.basics.person;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
}

