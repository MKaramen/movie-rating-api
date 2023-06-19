package myapp.controller

import myapp.exception.UserAlreadyExists
import myapp.service.UserService
import myapp.model.User
import myapp.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(val service: UserService, val roleService: RoleService) {
    @GetMapping
    fun getUsers() = service.getUsers()

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User>  {
        user.role = user.role?.id?.let {roleService.getRole(it)} ?: throw IllegalArgumentException("User role doesn't exist.")
        return if (service.checkUsername(user.username))
        {
            throw UserAlreadyExists("Username ${user.username} already exists.")
        } else {
            ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user))
        }
    }
}