package com.example.TRPOhome.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DUTY_SCHEDULE")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class DutySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "ROOM_NUMBER")
    private String name;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "RESPONSIBLE_PERSON")
    private String responsiblePerson;
}
