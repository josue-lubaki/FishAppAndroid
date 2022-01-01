package ca.josue.fishapp.domain.dto

class FishModelResponse(
        val name : String  = "poisson",
        val category : String = "poisson rouge",
        val price : Int = 10,
        val imageUrl : String = "image",
        val description : String = "Aucune description"
)