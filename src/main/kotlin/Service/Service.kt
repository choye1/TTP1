package Service
import Entity.Book
import Entity.Person

abstract class Service {
    companion object {
        fun clearConsole() {
            try {
                val process = ProcessBuilder("cmd", "/c", "cls").inheritIO().start()
                process.waitFor()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        fun AddBook(books: MutableList<Book>){
            val book = Book()
            println("О, как называется книга?")
            val name = readln()
            if(name.isNotEmpty()){
                book.name = name
            }
            else{
                println("Ошибка, попробуйте заново.")
                return
            }
            println("Сколько экземпляров?")
            val input = readln()
            var count = 0
            if (input.isNotEmpty()){
                count = input.toInt()
            }
            if (count > 0) {
                book.count = count
                book.access = count
            }
            else {
                println("Ошибка, попробуйте заново.")
                return
            }

            books.add(book)
            println("Книга успешно добавлена!")
        }

        fun AddPerson(persons: MutableList<Person>, name: String = ""):Person?{
            if(name!=""){
                val person = Person(name)
                persons.add(person)
                return person
            }

            println("Всегда рады новым пользователям! Как вас зовут?")
            val personName = readln()
            for (person in persons){
                if (person.name == personName){
                    println("Такой пользователь уже существует!!! \n")
                    return null
                }
            }

            persons.add(Person(personName))
            return null
        }

        fun SearchBook(books: MutableList<Book>): Boolean {
            println("Как называется книга?")
            val name = readln()
            for (book in books){
                return if (book.name == name) {
                    println("Книга найдена! Всего их ${book.count}. Доступно: ${book.access}. \n");
                    true
                } else{
                    println("Такой книги нет :( \n")
                    false
                }
            }

            println("Такой книги нет :(")
            return false
        }

        fun GiveBook(books: MutableList<Book>, persons: MutableList<Person>){
            println("Какую книгу хотите взять?")
            val bookName = readln()
            val selectedBook: Book? = searchBook(books,bookName)


            if (selectedBook != null) {
                println("На кого записать?")
            }
            else {
                println("Такой книги у нас пока что нет \n")
                return
            }

            val personName = readln()
            var selectedPerson: Person? = searchPerson(persons, personName)



            if (selectedPerson == null) {
                println("Раньше вас не встречал, давайте запишемся ( ´･･)ﾉ(._.`)")
                selectedPerson = AddPerson(persons, personName)

            }

            if (selectedPerson != null) {
                selectedPerson.books.add(selectedBook)
                selectedBook.access--
                println("Хорошо! На имя ${selectedPerson.name} записана книга ${selectedBook.name}")
            }

            else{
                println("Пороизошла ошибка, не получается добавить книгу в вашу карточку..")
            }


        }

        fun ReturnBook(books: MutableList<Book>, persons: MutableList<Person>){
            println("Как приятно получать книги обратно! Как вас зовут? ")
            val personName = readln()
            println("Секунду, найду вас в своем списке")
            val person = searchPerson(persons, personName)
            if (person == null){
                println("Такой карточки у нас нет T_T ")
                return
            }

            println("Хорошо, нашел. Какую книгу хотите вернуть? На вас записано:")
            for (book in person.books){
                println(" -> ${book.name}")
            }
            val book = searchBook(books, readln())
            if(book == null){
                println("У нас такой книги никогда не было 🤨")
                return
            }

            if(!person.books.contains(book)){
                println("Вы такую книгу не брали у нас!")
                return
            }

            person.books.remove(book)
            book.count++
            println("Успешно! Приходите к нам ещё!")



        }

        fun ShowAllBook(books: MutableList<Book>){
            println("Вот какие книги у нас есть:")
            for(book in books){
                println(book.name)
                return
            }

            println("Пока что у нас нет никаких книг :(")
        }

        fun searchPerson(persons: MutableList<Person>, name:String):Person?{
            for (person in persons){
                if (person.name.contains(name)){
                    return person
                }
            }
            return null
        }

        fun searchBook(books: MutableList<Book>, name: String):Book?{
            for (book in books){
                if (book.name == name && book.count > 0){
                    return book
                }
            }
            return null
        }



    }


}
