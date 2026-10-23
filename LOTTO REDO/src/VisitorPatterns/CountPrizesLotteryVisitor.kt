package VisitorPatterns

import MainObjects.Lottery
import kotlin.collections.mutableSetOf

class CountPrizesLotteryVisitor (
    val winners : Set<Pair<Int, Int>>
) : LotteryVisitor {
    override fun visit(lottery: Lottery) {

        val moneyToWin = lottery.moneyHeld * 0.9 // minus 10% for company operations

        //scaling
        val scaledWinners = mutableSetOf<Pair<Int,Int>>()

        for (winner in winners) {
            val scaled = winner.second * winner.second
            val scaledWinner = winner.first to scaled
            scaledWinners.add(scaledWinner)
        }

        //credits
        var allCredits = 0

        for (winner in scaledWinners) {
            allCredits += winner.second
        }

        val moneyForOneCredit = moneyToWin / allCredits

        val winnersAndMoney = mutableSetOf<Pair<Int,Int>>()

        for (winner in scaledWinners) {
            val prize = (winner.second * moneyForOneCredit).toInt()
            val winnerWithMoney = winner.first to prize
            winnersAndMoney.add(winnerWithMoney)
        }

        //WTF did I do here
        winnersAndMoney.sortedWith(compareBy { it.second }).reversed()

        lottery.winnersAndPrizes = winnersAndMoney
    }
}