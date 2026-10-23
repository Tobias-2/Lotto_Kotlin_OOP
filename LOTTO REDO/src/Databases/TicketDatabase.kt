package Databases
import MainObjects.Ticket

class TicketDatabase () {
    val allTickets: MutableSet<Ticket> = mutableSetOf()

    fun addTicket(newTicket: Ticket) {
        allTickets.add(newTicket)
    }
}