package ca.josue.fishapp.domain.api

data class ProductAPI (
    val __v: Int,
    val _id: String,
    val category: CategoryAPI,
    val countInStock: Int,
    val dateCreated: String,
    val description: String,
    val id: String,
    val image: String,
    val images: List<String>,
    val isFeatured: Boolean,
    val name: String,
    val price: Int,
    val richDescription: String
)