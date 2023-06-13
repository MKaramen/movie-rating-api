package myapp.config



import myapp.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(private val userRepository: UserRepository, private val userDetailsService: CustomUserDetailService) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance() // Not recommended for production use, use a secure encoder
    }

    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeRequests {
                authorize(method = HttpMethod.POST, pattern = "/movies", authenticated)
                authorize(method = HttpMethod.POST, pattern = "/users", authenticated)
                authorize("/", permitAll)
                authorize(anyRequest, authenticated)
            }
            csrf { disable() }
            httpBasic {}
        }
        return http.build()
    }

    fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
    }
}