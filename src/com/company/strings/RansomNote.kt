package com.company.strings

fun main() {
    // Test cases from the problem description
    println("=== Original HashMap Solution ===")
    println(canConstruct("a", "b"))       // false
    println(canConstruct("aa", "ab"))     // false
    println(canConstruct("aa", "aab"))    // true
    
    println("\n=== Optimized HashMap Solution (with early check) ===")
    println(canConstructOptimized("a", "b"))       // false
    println(canConstructOptimized("aa", "ab"))     // false
    println(canConstructOptimized("aa", "aab"))    // true
    
    println("\n=== IntArray Solution (fastest for lowercase a-z) ===")
    println(canConstructIntArray("a", "b"))       // false
    println(canConstructIntArray("aa", "ab"))     // false
    println(canConstructIntArray("aa", "aab"))    // true
}

/**
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by
 * using the letters from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 *
 *
 * Example 1:
 *
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 *
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 *
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 * */

// Original Solution - HashMap approach
// Time: O(m + n), Space: O(k) where k = unique chars
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    // Count frequency of each character in magazine
    val charCount = mutableMapOf<Char, Int>()
    
    for (char in magazine) {
        charCount[char] = charCount.getOrDefault(char, 0) + 1
    }
    
    // Check if ransomNote can be constructed
    for (char in ransomNote) {
        val count = charCount.getOrDefault(char, 0)
        if (count == 0) {
            return false  // Character not available or already used up
        }
        charCount[char] = count - 1  // Use the character
    }
    
    return true  // All characters were available
}

// Optimized Solution 1 - HashMap with early length check
// Time: O(m + n), Space: O(k)
// Optimization: Early return if ransomNote is longer than magazine
fun canConstructOptimized(ransomNote: String, magazine: String): Boolean {
    // Early exit if ransomNote is longer - impossible to construct
    if (ransomNote.length > magazine.length) return false
    
    val charCount = mutableMapOf<Char, Int>()
    
    for (char in magazine) {
        charCount[char] = charCount.getOrDefault(char, 0) + 1
    }
    
    for (char in ransomNote) {
        val count = charCount.getOrDefault(char, 0)
        if (count == 0) return false
        charCount[char] = count - 1
    }
    
    return true
}

// Optimized Solution 2 - IntArray for lowercase letters only
// Time: O(m + n), Space: O(1) - constant space of 26 slots
// Fastest solution for lowercase English letters (a-z)
// Benefits: Faster array access, no hash computation, better memory locality
fun canConstructIntArray(ransomNote: String, magazine: String): Boolean {
    // Early exit if ransomNote is longer
    if (ransomNote.length > magazine.length) return false
    
    // Array for lowercase letters a-z (26 slots)
    val charCount = IntArray(26)
    
    // Count characters in magazine
    for (char in magazine) {
        charCount[char - 'a']++
    }
    
    // Check if ransomNote can be constructed
    for (char in ransomNote) {
        val index = char - 'a'
        if (charCount[index] == 0) return false
        charCount[index]--
    }
    
    return true
}

// Bonus: Functional/Idiomatic Kotlin approach
// Time: O(m + n), Space: O(k)
// Most readable but similar performance to HashMap version
fun canConstructFunctional(ransomNote: String, magazine: String): Boolean {
    if (ransomNote.length > magazine.length) return false
    
    val magazineFreq = magazine.groupingBy { it }.eachCount().toMutableMap()
    
    for (char in ransomNote) {
        val count = magazineFreq[char] ?: 0
        if (count == 0) return false
        magazineFreq[char] = count - 1
    }
    
    return true
}
