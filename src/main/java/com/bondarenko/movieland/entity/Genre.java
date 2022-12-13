package com.bondarenko.movieland.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @Column(name = "id")
    private int genreId;

    @Column(name = "name")
    private String name;
}