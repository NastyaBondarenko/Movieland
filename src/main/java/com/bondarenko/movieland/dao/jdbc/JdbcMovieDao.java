package com.bondarenko.movieland.dao.jdbc;

import com.bondarenko.movieland.dao.MovieDao;
import com.bondarenko.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.bondarenko.movieland.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcMovieDao implements MovieDao {

    private static final String FIND_ALL_SQL = "SELECT id, name_russian, name_native, year_of_release, description," +
            "rating,price,picture_path,votes  FROM movie;";
    //    private final Logger log = LoggerFactory.getLogger(getClass());    private final MovieRowMapper movieRowMapper = new MovieRowMapper();

    private static final String FIND_BY_GENRE_ID_SQL = "SELECT movie.id, movie.name_russian, movie.name_native, " +
            "movie.year_of_release,movie.description, movie.rating,movie.price,movie.picture_path,movie.votes " +
            "FROM movie  INNER JOIN movie_genre ON movie.id=movie_genre.movie_id  WHERE movie_genre.genre_id=?  ;";

    private MovieRowMapper movieRowMapper = new MovieRowMapper();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, movieRowMapper);
    }

    @Override
    public List<Movie> findByGenreId(int genreId) {
        return jdbcTemplate.query(FIND_BY_GENRE_ID_SQL, movieRowMapper, genreId);
    }
}
