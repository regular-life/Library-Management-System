package library_management;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Member
{
    String name ;
    String phone ;
    int member_id ;
    int fine ;
    int age ;
    Vector<Book> books_issued_id ;

    Member(String name, String phone, int member_id, int age)
    {
        this.name = name ;
        this.phone = phone ;
        this.member_id = member_id ;
        this.books_issued_id = new Vector<Book>() ;
        this.fine = 0 ;
        this.age = age ;
    }

    public void issueBook(Vector<Book> List_Books)
    {
        if (this.fine != 0)
        {
            System.out.println("Clear your fine to issue a book.") ;
            return ;
        }
        if (this.books_issued_id.size() == 2)
        {
            System.out.println("You have already issued 2 books. Return a book to issue another one.") ;
            return ;
        }
        else if (this.books_issued_id.size() == 1)
        {
            long currentTimeMillis = System.currentTimeMillis() ;
            long currentTimeSeconds = currentTimeMillis / 1000 ;

            if (currentTimeSeconds - this.books_issued_id.get(0).status > 10)
            {
                System.out.println("Return the book you have issued first, and clear your fine to issue another book.") ;
                return ;
            }
            else
            {
                Scanner sc = new Scanner(System.in) ;

                System.out.print("Book ID: ") ;
                int book_id ;
                while (true)
                {
                    try
                    {
                        book_id = sc.nextInt() ;
                        break ;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Invalid Book ID. Please enter an integer.") ;
                        sc.nextLine() ;
                    }
                }
                sc.nextLine() ;

                System.out.print("Book Name: ") ;
                String book_name = sc.nextLine() ;

                for (Book listBook : List_Books)
                {
                    if (listBook.book_id == book_id && listBook.title.equals(book_name) && listBook.status == 0)
                    {
                        List_Books.remove(listBook) ;

                        listBook.status = (int) currentTimeSeconds ;
                        this.books_issued_id.add(listBook) ;
                        System.out.println("Book issued successfully!") ;

                        List_Books.add(listBook) ;
                        return ;
                    }
                }
                System.out.println("Book not found!") ;
            }
        }
        else
        {
            long currentTimeMillis = System.currentTimeMillis() ;
            long currentTimeSeconds = currentTimeMillis / 1000 ;

            Scanner sc = new Scanner(System.in) ;

            System.out.print("Book ID: ") ;
            int book_id ;
            while (true)
            {
                try
                {
                    book_id = sc.nextInt() ;
                    break ;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Invalid Book ID. Please enter an integer.") ;
                    sc.nextLine() ;
                }
            }
            sc.nextLine() ;

            System.out.print("Book Name: ") ;
            String book_name = sc.nextLine() ;

            for (Book listBook : List_Books)
            {
                if (listBook.book_id == book_id && listBook.title.equals(book_name) && listBook.status == 0)
                {
                    List_Books.remove(listBook) ;

                    listBook.status = (int) currentTimeSeconds ;
                    this.books_issued_id.add(listBook) ;
                    System.out.println("Book issued successfully!") ;

                    List_Books.add(listBook) ;
                    return ;
                }
            }
            System.out.println("Book not found!") ;
        }
    }

    public void returnBook(Vector<Book> List_Books)
    {
        if (this.books_issued_id.isEmpty())
        {
            System.out.println("You have not issued any books!") ;
            return ;
        }
        else
        {
            Scanner sc = new Scanner(System.in) ;

            System.out.print("Book ID: ") ;
            int book_id ;
            while (true)
            {
                try
                {
                    book_id = sc.nextInt() ;
                    break ;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Invalid choice. Please enter an integer.") ;
                    sc.nextLine() ;
                }
            }
            sc.nextLine() ;

            long currentTimeMillis = System.currentTimeMillis() ;
            long currentTimeSeconds = currentTimeMillis / 1000 ;

            int fl = 0 ;
            Book to_return = new Book("", "", 0) ;

            for (Book is_it : this.books_issued_id)
            {
                if (is_it.book_id == book_id)
                {
                    to_return = is_it ;

                    this.books_issued_id.remove(is_it) ;

                    int time_gap = (int) (currentTimeSeconds - is_it.status) ;
                    if (time_gap > 10)
                    {
                        this.fine = (time_gap - 10) * 3 ;
                        System.out.println("Book returned successfully! " + this.fine + " rupees fine has been charged for a delay of " + (time_gap - 10) + " days.") ;
                    }
                    fl = 1 ;
                    break ;
                }
            }

            if (fl == 0)
            {
                System.out.println("You have not issued this book!") ;
                return ;
            }

            for (Book is_it : List_Books)
            {
                if (is_it == to_return)
                {
                    List_Books.remove(is_it) ;

                    is_it.status = 0 ;
                    List_Books.add(is_it) ;
                    return ;
                }
            }
        }
    }

    public void listMyBooks()
    {
        if (this.books_issued_id.isEmpty())
        {
            System.out.println("You have not issued any books!") ;
            return ;
        }
        else
        {
            System.out.println("Books issued by you are: ") ;
            for (Book is_it : this.books_issued_id)
            {
                System.out.println("Book Title: " + is_it.title) ;
                System.out.println("Book Author: " + is_it.author) ;
                System.out.println("Book ID: " + is_it.book_id) ;
                System.out.println() ;
            }
        }
    }

    public void payFine()
    {
        if (this.fine == 0)
        {
            System.out.println("You have no fine to pay!") ;
        }
        else
        {
            System.out.println("You had a total fine of Rs." + this.fine + " .It has been paid successfully!") ;
            this.fine = 0 ;
        }
    }
}
