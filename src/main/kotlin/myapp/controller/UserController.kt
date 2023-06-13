package myapp.controller

import myapp.service.UserService
import myapp.model.User
import myapp.service.RoleService

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
    fun createUser(@RequestBody user: User): User  {
        user.role = user.role?.id?.let {roleService.getRole(it)} ?: throw IllegalArgumentException("Role doesn't exist.")
        return service.createUser(user)
    }
}