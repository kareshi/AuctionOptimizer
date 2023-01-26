import com.ebay.api.client.auth.oauth2.CredentialUtil
import com.ebay.api.client.auth.oauth2.OAuth2Api
import com.ebay.api.client.auth.oauth2.model.Environment
import optimizer.controller.SearchBot
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity

fun main(args: Array<String>) {
    query(authenticate())
}

private fun query(token: String) {
    val builder = RestTemplateBuilder()
    val restTemplate = builder.build()
    val headers = HttpHeaders()
    headers.setBearerAuth(token)
    val entity = HttpEntity<String>(headers)
    val responseEntity: ResponseEntity<String> = restTemplate.exchange(
        "https://api.sandbox.ebay.com/buy/browse/v1/item_summary/search?q=a&limit=3",
        HttpMethod.GET,
        entity,
        String::class.java
    )
    println(responseEntity)
}

private fun authenticate(): String {
    val inputStream = SearchBot::class.java.getClassLoader()
        .getResourceAsStream("ebay-config-sample.yaml")
    CredentialUtil.load(inputStream)
    val api = OAuth2Api()
    val oAuthResponse = api.getApplicationToken(Environment.SANDBOX, listOf("https://api.ebay.com/oauth/api_scope"))

    val token = oAuthResponse.accessToken.get().token
    println(token)
    return token
}