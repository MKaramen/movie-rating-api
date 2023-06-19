package myapp.controller

import myapp.exception.MovieNotFound
import myapp.exception.UserNotFound
import myapp.model.*
import myapp.service.*

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/{userId}/ratings")
class RatingController(val service: RatingService, val userService: UserService, val movieService: MovieService) {
    @GetMapping
    fun getRatings(@PathVariable userId: Long) : List<Rating> {
        if (userService.getUser(userId) == null) throw UserNotFound("User with $userId not found.")
        return service.getRatingsUser(userId)
    }

    @PostMapping
    fun createRating(@PathVariable userId: Long, @RequestBody rating: Rating): Rating {
        rating.user = userService.getUser(userId) ?: throw UserNotFound("User $userId not found.")
        rating.movie = rating.movie?.id?.let { movieService.getMovie(it) } ?: throw MovieNotFound("Movie not found")
        return service.createRating(rating)
    }
}