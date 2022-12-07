package com.bondarenko.movieland.dao.jdbc.mapper;

import com.bondarenko.movieland.entity.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MovieRowMapperTest {

    @Test
    @DisplayName("test Movie Row Mapper")
    public void testMovieRowMapper() throws SQLException {

        MovieRowMapper movieRowMapper = new MovieRowMapper();
        int localDate = LocalDateTime.of(1994, 01, 01, 0, 0).getYear();


        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name_russian")).thenReturn("Побег из Шоушенка");
        when(resultSet.getString("name_native")).thenReturn("The Shawshank Redemption");
        when(resultSet.getInt("year_of_release")).thenReturn(localDate);
        when(resultSet.getString("description")).thenReturn("Успешный банкир Энди Дюфрейн обвинен в убийстве");
        when(resultSet.getDouble("rating")).thenReturn(10.0);
        when(resultSet.getDouble("price")).thenReturn(120.0);
        when(resultSet.getString("picture_path")).thenReturn("https://images-na.ssl");
        when(resultSet.getInt("votes")).thenReturn(100);

        Movie actualMovie = movieRowMapper.mapRow(resultSet, 0);

        assertNotNull(actualMovie);
        assertEquals(1, actualMovie.getId());
        assertEquals("The Shawshank Redemption", actualMovie.getNameNative());
        assertEquals("Побег из Шоушенка", actualMovie.getNameRussian());
        assertEquals(localDate, actualMovie.getYearOfRelease());
        assertEquals("Успешный банкир Энди Дюфрейн обвинен в убийстве", actualMovie.getDescription());
        assertEquals(10.0, actualMovie.getRating());
        assertEquals(120.0, actualMovie.getPrice());
        assertEquals("https://images-na.ssl", actualMovie.getPicturePath());
        assertEquals(100, actualMovie.getVotes());
    }
}

