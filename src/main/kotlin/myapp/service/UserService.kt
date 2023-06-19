package myapp.service

import myapp.model.User
import myapp.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(val db: UserRepository) {
    fun createUser(user: User) = db.save(user)
    fun checkUsername(username: String): Boolean = db.existsByUsername(username)
    fun getUsers(): List<User> = db.findAll()

    fun getUser(userId: Long): User? = db.findByIdOrNull(userId)
}