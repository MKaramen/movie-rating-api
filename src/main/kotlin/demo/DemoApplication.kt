package demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

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


data class Message(val id: String?, val text: String)


@Service
class MessageService(val db: JdbcTemplate) {
	fun findMessage(): List<Message> = db.query("SELECT * FROM messages") { response, _ -> Message(id=response.getString("id"), text=response.getString("text"))}

	fun getMessage(messageId: String): List<Message> = db.query("SELECT * FROM messages WHERE id='$messageId'") { response, _ -> Message(id=response.getString("id"), text=response.getString("text"))}

	fun save(message: Message){
		val id = message.id ?: UUID.randomUUID().toString()
		db.update("insert into messages values ( ?, ? )",
			id, message.text)
	}
}