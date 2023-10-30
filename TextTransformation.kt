/*
    Prepare a program that can transform the text given by the user in a way chosen by him
    (e.g. only lowercase letters, only uppercase letters, changing letters to emoticons).
    Requirements:
        - [x] at least 6 available ways to change the text
        - [x] only one function (or method) responsible for text transformation
        - [x] ways to change text passed as a lambda (i.e., the function that changes the text is of higher order)
    For better readability, you can use typealias
 */

fun main() {
    print("Enter the text you want to transform: ")
    var inputText = readlnOrNull() ?: ""
    while (true) {
        println("▼ Select transformation (1-6) ▼")
        println("1. ▶ toLowercase")
        println("2. ▶ toUppercase")
        println("3. ▶ reverseText")
        println("4. ▶ caesarCipher")
        println("5. ▶ toASCII")
        println("6. ▶ toMorseCode")
        println("7. ▶ Change text")
        println("8. ▶ Exit")
        print("▶ Enter your choice: ")

        val menuChoice = readln().toInt()

        when (menuChoice) {
            1 -> transformText(inputText, toLowercase)
            2 -> transformText(inputText, toUppercase)
            3 -> transformText(inputText, reverseText)
            4 -> transformText(inputText, caesarCipher)
            5 -> transformText(inputText, toASCII)
            6 -> transformText(inputText, toMorseCode)
            7 -> {
                print("Enter the text you want to transform: ")
                inputText = readlnOrNull() ?: ""
            }
            8 -> return
            else -> println("Invalid choice")
        }
    }
}

fun transformText(inputText: String, transformation: TextTransformer) {
    println("Transformed text: ${transformation(inputText)}")
}

typealias TextTransformer = (String) -> String

val toLowercase: TextTransformer = { it.lowercase() }
val toUppercase: TextTransformer = { it.uppercase() }
val reverseText: TextTransformer = { it.reversed() }
val caesarCipher: TextTransformer = { text ->
    val shift = 3
    text.map {
        char -> when {
            char.isLetter() -> {
                val base = if (char.isLowerCase()) 'a' else 'A'
                val shiftedChar = ((char.code - base.code + shift) % 26 + 26) % 26 + base.code
                shiftedChar.toChar()
            } else -> char
        }
    }.joinToString("")
}
val toASCII: TextTransformer = {
    text -> text.map {
        it.code.toString()
    }.joinToString(" ")
}
val toMorseCode: TextTransformer = {
    text -> val morseCodeMap = mapOf(
        'A' to ".-", 'B' to "-...", 'C' to "-.-.", 'D' to "-..", 'E' to ".", 'F' to "..-.", 'G' to "--.", 'H' to "....",
        'I' to "..", 'J' to ".---", 'K' to "-.-", 'L' to ".-..", 'M' to "--", 'N' to "-.", 'O' to "---", 'P' to ".--.",
        'Q' to "--.-", 'R' to ".-.", 'S' to "...", 'T' to "-", 'U' to "..-", 'V' to "...-", 'W' to ".--", 'X' to "-..-",
        'Y' to "-.--", 'Z' to "--..", ' ' to " "
    )
    text.map {
        char -> morseCodeMap[char.uppercaseChar()] ?: ""
    }.joinToString(" ")
}
