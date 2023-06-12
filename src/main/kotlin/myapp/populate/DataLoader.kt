package myapp.populate

import jakarta.annotation.PostConstruct
import myapp.model.Movie
import myapp.model.User
import myapp.service.MovieService
import myapp.service.UserService
import org.springframework.stereotype.Component

@Component
class DataLoader(val userService: UserService, val movieService: MovieService) {

    @PostConstruct
    fun loadData() {
        userService.createUser(User(username = "toto", password = "toto"))
        movieService.save(Movie(name = "My Favorite Movie"))
    }
}