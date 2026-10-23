package MainObjects

import VisitorPatterns.PlayerVisitor

data class Player (
    val playerNumber: Int,
    val personalData: PersonalData,
    var money: Int
) {
    val tickets = mutableSetOf<Ticket>()

    init {
        require(money > 99) {"Tickets cost, give them at least 100"}
    }

    fun accept(visitor: PlayerVisitor) {
        visitor.visit(this)
    }
}