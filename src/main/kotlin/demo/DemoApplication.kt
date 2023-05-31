package demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.Optional

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, String>

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

@RestController
class MessageController(val service: MessageService) {
	@GetMapping("/messages")
	fun index(): List<Message> = service.findMessage()

	@GetMapping("/messages/{messageId}")
	fun getMessage(@PathVariable messageId: String): List<Message> {
		println(messageId)
		return service.getMessage(messageId)
	}

	@PostMapping("/messages")
	fun post(@RequestBody message: Message): String{
		service.save(message)
		return "Message created."
	}
}

@RestController
class CheckHealth
	@GetMapping("/")
	fun check(): String = "App is running"

@Table("MESSAGES")
data class Message(@Id var id: String?, val text: String)


@Service
class MessageService(val db: MessageRepository) {
	fun findMessage(): List<Message> = db.findAll().toList()

	fun getMessage(messageId: String): List<Message> = db.findById(messageId).toList()

	fun save(message: Message){
		db.save(message)
	}

	fun <T : Any> Optional<out T>.toList(): List<T> =
		if (isPresent) listOf(get()) else emptyList()
}