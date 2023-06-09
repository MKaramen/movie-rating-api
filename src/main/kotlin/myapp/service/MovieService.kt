package myapp.service

import myapp.model.Movie
import myapp.repository.MovieRepository
import org.springframework.stereotype.Service

@Service
class MovieService(val db: MovieRepository) {
    fun save(movie: Movie) {
        db.save(movie)
    }

    fun getMovies(): List<Movie> {
        return db.findAll()
    }
}