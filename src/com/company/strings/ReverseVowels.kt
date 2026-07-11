package com.company.strings

fun main() {
    println(reverseVowels("Darnell"))

    println(reverseVowels("Victoria"))

}


/**I want to store in a variable what vowels are
 * uppercase and lowercase.
 *
 * then I want to iterate over the string and check which ones exist in that string
 * if it does contain a vowel we store that vowel in another string
 *
 *
 *
 *
 *
 * */
fun reverseVowels(s: String): String {
    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

    //turns string into an array of chars
    val chars = s.toCharArray()

    //pointers
    var left = 0
    var right = chars.lastIndex

    while (left < right) {
        while (left < right && chars[left] !in vowels) {
            left++
        }
        while (left < right && chars[right] !in vowels) {
            right--
        }
        if (left < right) {
            val temp = chars[left]
            chars[left] = chars[right]
            chars[right] = temp
            left++
            right--
        }
    }

    return String(chars)
}
