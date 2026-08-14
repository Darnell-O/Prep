package com.company.spotify

fun main() {
    println(playListA)
    println(playListB)
    println(combinePlaylist(playListA,playListB))
    println(combinePlaylist2(playListA,playListB))
    println(combinePlaylist2(playListA,playListB).size)

}


/**
 * 2 lists of sorted song lists: topSongsA and TopSongsB
 * The list holds an object called the
 * data class TopListedSongs<String: title, Int: numberOfListens>
 *
 * The lists can be any size
 *
 * The lists are already sorted in descending order
 *
 * Create a new shared list that combines topSongsA and TopSongsB and returns the top 10 of that list
 *
 * If the songs being combined have the same number of plays, return in alphabetical order of the 2
 *
 * returning a list of 10 songs
 * */



fun combinePlaylist(listA: List<Playlist>, listB: List<Playlist>): List<Playlist> {
    val combined = mutableListOf<Playlist>()
    for (i in 0 until listA.size) {
        combined.add(listA[i])
        for (i in 0 until listB.size) {
            combined.add(listB[i])
        }
    }

    return combined.take(10)
}

fun combinePlaylist2(listA: List<Playlist>, listB: List<Playlist>): List<Playlist> {
    // Step 1: Concatenate both lists using the + operator
    // This creates a single list with all items from listA followed by all items from listB
    val combined = (listA + listB)
        // Step 2: Sort the combined list with custom comparator
        .sortedWith(
            // Primary sort: by timesPlayed in DESCENDING order (highest to lowest)
            compareByDescending<Playlist> { it.timesPlayed }
                // Secondary sort (tie-breaker): by name in ASCENDING alphabetical order (A-Z)
                // Only applies when timesPlayed values are equal
                .thenBy { it.name }
        )
        // Step 3: Take only the first 10 elements from the sorted list
        // Returns at most 10 items (or fewer if list has less than 10)
        .take(10)

    return combined
}

fun combinePlaylist3(listA: List<Playlist>, listB: List<Playlist>): List<Playlist> {
    val result = mutableListOf<Playlist>()
    var i = 0  // pointer for listA
    var j = 0  // pointer for listB

    // Merge the two sorted lists while keeping top 10
    while (result.size < 10 && (i < listA.size || j < listB.size)) {
        when {
            // If listA exhausted, take from listB
            i >= listA.size -> {
                result.add(listB[j])
                j++
            }
            // If listB exhausted, take from listA
            j >= listB.size -> {
                result.add(listA[i])
                i++
            }
            // Compare timesPlayed
            listA[i].timesPlayed > listB[j].timesPlayed -> {
                result.add(listA[i])
                i++
            }
            listA[i].timesPlayed < listB[j].timesPlayed -> {
                result.add(listB[j])
                j++
            }
            // If equal, sort alphabetically by name
            else -> {
                if (listA[i].name <= listB[j].name) {
                    result.add(listA[i])
                    i++
                } else {
                    result.add(listB[j])
                    j++
                }
            }
        }
    }

    return result
}


val playListA = listOf<Playlist>(
    Playlist(name = "Plain Jane", timesPlayed = 15),
    Playlist(name = "Energy", timesPlayed = 20),
    Playlist(name = "Running", timesPlayed = 25),
    Playlist(name = "Sicko Mode", timesPlayed = 15),
    Playlist(name = "Last Breathe", timesPlayed = 10),
    Playlist(name = "Express Yourself", timesPlayed = 100),
    Playlist(name = "Slob on My Knob", timesPlayed = 1500),
    Playlist(name = "Molly Ringwald", timesPlayed = 200),
    Playlist(name = "Run", timesPlayed = 205),
    Playlist(name = "Like That", timesPlayed = 250),
).sortedByDescending(Playlist::timesPlayed)

val playListB = listOf<Playlist>(
    Playlist(name = "Oops!...I did it again", timesPlayed = 150),
    Playlist(name = "Made You Look Remix", timesPlayed = 199),
    Playlist(name = "Nice for What", timesPlayed = 25),
    Playlist(name = "Sicko Mode", timesPlayed = 15),
    Playlist(name = "Sorry", timesPlayed = 10),
    Playlist(name = "Express Yourself", timesPlayed = 100),
    Playlist(name = "Look Alive", timesPlayed = 1500),
    Playlist(name = "Molly Ringwald", timesPlayed = 200),
    Playlist(name = "PowerGlide", timesPlayed = 205),
    Playlist(name = "Fight Night", timesPlayed = 250),
).sortedByDescending(Playlist::timesPlayed)



data class Playlist(
    val name: String,
    val timesPlayed: Int
)
