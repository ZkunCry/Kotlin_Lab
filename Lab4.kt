import java.util.Scanner

// =========================================================
// 1. ИНТЕРФЕЙСЫ
// =========================================================

// Интерфейс для консольного ввода/вывода 
interface ConsoleIO {
    fun printTransports(transports: List<Transport>)
    fun addTransportFromConsole()
}

// Интерфейс для операций над объектами 
interface TransportOperations {
    fun getSortedBySpeed(): List<Transport>
    fun getSortedByRange(): List<Transport>
    fun search(requiredSeats: Int, requiredCargoKg: Double): List<Transport>
}

// =========================================================
// 2. СИСТЕМА КЛАССОВ И НАСЛЕДОВАНИЕ 
// =========================================================

abstract class Transport(
    val model: String,
    val type: String,
    val fuelConsumption: Double, // Расход топлива (л/100км или л/ч)
    val maxSpeed: Double,        // км/ч
    val maxRange: Double,        // км
    val passengerCapacity: Int,  // Количество мест
    val cargoWeightKg: Double    // Внутреннее хранение в килограммах для точного поиска
) {
    abstract fun getDetailedInfo(): String
}

class Car(
    model: String, type: String, fuelConsumption: Double, maxSpeed: Double, maxRange: Double, passengerCapacity: Int,
    cargoWeightKg: Double, 
    val brand: String,
    val color: String
) : Transport(model, type, fuelConsumption, maxSpeed, maxRange, passengerCapacity, cargoWeightKg) {
    override fun getDetailedInfo(): String {
        return "🚗 Автомобиль [$brand $model, Цвет: $color] Тип: $type | Скорость: $maxSpeed км/ч | Дальность: $maxRange км | Мест: $passengerCapacity | Груз: $cargoWeightKg кг"
    }
}

class Ship(
    model: String, type: String, fuelConsumption: Double, maxSpeed: Double, maxRange: Double, passengerCapacity: Int,
    cargoWeightTons: Double, 
    val name: String,
    val homePort: String,
    val crewCount: Int,
    val engineCount: Int
) : Transport(model, type, fuelConsumption, maxSpeed, maxRange, passengerCapacity, cargoWeightTons * 1000.0) { // Перевод в кг
    override fun getDetailedInfo(): String {
        return "⛴️ Корабль [$name, Порт: $homePort] Модель: $model | Экипаж: $crewCount | Двигателей: $engineCount | Скорость: $maxSpeed км/ч | Дальность: $maxRange км | Мест: $passengerCapacity | Груз: ${cargoWeightKg / 1000} тонн"
    }
}

class Airplane(
    model: String, type: String, fuelConsumption: Double, maxSpeed: Double, maxRange: Double, passengerCapacity: Int,
    cargoWeightTons: Double, 
    val maxAltitude: Double,
    val crewCount: Int,
    val engineCount: Int
) : Transport(model, type, fuelConsumption, maxSpeed, maxRange, passengerCapacity, cargoWeightTons * 1000.0) { 
    override fun getDetailedInfo(): String {
        return "✈️ Самолет [$model] Тип: $type | Высота: $maxAltitude м | Экипаж: $crewCount | Двигателей: $engineCount | Скорость: $maxSpeed км/ч | Дальность: $maxRange км | Мест: $passengerCapacity | Груз: ${cargoWeightKg / 1000} тонн"
    }
}

// =========================================================
// 3. Реализация интерфейсов
// =========================================================

class FleetManager : ConsoleIO, TransportOperations {
    private val fleet = mutableListOf<Transport>()
    private val scanner = Scanner(System.`in`)

    fun loadDummyData() {
        fleet.add(Car("Camry", "Седан", 8.5, 210.0, 700.0, 5, 450.0, "Toyota", "Черный"))
        fleet.add(Airplane("737 MAX", "Пассажирский", 2500.0, 850.0, 6500.0, 180, 5.0, 12000.0, 6, 2))
        fleet.add(Ship("Севморпуть", "Ледокол", 500.0, 40.0, 20000.0, 50, 150.0, "Арктика", "Мурманск", 30, 4))
    }

    override fun printTransports(transports: List<Transport>) {
        if (transports.isEmpty()) {
            println("Список пуст.")
            return
        }
        transports.forEach { println(it.getDetailedInfo()) }
    }

