/*
    Prepare an application that allows you to forge items in a forge.
    - [ ] A simple interface to communicate with the user
    - [ ] a minimum of 10 items that can be forged (e.g. Rings of Power, shovel, bucket, etc.).
    - [ ] each item has the following features:
        - [ ] durability
        - [ ] description
        - [ ] name
        - [ ] use counter
        - [ ] the method that allows you to use the item
        - [ ] additional options (at your discretion, e.g. a bucket can leak after X uses, etc.)
        - [ ] the item's durability is defined as a throw of the dice indicated by the user specified dice (d20, d10, etc.)
    - [ ] created items are saved in the user's "inventory"
    - [ ] adding (forging)
    - [ ] deletion
    - [ ] use
    - [ ] displaying of item details
    - [ ] displaying the entire inventory
    - [ ] equipments stored only for the time of user interaction with the program (e.g. list, map, etc.)
    - [ ] user has the ability to use his equipment
    - [ ] equipment after losing all strength (related to uses) disappears from the inventory
*/

fun main()
{
    val forge = ForgeGame()
    forge.menuStart()
}

data class ItemStatistics(
    val name: String,
    val description: String,
    val durability: Int,
    val useCounter: Int,
    val convertThreshold: Int,
)

class ForgeGame
{
    private val inventory = mutableListOf<ItemStatistics>()
    private var diceSides: Int = 20
    fun menuStart()
    {
        while (true) {
            println("⚒ Forge ⚒")
            println("▼ What do you want to do ▼")
            println("1. ▶ Forge an item")
            println("2. ▶ Forge an custom item")
            println("3. ▶ Use an item")
            println("4. ▶ Show inventory")
            println("5. ▶ Change dice")
            println("6. ▶ Exit")
            print("▶ Enter your choice: ")

            val menuChoice = readln().toInt()

            when (menuChoice) {
                1 -> forgeItem()
//                2 -> forgeCustomItem()
//                3 -> useItem()
//                4 -> showInventory()
                5 -> changeDice()
                6 -> return
                else -> println("Invalid choice")
            }
        }
    }

    private fun forgeItem() {
        val items = listOf(
            ItemStatistics("Magic Sword", "A sword that can cut through anything", rollDice(), 0, 3),
            ItemStatistics("Magic Shield", "A shield that can block anything", rollDice(), 0, 2),
            ItemStatistics("Magic Armor", "An armor that can protect from anything", rollDice(), 0, 3),
            ItemStatistics("Magic Wand", "A wand that always hits it's target", rollDice(), 0, 1),
        )

        println("▼ What do you want to forge ▼")
        items.forEachIndexed { index, item ->
            println("${index + 1}. ▶ ${item.name}")
        }
        println("${items.size + 1}. ▶ Exit")
        print("▶ Enter your choice: ")

        val itemChoice = readln().toInt()

        when (itemChoice) {
            in 1..items.size -> {
                val selectedItem = items[itemChoice - 1]
                inventory.add(selectedItem)
            }
            items.size + 1 -> return
            else -> println("Invalid choice")
        }
    }

    private fun rollDice(): Int {
        return (1..diceSides).random()
    }

    private fun changeDice() {
        println("Current number of dice sides: $diceSides")
        print("Enter a new number of sides for the dice: ")
        val newDiceSides = readlnOrNull()?.toInt()

        if (newDiceSides != null && newDiceSides > 0) {
            diceSides = newDiceSides
            println("Dice sides changed to $diceSides")
        } else {
            println("Invalid input. Please enter a positive integer for the number of dice sides.")
        }
    }
}