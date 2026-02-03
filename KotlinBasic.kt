/*
    Основы программирования на Kotlin
*/

// Переменные
var variable_1 = 1 // Объявление новой переменной
val variable_2 = 2 // Объявление новой неизменяемой переменной
var variable_3 = 3.4
var variable_4 = "Hello!"
var variable_5: Int = 5 // Объявление новой переменной с указанием её типа данных

// Типы данных
// Числовые типы данных
var variable_int: Int = -2147483647 // Целочисленный тип данных (со знаком), занимает 4 байта (32 бита)
var variable_long: Long = 9_223_372_036_854_775_807 // Целочисленный тип данных (со знаком), занимает 8 байт (64 бита)
var variable_short: Short = -32767 // Целочисленный тип данных (со знаком), занимает 2 байта (16 бит)
var variable_byte: Byte = 127 // Целочисленный тип данных (со знаком), занимает 1 байта (8 бита)

var variable_uint: UInt = 4294967295U // Целочисленный тип данных (без знака), занимает 4 байта (32 бита)
var variable_ulong: ULong = 18446744073709551615U// Целочисленный тип данных (без знака), занимает 8 байт (64 бита)
var variable_ushort: UShort = 65535u // Целочисленный тип данных (без знака, занимает 2 байта (16 бит)
var variable_ubyte: UByte = 255u // Целочисленный тип данных (без знака), занимает 1 байта (8 бита)

var variable_float: Float = 2.5f // Тип числа с плавающей точкой (дробное число), занимает 4 байта (32 бита)
var variable_double: Double = 2.5 // Тип числа с плавающей точкой (дробное число), занимает 8 байт (64 бита)

// Символьный тип данных
var variable_char: Char = 'a' // Символьный тип данных. Хранит один отдельный символ.
var variable_char2: Char = '@'
var variable_char3: Char = '\u0061'

// Строчный тип данных
var variable_string: String = "Hello Kotlin!"
var variable_string2: String = "I'm Steve!"
var variable_string3: String = "Магазин \"Подсолнух\""
var variable_multistring: String = """Я помню чудное мгновенье:
Передо мной явилась ты,
Как мимолетное виденье,
Как гений чистой красоты."""
var variable_multistring2: String = "В томленьях грусти безнадежной,\nВ тревогах шумной суеты,\nЗвучал мне долго голос нежный\nИ снились милые черты."

// Логический тип данных
var variable_true: Boolean = true
var variable_false: Boolean = false
var variable_boolean: Boolean = 5 == 5

// Неопределённый тип данных
var variable: Any = 0

fun demo1(){
    println("Hello") // Вывод данных в консоль с последующим переносом строки
    print("Kotlin") // Вывод данных в консоль без последующего переноса строки
    println("Have a nice day!")

    variable_1 = 2
    println(variable_1)
    println(variable_2)
    variable_4 = "Hello Kotlin!"
    println(variable_4)

    println(variable_long)

    println(variable_char)
    println(variable_char3)

    println(variable_string)
    println(variable_string2)
    println(variable_string3)
    println(variable_multistring)
    println(variable_multistring2)

    println(variable_true)
    println(variable_false)
    println(variable_boolean)

    variable = "Привет"
}

// Ввод и вывод данных
fun inputOutput() {
    // $ - используется для встраивания значений в строку
    var name = "Андрей"
    var age = 27
    println("Имя: $name, Возраст: $age") // Шаблон строки с выводом переменных

    // Ввод данных с помощью консоли - функция readln()
    var login: String = readln()
    var code: Int = readln().toInt()

    println("Ваш логин: $login")
}

// Арифметические операторы в Kotlin
fun arithmetic(){
    var x = 5
    var y = 6
    var z: Any

    z = x + y // + (сложение): возвращает сумму двух чисел
    println(z)

    z = x - y // - (вычитание): возвращает разность двух чисел
    println(z)

    z = x * y // * (умножение): возвращает произведение двух чисел
    println(z)

    z = x / y // / (деление): возвращает частное двух чисел. Целочисленный результат при целочисленных исходных
    println(z)

    var v = 5.0f
    var w = 6.0
    z = v / w // / (деление): возвращает частное двух чисел. Дробный результат при исходных числах с плавающей точкой
    println(z)

    x = 10
    y = 7
    z = x % y // % (деление с остатком): возвращает остаток от целочисленного деления
    println(z)

    var a = 1.1
    a++ // ++ (инкремент): увеличение значения переменной на единицу
    println(a)
    a-- // -- (декремент): уменьшение значения переменной на единицу
    println(a)

    var i = 0
    var j = 0
    println("i = ${i++}, j = ${j++}") // Возвращает результат до увеличения значения
    println("i = ${++i}, j = ${++j}") // Возвращает результат после увеличения значения
    println("i = $i, j = $j")

    x = 5
    y = 6
    x += y // присваивание со сложением, аналогично x = x + y
    println(x)
    x -= y // присваивание с вычитанием, аналогично x = x - y
    println(x)
    x *= y // присваивание с умножением, аналогично x = x * y
    println(x)
    x /= y // присваивание с делением, аналогично x = x / y
    println(x)
}

// Функция main() - точка запуска программы на Kotlin
fun main(){
    arithmetic()

    // Операции сравнения. Операторы сравнения.
    var x = 5
    var y = 6
    println(x > y) // > (больше, чем): возвращает true, если первое значение больше второго значения, иначе возвращает false
    println(x < y) // < (меньше, чем): возвращает true, если первое значение меньше второго значения, иначе возвращает false
    println(x == y) // == (равно): возвращает true, если значения равны между собой, иначе false.
    println(x >= y) // >= (больше или равно): возвращает true, если первое значение больше или равно второму значению, иначе возвращает false
    println(x <= y) // <= (меньше или равно): возвращает true, если первое значение меньше или равно второму значению, иначе возвращает false
    println(x != y) // != (не равно): возвращает true, если значения не равны между собой, иначе false

    println("--------------------------------")

    // Логические операции. Логические операторы
    var t = 5 < 6
    var f = 12 > 15
    println(t and f) // and (И): возвращает true, если оба логических значения равны true, иначе false
    println(t or f) // or (ИЛИ): возвращает true, если хотя бы одно логическое значение равно true, иначе false
    println(t xor f) // xor (исключающее ИЛИ): возвращает true, если только одно логическое значение равно true, иначе false
    println(!t) // ! (НЕ): заменяет логическое значение на противоположное - true на false, false на true

    var var_range = 1..10
    var find = 10
    print(find in var_range) // in (наличие в последовательности): возвращает true если значение находится в последовательности, иначе false
}
