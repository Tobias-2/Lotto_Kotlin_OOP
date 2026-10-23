package CommandPattern

import Databases.LotteriesDatabase
import Databases.PlayerDatabase
import Databases.TicketDatabase
import MainObjects.Lottery
import MainObjects.PersonalData
import MainObjects.Player
import MainObjects.Ticket
import UserInterface.GetRandom
import UserInterface.InputReader
import VisitorPatterns.SerialNumberTicketVisitor

class AddRandomTicketsCommands (
    val random: GetRandom,
    val playerDatabase: PlayerDatabase,
    val ticketDatabase: TicketDatabase,
    val lotteriesDatabase: LotteriesDatabase,
    val input: InputReader
) : Command {
    override fun execute() {

        println()
        println("====================================================================")
        println("====================== ADDING RANDOM TICKETS =======================")
        println("====================================================================")
        println()

        println("Please pick a lottery to which you want to add random tickets")
        println()
        val lottery = input.readLottery("Lottery of choice = ", lotteriesDatabase)
        println()

        println("How many random tickets do you wany to add to this game?")

        var count = input.readAddFakeTickets("Amount of random tickets = ", ticketDatabase)

        while (count > 0 && playerDatabase.allPlayers.size < 10000) {

            val fakeName = random.getRandomName()
            val fakeSurname = random.GetRandomSurname()
            val fakeAge = random.getRandomAge()

            val fakePersonalData = PersonalData(fakeName, fakeSurname, fakeAge)

            var fakeIDNumber = 0

            for (player in playerDatabase.allPlayers) {
                if (player.personalData.name == fakeName && player.personalData.surname == fakeSurname) {
                    fakeIDNumber = player.playerNumber
                } else {
                    fakeIDNumber = random.getRandomID(playerDatabase)
                }
            }

            val fakeMoney = random.getRandomMoney(lottery)

            var fakePlayer = Player(fakeIDNumber, fakePersonalData, fakeMoney)

            for (player in playerDatabase.allPlayers) {
                if (player.playerNumber == fakeIDNumber) {
                    fakePlayer = player
                }
            }

            if (fakePlayer !in playerDatabase.allPlayers) {
                playerDatabase.allPlayers.add(fakePlayer)
            }

            val randomLotterySet = random.getRandomNumbers(lottery)

            val fakeTicket = Ticket(randomLotterySet, lottery, fakePlayer)

            fakeTicket.fake = true

            ticketDatabase.allTickets.add(fakeTicket)
            fakeTicket.accept(SerialNumberTicketVisitor(ticketDatabase))

            count--
        }

        println()
        println("====================================================================")
        println("====================== THE NEW RANDOM TICKETS ======================")
        println("====================================================================")
        println()

        val setOfFakes = mutableSetOf<Ticket>()

        for (ticket in ticketDatabase.allTickets) {
            if (ticket.fake) {
                setOfFakes.add(ticket)
            }
        }

        var number = 1

        for (ticket in setOfFakes) {

            val name = ticket.owner.personalData.name
            val surname = ticket.owner.personalData.surname
            val serial = ticket.serialNumber
            val numbers = ticket.numbers
            println("   $number. NAME: ${name}  SURNAME: ${surname}     TICKET_SERIAL_NR ${serial}")
            println("               LOTTERY NUMBERS: ${numbers}")
            println()
            number++

        }


        println("====================================================================")
        println("=============================== END ================================")
        println("====================================================================")
        println()
    }
}