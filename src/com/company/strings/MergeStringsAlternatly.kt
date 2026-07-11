package com.company.strings

fun main() {
    println(mergeAlternately("Darnell", "Otto"))   // "DOatrtnоell"
    println(mergeAlternately("ab", "pqrs"))         // "apbqrs"
    println(mergeAlternately("abcd", "pq"))         // "apbqcd"
}

/**
 * Merges two strings by alternating one character at a time from each string.
 * If one string is longer, the remaining characters are appended at the end.
 *
 * Time complexity:  O(n + m) — n = word1.length, m = word2.length
 * Space complexity: O(n + m) — for the result
 */
fun mergeAlternately(word1: String, word2: String): String = buildString {
    val minLen = minOf(word1.length, word2.length)

    for (i in 0 until minLen) {
        append(word1[i])
        append(word2[i])
    }

    // Append remaining characters of the longer string
    append(word1.drop(minLen))
    append(word2.drop(minLen))
}
