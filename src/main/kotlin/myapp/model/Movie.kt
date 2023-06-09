package myapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
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

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "movie")
    @JsonIgnore
    var ratings: List<Rating> = mutableListOf()
) {
    constructor(name: String) : this(null, name)
}
