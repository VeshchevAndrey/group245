package com.example.application245

object CreaturesRepository {
    val creatures = listOf(
        Creature(
            id = 1,
            name = "Гоблин",
            description = "Маленькое хитрое существо",
            imageRes = R.drawable.goblin_ffvii
        ),
        Creature(
            id = 2,
            name = "Дракон",
            description = "Огнедышащее существо",
            imageRes = R.drawable.dragon_ffvii
        ),
        Creature(
            id = 3,
            name = "Железный человек",
            description = "Гигантское существо",
            imageRes = R.drawable.iron_man_ffvii
        )
    )
    fun getById(id: Int): Creature? {
        return creatures.find{ it.id == id }
    }
}