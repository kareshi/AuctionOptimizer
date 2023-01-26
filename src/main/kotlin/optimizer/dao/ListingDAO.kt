package optimizer.dao

import optimizer.model.Listing

interface ListingDAO {

    fun search(keyWords : List<String>) : List<Listing>

}