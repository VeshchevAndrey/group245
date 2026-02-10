// Функция main() - точка запуска программы на Kotlin
fun main(){
    // Последовательности

    // Массивы
    val numbers: Array<Int> // Создание пустого массива
    numbers = arrayOf(1, 3, 5, 7) // Передача набора значений для созданного ранее массива

    val chars: Array<Char> = arrayOf('w', 'a', 's', 'd') // Создание массива с изначальными данными

    val strings = arrayOf("hello", "goodbye", "thanks") // Создание массива с неявной типизацией

    println(strings[0]) // Обращение к первому элементу массива
    println(chars[1]) // Обращение к второму элементу массива
    println("${chars[0]} - up, ${chars[1]} - left, ${chars[2]} - down, ${chars[3]} - right") // Использование элементов массива

    var names = arrayOf("James", "George", "Maria")
    // Перебор элементов массива
    for (i in names){
        println("У нас работает: $i")
    }

    println("Кнопки управления:")
    for (char in chars){
        println("Клавиша $char")
    }

    // Проверка наличия элементов в массиве
    names = arrayOf("Иван", "Георгий", "Мария")
    println("Андрей" in names) // in - оператор проверки наличия элемента в последовательности
    println("Мария" in names)
    println("James" !in names) // !in - оператор проверки отсутствия элемента в последовательности

    // Свойства массива
    println(names.size) // .size - длина массива
    println(names.lastIndex) // .lastIndex - возвращает индекс последнего значения
    println(names.indices) // .indices - возвращает диапазон индексов элемента массива

    // Действия с массивами
    names[0] = "Евгений" // Изменение элемента массива через его индекс
    for (i in names) println("У нас работает: $i")

    // Создание пустого массива определённой длины
    val workers = arrayOfNulls<String>(5)
    workers[2] = "Сергей"
    for (i in workers) println("У нас работает: $i")

    // Диапазоны
    // .. - оператор создания увеличивающегося диапазона
    val range = 1..5 // Создание числового диапазона от 1-ого до 2-ого числа включительно
    println(range)
    for (i in range) println("В диапазоне есть: $i") // Перебор значений диапазона

    val chars_range = 'а'..'я' // Создание символьного диапазона
    for (i in chars_range) print("$i ")

    // downTo - оператор создания уменьшающегося диапазона
    val range_2 = 10 downTo 1
    for (i in range_2) println("В диапазоне есть: $i")

    // until - оператор создания увеличивающегося диапазона, не включающий последнее значение. Аналог - ..<
    var range_3 = 1 until 10
    println(range_3)
    for (i in range_3) println("В диапазоне есть: $i")
}
