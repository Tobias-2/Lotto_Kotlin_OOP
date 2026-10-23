package VisitorPatterns

import Databases.LotteriesDatabase
import Databases.TicketDatabase
import MainObjects.Lottery
import MainObjects.Ticket

class CheckWinnersLotteryVisitor(
    val ticketDatabase: TicketDatabase
) : LotteryVisitor {
    override fun visit(lottery: Lottery) {
        val winningNumbers = lottery.winningNumbers
        val ticketsInThisLottery = mutableSetOf<Ticket>()
        val winners = mutableSetOf<Pair<Int, Int>>()
        var minimumGuessCount: Int = 4

        // Minimum amount of correct guesses
        if ( lottery.amountOfNumbers < 4) {
            if (lottery.amountOfNumbers == 3) {
                minimumGuessCount = 3
            } else if (lottery.amountOfNumbers == 2) {
                minimumGuessCount = 2
            } else {
                minimumGuessCount = 1
            }
        }

        // Amount of tickets in this lottery
        for (ticket in ticketDatabase.allTickets) {
            if (ticket.lottery == lottery) {
                ticketsInThisLottery.add(ticket)
            }
        }

        // Guess counting
        for (ticket in ticketsInThisLottery) {
            var guessCount : Int = 0
            val playerID: Int = ticket.owner.playerNumber
            for (number in ticket.numbers) {
                if (number in winningNumbers) {
                    guessCount++
                }
            }
            if (guessCount >= minimumGuessCount) {
                val winnerIdAndGuessCount: Pair<Int, Int> = playerID to guessCount
                winners.add(winnerIdAndGuessCount)
                lottery.winningTickets.add(ticket)
            }
        }

        lottery.winnersAndGuesses = winners
    }
}