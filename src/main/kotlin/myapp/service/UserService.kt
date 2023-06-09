package myapp.service

import myapp.model.User
import myapp.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val db: UserRepository) {
    fun createUser(user: User) = db.save(user)
    fun checkUsername(username: String) = db.findByUsername(username)
    fun getUsers(): List<User> = db.findAll()
}