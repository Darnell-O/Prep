package com.company.hashmap



fun main(args: Array<String>) {

    println(maxFreq("America", 2, 2, 26))

    println(maxFreq("aababcaab", 2, 3, 4)) // Expected: 2
    println(maxFreq("aaaa", 1, 3, 3))       // Expected: 2
    println(maxFreq("aabcabcab", 2, 2, 3))  // Expected: 3


}

/**
 * Given a string s, return the maximum number of occurrences of any substring under the following rules:
 *
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 *
 *
 * Example 1:
 *
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 occurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 * Example 2:
 *
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 *
 * in the MaxFreq.kt
 * create a look that will go through a string and add each character to a a hash map.
 * the max size is 26
 *
 * */

fun maxFreq(s: String, maxLetters: Int, minSize: Int, maxSize: Int): Int {
    val substringCount = HashMap<String, Int>()
    var result = 0

    for (i in 0..s.length - minSize) {
        val sub = s.substring(i, i + minSize)

        // Loop through substring chars, add to HashMap (max size 26)
        val charMap = HashMap<Char, Int>()
        for (c in sub) {
            charMap[c] = (charMap[c] ?: 0) + 1
        }

        // Only count if unique chars <= maxLetters
        if (charMap.size <= maxLetters) {
            substringCount[sub] = (substringCount[sub] ?: 0) + 1
            result = maxOf(result, substringCount[sub]!!)
        }
    }

    return result
}

