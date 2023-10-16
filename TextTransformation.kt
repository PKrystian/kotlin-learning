/*
    Prepare a program that can transform the text given by the user in a way chosen by him
    (e.g. only lowercase letters, only uppercase letters, changing letters to emoticons).
    Requirements:
        - [ ] at least 6 available ways to change the text
        - [ ] only one function (or method) responsible for text transformation
        - [ ] ways to change text passed as a lambda (i.e., the function that changes the text is of higher order)
    For better readability, you can use typealias
 */

fun main() {
    print("Enter text: ")
    val text = readln()
    val transformation = TextTransformation(text)
    transformation.start()
}

class TextTransformation (
    text: String
) {
    fun start()
    {
        while (true) {
            println("Choose transformation:")
            println("1. ▶ Only lowercase letters")
            println("2. ▶ Only uppercase letters")
            println("3. ▶ Letters to emoticons")
            println("4. ▶ Letters to numbers (ASCII)")
            println("7. ▶ Exit")
        }
    }
}