package com.example.TRPOhome.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STUDENTS_CONTROL")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class StudentControl {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ARRIVAL_TIME")
    private Date at;

    @Column(name = "DEPARTURE_TIME")
    private Date dt;

    @ManyToOne
    @JoinColumn
    private Student student;
}
