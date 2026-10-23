package UserInterface

import VisitorPatterns.GenerateNumbersVisitor
import MainObjects.Lottery
import CommandPattern.AddPlayerCommand
import Databases.LotteriesDatabase
import Databases.PlayerDatabase
import UserInterface.InputReader
import CommandPattern.AddLotteryCommand
import CommandPattern.AddRandomTicketsCommands
import CommandPattern.AddTicketCommand
import CommandPattern.Command
import CommandPattern.KillLottery
import Databases.TicketDatabase
import MainObjects.PersonalData
import MainObjects.Player
import UserInterface.Application
import UserInterface.Menu
import kotlin.random.Random

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    Application().lottoGame()

    /*
    //val lottery0 = Lottery(200, 6)
    //lottery0.accept(GenerateNumbersVisitor())
    //val winNumbers = lottery0.winningNumbers
    //println(winNumbers)

    //val firstGamePlayers = PlayerDatabase()
    //val input = InputReader()
    //AddPlayerCommand(firstGamePlayers, input).execute()

    //val lotteryDatabase = LotteriesDatabase()
    //val input = InputReader()
    //AddLotteryCommand(lotteryDatabase, input).execute()


    val lotteryTest = Lottery(444, 70,7, 70)
    val personalDataTest = PersonalData("Lin", "Change", 33)
    val playerTest = Player(55, personalDataTest, 100)

    val ticketDatabase = TicketDatabase()
    val lotteryDatabase = LotteriesDatabase()
    val firstGamePlayers = PlayerDatabase()
    val input = InputReader()
    val random = GetRandom()
    val playerDatabase = PlayerDatabase()

    lotteryDatabase.allLotteries.add(lotteryTest)
    firstGamePlayers.allPlayers.add(playerTest)

    AddTicketCommand(ticketDatabase, input, firstGamePlayers, lotteryDatabase, playerTest).execute()
    AddTicketCommand(ticketDatabase, input, firstGamePlayers, lotteryDatabase, playerTest).execute()

    KillLottery(input, lotteryDatabase, ticketDatabase, firstGamePlayers).execute()
    println("Winning tickets: ${lotteryTest.winningTickets}")


    AddRandomTicketsCommands(lotteryTest, random, playerDatabase, ticketDatabase, input).execute()

     */
}