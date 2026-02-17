/* Функции в Kotlin */

fun main() {
//    welcome()
//    logIn("admin")
//    newUser("Мария", 25)
//    employee("Andrey", "Veshchev", 27, "High")
//    employee("John", "Smith", 19)
//    employee(
//        education = "Low",
//        surname = "Walker",
//        name = "Johnny",
//        age = 18
//    ) // Передача параметров функции с указанием имен параметров
//    val c = sum(2, 3)
//    println(c)
//    println(sum(5, 6))
//    println(sum(8, 9))
    println(powerA3(2f))
    println(powerA3(3f))
    println(powerA3(4f))
    println(powerA3(5f))
    println(powerA3(6f))

    employees("Иван", "Андрей", "Олег", "Мария", "Сергей")

    group("СУ-11", 1, "Андреев", "Иванов", "Сергеев")

    country("Россия", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Казань", capital = "Москва")

    println(mean(2.0, 6.0, 4.0))

    var sqr = sqaure(10)
    println(sqr)

    println(findLetter("я", "яблоко"))

    println(summ(2, 4))

    var result = mathematic(4, 5, fun (x: Int, y: Int) = x + y)
    println(result)
    result = mathematic(4, 5, fun (x: Int, y: Int) = x - y)
    println(result)
    result = mathematic(4, 5, fun (x: Int, y: Int) = x * y)
    println(result)
    result = mathematic(4, 5, fun (x: Int, y: Int) = x / y)
    println(result)

    hello()
    greetings("Andrey")
}

// fun - оператор объявления функции
fun welcome() {
    print("Введите своё имя: ")
    val user = readln()
    println("Добро пожаловать в систему, $user")
}

// Объявление функции с одним параметром
fun logIn(login: String){
    println("$login вошел в систему!")
}

// Объявление функции с несколькими параметрами разных типов
fun newUser(name: String, age: Byte){
    println("Имя: $name, возраст: $age")
}

// Объявление функции с необязательным параметром (параметр имеет изначальное значение)
fun employee(name: String, surname: String, age: Byte, education: String = "Middle"){
    println("Name: $surname $name, age: $age, education: $education")
}

// Объявление функции, возвращающей значение
// return - возвращает значение из функции в программу
fun sum(x: Int, y: Int): Int {
    return x + y
}

// Описать функцию PowerA3(A), вычисляющую третью степень числа A и возвращающую ее. С помощью этой процедуры найти третьи степени пяти данных чисел.

fun powerA3(a: Float): Float {
    return a * a * a
}

// Функции с переменным количеством параметров
// vararg - оператор параметра со множеством значений
fun employees(vararg names: String){
    for (i in names) println(i)
}

fun group(name: String, course: Byte, vararg students: String){
    println("Группа $name, $course курс.\nСтуденты:")
    for (student in students) println(student)
}

fun country(name: String, vararg bigCity: String, capital: String){
    println("Страна: $name. Столица: $capital.\nКрупные города: ")
    for (city in bigCity) println(city)
}

// Функция расчёта среднего значения множества дробных (вещественных) чисел
fun mean(vararg numbers: Double): Double {
    var sum = 0.0
    for (i in numbers) sum += i
    return sum / numbers.size
}

// Однострочные функции
fun sqaure(number: Int) = number * number

fun findLetter(letter: String, word: String) = letter in word

// Анонимная функция
val summ = fun(x: Int, y: Int): Int {
    return x + y
}

fun mathematic(x: Int, y: Int, operation: (Int, Int) -> Int): Int{
    return operation(x, y)
}

// Лямбда-выражения
val hello = { println("Welcome home!") }
val greetings = { name: String -> println("Welcome home, $name!") }
