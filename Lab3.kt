/*
* Лабораторная работа №3
* Задан массив целых чисел.
* Удалить повторяющиеся элементы, чтобы любое значение встречалось
* в массиве только один раз.
*
*/

/**
 * Метод 1: Идиоматичный подход (Функциональный стиль)
 * * Обоснование: Использование встроенного метода distinct() — самый лаконичный
 * и читаемый способ в Kotlin. Под капотом он использует LinkedHashSet,
 * что гарантирует сложность O(N) по времени и сохраняет исходный порядок элементов.
 */
fun removeDuplicatesIdiomatic(arr: IntArray): IntArray {
    return arr.distinct().toIntArray()
}

/**
 * Метод 2: Ручной проход с использованием HashSet
 * * Обоснование: Если встроенных методов нет (или стоит задача написать алгоритм вручную),
 * лучше всего использовать изменяемый список MutableList для накопления результата и
 * структуру HashSet для быстрой проверки уникальности (поиск за O(1)).
 * Цикл for здесь эффективнее forEach, так как избегает создания лишних объектов-лямбд.
 */
fun removeDuplicatesManual(arr: IntArray): IntArray {
    val result = mutableListOf<Int>()
    val seen = hashSetOf<Int>()

    for (element in arr) {
        if (seen.add(element)) {
            result.add(element)
        }
    }

    return result.toIntArray()
}

/**
 * Метод 3: Сортировка и удаление in-place (без дополнительных структур данных)
 * * Обоснование: Если сохранение оригинального порядка не требуется, а память сильно ограничена
 * (нельзя выделять O(N) памяти под HashSet), можно использовать сортировку (O(N log N)).
 * После сортировки дубликаты окажутся рядом.
 */
fun removeDuplicatesWithSort(arr: IntArray): IntArray {
    if (arr.isEmpty()) return arr
    val sortedArr = arr.clone()
    sortedArr.sort()

    var uniqueCount = 1


    for (i in 1 until sortedArr.size) {

        if (sortedArr[i] != sortedArr[i - 1]) {
            sortedArr[uniqueCount] = sortedArr[i]
            uniqueCount++
        }
    }

    return sortedArr.copyOf(uniqueCount)
}
fun main() {
    var numbers: List<Int>? = null

    while (numbers == null) {
        print("Введите массив целых чисел: ")
        val input = readln()
        val parts = input.split(" ").filter { it.isNotBlank() }

        if (parts.isEmpty()) {
            println("Вы ничего не ввели. Попробуйте еще раз.")
            continue
        }

        val parsedNumbers = parts.map { it.toIntOrNull() }

        if (parsedNumbers.any { it == null }) {
            println("Ошибка: Вы ввели недопустимые символы. Нужны только целые числа.")
        } else {
            numbers = parsedNumbers.filterNotNull()
        }
    }

    println("Успешно введен массив: $numbers")
    val n = numbers.size
    val inputArray = numbers.toIntArray()
    println("\nИсходный массив: ${numbers.joinToString(", ")}")

    // 1. Идиоматичный подход
    val result1 = removeDuplicatesIdiomatic(inputArray)
    println("Метод 1 (Идиоматичный, сохраняет порядок): ${result1.joinToString(", ")}")

    // 2. Ручной подход с использованием коллекций
    val result2 = removeDuplicatesManual(inputArray)
    println("Метод 2 (С изменяемыми коллекциями, сохраняет порядок): ${result2.joinToString(", ")}")

    // 3. Подход с сортировкой (без выделения O(N) дополнительной памяти под хэш-таблицы)
    val result3 = removeDuplicatesWithSort(inputArray)
    println("Метод 3 (Сортировка in-place, изменяет порядок): ${result3.joinToString(", ")}")
}
