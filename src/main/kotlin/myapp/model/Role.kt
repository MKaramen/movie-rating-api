package myapp.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name="roles")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    val id: Long? = 0,

    @NotBlank(message = "Role name is required.")
    val name: String = ""
) {
    constructor(name: String) : this(null, name)
}