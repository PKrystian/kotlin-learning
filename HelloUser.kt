/*
    Write a program that displays the text "Hello <name>!" in the console,
    where <name> is the user's name (ask for it in the console).
 */
fun main() {
    print("Enter your name: ")
    val name = readlnOrNull()
    if (name != null) {
        println("Hello $name!")
    } else {
        println("Hello, anonymous!")
    }
}
