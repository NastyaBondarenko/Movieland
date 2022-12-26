package com.bondarenko.movieland.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_sequence")
    @SequenceGenerator(name = "country_id_sequence", sequenceName = "country_id_sequence")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}