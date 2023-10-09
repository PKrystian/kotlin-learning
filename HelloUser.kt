fun main() {
    print("Insert your name: ")
    val name = readLine()
    if (name != null) {
        println("Hello $name!")
    } else {
        println("Hello, anonymous!")
    }
}