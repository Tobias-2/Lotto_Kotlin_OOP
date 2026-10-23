package VisitorPatterns

import MainObjects.Lottery

import kotlin.random.Random

class RandomNumbersLotteryVisitor (
    val lottery: Lottery
) : LotteryVisitor {
    override fun visit(lottery: Lottery) {

        val range = lottery.range
        val count = lottery.amountOfNumbers

        val randomSet = mutableSetOf<Int>()

        while (randomSet.size < count) {
            randomSet.add(Random.nextInt(0, range))
        }


    }
}