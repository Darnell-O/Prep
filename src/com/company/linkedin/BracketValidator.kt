package com.company.linkedin

import java.util.*

fun main(args: Array<String>) {
    // Use case 1: valid — all brackets properly matched and nested
    println(isValid("({[]})")) // true
    println(isValidOptimized("({[]})")) // true

    // Use case 2: invalid — mismatched closer
    println(isValid("({[})")) // false
    println(isValidOptimized("({[})")) // false

    // Use case 3: invalid — closer with no opener (empty stack)
    println(isValid("]hello")) // false
    println(isValidOptimized("]hello")) // false
}
/**
 * Solution O(n) Time,Space
 * We iterate through our string, making sure that:
 *
 * each closer corresponds to the most recently seen, unclosed opener
 * every opener and closer is in a pair
 * We use a stack ↴ to keep track of the most recently seen, unclosed opener.
 * And if the stack is ever empty when we come to a closer, we know that closer doesn't have an opener.
 *
 * So as we iterate:
 * If we see an opener, we push it onto the stack.
 * If we see a closer, we check to see if it is the closer for the opener at the top of the stack.
 * If it is, we pop from the stack. If it isn't, or if the stack is empty, we return false.
 * If we finish iterating and our stack is empty, we know every opener was properly closed.
 *
 * Step-by-step breakdown of `BracketValidator.isValid()` provided above. No code changes were needed for this
 * request — the explanation covers:
 *
 * 1. Building the opener→closer map and sets
 * 2. Initializing the stack
 * 3. Iterating through the string — pushing openers, validating closers against the stack top
 * 4. Final `openersStack.isEmpty()` check to ensure no unclosed openers remain
 *
 * All three use cases (`"({[]})"` → true, `"({[})"` → false, `"]hello"` → false) were also walked through step by
 * step.
 */
/** Time : O(n) , Space : O(n) */
fun isValid(code: String): Boolean {
    // Create a map that pairs each opening bracket with its corresponding closing bracket
    val openersToClosers: MutableMap<Char?, Char?> = HashMap<Char?, Char?>()
    openersToClosers.put('(', ')')  // Map opening parenthesis to closing parenthesis
    openersToClosers.put('[', ']')  // Map opening square bracket to closing square bracket
    openersToClosers.put('{', '}')  // Map opening curly brace to closing curly brace

    // Extract all opening brackets as a set for quick lookup
    val openers = openersToClosers.keys
    // Extract all closing brackets as a set for quick lookup
    val closers: MutableSet<Char?> = HashSet<Char?>(openersToClosers.values)

    // Create a stack (Deque) to track unclosed opening brackets
    val openersStack: Deque<Char?> = ArrayDeque<Char?>()

    // Iterate through each character in the input string
    for (i in 0..<code.length) {
        // Get the current character
        val c = code.get(i)

        // Check if current character is an opening bracket
        if (openers.contains(c)) {
            // Push opening bracket onto stack to track it
            openersStack.push(c)
        }
        // Check if current character is a closing bracket
        else if (closers.contains(c)) {
            // Check if stack is empty (closing bracket with no matching opener)
            if (openersStack.isEmpty()) {
                return false  // Invalid: closer has no opener
            } else {
                // Pop the most recent unclosed opening bracket from stack
                val lastUnclosedOpener: Char = openersStack.pop()!!

                // Check if this closer matches the most recently opened bracket
                // if this closer doesn't correspond to the most recently
                // seen unclosed opener, short-circuit, returning false
                if (openersToClosers.get(lastUnclosedOpener) != c) {
                    return false  // Invalid: mismatched brackets
                }
            }
        }
        // If character is neither opener nor closer, continue (ignore it)
    }

    // Check if all opening brackets were closed (stack should be empty)
    return openersStack.isEmpty()  // True if valid, false if unclosed brackets remain
}

/**
 * OPTIMIZED VERSION - More efficient implementation
 * Time: O(n) - single pass through string
 * Space: O(n) - stack can hold up to n/2 brackets in worst case
 *
 * Improvements over original:
 * - No nullable types (Char instead of Char?)
 * - No HashMap/HashSet overhead
 * - Direct character matching using when expression
 * - Slightly better constant time factors
 */
fun isValidOptimized(code: String): Boolean {
    // Stack to track unclosed opening brackets (non-nullable)
    val stack = ArrayDeque<Char>()

    // Iterate through each character
    for (char in code) {
        // For opening brackets, push onto stack
        when (char) {
            '(', '[', '{' -> stack.push(char)
            // For closing brackets, validate against stack top
            ')' -> {
                // Check if stack empty or top doesn't match
                if (stack.isEmpty() || stack.pop() != '(') return false
            }

            ']' -> {
                if (stack.isEmpty() || stack.pop() != '[') return false
            }

            '}' -> {
                if (stack.isEmpty() || stack.pop() != '{') return false
            }
            // Ignore all other characters (letters, numbers, etc.)
        }
    }

    // Valid only if all brackets were closed (stack empty)
    return stack.isEmpty()
}
