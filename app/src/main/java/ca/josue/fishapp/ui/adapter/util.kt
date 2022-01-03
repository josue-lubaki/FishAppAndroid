package ca.josue.fishapp.ui.adapter

class util {
    companion object{
        fun whichState(status : String) : String{
            return when (status) {
                StateOrder.ATTENTE -> {
                    "En attente"
                }
                StateOrder.TRAITEMENT -> {
                    "En Traitement"
                }
                StateOrder.EXPEDIE -> {
                    "Expédiée"
                }
                StateOrder.LIVRE -> {
                    "Livrée"
                }
                StateOrder.ECHOUE -> {
                    "Échouée"
                }
                StateOrder.ACCEPTE -> {
                    "Acceptée"
                }
                "null", "" -> {
                    ""
                }
                else -> "Error"
            }
        }
    }
}
