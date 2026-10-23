package CommandPattern

import Databases.LotteriesDatabase
import Databases.PlayerDatabase
import MainObjects.Lottery
import UserInterface.InputReader
import VisitorPatterns.AddMoneyLotteryVisitor

class AddLotteryCommand (
    val lotteryDatabase: LotteriesDatabase,
    val input: InputReader
) : Command {
    override fun execute() {

        println()
        println("=================================================")
        println("================ LOTTERY CREATOR ================")
        println("=================================================")


        println()
        println("Pick an ID number for this game between 0 and 999")
        val existingLotteries = lotteryDatabase.allLotteries
        val gameID: Int = input.readGameID("This game's ID = ", existingLotteries)
        println()
        println("Pick a number for range between 50 and 99")
        val range: Int = input.readRangeNumber("This lottery's range limit = ")
        println()
        println("Pick a number for amount of numbers from 1 to 10 ")
        val amountOfNumbers: Int = input.readAmountOfNumbers("Amount of numbers in this lottery = ")
        println()
        println("Pick a price of an entrance fee between 10 to 100")
        val lotteryFee: Int = input.readEntranceFee("Price of one ticket = ")
        println()

        val lottery = Lottery(gameID, range, amountOfNumbers,lotteryFee)

        println("Would You like to add some start money for this lottery?")
        val choice = input.readYesAndNo("Your answer = ")
        if (choice == "Y") {
            println("How much would You like to add?")
            val addSum = input.readPositiveInt("Sum You add to this lottery = ")
            lottery.accept(AddMoneyLotteryVisitor(addSum))
        } else {
            println("Ok, if NO then NO")
        }
        println()

        lotteryDatabase.allLotteries.add(lottery)

        println("=================================================")
        println("========== Lottery successfully added! ==========")
        println("=================================================")
    }
}