package Databases
import MainObjects.Lottery

class LotteriesDatabase {

    val allLotteries = mutableSetOf<Lottery>()

    val endedLotteries = mutableSetOf<Lottery>()

    fun addNewLottery(newLottery: Lottery) {
        allLotteries.add(newLottery)
    }
}