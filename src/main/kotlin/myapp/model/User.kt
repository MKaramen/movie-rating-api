package myapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime


@Entity
@Table(name="users")
data class User (
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @NotBlank(message = "Username is required.")
    @Column(nullable=false, unique=true)
    val username: String = "",

    @NotBlank(message = "Password is required.")
    @Column(nullable=false)
    @JsonIgnore
    val password: String = "",

    @Column(name = "created_at")
    var createdAt : LocalDateTime = LocalDateTime.now(),

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    var ratings: List<Rating> = mutableListOf()
) {
    constructor(username: String, password: String) : this(null, username, password)
}