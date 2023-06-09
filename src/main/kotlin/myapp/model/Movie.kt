package myapp.model

import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity
@Table(name = "movies")
data class Movie(
    @Id
    @Column(name="movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(unique=true, nullable = false)
    @NotBlank(message = "The movie name is required.")
    val name: String = "",

// TODO CHECK IF THIS USEFUL OR NOT BUT PROBABLY GOOD IDEA TO REMOVE
    @OneToMany(mappedBy = "movie")
    val ratings: List<Rating> = mutableListOf()
) {
    constructor(name: String) : this(null, name)
}
