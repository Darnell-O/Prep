package com.company.linkedin

import java.util.*

    fun main(args: Array<String>) {
        // Use case 1: valid — all brackets properly matched and nested
        println(isValid("({[]})")) // true

        // Use case 2: invalid — mismatched closer
        println(isValid("({[})")) // false

        // Use case 3: invalid — closer with no opener (empty stack)
        println(isValid("]hello")) // false
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
        val openersToClosers: MutableMap<Char?, Char?> = HashMap<Char?, Char?>()
        openersToClosers.put('(', ')')
        openersToClosers.put('[', ']')
        openersToClosers.put('{', '}')

        val openers = openersToClosers.keys
        val closers: MutableSet<Char?> = HashSet<Char?>(openersToClosers.values)

        val openersStack: Deque<Char?> = ArrayDeque<Char?>()

        for (i in 0..<code.length) {
            val c = code.get(i)
            if (openers.contains(c)) {
                openersStack.push(c)
            } else if (closers.contains(c)) {
                if (openersStack.isEmpty()) {
                    return false
                } else {
                    val lastUnclosedOpener: Char = openersStack.pop()!!
                    // if this closer doesn't correspond to the most recently
                    // seen unclosed opener, short-circuit, returning false
                    if (openersToClosers.get(lastUnclosedOpener) != c) {
                        return false
                    }
                }
            }
        }

        return openersStack.isEmpty()
    }
