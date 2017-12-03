/*

1

h = 1, w = 1

---

5 4 3
6 1 2
7 8 9

h = 3, w = 3

---

17 16 15 14 13
18  5  4  3 12
19  6  1  2 11
20  7  8  9 10
21 22 23 24 25

h = 5, w = 5

---

37 36 35 34 33 32 31
38 17 16 15 14 13 30
39 18  5  4  3 12 29
40 19  6  1  2 11 28
41 20  7  8  9 10 27
42 21 22 23 24 25 26
43 44 45 46 47 48 49

h = 7, w = 7

---

squares start at one, always increase by two
=>
sizes are 1², 3², 5², 7², 9², 11², ...

target = 289326
sqrt(289326) = 537,9

=> square we are looking for is
h = 539, w = 539

539 * 539 = 290521

square looks like this:

289445 ...
.
.
.
289983 ... 290521

coordinates =
-269 <-> 0 <-> 269

289445 - 289326 = 119

-269 + 119 = -150
=> println(manhattan(Point(-150, 269)))


For the second part I just looked up the sequence on the OEIS

1, 1, 2, 4, 5, 10, 11, 23, 25, 26, 54
=>
https://oeis.org/A141481
https://oeis.org/A141481/b141481.txt
=>
295229

*/

import kotlin.math.abs

fun main(args: Array<String>) {
    println(manhattan(Point(0, 0)))
    println(manhattan(Point(2, 1)))
    println(manhattan(Point(0, -2)))

    println(manhattan(Point(-150, 269)))
}

fun manhattan(p1: Point, p2: Point = Point(0, 0)): Int {
    return abs(p1.x - p2.x) + abs(p1.y - p2.y)
}

data class Point(val x: Int, val y: Int)