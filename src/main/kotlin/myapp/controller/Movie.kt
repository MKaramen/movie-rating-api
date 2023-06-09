package myapp.controller

import myapp.model.Movie
import myapp.service.MovieService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movies")
class Movie(val service: MovieService) {
    @GetMapping
    fun getMovies(): List<Movie> = service.getMovies()

    @PostMapping
    fun createMovie(@RequestBody movie: Movie) {
        service.save(movie)
    }
}