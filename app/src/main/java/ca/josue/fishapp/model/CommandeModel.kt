package ca.josue.fishapp.model

class CommandeModel(
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
        val orderItems: List<String>,
        val phone: String,
        val quartier: String,
        val status: String,
        val totalPrice: Int,
        val user: UserModel
)