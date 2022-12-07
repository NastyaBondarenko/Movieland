package com.bondarenko.movieland.dao.jdbc.mapper;

import com.bondarenko.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String nameRussian = resultSet.getString("name_russian");
        String nameNative = resultSet.getString("name_native");
        Timestamp yearOfReleaseTimesTemp = resultSet.getTimestamp("year_of_release");
        String description = resultSet.getString("description");
        Double rating = resultSet.getDouble("rating");
        Double price = resultSet.getDouble("price");
        String picturePath = resultSet.getString("picture_path");
        int votes = resultSet.getInt("votes");

        return Movie.builder().
                id(id)
                .nameRussian(nameRussian)
                .nameNative(nameNative)
                .yearOfRelease(yearOfReleaseTimesTemp.toLocalDateTime().toLocalDate().getYear())
                .description(description)
                .rating(rating)
                .price(price)
                .picturePath(picturePath)
                .votes(votes)
                .build();
    }
}
