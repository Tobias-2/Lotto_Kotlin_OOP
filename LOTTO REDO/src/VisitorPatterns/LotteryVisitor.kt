package VisitorPatterns

import MainObjects.Lottery

interface LotteryVisitor {
    fun visit(lottery: Lottery) : Unit
}