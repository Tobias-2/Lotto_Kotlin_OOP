package VisitorPatterns

import MainObjects.Player

interface PlayerVisitor {
    fun visit(player: Player)
}