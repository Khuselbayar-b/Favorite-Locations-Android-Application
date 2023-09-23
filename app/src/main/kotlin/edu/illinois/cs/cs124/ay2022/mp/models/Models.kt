// ktlint-disable filename
@file:Suppress("Filename")

package edu.illinois.cs.cs124.ay2022.mp.models

/*
 * Models storing information about places retrieved from the backend server.
 *
 * You will need to understand some of the code in this file and make changes starting with MP1.
 */

class Place(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val description: String = ""
)

fun List<Place>.search(searchString: String): List<Place> {
    if (this.isEmpty() || searchString.isBlank()) {
        return this
    }
    val trimmedSearchString = searchString.trim()
    val processedSearchString = trimmedSearchString.replace(
        Regex("[.!?,:;/]"),
        " "
    ).replace(Regex("[^a-zA-Z0-9\\s]"), "")

    val matchingPlaces = mutableListOf<Place>()
    for (place in this) {
        val processedDescription = place.description.replace(
            Regex("[.!?,:;/]"),
            " "
        ).replace(Regex("[^a-zA-Z0-9\\s]"), "")
        val words = processedDescription.split(" ")
        if (words.any { it.equals(processedSearchString, ignoreCase = true) }) {
            matchingPlaces.add(place)
        }
    }

    return matchingPlaces
}
