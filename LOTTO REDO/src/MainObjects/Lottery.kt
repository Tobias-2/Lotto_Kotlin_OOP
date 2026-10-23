package MainObjects
import VisitorPatterns.LotteryVisitor
class Lottery (
    val gameID: Int,
    val range: Int,
    val amountOfNumbers: Int,
    val entranceFee : Int
) {

    var winningNumbers = mutableSetOf<Int>()

    var moneyHeld = 0

    var lotteryState = true // true - open | false - ended

    var winnersAndGuesses = mutableSetOf<Pair<Int,Int>>()
    var winnersAndPrizes = mutableSetOf<Pair<Int,Int>>()
    var winningTickets = mutableSetOf<Ticket>()

    init {
        require(gameID in 0 until 1000) {"Game ID must be in range from 0 to 999"}
        require(range in 50 until 100) {"Range must be between 50 and 99"}
        require(amountOfNumbers > 0) {"Give them Something to pick from"}
        require(range > amountOfNumbers) {"Doesn't make sense"}
    }

    fun accept(visitor: LotteryVisitor) : Unit {
        visitor.visit(this)
    }
}