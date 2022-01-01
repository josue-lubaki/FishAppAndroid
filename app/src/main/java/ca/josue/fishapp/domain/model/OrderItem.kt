package ca.josue.fishapp.domain.model

data class OrderItem(
    val __v: Int,
    val _id: String,
    val id: String,
    val product: Product,
    val quantity: Int
)