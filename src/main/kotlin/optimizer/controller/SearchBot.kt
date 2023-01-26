package optimizer.controller

import optimizer.dao.ListingDAO
import optimizer.model.Listing

class SearchBot(listingDAO : ListingDAO) {

    val listingDAO : ListingDAO
    init {
        this.listingDAO = listingDAO
    }

    fun searchByMaxPrice (keyWords : List<String>, maxPrice : Double) : List<Listing> {
        val allListings : List<Listing> = this.listingDAO.search(keyWords)
        return allListings.filter { l -> l.notional< maxPrice }
    }

}