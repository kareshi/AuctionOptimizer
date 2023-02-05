package demo

import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.codec.binary.Hex
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@SpringBootApplication
open class DemoApplication

data class Message(val id: String?, val text: String)

data class EbayAccountDeletionClosureResponse(val challengeResponse: String)

@RestController
class MessageResource {
    @GetMapping("/")
    fun index(): List<Message> = listOf(
        Message("1", "Hello!"),
        Message("2", "Bonjour!"),
        Message("3", "Privet!"),
        Message("4", "Guten Tag!"),
    )

    @GetMapping("/ebay")
    fun challengeCode(request : HttpServletRequest): ResponseEntity<EbayAccountDeletionClosureResponse> {
        val challengeCode : String? = request.getParameter("challenge_code")
        println("challengeCode=$challengeCode")

        val verificationToken : String? = request.getHeader("Authorization")
        println("verificationToken=$verificationToken")

        val endpoint = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/ebay"
        println("endpoint=$endpoint")

        val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
        digest.update((challengeCode?:"").toByteArray(StandardCharsets.UTF_8))
        digest.update((verificationToken?:"").toByteArray(StandardCharsets.UTF_8))
        val bytes: ByteArray = digest.digest(endpoint.toByteArray(StandardCharsets.UTF_8))

        val challengeResponse = Hex.encodeHexString(bytes)
        println("challengeResponse=$challengeResponse")

        val ebayAccountDeletionClosureResponse = EbayAccountDeletionClosureResponse(challengeResponse)
        val result : ResponseEntity<EbayAccountDeletionClosureResponse> = ResponseEntity(ebayAccountDeletionClosureResponse, HttpStatus.OK)
        return result
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}