package myapp.repository

import myapp.model.Movie
import org.springframework.data.jpa.repository.JpaRepository;

interface MovieRepository: JpaRepository<Movie, Long>