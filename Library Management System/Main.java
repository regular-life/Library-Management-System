package org.example ;

import java.util.* ;
import java.util.regex.* ;
import java.util.Map.* ;

class Book 
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

class Member 
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

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Library Portal Initialized…") ;
        int flag = 0 ;
        Vector<Book> List_Books = new Vector<Book>() ;
        HashMap<Integer, Member> List_Members = new HashMap<Integer, Member>() ;
        Member curr = new Member("", "", 0, 0) ;
        Member m_to_khaali_hu_bhai = new Member("", "", 0, 0) ;
        int books_count = 0 ;
        int members_count = 0 ;
        int prev_flag = 10000 ;
        while (true) 
        {
            if (flag == 0) 
            {
                System.out.println("---------------------------------\n" +
                        "1. Enter as a librarian\n" +
                        "2. Enter as a member\n" +
                        "3. Exit\n" +
                        "---------------------------------") ;
                while (true)
                {
                    try
                    {
                        flag = sc.nextInt() ;
                        sc.nextLine() ;
                        break ;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Invalid option. Please enter an integer.") ;
                        sc.nextLine() ;
                    }
                }
            }
            else if (flag == 1) 
            {
                System.out.println("---------------------------------\n1. Register a member\n" +
                        "2. Remove a member\n" +
                        "3. Add a book\n" +
                        "4. Remove a book\n" +
                        "5. View all members along with their books and fines to be paid\n" +
                        "6. View all books\n" +
                        "7. Back\n---------------------------------") ;

                int librarian_choice ;
                while (true) 
                {
                    try 
                    {
                        librarian_choice = sc.nextInt() ;
                        break ;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid choice. Please enter an integer.") ;
                        sc.nextLine() ; 
                    }
                }
                sc.nextLine() ;
                if (librarian_choice == 1) 
                {
                    Member new_member = new Member("", "", members_count, 0) ;

                    System.out.print("1. Member Name: ") ;
                    int name_valid = 0 ;
                    while (name_valid == 0) 
                    {
                        String name = sc.nextLine() ;
                        String namePattern = "^[a-zA-Z\\s.'-]+$" ;
                        Pattern pattern = Pattern.compile(namePattern) ;
                        Matcher matcher = pattern.matcher(name) ;
                        if (!matcher.matches()) 
                        {
                            System.out.println("Invalid name.") ;
                        }
                        else 
                        {
                            name_valid = 1 ;
                            new_member.name = name ;
                        }
                    }

                    System.out.print("2. Member Age: ") ;
                    int age ;
                    while (true)
                    {
                        try
                        {
                            age = sc.nextInt() ;
                            sc.nextLine() ;
                            break ;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("Invalid integer. Please enter an integer.") ;
                            sc.nextLine() ;
                        }
                    }

                    System.out.print("3. Member Phone: ") ;
                    int phone_valid = 0 ;
                    while (phone_valid == 0) 
                    {
                        String phone = sc.nextLine() ;
                        String phonePattern = "^[0-9]{10}$" ;
                        Pattern pattern = Pattern.compile(phonePattern) ;
                        Matcher matcher = pattern.matcher(phone) ;
                        if (!matcher.matches()) 
                        {
                            System.out.println("Invalid phone number.") ;
                        }
                        else 
                        {
                            phone_valid = 1 ;
                            new_member.phone = phone ;
                        }
                    }

                    members_count++ ;
                    List_Members.put(new_member.member_id, new_member) ;
                    System.out.println("---------------------------------\nMember Successfully Registered with Member ID: " + new_member.member_id + "\n---------------------------------") ;
                }
                else if (librarian_choice == 2) 
                {
                    System.out.print("Member ID: ") ;
                    int member_id = sc.nextInt() ;
                    sc.nextLine() ;

                    int member_exist = 0 ;

                    for (Entry<Integer, Member> entry : List_Members.entrySet()) 
                    {
                        if (entry.getValue().member_id == member_id) 
                        {
                            List_Members.remove(entry.getKey()) ;
                            member_exist = 1 ;
                            break ;
                        }
                    }
                    if (member_exist == 0) 
                    {
                        System.out.println("Member not found!") ;
                    }
                    else 
                    {
                        System.out.println("---------------------------------\nMember with Member ID: " + member_id + " Successfully removed!\n---------------------------------") ;
                    }
                }
                else if (librarian_choice == 3) 
                {
                    Book new_book = new Book("", "", 0) ;

                    System.out.print("1. Book title: ") ;
                    new_book.title = sc.nextLine() ;

                    System.out.print("2. Book author: ") ;
                    new_book.author = sc.nextLine() ;

                    System.out.print("3. Copies: ") ;
                    int copies ;
                    while (true)
                    {
                        try
                        {
                            copies = sc.nextInt() ;
                            sc.nextLine() ;
                            break ;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("Invalid integer. Please enter an integer.") ;
                            sc.nextLine() ;
                        }
                    }
                    for (int i = 0 ; i < copies ; i++) 
                    {
                        Book temp = new Book(new_book.title, new_book.author, books_count) ;
                        List_Books.add(temp) ;
                        books_count++ ;
                    }

                    System.out.println("---------------------------------\n" +
                            "Books Added Successfully!\n" +
                            "---------------------------------") ;
                }
                else if (librarian_choice == 4) 
                {
                    System.out.print("Book ID: ") ;
                    int book_id ;
                    while (true)
                    {
                        try
                        {
                            book_id = sc.nextInt() ;
                            sc.nextLine() ;
                            break ;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("Invalid Book ID. Please enter an integer.") ;
                            sc.nextLine() ;
                        }
                    }

                    System.out.print("Book Name: ") ;
                    String book_name = sc.nextLine() ;

                    int book_exist = 0 ;

                    for (Book listBook : List_Books) 
                    {
                        if (listBook.book_id == book_id && listBook.title.equals(book_name)) 
                        {
                            List_Books.remove(listBook) ;
                            book_exist = 1 ;
                            break ;
                        }
                    }
                    if (book_exist == 0) 
                    {
                        System.out.println("Book not found!") ;
                    }
                    else 
                    {
                        System.out.println("---------------------------------\n" +
                                "Book Removed Successfully!\n" +
                                "---------------------------------") ;
                    }
                }
                else if (librarian_choice == 5) 
                {
                    System.out.println("Members in the library are: ") ;
                    System.out.println() ;
                    for (Entry<Integer, Member> entry : List_Members.entrySet()) 
                    {
                        System.out.println("Member Name: " + entry.getValue().name) ;
                        System.out.println("Member Phone: " + entry.getValue().phone) ;
                        System.out.println("Member ID: " + entry.getValue().member_id) ;
                        int fine = entry.getValue().fine ;
                        for (Book is_it : entry.getValue().books_issued_id) 
                        {
                            long currentTimeMillis = System.currentTimeMillis() ;
                            long currentTimeSeconds = currentTimeMillis / 1000 ;

                            int time_gap = (int) (currentTimeSeconds - is_it.status) ;
                            if (time_gap > 10) 
                            {
                                fine += (time_gap - 10) * 3 ;
                            }
                        }
                        System.out.println("Fine: " + fine) ;
                        System.out.println() ;
                    }
                }
                else if (librarian_choice == 6) 
                {
                    System.out.println("---------------------------------\nBooks in the library are: ") ;
                    for (Book listBook : List_Books) 
                    {
                        System.out.println("Book Title: " + listBook.title) ;
                        System.out.println("Book Author: " + listBook.author) ;
                        System.out.println("Book ID: " + listBook.book_id) ;
                        System.out.println() ;
                    }
                    System.out.println("---------------------------------") ;
                }
                else if (librarian_choice == 7) 
                {
                    flag = 0 ;
                }
                else 
                {
                    System.out.println("Invalid choice!") ;
                }
                prev_flag = 1 ;
            }
            else if (flag == 2) 
            {
                if (prev_flag != 2 || curr == m_to_khaali_hu_bhai) 
                {
                    Member to_find = new Member("", "", 0, 0) ;
                    prev_flag = 2 ;
                    sc.nextLine() ;
                    System.out.print("Member Name: ") ;
                    int name_valid = 0 ;
                    while (name_valid == 0) 
                    {
                        String name = sc.nextLine() ;
                        String namePattern = "^[a-zA-Z\\s.'-]+$" ;
                        Pattern pattern = Pattern.compile(namePattern) ;
                        Matcher matcher = pattern.matcher(name) ;
                        if (!matcher.matches()) 
                        {
                            System.out.println("Invalid name.") ;
                        }
                        else 
                        {
                            name_valid = 1 ;
                            to_find.name = name ;
                        }
                    }

                    System.out.print("Member Phone: ") ;
                    int phone_valid = 0 ;
                    while (phone_valid == 0) 
                    {
                        String phone = sc.nextLine() ;
                        String phonePattern = "^[0-9]{10}$" ;
                        Pattern pattern = Pattern.compile(phonePattern) ;
                        Matcher matcher = pattern.matcher(phone) ;
                        if (!matcher.matches()) 
                        {
                            System.out.println("Invalid phone number.") ;
                        }
                        else 
                        {
                            phone_valid = 1 ;
                            to_find.phone = phone ;
                        }
                    }

                    int member_exist = 0 ;
                    for (Entry<Integer, Member> entry : List_Members.entrySet()) 
                    {
                        if (entry.getValue().name.equals(to_find.name) && entry.getValue().phone.equals(to_find.phone)) 
                        {
                            to_find = entry.getValue() ;
                            member_exist = 1 ;
                            break ;
                        }
                    }
                    if (member_exist == 0) 
                    {
                        System.out.println("Member with Name: " + to_find.name + " and Phone No: " + to_find.phone + " doesn't exist.\n") ;
                        flag = 0 ;
                        continue ;
                    }
                    else 
                    {
                        curr = to_find ;
                        System.out.println("---------------------------------\n" +
                                "1. List Available Books\n" +
                                "2. List My Books\n" +
                                "3. Issue book\n" +
                                "4. Return book\n" +
                                "5. Pay fine\n" +
                                "6. Back\n" +
                                "---------------------------------") ;
                    }
                }
                else 
                {
                    int member_id = curr.member_id ;
                    System.out.println("---------------------------------\n" +
                            "1. List Available Books\n" +
                            "2. List My Books\n" +
                            "3. Issue book\n" +
                            "4. Return book\n" +
                            "5. Pay fine\n" +
                            "6. Back\n" +
                            "---------------------------------") ;
                }
                int member_choice ;
                while (true) 
                {
                    try 
                    {
                        member_choice = sc.nextInt() ;
                        break ;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid choice. Please enter an integer.") ;
                        sc.nextLine() ; 
                    }
                }
                sc.nextLine() ;
                if (member_choice == 1) 
                {
                    System.out.println("---------------------------------\nBooks in the library are: ") ;
                    for (Book listBook : List_Books) 
                    {
                        if (listBook.status == 0) 
                        {
                            System.out.println("Book Title: " + listBook.title) ;
                            System.out.println("Book Author: " + listBook.author) ;
                            System.out.println("Book ID: " + listBook.book_id) ;
                            System.out.println() ;
                        }
                    }
                    System.out.println("---------------------------------") ;
                }
                else if (member_choice == 2) 
                {
                    curr.listMyBooks() ;
                }
                else if (member_choice == 3) 
                {
                    curr.issueBook(List_Books) ;
                }
                else if (member_choice == 4) 
                {
                    curr.returnBook(List_Books) ;
                }
                else if (member_choice == 5) 
                {
                    curr.payFine() ;
                }
                else if (member_choice == 6) 
                {
                    flag = 0 ;
                    curr = m_to_khaali_hu_bhai ;
                    prev_flag = 10000 ;
                }
                else 
                {
                    System.out.println("Invalid choice!") ;
                }
            }
            else if (flag == 3) 
            {
                System.out.println("---------------------------------\n" +
                        "Thanks for visiting!\n" +
                        "---------------------------------");
                break ;
            }
            else 
            {
                System.out.println("Invalid choice!") ;
                flag = 0 ;
            }
        }
    }
}
