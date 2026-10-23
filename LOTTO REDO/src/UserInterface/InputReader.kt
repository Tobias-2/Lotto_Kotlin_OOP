package UserInterface
import Databases.LotteriesDatabase
import MainObjects.Player
import Databases.PlayerDatabase
import MainObjects.Lottery
import MainObjects.Ticket
import Databases.TicketDatabase

class InputReader {

    fun readInt(prompt: String) : Int {
        while (true) {
            print(prompt)

            val value = readlnOrNull()
                ?.trim()
                ?.toIntOrNull()

            if (value != null) {
                return value
            }

            println("Please write a valid integer")
        }
    }

    fun readGameInput(prompt: String, range: Int) : Int {

        while (true) {

            val value = readInt(prompt)

            if (value in 0..range) {
                return value
            }

            println("Please, pick a new integer in a given range -> from 0 to $range")
        }
    }

    fun readText(prompt: String) : String {
        while (true) {
            print(prompt)

            val value = readlnOrNull()?.trim().toString()

            if (value != "") {
                return value
            }

            println("Please, enter some letters.")
        }
    }

    fun readNameOrSurname(prompt: String) : String {

        while (true) {

            val value = readText(prompt)

            if (value.length > 1) {
                return value
            }

            println("This String must have at least 2 letters")
        }
    }

    fun readPlayerNumber(prompt: String, players: MutableSet<Player>) : Int {

        val takenNumbers = mutableSetOf<Int>()

        for (player in players) {
            val playerNumber = player.playerNumber
            takenNumbers.add(playerNumber)
        }

        while (true) {

            val value = readInt(prompt)

            if (value in 0 until 1000 && value !in takenNumbers) {
                return value
            }

            println("!!! THIS NUMBER IS ALREADY IN USE !!!")
            println("Please pick a free Integer between 0 and 999")
            println("Numbers that are already in use: $takenNumbers")

        }
    }

    fun readMoney (prompt: String) : Int {

        while (true) {

            val value = readInt(prompt)

            if (value > 99) {
                return value
            }

            println("The player must start with at least 100 money")
        }

    }

    fun readAge(prompt: String) : Int {
        while (true) {

            val value = readInt(prompt)

            if (value > 17) {
                return value
            }

            print("Grow up first")
        }
    }

    fun readRangeNumber(prompt: String) : Int {
        while (true) {

            val value = readInt(prompt)

            if (value in 50 until 100) {
                return value
            }

            println("The range number must be from 50 to 99")
        }
    }

    fun readAmountOfNumbers(prompt: String) : Int {
        while (true) {

            val value = readInt(prompt)

            if (value in 1 until 11) {
                return value
            }

            println("Amount of numbers must be in range from 1 to 10")

        }
    }

    fun readGameID(prompt: String, games: MutableSet<Lottery>) : Int {


        val takenNumbers = mutableSetOf<Int>()

        for (game in games) {
            val gameNumber = game.gameID
            takenNumbers.add(gameNumber)
        }

        while (true) {


            val value = readInt(prompt)

            if (value in 0 until 10000 && value !in takenNumbers) {
                return value
            }

            println("GameID must be between 0 and 9999")
        }
    }

    fun readPlayerChoiceNumbers(prompt: String, lottery: Lottery) : Set<Int> {

        val playersNumbers = mutableSetOf<Int>()
        val range = lottery.range
        var amountOfChoice = lottery.amountOfNumbers

        while (amountOfChoice != 0) {

            println("Number nr. $amountOfChoice")

            val value = readInt(prompt)

            if (value in 0 until range && value !in playersNumbers) {
                playersNumbers.add(value)
                amountOfChoice--
            } else {
                println()
                println("Pick new unique number in a given range: 0 - $range")
            }

            println()
        }

        return playersNumbers


    }

    fun readPlayer(prompt: String, playersData: PlayerDatabase) : Player {

        val playersIDs = mutableSetOf<Int>()

        for (playerData in playersData.allPlayers) {
            playersIDs.add(playerData.playerNumber)
        }

        println("IDs of all players:")
        println("   $playersIDs")
        println()

        while (true) {
            val value = readInt(prompt)

            if (value in playersIDs) {
                for (player in playersData.allPlayers) {
                    if (player.playerNumber == value) {
                        return player
                    }
                }
            }
            println("Player's ID must be correct")
        }
    }


    fun readLottery(prompt: String, lotteriesDatabase: LotteriesDatabase) : Lottery {

        val allGameIDs = mutableSetOf<Int>()

        for (lotteryData in lotteriesDatabase.allLotteries) {
            allGameIDs.add(lotteryData.gameID)
        }

        println("All lotteries to choose")
        println("   $allGameIDs")
        println()

        while (true) {

            val value = readInt(prompt)

            if (value in allGameIDs) {
                for (lottery in lotteriesDatabase.allLotteries) {
                    if (lottery.gameID == value) {
                        return lottery
                    }
                }
            }

            println("Pick one of existing games!")
        }

    }

    fun readEntranceFee(prompt: String) : Int {

        while (true) {

            val value = readInt(prompt)

            if (value in 10 until 101) {
                return value
            }

            println("Entrance fee must be between 10 and 100")
        }

    }

    fun readPositiveInt(prompt: String) : Int {

        while (true) {

            val value = readInt(prompt)

            if (value > 0) {
                return value
            }

        }

    }

    fun readYesAndNo(prompt: String) : String {

        while (true) {

            println("Please pice value Yes[Y] or No[N]")

            var value = readText(prompt)

            value.uppercase()

            if (value == "YES" || value == "NO") {
                if (value == "YES") {
                    value = "Y"
                } else if (value == "NO") {
                    value = "N"
                } else {
                    value = "wrong"
                }
            }


            if (value == "Y" || value == "N") {
                return value
            }

            println("Enter a valid answer")

        }

    }

    fun readAddFakeTickets(prompt: String, ticketDatabase: TicketDatabase) : Int {

        while (true) {

            val value = readPositiveInt(prompt)

            val maxAdd = 10000 - ticketDatabase.allTickets.size

            if (value < maxAdd) {
                return value
            }

            println()
            println("Maximum number you can choose is $maxAdd, but don't go crazy")

        }

    }

    fun readMenuChoice(prompt: String) : Int {

        while (true) {

            val value = readPositiveInt(prompt)

            if (value in 1 .. 6) {
                return value
            }

            println("Your choice must be a full number in range from 1 to 6")

        }

    }

}