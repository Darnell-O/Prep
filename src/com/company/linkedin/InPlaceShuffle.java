package com.company.linkedin;

import java.util.Arrays;
import java.util.Random;

public class InPlaceShuffle {

    public static void main(String[] args) {

        /**
         * Step-by-step breakdown of `BracketValidator.isValid()` provided above. No code changes were needed for this
         * request — the explanation covers:
         * 1. Building the opener→closer map and sets
         * 2. Initializing the stack
         * 3. Iterating through the string — pushing openers, validating closers against the stack top
         * 4. Final `openersStack.isEmpty()` check to ensure no unclosed openers remain
         * All three use cases (`"({[]})"` → true, `"({[})"` → false, `"]hello"` → false) were also walked through step
         * by step.
         * */


        // Use case 1: normal array — multiple elements, result should be a permutation of the original
        int[] array1 = {6, 11, 3, 6, 76, 100, 32, 2, 0};
        shuffle(array1);
        System.out.println("Shuffled: " + Arrays.toString(array1));

        // Use case 2: two-element array — only one possible swap can occur
        int[] array2 = {1, 2};
        shuffle(array2);
        System.out.println("Two elements: " + Arrays.toString(array2));

        // Use case 3: single-element array — guard clause triggers, array unchanged
        int[] array3 = {42};
        shuffle(array3);
        System.out.println("Single element: " + Arrays.toString(array3)); // [42]
    }

    private static Random random = new Random();

    private static int getRandom(int floor, int ceiling) {
        return random.nextInt((ceiling - floor) + 1) + floor;
    }

    public static void shuffle(int[] array) {

        // if it's 1 - 0 items, just return
        if (array.length <= 1) {
            return;
        }

        // walk through the array
        for (int indexWeAreChoosingFor = 0;
             indexWeAreChoosingFor < array.length - 1; indexWeAreChoosingFor++) {
            // choose a random not-yet-placed item to place there
            // (could also be the item currently in that spot)
            // must be an item AFTER the current item, because the stuff
            // before has all already been placed
            int randomChoiceIndex = getRandom(indexWeAreChoosingFor, array.length - 1);

            // places our random choice in the spot swapping
            if (randomChoiceIndex != indexWeAreChoosingFor) {
                int valueAtIndexWeChoseFor = array[indexWeAreChoosingFor];
                array[indexWeAreChoosingFor] = array[randomChoiceIndex];
                array[randomChoiceIndex] = valueAtIndexWeChoseFor;
            }

        }
    }
}
