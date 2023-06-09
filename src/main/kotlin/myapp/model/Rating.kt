package myapp.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
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
    val id: Long? = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    var user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    var movie: Movie? = null,

    @Min(value=0, message= "Rating must be minimum 0")
    @Max(value=10, message="Rating cannot be greater than 10")
    val rating: Int = 0,

    @Column(name = "created_at")
    var createdAt : LocalDateTime = LocalDateTime.now(),
) {
    constructor(user: User, movie: Movie, rating: Int) : this(null, user, movie, rating)
}