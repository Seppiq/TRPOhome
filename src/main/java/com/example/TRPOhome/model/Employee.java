package com.example.TRPOhome.model;

import com.example.TRPOhome.model.enums.Access;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEES")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column
    private String googleName;

    @Column
    private String googleUsername;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "ROLE")
    private String role;

   /* @Enumerated(EnumType.STRING)
    private UserRole role;*/

    @Column(name = "BIRTHDATE")
    private Date birthdate;

    @Column(name = "MOBILE_PHONE")
    private String mp;

    @Column(name = "POSITION")
    private String position;

    @Enumerated(EnumType.STRING)
    private Access access;

/*    public Employee(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }*/
}
