package myapp.model

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import java.time.LocalDateTime

@Entity
@Table(name="ratings")
class Rating (
    @Id
    @Column(name="rating_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val ratingId: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    val movie: Movie,

    @Min(value=0, message= "Rating must be minimum 0")
    @Max(value=10, message="Rating cannot be greater than 10")
    val rating: Int,

    @Column(nullable = false)
    val timestamp: LocalDateTime
)