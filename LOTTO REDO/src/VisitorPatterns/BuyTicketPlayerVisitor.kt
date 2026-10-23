package VisitorPatterns

import MainObjects.Player
import MainObjects.Lottery

class BuyTicketPlayerVisitor (
    val lottery: Lottery
) : PlayerVisitor {
    override fun visit(player: Player) {
        player.money -= lottery.entranceFee
    }
}