import Entity.Book
import Entity.Person
import Service.Service

fun main(args: Array<String>) {
    val books: MutableList<Book> = mutableListOf()
    val persons: MutableList<Person> = mutableListOf()
    Start(books, persons)

}

fun Start(books: MutableList<Book>, persons: MutableList<Person>){
    println("Команды: \n 1. Добавить книгу \n 2. Добавить читателя \n 3. Найти книгу \n 4. Выдать книгу \n 5. Вернуть книгу \n 6. Показать все книги \n 7. Выход \n Что хочите?");
    var action = 0
    val input =  readln()
    if (input.isNotEmpty()) {
        action = input.toInt()
    }


    when (action) {
        1 -> Service.AddBook(books);
        2 -> Service.AddPerson(persons)
        3 -> Service.SearchBook(books)
        4 -> Service.GiveBook(books,persons)
        5 -> Service.ReturnBook(books,persons)
        6 -> Service.ShowAllBook(books)
        7 -> return
        else -> {
            Service.clearConsole()
            print("Ты.. кажется.. перепутал............ \n")
            Start(books,persons)
        }
    }

    Start(books, persons)
}

