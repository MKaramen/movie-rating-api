package myapp.populate

import jakarta.annotation.PostConstruct
import myapp.model.Movie
import myapp.model.Role
import myapp.model.User
import myapp.service.MovieService
import myapp.service.RoleService
import myapp.service.UserService
import org.springframework.stereotype.Component

@Component
class DataLoader(val userService: UserService, val movieService: MovieService, val roleService: RoleService) {

    @PostConstruct
    fun loadData() {
        try {
            val adminRole = roleService.save(Role(name = "admin"))
            val userRole = roleService.save(Role(name = "user"))
            userService.createUser(User(username = "toto", password = "toto", role = adminRole))
            userService.createUser(User(username = "tata", password = "tata", role = userRole))
            movieService.save(Movie(name = "My Favorite Movie"))
        } catch (e: Exception) {
            println(e)
        }
    }
}