package com.example.TRPOhome.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STUDENTS")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "BIRTHDATE")
    private Date birthdate;

    @Column(name = "DATANARUSHENIA")
    private Date dataNarusheniya;

    @Column(name = "NAMENARUSHENIA")
    private String NAMENARUSHENIA;

    @Column(name = "MOBILE_PHONE")
    private String mp;

    @Column(name = "ROOM_NUMBER")
    private Integer roomNumber;

    @ManyToOne
    @JoinColumn
    private EvaluationOfTheRoom evaluationOfTheRoom;
}
