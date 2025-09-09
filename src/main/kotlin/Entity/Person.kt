package Entity

import Entity.Book

class Person(){
    var name: String = ""
    var books: MutableList<Book> = mutableListOf()

    constructor(_name:String) : this() {
        name = _name
    }
}
