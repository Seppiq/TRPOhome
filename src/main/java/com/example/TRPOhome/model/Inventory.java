package com.example.TRPOhome.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "INVENTORYS")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "RP")
    private String responsiblePerson;
}
