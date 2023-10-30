/*
    Prepare an application that allows you to forge items in a forge.
    - [x] A simple interface to communicate with the user
    - [x] a minimum of 10 items that can be forged (e.g. Rings of Power, shovel, bucket, etc.).
    - [x] each item has the following features:
        - [x] durability
        - [x] description
        - [x] name
        - [x] use counter
        - [x] the method that allows you to use the item
        - [x] additional options (at your discretion, e.g. a bucket can leak after X uses, etc.)
        - [x] the item's durability is defined as a throw of the dice indicated by the user specified dice (d20, d10, etc.)
    - [x] created items are saved in the user's "inventory"
    - [x] adding (forging)
    - [x] deletion
    - [x] use
    - [x] displaying of item details
    - [x] displaying the entire inventory
    - [x] equipments stored only for the time of user interaction with the program (e.g. list, map, etc.)
    - [x] user has the ability to use his equipment
    - [x] equipment after losing all strength (related to uses) disappears from the inventory
*/

fun main()
{
    val forge = ForgeGame()
    forge.menuStart()
}

data class ItemStatistics(
    var name: String,
    val description: String,
    var durability: Int,
    var useCounter: Int,
    val convertThreshold: Int,
)

class ForgeGame
{
    private val inventory = mutableListOf<ItemStatistics>()
    private var diceSides: Int = 20
    fun menuStart() {
        while (true) {
            println("⚒ Forge ⚒")
            println("▼ What do you want to do ▼")
            println("1. ▶ Forge an item")
            println("2. ▶ Forge an custom item")
            println("3. ▶ Use an item")
            println("4. ▶ Show inventory")
            println("5. ▶ Change dice")
            println("6. ▶ Remove an item")
            println("7. ▶ Exit")
            print("▶ Enter your choice: ")

            val menuChoice = readln().toInt()

            when (menuChoice) {
                1 -> forgeItem()
                2 -> forgeCustomItem()
                3 -> useItem()
                4 -> showInventory()
                5 -> changeDice()
                6 -> removeItem()
                7 -> return
                else -> println("Invalid choice")
            }
        }
    }

    private fun forgeItem() {
        val items = listOf(
            ItemStatistics("Sword", "A sword that can cut through anything", rollDice(), 0, 3),
            ItemStatistics("Shield", "A shield that can block anything", rollDice(), 0, 2),
            ItemStatistics("Armor", "An armor that can protect from anything", rollDice(), 0, 4),
            ItemStatistics("Wand", "A wand that always hits it's target", rollDice(), 0, 4),
            ItemStatistics("Ring", "A ring that can make you invisible", rollDice(), 0, 3),
            ItemStatistics("Shovel", "A shovel that can dig through anything", rollDice(), 0, 2),
            ItemStatistics("Bucket", "A bucket that can hold anything", rollDice(), 0, 2),
            ItemStatistics("Pickaxe", "A pickaxe that can mine anything", rollDice(), 0, 2),
            ItemStatistics("Vest", "A vest that shines like a night sky", rollDice(), 0, 3),
            ItemStatistics("Bow", "A bow that can shoot anything", rollDice(), 0, 3),
        )

        println("▼ What do you want to forge ▼")
        items.forEachIndexed {
                index, item -> println("${index + 1}. ▶ ${item.name}")
        }
        println("${items.size + 1}. ▶ Exit")
        print("▶ Enter your choice: ")

        when (val itemChoice = readln().toInt()) {
            in 1..items.size -> {
                val selectedItem = items[itemChoice - 1]
                inventory.add(selectedItem)
            }
            items.size + 1 -> return
            else -> println("Invalid choice")
        }
    }

    private fun forgeCustomItem() {
        print("Enter the name of the item: ")
        val name = readlnOrNull() ?: ""
        print("Enter the description of the item: ")
        val description = readlnOrNull() ?: ""
        print("Enter the convert threshold of the item: ")
        val convertThreshold = readlnOrNull()?.toInt() ?: 0

        val item = ItemStatistics(name, description, rollDice(), 0, convertThreshold)
        inventory.add(item)
    }

    private fun useItem() {
        println("▼ What do you want to use ▼")
        inventory.forEachIndexed {
                index, item -> println("${index + 1}. ▶ ${item.name}")
        }
        println("${inventory.size + 1}. ▶ Exit")
        print("▶ Enter your choice: ")

        when (val itemChoice = readln().toInt()) {
            in 1..inventory.size -> {
                val selectedItem = inventory[itemChoice - 1]
                println("Name: ${selectedItem.name}")
                println("Description: ${selectedItem.description}")
                println("Durability: ${selectedItem.durability}")
                println("Use counter: ${selectedItem.useCounter}")
                println("Convert threshold: ${selectedItem.convertThreshold}")
                println("Do you want to use this item? (y/n)")
                val useChoice = readln().lowercase()
                if (useChoice == "y") {
                    selectedItem.useCounter += 1
                    selectedItem.durability -= 1
                    if (selectedItem.useCounter == selectedItem.convertThreshold) {
                        println("Item converted, now it's magical!")
                        selectedItem.name = "Magical ${selectedItem.name}"
                    }
                    if (selectedItem.durability == 0) {
                        println("Item broke!")
                        inventory.removeAt(itemChoice - 1)
                    }
                }
            }
            inventory.size + 1 -> return
            else -> println("Invalid choice")
        }
    }

    private fun showInventory() {
        println("▼ Inventory ▼")
        inventory.forEachIndexed {
                index, item -> println("${index + 1}. ▶ ${item.name}")
        }
        println("${inventory.size + 1}. ▶ Exit")
        print("▶ Enter your choice: ")

        when (val itemChoice = readln().toInt()) {
            in 1..inventory.size -> {
                val selectedItem = inventory[itemChoice - 1]
                println("Name: ${selectedItem.name}")
                println("Description: ${selectedItem.description}")
                println("Durability: ${selectedItem.durability}")
                println("Use counter: ${selectedItem.useCounter}")
                println("Convert threshold: ${selectedItem.convertThreshold}")
            }
            inventory.size + 1 -> return
            else -> println("Invalid choice")
        }
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

    private fun rollDice(): Int {
        return (1..diceSides).random()
    }

    private fun removeItem() {
        println("▼ What do you want to remove ▼")
        inventory.forEachIndexed {
            index, item -> println("${index + 1}. ▶ ${item.name}")
        }
        println("${inventory.size + 1}. ▶ Exit")
        print("▶ Enter your choice: ")

        when (val itemChoice = readln().toInt()) {
            in 1..inventory.size -> {
                val selectedItem = inventory[itemChoice - 1]
                println("Name: ${selectedItem.name}")
                println("Description: ${selectedItem.description}")
                println("Durability: ${selectedItem.durability}")
                println("Use counter: ${selectedItem.useCounter}")
                println("Convert threshold: ${selectedItem.convertThreshold}")
                println("Do you want to remove this item? (y/n)")
                val useChoice = readln().lowercase()
                if (useChoice == "y") {
                    inventory.removeAt(itemChoice - 1)
                }
            }
            inventory.size + 1 -> return
            else -> println("Invalid choice")
        }
    }
}