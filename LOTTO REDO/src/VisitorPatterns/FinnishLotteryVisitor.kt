package VisitorPatterns

import Databases.LotteriesDatabase
import MainObjects.Lottery

class FinnishLotteryVisitor(
    val lotteriesDatabase: LotteriesDatabase
) : LotteryVisitor {
    override fun visit(lottery: Lottery)  {
        lottery.lotteryState = false
        println("Lottery ${lottery.gameID} has ended")
        lotteriesDatabase.endedLotteries.add(lottery)
        lotteriesDatabase.allLotteries.remove(lottery)
    }
}