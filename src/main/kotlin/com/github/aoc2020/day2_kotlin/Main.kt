@file:JvmName("Main")

package com.github.aoc2020.day2_kotlin

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

fun toPair(i: Int) : Pair<Int,Int> {
    return Pair (i * 3, i);
}

fun main() {
    var input = readFile("input.txt")
    var forest : Set<Pair<Int,Int>> = HashSet();
    var width = -1;
    for (y in input.indices) {
        val line = input.get(y)
        width = line.length
        for (x in line.indices) {
            if (line[x] == '#') forest = forest.plus(Pair(x,y));
        }
    }
    var trees = rideAtSlope(width, forest,3,1)
    println("trees = $trees")
    var t11 : Long = rideAtSlope(width, forest,1,1).toLong()
    var t31 : Long = rideAtSlope(width, forest,3,1).toLong()
    var t51 : Long = rideAtSlope(width, forest,5,1).toLong()
    var t71 : Long = rideAtSlope(width, forest,7,1).toLong()
    var t12 : Long = rideAtSlope(width, forest,1,2).toLong()
    println("trees2: ${t11*t31*t51*t71*t12}")
}

private fun rideAtSlope(width: Int, forest: Set<Pair<Int, Int>>, dx: Int, dy: Int): Int {
    var trees = (1..10000).map { x -> Pair((x * dx) % width, x * dy) }.filter { x -> forest.contains(x) }.count()
    return trees
}

fun readFile(fileName: String) : List<String> {
    return Files.lines(Path.of(fileName))
        .collect(Collectors.toUnmodifiableList())
}