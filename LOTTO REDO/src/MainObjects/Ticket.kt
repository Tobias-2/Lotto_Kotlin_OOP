package MainObjects
import VisitorPatterns.TicketVisitor

class Ticket (
    val numbers: Set<Int>,
    val lottery: Lottery,
    val owner: Player,
) {
    init {
        for (number in numbers) {
            require(number >= 0)  {"Put a non negative number, please."}
        }
    }

    var serialNumber: Int = 0

    var fake = false


    fun accept(visitor: TicketVisitor) {
        visitor.visit(this)
    }
}