    override fun addTransportFromConsole() {
        println("\n--- Добавление нового транспорта ---")
        println("1. Автомобиль")
        println("2. Самолет")
        println("3. Корабль")
        println("0. Отмена (вернуться в меню)")

        val choice: Int
        while (true) {
            print("Выберите тип (1-3): ")
            val input = scanner.next()
            val parsedChoice = input.toIntOrNull()

            if (parsedChoice == null) {
                println("❌ Ошибка: Введите число.")
            } else if (parsedChoice !in 0..3) {
                println("❌ Ошибка: Выберите вариант из списка (0, 1, 2 или 3).")
            } else {
                choice = parsedChoice
                break
            }
        }
        if (choice == 0) {
            println("Возврат в главное меню...")
            return
        }
        try {
            // Общие свойства
            print("Модель: ")
            val model = scanner.next()
            print("Тип: ")
            val type = scanner.next()
            print("Расход топлива: ")
            val fuel = scanner.nextDouble()
            print("Макс. скорость (км/ч): ")
            val speed = scanner.nextDouble()
            print("Дальность хода (км): ")
            val range = scanner.nextDouble()
            print("Количество мест: ")
            val seats = scanner.nextInt()

            when (choice) {
                1 -> {
                    print("Грузоподъемность (в килограммах): ")
                    val cargoKg = scanner.nextDouble()
                    print("Марка: ")
                    val brand = scanner.next()
                    print("Цвет: ")
                    val color = scanner.next()
                    fleet.add(Car(model, type, fuel, speed, range, seats, cargoKg, brand, color))
                    println("✅ Автомобиль успешно добавлен!\n")
                }
                2 -> {
                    print("Грузоподъемность (в тоннах): ")
                    val cargoTons = scanner.nextDouble()
                    print("Максимальная высота (м): ")
                    val alt = scanner.nextDouble()
                    print("Количество членов экипажа: ")
                    val crew = scanner.nextInt()
                    print("Количество двигателей: ")
                    val engines = scanner.nextInt()
                    fleet.add(Airplane(model, type, fuel, speed, range, seats, cargoTons, alt, crew, engines))
                    println("✅ Самолет успешно добавлен!\n")
                }
                3 -> {
                    print("Грузоподъемность (в тоннах): ")
                    val cargoTons = scanner.nextDouble()
                    print("Название корабля: ")
                    val name = scanner.next()
                    print("Порт приписки: ")
                    val port = scanner.next()
                    print("Количество членов экипажа: ")
                    val crew = scanner.nextInt()
                    print("Количество двигателей: ")
                    val engines = scanner.nextInt()
                    fleet.add(Ship(model, type, fuel, speed, range, seats, cargoTons, name, port, crew, engines))
                    println("✅ Корабль успешно добавлен!\n")
                }
                else -> println("❌ Неизвестный тип транспорта.")
            }
        } catch (e: Exception) {
            println("❌ Ошибка ввода. Проверьте правильность введенных данных.")
            scanner.nextLine() 
        }
    }

    // --- Реализация TransportOperations ---

    override fun getSortedBySpeed(): List<Transport> {
        return fleet.sortedByDescending { it.maxSpeed }
    }

    override fun getSortedByRange(): List<Transport> {
        return fleet.sortedByDescending { it.maxRange }
    }

    override fun search(requiredSeats: Int, requiredCargoKg: Double): List<Transport> {
        return fleet.filter { it.passengerCapacity >= requiredSeats && it.cargoWeightKg >= requiredCargoKg }
    }
}

private fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        val input = readlnOrNull()?.trim() ?: ""
        val value = input.toIntOrNull()
        if (value != null && value >= 0) return value
        println("❌ Ошибка: Введите целое положительное число.")
    }
}

private fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        val input = readlnOrNull()?.trim()?.replace(',', '.') ?: ""
        val value = input.toDoubleOrNull()
        if (value != null && value >= 0.0) return value
        println("❌ Ошибка: Введите положительное число.")
    }
}

fun main() {
    val manager = FleetManager()
    manager.loadDummyData() 
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\n=== ГЛАВНОЕ МЕНЮ ===")
        println("1. Показать весь транспорт")
        println("2. Добавить транспорт")
        println("3. Сортировка по скорости")
        println("4. Сортировка по дальности перемещения")
        println("5. Поиск по местам и грузу")
        println("0. Выход")
        print("Выберите действие: ")

        val input = readln().trim()

        if (input.isEmpty()) {
            println("❌ Ошибка: Ввод не может быть пустым.")
            continue
        }

        val choice = input.toIntOrNull()
        if (choice == null) {
            println("❌ Ошибка: '${input}' не является числом. Введите цифру от 0 до 5.")
            continue
        }

        when (choice) {
            1 -> manager.printTransports(manager.getSortedBySpeed())
            2 -> manager.addTransportFromConsole()
            3 -> {
                println("\n--- Сортировка по скорости ---")
                manager.printTransports(manager.getSortedBySpeed())
            }
            4 -> {
                println("\n--- Сортировка по дальности ---")
                manager.printTransports(manager.getSortedByRange())
            }
            5 -> {
                println("\n--- Поиск ---")
                val seats = readInt("Минимальное количество мест: ")
                val cargo = readDouble("Минимальный вес груза (в кг): ")
                val results = manager.search(seats, cargo)
                println("\nРезультаты поиска:")
                manager.printTransports(results)
            }
            0 -> {
                println("Выход из программы...")
                return
            }
            else -> println("Неверный ввод, попробуйте снова.")
        }
    }
}
