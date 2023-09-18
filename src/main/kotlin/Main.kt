import java.util.Scanner

fun main() {
    val anagramsMap = mutableMapOf<String, MutableList<String>>()

    while (true) {
        println("Choose an option:")
        println("1. Check if two texts are anagrams")
        println("2. Find texts from feature 1 that are anagrams of a given string")
        println("3. Quit")

        val choice = getModeFromUser()

        when (choice) {
            1 -> checkAnagrams(anagramsMap)
            2 -> findAnagrams(anagramsMap)
            3 -> {
                println("Goodbye!")
                return
            }

            else -> println("Invalid option. Please choose again.")
        }
    }
}

fun getModeFromUser(): Int {
    val scanner = Scanner(System.`in`)
    return try {
        scanner.nextInt()
    } catch (ex: Exception) {
        scanner.next()
        0
    }
}

fun checkAnagrams(anagramsMap: MutableMap<String, MutableList<String>>) {
    val scanner = Scanner(System.`in`)

    println("Enter the first text:")
    val text1 = scanner.next()

    println("Enter the second text:")
    val text2 = scanner.next()

    if (areAnagrams(text1, text2)) {
        println("$text1 and $text2 are anagrams of each other.")

        anagramsMap.computeIfAbsent(text1) { mutableListOf() }.add(text2)
        anagramsMap.computeIfAbsent(text2) { mutableListOf() }.add(text1)
    } else {
        println("$text1 and $text2 are not anagrams.")
    }
}

fun findAnagrams(anagramsMap: MutableMap<String, MutableList<String>>) {
    val scanner = Scanner(System.`in`)

    println("Enter the target string:")
    val target = scanner.next()

    val storedAnagrams = anagramsMap[target] ?: emptyList()

    if (storedAnagrams.isEmpty()) {
        println("No stored anagrams found for $target.")
    } else {
        println("Texts from feature 1 that are anagrams of $target:")
        storedAnagrams.forEach { println(it) }
    }
}

fun areAnagrams(text1: String, text2: String) = text1.toSortedCharArray() == text2.toSortedCharArray()

fun String.toSortedCharArray() = this.toCharArray().sorted()
