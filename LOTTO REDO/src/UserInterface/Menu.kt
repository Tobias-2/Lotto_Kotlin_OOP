package UserInterface

import CommandPattern.AddLotteryCommand
import CommandPattern.AddPlayerCommand
import CommandPattern.AddRandomTicketsCommands
import CommandPattern.AddTicketCommand
import CommandPattern.Command
import CommandPattern.KillLottery
import Databases.LotteriesDatabase
import Databases.PlayerDatabase
import Databases.TicketDatabase
import MainObjects.Lottery
import MainObjects.Player

class Menu {

    val playerDatabase = PlayerDatabase()
    val ticketDatabase = TicketDatabase()
    val lotteriesDatabase = LotteriesDatabase()
    val input =  InputReader()
    val random = GetRandom()

    var running = true

    fun startView() {
        println("=========================================")
        println("=============== HELLO ! =================")
        println("======= LOTTO PROGRAM HAS STARTED =======")
        println("=========================================")
        println()
    }

    fun printActions() {

        println()
        println("   Pick an action:")
        println("       1. Add Lottery")
        println("       2. Add Player")
        println("       3. Add Ticket")
        println("       4. Finish Lottery")
        println("       5. Generate Random Tickets")
        println("       6. Exit")
        println()

    }

    fun choosing() {
        val choice = input.readMenuChoice("Enter your action number = ")
        println()


        when (choice) {
            1 -> AddLotteryCommand(lotteriesDatabase, input).execute()
            2 -> AddPlayerCommand(playerDatabase, input).execute()
            3 -> AddTicketCommand(ticketDatabase, input, playerDatabase, lotteriesDatabase).execute()
            4 -> KillLottery(input, lotteriesDatabase, ticketDatabase, playerDatabase).execute()
            5 -> AddRandomTicketsCommands(random, playerDatabase, ticketDatabase, lotteriesDatabase, input).execute()
            6 -> exit()
        }
    }

    fun exit() {
        running = false
        println()
        println("==========================================")
        println("========== LOTTO HAS FINISHED ============")
        println("==========================================")
    }
}