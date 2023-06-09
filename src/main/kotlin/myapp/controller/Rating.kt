package myapp.controller

import myapp.model.Rating
import myapp.service.RatingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ratings")
class Rating(val service: RatingService) {
    @GetMapping
    fun getRatings(@RequestParam user: Long) : List<Rating> = service.getRatingsUser(user)

    @PostMapping
    fun createRating(@RequestBody rating: Rating) = service.createRating(rating)
}