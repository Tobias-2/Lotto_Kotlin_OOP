package CommandPattern

import Databases.LotteriesDatabase
import Databases.PlayerDatabase
import Databases.TicketDatabase
import MainObjects.Lottery
import MainObjects.Player
import MainObjects.Ticket
import UserInterface.InputReader
import VisitorPatterns.AddMoneyPlayerVisitor
import VisitorPatterns.BuyTicketPlayerVisitor
import VisitorPatterns.SerialNumberTicketVisitor

class AddTicketCommand (
    val ticketDatabase: TicketDatabase,
    val input: InputReader,
    val playerDatabase: PlayerDatabase,
    val lotteriesDatabase: LotteriesDatabase,
) : Command {
    override fun execute() {

        println()
        println("================================================")
        println("================ TICKET CREATOR ================")
        println("================================================")

        println()
        val owner: Player = input.readPlayer("Pick an owner of this ticket = ", playerDatabase)
        println()
        println("================================================")
        println()
        println("You have ${owner.money} money")
        println()
        println("================================================")
        println()
        println("Current lotteries cost:")
        var gameCount = 1
        for (game in lotteriesDatabase.allLotteries) {
            println("   $gameCount. Name: ${game.gameID} -> entrance fee: ${game.entranceFee}$")
            gameCount ++
        }
        println()

        println("================================================")
        println()
        val lottery: Lottery = input.readLottery("Pick one of available lotteries = ", lotteriesDatabase)

        if (lottery.entranceFee > owner.money) {
            println("You don't have money for this lottery!")
            println("Your money = ${owner.money} | Entrance fee = ${lottery.entranceFee}")
            println("You need to add at least ${lottery.entranceFee - owner.money}")
            while (owner.money <  lottery.entranceFee) {
                println()
                val newMoney = input.readPositiveInt("How much money do you add = ")
                owner.accept(AddMoneyPlayerVisitor(newMoney))
                println("Now You have ${owner.money} money on your account")
            }
        }

        owner.accept(BuyTicketPlayerVisitor(lottery))
        println()
        println("Amount of money left on your account = ${owner.money}")

        println()
        println("================================================")
        println()
        val amount = lottery.amountOfNumbers
        val range = lottery.range
        println("Pick your unique lottery numbers! \nAmount: $amount | Range: 0 - $range ")
        val yourNumbersPrompt = "Your number = "
        val numbers: Set<Int> = input.readPlayerChoiceNumbers(yourNumbersPrompt, lottery)

        val ticket = Ticket (numbers, lottery, owner)



        //activating serial for ticket
        val creatingSerial = SerialNumberTicketVisitor(ticketDatabase)
        ticket.accept(creatingSerial)

        ticketDatabase.allTickets.add(ticket)

        owner.tickets.add(ticket)

        lottery.moneyHeld += lottery.entranceFee

        println()
        println("================================================")
        println("========== Ticket successfully added! ==========")
        println("================================================")
        println("                      |                         ")
        println("                      |                         ")
        println("                      V                         ")
        println("================== Your ticket =================")
        println("-> OWNER : ${owner.personalData.name} ${owner.personalData.surname} ")
        println("-> PLAYER NUMBER: ${owner.playerNumber}")
        println("-> LOTTERY NAME : ${lottery.gameID}")
        println("-> NUMBERS OF CHOICE : $numbers")
        println("-> TICKET SERIAL NUMBER: ${ticket.serialNumber}")
        println("================================================")

        println()

        var ticketsInThisGameCount = 0
        for (ticket in ticketDatabase.allTickets) {
            if (ticket.lottery.gameID == lottery.gameID) {
                ticketsInThisGameCount++
            }
        }


        println("Amount of tickets in this game: $ticketsInThisGameCount")
        println("Amount of money in this game: ${lottery.moneyHeld}")
    }
}