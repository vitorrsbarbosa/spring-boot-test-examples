package com.basics.person;

import lombok.*;

import javax.persistence.Entity;

@Entity(name = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
}

