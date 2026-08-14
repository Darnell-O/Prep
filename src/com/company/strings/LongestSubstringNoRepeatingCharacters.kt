package com.company.strings


fun main() {

    println(lengthOfLongestSubstring("abcabcbb"))
    println(lengthOfLongestSubstring("bbbbb"))
    println(lengthOfLongestSubstring("pwwkew"))
}

/**
 *
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 105
 * s consists of English letters, digits, symbols and spaces.
 *
 * */


fun lengthOfLongestSubstring(string: String): Int {
    // Edge case: empty string
    if (string.isEmpty()) return 0

    val uniqueLetters = HashSet<Char>()
    var maxLength = 0  // Track the maximum length found
    var left = 0       // Left pointer of the sliding window

    // Right pointer iterates through the string
    for (right in string.indices) {
        val char = string[right]

        // If character already exists in the set, shrink window from left
        while (char in uniqueLetters) {
            uniqueLetters.remove(string[left])
            left++
        }

        // Add current character to the set
        uniqueLetters.add(char)

        // Update max length (window size is right - left + 1)
        maxLength = maxOf(maxLength, right - left + 1)
    }

    return maxLength
}


fun lengthOfLongestSubstring2(s: String): Int {
    val lastSeen = mutableMapOf<Char, Int>()
    var left = 0
    var longest = 0
    for (right in s.indices) {
        val char = s[right]
        val previous = lastSeen[char]

        if (previous != null && previous >= left) {
            left = previous + 1
        }
        lastSeen[char] = right
        longest = maxOf(longest, right - left + 1)
    }
    return longest
}
