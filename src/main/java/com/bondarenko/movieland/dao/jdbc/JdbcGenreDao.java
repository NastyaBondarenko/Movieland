package com.bondarenko.movieland.dao.jdbc;

import com.bondarenko.movieland.dao.GenreDao;
import com.bondarenko.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.bondarenko.movieland.entity.Genre;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcGenreDao implements GenreDao {
    private static final String FIND_ALL_GENRES_SQL = "SELECT id, name FROM genre;";
    private final GenreRowMapper genreRowMapper = new GenreRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> findAll() {
        log.info("Find all genres");
        return jdbcTemplate.query(FIND_ALL_GENRES_SQL, genreRowMapper);
    }
}
