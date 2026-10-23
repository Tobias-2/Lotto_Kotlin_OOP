package CommandPattern

import UserInterface.InputReader
import MainObjects.PersonalData
import Databases.PlayerDatabase
import MainObjects.Player

class AddPlayerCommand(
    val playerDatabase: PlayerDatabase,
    val input: InputReader
) : Command {
    override fun execute() {

        println("==========================================")
        println("============= PLAYER CREATOR =============")
        println("==========================================")

        val playerNumberPrompt: String = "Please pick a number from 0 to 999 = "
        val alreadyTakenNumbers: MutableSet<Player> = playerDatabase.allPlayers
        val playerNumber: Int = input.readPlayerNumber(playerNumberPrompt, alreadyTakenNumbers)

        val moneyPrompt = "Player's initial money, minimum 100 = "
        val money: Int = input.readMoney(moneyPrompt)

        val name = input.readNameOrSurname("Name = ")
        val surname = input.readNameOrSurname("Surname = ")
        val age = input.readAge("Age = ")

        val personalData = PersonalData(name, surname, age)

        val player = Player(playerNumber, personalData, money)

        playerDatabase.allPlayers.add(player)

        println("==========================================")
        println("======= Player successfully added! =======")
        println("==========================================")
    }
}