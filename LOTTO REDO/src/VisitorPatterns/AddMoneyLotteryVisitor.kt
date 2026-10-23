package VisitorPatterns

import MainObjects.Lottery

class AddMoneyLotteryVisitor (
    val choiceSum: Int
) : LotteryVisitor {
    override fun visit(lottery: Lottery) {
        lottery.moneyHeld += choiceSum
    }
}