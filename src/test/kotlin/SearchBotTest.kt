import optimizer.controller.SearchBot
import optimizer.dao.ListingDAO
import optimizer.dao.impl.ListDAODummyImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SearchBotTest {

    private val listingDAO : ListingDAO = ListDAODummyImpl()
    private val subject : SearchBot = SearchBot(listingDAO)

    @Test
    fun testSearchByMaxPrice() {
        val result = subject.searchByMaxPrice(listOf(), 1000.0)
        Assertions.assertEquals(3, result.size)
    }

}