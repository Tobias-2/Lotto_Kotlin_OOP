package Databases
import MainObjects.Player

class PlayerDatabase {
    val allPlayers: MutableSet<Player> = mutableSetOf()

    fun addTicket(newPlayer: Player) {
        allPlayers.add(newPlayer)
    }
}