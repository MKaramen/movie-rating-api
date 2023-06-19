package myapp.controller

import jakarta.websocket.server.PathParam
import myapp.exception.MovieAlreadyExists
import myapp.exception.MovieNotFound
import myapp.model.Movie
import myapp.service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movies")
class MovieController(val service: MovieService) {
    @GetMapping
    fun getMovies(): List<Movie> = service.getMovies()

    @GetMapping("/{id}")
    fun getMovie(@PathVariable id: Long): Movie? = service.getMovie(id) ?: throw MovieNotFound("Movie with id $id not found.")

    @PostMapping
    fun createMovie(@RequestBody movie: Movie): ResponseEntity<Movie> {
        return if (service.checkMovieByName(movie.name)) {
            throw MovieAlreadyExists("The movie with the name ${movie.name} already exists.")
        } else {
            ResponseEntity.status(HttpStatus.CREATED).body(service.save(movie))
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id : Long): ResponseEntity<Unit> {
        return if (service.checkMovie(id)) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.deleteMovie(id))
        }
        else {
            throw MovieNotFound("Movie with id $id was not found.")
        }
    }
}