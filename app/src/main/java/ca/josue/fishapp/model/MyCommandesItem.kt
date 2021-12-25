package ca.josue.fishapp.model

data class MyCommandesItem(
    val __v: Int,
    val _id: String,
    val apartment: String,
    val avenue: String,
    val city: String,
    val commune: String,
    val country: String,
    val dateOrdered: String,
    val id: String,
    val notes: String,
    val orderItems: List<OrderItem>,
    val phone: String,
    val quartier: String,
    val status: String,
    val totalPrice: Int,
    val user: String
)