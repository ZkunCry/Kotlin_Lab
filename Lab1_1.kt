import kotlin.math.sqrt
import kotlin.math.pow
import kotlin.math.abs
/*
* Лабораторная работа №1
* Задание:
*   Заданы координаты центров и радиусы двух окружностей.
*   Определить, находится ли одна из них целиком внутри другой, пересекаются ли они.
*
* */

fun checkCirclesIf(x1: Double, y1: Double, r1: Double,
                   x2: Double, y2: Double, r2: Double): String {

    val d = sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))

    if (d + minOf(r1, r2) <= maxOf(r1, r2)) {
        return "Одна окружность полностью внутри другой"
    }

    if (abs(r1 - r2) < d && d < r1 + r2) {
        return "Окружности пересекаются"
    }

    return "Окружности не пересекаются"
}
fun checkCirclesWhen(x1: Double, y1: Double, r1: Double,
                     x2: Double, y2: Double, r2: Double): String {

    val d = sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))

    return when {
        d + minOf(r1, r2) <= maxOf(r1, r2) ->
            "Одна окружность полностью внутри другой"

        d in abs(r1 - r2)..(r1 + r2) && d != abs(r1 - r2) && d != r1 + r2 ->
            "Окружности пересекаются"

        else ->
            "Окружности не пересекаются"
    }
}
fun main() {
    println("Введите x1 y1 r1:")
    val input1 = readln().split(" ")

    if (input1.size != 3) {
        println("Ошибка: нужно ввести 3 значения")
        return
    }

    val x1 = input1[0].toDoubleOrNull()
    val y1 = input1[1].toDoubleOrNull()
    val r1 = input1[2].toDoubleOrNull()

    if (x1 == null || y1 == null || r1 == null) {
        println("Ошибка: введены некорректные числа")
        return
    }

    println("Введите x2 y2 r2:")
    val input2 = readln().split(" ")

    if (input2.size != 3) {
        println("Ошибка: нужно ввести 3 значения")
        return
    }

    val x2 = input2[0].toDoubleOrNull()
    val y2 = input2[1].toDoubleOrNull()
    val r2 = input2[2].toDoubleOrNull()

    if (x2 == null || y2 == null || r2 == null) {
        println("Ошибка: введены некорректные числа")
        return
    }

    println("Решение через if:")
    println(checkCirclesIf(x1, y1, r1, x2, y2, r2))

    println("Решение через when:")
    println(checkCirclesWhen(x1, y1, r1, x2, y2, r2))
}