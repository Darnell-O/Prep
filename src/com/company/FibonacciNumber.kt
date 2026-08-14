package com.company


fun main() {
    println(fib(2))
    println(fib(3))
    println(fib(4))
}


/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence,
 * called the Fibonacci sequence, such that each number is the sum of the two preceding ones,
 * starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 * Example 2:
 *
 * Input: n = 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 * Example 3:
 *
 * Input: n = 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 * */

/**
 * Time Complexity: O(n)
 * Space Complexity: O(1)*/

fun fib(n: Int): Int {
    // Base case: If n is 0 or 1, return n itself (F(0)=0, F(1)=1)
    if (n <= 1) return n
    
    // Initialize prev to 0 (represents F(0), the first Fibonacci number)
    var prev = 0
    
    // Initialize current to 1 (represents F(1), the second Fibonacci number)
    var current = 1

    // Loop from 2 to n to calculate each Fibonacci number iteratively
    for (i in 2..n) {
        // Calculate next Fibonacci number: F(n) = F(n-1) + F(n-2)
        val next = prev + current
        
        // Shift window forward: update prev to the old current value
        prev = current
        
        // Shift window forward: update current to the newly calculated next value
        current = next
    }
    
    // Return the nth Fibonacci number stored in current
    return current
}
