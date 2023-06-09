package myapp.service

import myapp.model.Rating
import myapp.repository.RatingRepository
import org.springframework.stereotype.Service

@Service
class RatingService(val db: RatingRepository) {
    fun getRatingsUser(user: Long): List<Rating> = db.findByUserId(user)

    fun createRating(rating: Rating): Rating = db.save(rating)
}