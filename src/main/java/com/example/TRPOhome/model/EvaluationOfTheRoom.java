package com.example.TRPOhome.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEES_CONTROL")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class EvaluationOfTheRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "wardrobe")
    private Integer wardrobe;

    @Column(name = "chairs")
    private Integer chairs;

    @Column(name = "Shelves")
    private Integer shelves;

    @Column(name = "beds")
    private Integer beds;

    @Column(name = "repair")
    private Date repair;
}
