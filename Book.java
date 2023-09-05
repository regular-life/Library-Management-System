package library_management;

public class Book
{
    String title ;
    String author ;
    int book_id ;
    int status ;

    Book(String title, String author, int book_id)
    {
        this.title = title ;
        this.author = author ;
        this.book_id = book_id ;
        this.status = 0 ;
    }
}
