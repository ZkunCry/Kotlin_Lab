/**
 * Лабораторная работа №2
 * Задано количество единиц в двоичном представлении целого числа.
 * Найти все возможные соответствующие числа с учетом используемого типа данных.
 *
 * В данной лабораторной работе выбор был сделан в сторону битовых операций.
 * Вместо того, чтобы перебирать все числа от 0 до Int.MAX_VALUE и
 * проверять каждое на количество единиц,
 * использован эффективный алгоритм генерации чисел с заданным количеством единиц.
 *
 * */

fun findNumbersWhile(k: Int) {
    if (k <= 0 || k > 31) return

    var n = (1 shl k) - 1 // Сдвиг 1 на k позиций влево

    while (n > 0 && n <= Int.MAX_VALUE) {
        println(n)

        val c = n and -n // Берётся самый младишй байт, равный 1
        val r = n + c   // Добавление полученного бита к числу, сдвигая комбинацию к следущему числу с k единицами

        if (r > Int.MAX_VALUE) break

        n = (((r xor n) shr 2) / c) or r /**
        Перестановка оставшихся единиц справа,
        чтобы сохранить их колличество равным k
         */
    }
}


fun findNumbersFor(k: Int) {
    if (k <= 0 || k > 31) return

    var n = (1 shl k) - 1

    for (i in 0..Int.MAX_VALUE) {
        if (n <= 0 || n > Int.MAX_VALUE) break

        println(n)

        val c = n and -n
        val r = n + c

        if (r > Int.MAX_VALUE) break

        n = (((r xor n) shr 2) / c) or r
    }
}

fun main() {
    var k: Int

    while (true) {
        print("Введите количество единиц в двоичном представлении (1..31): ")
        val input = readln()

        val parsed = input.toIntOrNull()

        if (parsed == null) {
            println("Ошибка: введено не целое число\n")
            continue
        }

        if (parsed <= 0 || parsed > 31) {
            println("Ошибка: число должно быть в диапазоне от 1 до 31\n")
            continue
        }

        k = parsed
        break
    }

    println("\nРешение через while:")
    findNumbersWhile(k)

    println("\nРешение через for:")
    findNumbersFor(k)
}