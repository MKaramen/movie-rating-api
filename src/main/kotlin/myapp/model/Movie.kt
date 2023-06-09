package myapp.model

import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity
@Table(name = "movies")
data class Movie(
    @Id
    @Column(name="movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val movieId: Long = 0,

    @Column(unique=true, nullable = false)
    @NotBlank(message = "The movie name is required.")
    val name: String,

    @OneToMany(mappedBy = "movie")
    val ratings: List<Rating> = mutableListOf()
)
