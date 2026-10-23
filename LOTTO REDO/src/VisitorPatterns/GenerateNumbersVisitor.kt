package VisitorPatterns

import Databases.LotteriesDatabase
import MainObjects.Lottery
import kotlin.random.Random

class GenerateNumbersVisitor (
) : LotteryVisitor {

    override fun visit(lottery: Lottery) {


        val count = lottery.amountOfNumbers
        val range = lottery.range

        val numbers = mutableSetOf<Int>()

        while (numbers.size < (count + 1)) {
            numbers.add(Random.nextInt(0, range))
        }
        lottery.winningNumbers = numbers

    }
}