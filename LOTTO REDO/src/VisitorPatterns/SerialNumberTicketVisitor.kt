package VisitorPatterns

import Databases.TicketDatabase
import MainObjects.Ticket
import kotlin.random.Random

class SerialNumberTicketVisitor (
    val ticketDatabase: TicketDatabase
) : TicketVisitor {
    override fun visit (ticket: Ticket) {

        val bottomRange = 10000
        val    topRange = 99999
        val   oneSerial = 1

        val takenSerials = mutableSetOf<Int>()

        for (element in ticketDatabase.allTickets) {
            takenSerials.add(element.serialNumber)
        }

        while (true) {
            val thisSerial = Random.nextInt(bottomRange, topRange)

            if (thisSerial !in takenSerials) {
                ticket.serialNumber = thisSerial
                break
            }
        }

    }
}