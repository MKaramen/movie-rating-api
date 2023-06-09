package myapp.repository

import myapp.model.Rating
import org.springframework.data.jpa.repository.JpaRepository;

interface RatingRepository: JpaRepository<Rating, Long>