package myapp.controller

import jakarta.validation.Valid
import myapp.model.Rating
import myapp.model.User
import myapp.service.RatingService
import myapp.service.UserService
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/{userId}/ratings")
class Rating(val service: RatingService, val userService: UserService) {
    @GetMapping
    fun getRatings(@PathVariable userId: Long) : List<Rating> {
        if (userService.getUser(userId) == null) throw IllegalArgumentException("User not found")
        return service.getRatingsUser(userId)
    }

    @PostMapping
    fun createRating(@PathVariable userId: Long, @RequestBody rating: Rating) {
        val user: User = userService.getUser(userId) ?: throw IllegalArgumentException("User not found")
        println(user)
//        SET USER IN RATING HERE
//        FIND MOVIE WITH ID
//        SET MOVIE IN RATING
//        SAVE RATING
    }
}