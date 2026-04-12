/*
* Лабораторная работа №1
* Задание:
*   Заданы три вещественных положительных числа.
*   Вывести их строго в порядке возрастания или информацию, какие из них совпадают.
**/

fun solveIf(a: Double, b: Double, c: Double) {

    if (a == b && b == c) {
        println("Все числа совпадают")
    }
    else if (a == b) {
        println("Числа a и b совпадают")
    }
    else if (a == c) {
        println("Числа a и c совпадают")
    }
    else if (b == c) {
        println("Числа b и c совпадают")
    }
    else {
        val min =  minOf(a, b, c)
        val max = maxOf(a, b, c)
        val mid = a + b + c - min - max

        println("$min $mid $max")
    }
}

fun solveWhen(a: Double, b: Double, c: Double) {

    val min = minOf(a, b, c)
    val max = maxOf(a, b, c)
    val mid = a + b + c - min - max

    when {
        a == b && b == c ->
            println("Все числа совпадают")

        a == b ->
            println("Числа a и b совпадают")

        a == c ->
            println("Числа a и c совпадают")

        b == c ->
            println("Числа b и c совпадают")

        else ->
            println("$min $mid $max")
    }
}

fun main() {

    println("Введите три положительных числа:")
    val input = readln().split(" ")

    if (input.size != 3) {
        println("Ошибка: нужно ввести ровно 3 числа")
        return
    }

    val a = input[0].toDoubleOrNull()
    val b = input[1].toDoubleOrNull()
    val c = input[2].toDoubleOrNull()

    if (a == null || b == null || c == null) {
        println("Ошибка: введены некорректные числа")
        return
    }

    if (a <= 0 || b <= 0 || c <= 0) {
        println("Ошибка: числа должны быть положительными")
        return
    }

    println("Решение через if:")
    solveIf(a, b, c)

    println("Решение через when:")
    solveWhen(a, b, c)
}