package UserInterface

import CommandPattern.Command

class Application {
    val menu = Menu()

    fun lottoGame() {

        menu.startView()

        while (menu.running) {
            menu.printActions()
            menu.choosing()
        }
    }
}