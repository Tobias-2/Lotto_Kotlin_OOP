package VisitorPatterns

import MainObjects.Ticket

interface TicketVisitor {
    fun visit(ticket: Ticket) : Unit
}