package backend;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DBInitializer.init();

        LibraryService service =
                new LibraryService(new SQLiteBookRepository());

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n📚 LIBRARY SYSTEM");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Delete Book");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {

                    case 1 -> {
                        System.out.print("Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Author: ");
                        String author = scanner.nextLine();

                        service.addBook(new Book(title, author));
                        System.out.println("✅ Book added!");
                    }

                    case 2 -> {
                        System.out.println("\n📖 BOOK LIST:");
                        for (Book b : service.getAllBooks()) {
                            System.out.println(
                                    b.getId() + " | " +
                                            b.getTitle() + " | " +
                                            b.getAuthor() + " | " +
                                            (b.isBorrowed() ? "❌ Borrowed" : "✅ Available")
                            );
                        }
                    }

                    case 3 -> {
                        System.out.print("Book ID to borrow: ");
                        int id = scanner.nextInt();

                        service.borrowBook(id);
                        System.out.println("📕 Book borrowed!");
                    }

                    case 4 -> {
                        System.out.print("Book ID to return: ");
                        int id = scanner.nextInt();

                        service.returnBook(id);
                        System.out.println("📗 Book returned!");
                    }

                    case 5 -> {
                        System.out.print("Book ID to delete: ");
                        int id = scanner.nextInt();

                        service.deleteBook(id);
                        System.out.println("🗑 Book deleted!");
                    }

                    case 0 -> {
                        System.out.println("👋 Exiting...");
                        return;
                    }

                    default -> System.out.println("❌ Invalid choice");
                }

            } catch (Exception e) {
                System.out.println("⚠ Error: " + e.getMessage());
            }
        }
    }
}
