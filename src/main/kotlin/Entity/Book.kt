package Entity

class Book(){
    var name: String = ""
    var count: Int = 0
    var access: Int = 0
    constructor(_name: String, _count:Int) : this(){
        name = _name
        count = _count
        access = count
    }
}