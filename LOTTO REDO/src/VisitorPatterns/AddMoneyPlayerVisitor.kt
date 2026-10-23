package VisitorPatterns

import MainObjects.Player

class AddMoneyPlayerVisitor (
    val addedMoney: Int
) : PlayerVisitor {

    init {
        require(addedMoney > 0) {"If you add money, it needs to be a positive sum"}
    }

    override fun visit(player: Player) {
        player.money += addedMoney
    }
}