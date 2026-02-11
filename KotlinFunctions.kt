/* Функции в Kotlin */

fun main() {
   welcome()
   logIn("admin")
   newUser("Мария", 25)
   employee("Andrey", "Veshchev", 27, "High")
   employee("John", "Smith", 19)
   employee(
       education = "Low",
       surname = "Walker",
       name = "Johnny",
       age = 18
   ) // Передача параметров функции с указанием имен параметров
   val c = sum(2, 3)
   println(c)
   println(sum(5, 6))
   println(sum(8, 9))
  println(powerA3(2f))
    println(powerA3(3f))
    println(powerA3(4f))
    println(powerA3(5f))
    println(powerA3(6f))

    employees("Иван", "Андрей", "Олег", "Мария", "Сергей")
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

// vararg - оператор параметра со множеством значений
fun employees(vararg names: String){
    for (i in names) println(i)
}
