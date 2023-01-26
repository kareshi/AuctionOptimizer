package optimizer.dao.impl

import optimizer.dao.ListingDAO
import optimizer.model.Listing

class ListDAODummyImpl : ListingDAO {
    override fun search(keyWords: List<String>) : List<Listing> {
        val result: ArrayList<Listing> = ArrayList()
        val listing1 = Listing("1", "The Wandering Emperor FOIL alternate art", 200.0, "USD")
        val listing2 = Listing("2", "The Wandering Emperor FOIL ", 50.0, "USD")
        val listing3 = Listing("3", "The Wandering Emperor", 20.0, "USD")
        result.add(listing1)
        result.add(listing2)
        result.add(listing3)
        return result
    }
}