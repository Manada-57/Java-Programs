import java.util.*;
class Book {
    String title;
    String author;
    String ISBN;
    double price;
    public Book(String title, String author, String ISBN, double price) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.price = price;
    }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Book Title: " + title + ", Author: " + author + ", ISBN: " + ISBN + ", Price: $" + price;
    }
}

class BookManager {
    private Vector<Book> books;
    public BookManager() {
        books = new Vector<>();
    }
    public void insertBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }
    public void deleteBook(String ISBN) {
        Book bookToDelete = findBookByISBN(ISBN);
        if (bookToDelete != null) {
            books.remove(bookToDelete);
            System.out.println("Book deleted: " + bookToDelete);
        } else {
            System.out.println("Book with ISBN " + ISBN + " not found.");
        }
    }
    public void updateBook(String ISBN) {
        Book bookToUpdate = findBookByISBN(ISBN);
        if (bookToUpdate != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter new title: ");
            bookToUpdate.setTitle(scanner.nextLine());

            System.out.print("Enter new author: ");
            bookToUpdate.setAuthor(scanner.nextLine());

            System.out.print("Enter new price: ");
            bookToUpdate.setPrice(scanner.nextDouble());

            System.out.println("Book updated: " + bookToUpdate);
        } else {
            System.out.println("Book with ISBN " + ISBN + " not found.");
        }
    }
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books to display.");
        } else {
            System.out.println("\nDisplaying all books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
    private Book findBookByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }
}

public class Bookapp {
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("\nMenu:\n1. Insert Book\n2. Delete Book by ISBN\n3. Update Book by ISBN\n4. Display All Books\n5. Exit");
        while(true){
         System.out.print("Enter your choice: ");
         choice = scanner.nextInt();
         scanner.nextLine();  
         switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String ISBN = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    Book newBook = new Book(title, author, ISBN, price);
                    manager.insertBook(newBook);
                    break;
                case 2:
                    System.out.print("Enter ISBN of the book to delete: ");
                    String isbnToDelete = scanner.nextLine();
                    manager.deleteBook(isbnToDelete);
                    break;
                case 3:
                    System.out.print("Enter ISBN of the book to update: ");
                    String isbnToUpdate = scanner.nextLine();
                    manager.updateBook(isbnToUpdate);
                    break;
                case 4:
                    manager.displayBooks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;  
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
         }
        }
    
    }
}

