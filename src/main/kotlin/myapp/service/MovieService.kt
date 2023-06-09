package myapp.service

import myapp.model.Movie
import myapp.repository.MovieRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MovieService(val db: MovieRepository) {
    fun save(movie: Movie)  = db.save(movie)
    fun getMovies(): List<Movie> = db.findAll()

    fun getMovie(id:Long): Movie? = db.findByIdOrNull(id)

    fun deleteMovie(id: Long) = db.deleteById(id)
}