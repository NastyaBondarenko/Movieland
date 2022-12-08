package com.bondarenko.movieland.dao.jdbc.mapper;

import com.bondarenko.movieland.entity.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GenreRowMapperTest {

    @Test
    @DisplayName("test Genre Row Mapper")
    public void testGenreRowMapper() throws SQLException {

        GenreRowMapper genreRowMapper = new GenreRowMapper();
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("трилер");
        Genre actualGenre = genreRowMapper.mapRow(resultSet, 0);

        assertNotNull(actualGenre);
        assertEquals(1, actualGenre.getId());
        assertEquals("трилер", actualGenre.getName());
    }
}

