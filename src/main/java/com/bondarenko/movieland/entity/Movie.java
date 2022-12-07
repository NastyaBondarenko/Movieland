package com.bondarenko.movieland.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
@Getter
@Setter
@Builder
//@RequiredArgsConstructor
public class Movie {
    //    @Id
//    @Column()
//    @Id

    private int id;
    private String nameRussian;
    private String nameNative;
    private int yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String picturePath;
    private int votes;
}
