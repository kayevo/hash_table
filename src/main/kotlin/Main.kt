package org.example

fun main() {
    val names = arrayOf<String>("Alice", "Bob")
    val codes = arrayOf<Int>(10, 20)

    val customerHashTable = HashTable<Int, String>()

    // when add customer, given code 10, then has Alice in index 10
    if (customerHashTable.put(value = names[0], key = codes[0]) != 10) throw Exception("Failed")

    // when add customer, given code 20, then has Alice in index 20
    if (customerHashTable.put(value = names[1], key = codes[1]) != 20) throw Exception("Failed")

    // when get customer, given 10, then return Alice
    if(customerHashTable.get(10) != "Alice") throw Exception("Failed")

    // when get customer, given 20, then return Bob
    if(customerHashTable.get(20) != "Bob") throw Exception("Failed")

}

class ElementNode<K, V>(val key: K, val value: V){
    var nextNode: ElementNode<K, V>? = null
}

class HashTable<K, V>(){
    private val capacity = 100
    private val elements = Array<ElementNode<K, V>?>(capacity) { null }

    // code = key fruit = value
    fun put(key: K, value: V): Int{
        val hash = key.hashCode()
        val index = hash % capacity
        val newElement = ElementNode(key, value)

        if (elements[index] == null) {
            elements[index] = newElement
        } else {
            newElement.nextNode = elements[index]
            elements[index] = newElement
        }
        return index
    }

    fun get(key: K): V?{
        val hash = key.hashCode()
        val index = hash % capacity
        var value: V? = null

        while (elements[index] != null) {
            if (elements[index]?.key == key){
                value = elements[index]?.value
                break
            }
            elements[index] = elements[index]?.nextNode
        }

        return value
    }
}