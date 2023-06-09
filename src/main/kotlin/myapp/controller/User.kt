package myapp.controller

import jakarta.validation.Valid
import myapp.service.UserService
import myapp.model.User

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class User(val service: UserService) {
    @GetMapping
    fun getUsers() = service.getUsers()

    @PostMapping
    fun createUser(@RequestBody user: User) = service.createUser(user)
}