package UserInterface

import Databases.PlayerDatabase
import MainObjects.Lottery
import kotlin.random.Random

class GetRandom {

    fun getRandomNumbers(lottery: Lottery) : Set<Int> {

        val range = lottery.range
        val count = lottery.amountOfNumbers

        val randomSet = mutableSetOf<Int>()

        while (randomSet.size < count) {
            randomSet.add(Random.nextInt(0, range))
        }

        return randomSet

    }

    fun getRandomName() : String {

        val names = listOf(
            "James", "Mary", "Robert", "Patricia", "John",
            "Jennifer", "Michael", "Linda", "David", "Elizabeth",
            "William", "Barbara", "Richard", "Susan", "Joseph",
            "Jessica", "Thomas", "Sarah", "Christopher", "Karen",
            "Charles", "Lisa", "Daniel", "Nancy", "Matthew",
            "Betty", "Anthony", "Sandra", "Mark", "Ashley",
            "Donald", "Dorothy", "Steven", "Kimberly", "Paul",
            "Emily", "Andrew", "Donna", "Joshua", "Michelle",
            "Kenneth", "Carol", "Kevin", "Amanda", "Brian",
            "Melissa", "George", "Deborah", "Edward", "Stephanie"
        )

        val randomIndex = Random.nextInt(0,50)

        return names[randomIndex]

    }


    fun GetRandomSurname () : String {

        val surnames = listOf(
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore", "Jackson", "Martin",
            "Lee", "Perez", "Thompson", "White", "Harris",
            "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson",
            "Walker", "Young", "Allen", "King", "Wright",
            "Scott", "Torres", "Nguyen", "Hill", "Flores",
            "Green", "Adams", "Nelson", "Baker", "Hall",
            "Rivera", "Campbell", "Mitchell", "Carter", "Roberts"
        )

        val randomIndex = Random.nextInt(0,50)

        return surnames[randomIndex]
    }


    fun getRandomAge() : Int {
        return Random.nextInt(18, 100)
    }

    fun getRandomID(playerDatabase: PlayerDatabase) : Int {


        val takenIDs = mutableSetOf<Int>()

        for (player in playerDatabase.allPlayers) {
            takenIDs.add(player.playerNumber)
        }


        val ID = Random.nextInt(0, 10000)

        while (true) {
            if (ID !in takenIDs) {
                return ID
            }
        }

    }

    fun getRandomMoney(lottery: Lottery) : Int {
        val amountToPlayInFewLotteries = lottery.entranceFee * 5
        return Random.nextInt(amountToPlayInFewLotteries, 99999)
    }
}

