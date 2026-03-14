package com.company.linkedin

import java.util.*

fun main(args: Array<String>) {
    /**
     * Step-by-step breakdown of `BracketValidator.isValid()` provided above. No code changes were needed for this
     * request — the explanation covers:
     * 1. Building the opener→closer map and sets
     * 2. Initializing the stack
     * 3. Iterating through the string — pushing openers, validating closers against the stack top
     * 4. Final `openersStack.isEmpty()` check to ensure no unclosed openers remain
     * All three use cases (`"({[]})"` → true, `"({[})"` → false, `"]hello"` → false) were also walked through step
     * by step.
     */


    // Use case 1: normal array — multiple elements, result should be a permutation of the original


    val array1 = intArrayOf(6, 11, 3, 6, 76, 100, 32, 2, 0)
    shuffle(array1)
    println("Shuffled: " + array1.contentToString())

    // Use case 2: two-element array — only one possible swap can occur
    val array2 = intArrayOf(1, 2)
    shuffle(array2)
    println("Two elements: " + array2.contentToString())

    // Use case 3: single-element array — guard clause triggers, array unchanged
    val array3 = intArrayOf(42)
    shuffle(array3)
    println("Single element: " + array3.contentToString()) // [42]
}

private val random = Random()

private fun getRandom(floor: Int, ceiling: Int): Int {
    return random.nextInt((ceiling - floor) + 1) + floor
}

fun shuffle(array: IntArray) {
    // if it's 1 - 0 items, just return

    if (array.size <= 1) {
        return
    }

    // walk through the array
    for (indexWeAreChoosingFor in 0..<array.size - 1) {
        // choose a random not-yet-placed item to place there
        // (could also be the item currently in that spot)
        // must be an item AFTER the current item, because the stuff
        // before has all already been placed
        val randomChoiceIndex = getRandom(indexWeAreChoosingFor, array.size - 1)

        // places our random choice in the spot swapping
        if (randomChoiceIndex != indexWeAreChoosingFor) {
            val valueAtIndexWeChoseFor = array[indexWeAreChoosingFor]
            array[indexWeAreChoosingFor] = array[randomChoiceIndex]
            array[randomChoiceIndex] = valueAtIndexWeChoseFor
        }
    }
}