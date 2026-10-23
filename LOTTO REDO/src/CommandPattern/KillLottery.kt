package CommandPattern
import Databases.LotteriesDatabase
import Databases.PlayerDatabase
import Databases.TicketDatabase
import UserInterface.InputReader
import VisitorPatterns.AddMoneyPlayerVisitor
import VisitorPatterns.CheckWinnersLotteryVisitor
import VisitorPatterns.CountPrizesLotteryVisitor
import VisitorPatterns.FinnishLotteryVisitor
import VisitorPatterns.GenerateNumbersVisitor
import kotlin.math.sign

class KillLottery(
    val input: InputReader,
    val lotteriesDatabase: LotteriesDatabase,
    val ticketDatabase: TicketDatabase,
    val playerDatabase: PlayerDatabase
) : Command {
    override fun execute() {
        println()
        println("===========================================================")
        println("==================== KILLING A LOTTERY ====================")
        println("===========================================================")
        println()

        println("Please pick a lottery that you want to end")
        println()
        val lottery = input.readLottery("Lottery of choice = ", lotteriesDatabase)
        println()

        println("===========================================================")
        println()
        println("Are you sure? What if it has children?")
        val choice = input.readYesAndNo("Are you sure? Y/N = ")
        if (choice == "Y") {
            lottery.accept(GenerateNumbersVisitor())
            lottery.accept(CheckWinnersLotteryVisitor(ticketDatabase))
            lottery.accept(CountPrizesLotteryVisitor(lottery.winnersAndGuesses))

            println()
            println("===========================================================")
            println("   Winning numbers: ${lottery.winningNumbers}")
            println("===========================================================")
            println()
            println("                           Winners:                        ")
            println()
            for (winner in lottery.winnersAndGuesses) {
                val winnerID = winner.first
                var prize = 0
                val winnerGuessed = winner.second
                for (person in lottery.winnersAndPrizes) {
                    if (winnerID == person.first) {
                        prize = person.second
                    }
                }
                var winnerPlace = 1
                winnerPlace++
                println("  $winnerPlace. - ID: $winnerID - Numbers guessed: $winnerGuessed - Money prize: $prize")
                println()
            }

            for (winTicket in lottery.winningTickets) {
                for (player in playerDatabase.allPlayers) {

                    var prize = 0

                    for (winner in lottery.winnersAndPrizes) {
                        if (player.playerNumber == winner.first) {
                            prize = winner.second
                        }
                    }

                    for (playerTicket in player.tickets) {
                        if (winTicket.serialNumber == playerTicket.serialNumber) {
                            player.accept(AddMoneyPlayerVisitor(prize))
                            player.tickets.remove(playerTicket)
                        }
                    }
                }
            }

            lottery.accept(FinnishLotteryVisitor(lotteriesDatabase))

            println()
            println("===========================================================")
            println()
            println("      Lottery ${lottery.gameID} has come to an end.")
            println()
            println("===========================================================")
        } else {
            println()
            println("===========================================================")
            println()
            println("                 Make up your mind!")
            println("                         Bye")
            println()
            println("===========================================================")
            println()
        }
    }
}