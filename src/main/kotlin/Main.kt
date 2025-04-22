package org.example

fun main() {
    val fruits = arrayOf<String>("Apple", "Pear")
    val codes = arrayOf<Int>(10, 20)

    val fruitsHashTable = FruitsHashTable()

    // when add fruit, given code 10, then has apple in index 10
    if (fruitsHashTable.addFruit(fruits[0], codes[0]) != 10) throw Exception("Failed")

    // when add fruit, given code 20, then has apple in index 20
    if (fruitsHashTable.addFruit(fruits[1], codes[1]) != 20) throw Exception("Failed")

    // when get fruit, given 10, then return apple
    if(fruitsHashTable.getFruit(10) != "Apple") throw Exception("Failed")

    // when get fruit, given 20, then return pear
    if(fruitsHashTable.getFruit(20) != "Pear") throw Exception("Failed")

}

class FruitsNode(val code: Int, val fruit: String){
    var nextNode: FruitsNode? = null
}

class FruitsHashTable(){
    private val capacity = 100
    private val fruits = Array<FruitsNode?>(capacity) { null }

    fun addFruit(fruit: String, code: Int): Int{
        val hash = code.hashCode()
        val fruitIndex = hash % capacity
        val newFruit = FruitsNode(code, fruit)
        var foundFruit = fruits[fruitIndex]

        if (foundFruit == null) {
            fruits[fruitIndex] = newFruit
        } else {
            newFruit.nextNode = foundFruit
            fruits[fruitIndex] = newFruit
        }
        return fruitIndex
    }

    fun getFruit(code: Int): String?{
        val hash = code.hashCode()
        val fruitIndex = hash % capacity
        var fruit: String? = null
        var foundFruit = fruits[fruitIndex]

        while (foundFruit != null) {
            if (foundFruit.code == code){
                fruit = foundFruit.fruit
                break
            }
            foundFruit = foundFruit.nextNode
        }

        return fruit
    }
}