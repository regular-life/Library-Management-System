# Library Management System

## Introduction
This is a simple readme document to help you understand the Library Management System implemented in Java using Object-Oriented Programming (OOP) principles. The application allows users to interact with a library system as either a librarian or a member. It was developed using IntelliJ IDEA and Maven.

## Table of Contents
1. [How to Run the Application](#how-to-run-the-application)
2. [Features](#features)
3. [User Guide](#user-guide)
4. [Code Structure](#code-structure)
5. [Contributors](#contributors)

## How to Run the Application
1. **Prerequisites:**
   - Java Development Kit (JDK) installed on your computer.
   - IntelliJ IDEA with Maven.

2. **Cloning the Repository:**
   - Clone the repository to your local machine using the following command:
     ```bash
     git clone https://github.com/regular-life/Library-Management-System.git
     ```
     **OR**
   - Download the .zip file from https://github.com/regular-life/Library-Management-System.

3. **Opening the Project in IntelliJ:**
   - Open IntelliJ IDEA.
   - Click on "File" -> "Open" and select the project directory you just cloned.

4. **Running the Application:**
   - Locate the `Main.java` file in the project.
   - Right-click on `Main.java` and select `Run Main.main()`.

5. **Using the Application:**
   - Follow the on-screen instructions to use the Library Management System as either a librarian or a member.

## Features
Here are the key features of the Library Management System:

1. **Add Book:**
   - Allows librarians to add a new book to the library.
   - Takes input for book details (title, author, total copies).

2. **Remove Book:**
   - Allows librarians to remove a book from the library.
   - Takes input for book ID.

3. **Register Member:**
   - Allows librarians to add a new member to the library.
   - Takes input for member details (name, age, phone number).

4. **Remove Member:**
   - Allows librarians to remove a member from the library.
   - Takes input for member ID.

5. **Enter as a Member:**
   - Takes the member's name and phone number as input.
   - Logs in as the particular member, if already registered.

6. **Issue Book:**
   - Allows members to borrow a book from the library, given their penalty amount is zero.
   - Displays available books and takes input for book ID.

7. **Return Book:**
   - Allows members to return a borrowed book.
   - Displays borrowed books and takes input for book ID.

8. **List Books:**
   - Displays a list of all available books in the library.

9. **List Members:**
   - Displays a list of all registered members of the library.

10. **Calculate Fine:**
    - Calculates and displays the fine amount for a book if it's returned after the due date (10 days).

11. **Exit:**
    - Terminates the application.


## User Guide
1. **Librarian Interface:**
   - To use the librarian interface, choose "Enter as a librarian" from the main menu.
   - Follow the prompts to perform librarian-specific actions.

2. **Member Interface:**
   - To use the member interface, choose "Enter as a member" from the main menu.
   - Provide your name and phone number to log in as a member.
   - Follow the prompts to perform member-specific actions.

3. **General Instructions:**
   - Input validation is implemented to handle incorrect or invalid user inputs.
   - Proper error messages are displayed to guide the user.

## Code Structure
The code is structured into two main classes: `Book` and `Member`. These classes represent the entities within the library system. The `Main` class contains the main program logic and user interface.

### Class: Book

   1. **Attributes:**
      - `title` (String): The title of the book.
      - `author` (String): The author of the book.
      - `book_id` (int): An identifier for the book.
      - `status` (int): Represents the status of the book (0 for available, current time(in seconds) for issued books).
  2. **Constructor:**
     The Book class has a constructor that initializes the book's attributes when a new book object is created.
      ```cpp
          Book(String title, String author, int book_id)
          {
              this.title = title ;
              this.author = author ;
              this.book_id = book_id ;
              this.status = 0 ;
          }
      ```

### Class: Member
  1. **Attributes:**
     - `name` (String): The name of the member.
     - `phone` (String): The phone number of the member.
     - `member_id` (int): An identifier for the member.
     - `fine` (int): Represents the fine amount associated with the member. Fine of ₹ 3/day is charged for every day after 10 days. (NOTE: The program is made so that 1s in real world equals 1 day for the program.)
     - `age` (int): The age of the member.
     - `books_issued_id` (Vector<Book>): A collection of books issued by the member.
  2. **Constructor:**
     The Member class has a constructor that initializes the member's attributes when a new member object is created.
     ```cpp
     Member(String name, String phone, int member_id, int age)
     {
         this.name = name ;
         this.phone = phone ;
         this.member_id = member_id ;
         this.books_issued_id = new Vector<Book>() ;
         this.fine = 0 ;
         this.age = age ;
     }
     ```
  3. **Methods:**
     The Member class contains several methods to interact with the library system:
     - `issueBook(Vector<Book> List_Books)`: Allows a member to issue a book if they meet certain conditions (no outstanding fine and number of books already issued is not more than one).
     - `returnBook(Vector<Book> List_Books)`: Allows a member to return a book and calculate fines if it's returned after the due date.
     - `listMyBooks()`: Lists the books issued by the member.
     - `payFine()`: Allows the member to pay any outstanding fines.

### Class: Main
  1. **Main Method:**

     - The main method is the entry point of the program. It starts by initializing various variables, data structures, and flags that will be used throughout the program.
     - `flag` is used to determine the user's role (librarian or member) or if they want to exit the program.
     - `Vector<Book> List_Books` stores information about books using a dynamic array-like structure.
     - `HashMap<Integer, Member> List_Members` stores information about members using a key-value pair structure.
     - `curr` is an object of the `Member` class representing the current user (initialized as an empty member).
     - `m_to_khaali_hu_bhai` is another `Member` object used as a placeholder. It is always an empty member and is never changed.
     - `books_count` and `members_count` keep track of the total number of books and members.
     - `prev_flag` is used to remember the previous user role or state. This is helpful for user login and remembering the previous state.

  2. **Main Menu Loop:**

     - The program enters an infinite loop controlled by the `while (true)` statement, which serves as the main menu.
     - If `flag` is `0`, it displays the main menu options, reads the user's choice, and sets flag accordingly.
     - Depending on the user's role (librarian or member), different sub-menus are displayed.

  3. **Librarian Options (flag == 1):**

     - The librarian menu displays options to register members, remove members, add books, remove books, view member information, and view all books.
     - Input validation is implemented using try-catch blocks, ensuring that the user enters valid choices and data. Regex is used to ensure that name, phone number are practically valid.
     - When registering a member or adding a book, data is collected from the user and stored in the respective data structures.

  4. **Member Options (flag == 2):**

     - The member menu allows members to perform actions such as listing available books, listing their own books, issuing books, returning books, paying fines, and going back to the previous menu.
     - Similar to the librarian menu, input validation is applied here to ensure valid choices and data entry. Regex is used to ensure that name, phone number are practically valid.

  5. **Exiting the Program (flag == 3):**

     - If the user selects to exit (flag == 3), the program exits the infinite loop, terminating the application.

  6. **Input Validation:**

     - Input validation is applied throughout the code to ensure that the user enters valid data. For example, when entering member names, ages, and phone numbers, regex is used to validate the format.

  7. **Data Storage:**

     - Information about books and members is stored in the `List_Books vector` and `List_Members hash map`, respectively.
     - Integers `books_count` and `members_count` are used to store the number of books and members, respectively.


## Contributors
- [Yash Bhardwaj](https://linktr.ee/yash_04) - [GitHub Profile](https://github.com/regular-life)

Feel free to reach out to me for any questions or issues related to the project.

If you encounter any issues or have suggestions for improvement, please let me know at yash22586@iiitd.ac.in.
