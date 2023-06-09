package myapp.controller

import jakarta.websocket.server.PathParam
import myapp.model.Movie
import myapp.service.MovieService
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
    fun getMovie(@PathVariable id: Long): Movie? = service.getMovie(id)

    @PostMapping
    fun createMovie(@RequestBody movie: Movie): Movie = service.save(movie)

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id : Long) = service.deleteMovie(id)
}