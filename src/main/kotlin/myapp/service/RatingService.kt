package myapp.service

import myapp.repository.RatingRepository
import org.springframework.stereotype.Service

@Service
class RatingService(val db: RatingRepository) {
}