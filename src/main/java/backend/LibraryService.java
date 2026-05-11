package backend;

import java.util.List;

public class LibraryService {

    private BookRepository repo;

    public LibraryService(BookRepository repo) {
        this.repo = repo;
    }

    public void addBook(Book book) throws InvalidBookException {
        if (book.getTitle().isBlank() || book.getAuthor().isBlank()) {
            throw new InvalidBookException("Title/Author cannot be empty");
        }
        repo.add(book);
    }

    public void deleteBook(int id) {
        repo.delete(id);
    }

    public void borrowBook(int id) throws BookNotAvailableException {
        for (Book b : repo.findAll()) {
            if (b.getId() == id) {
                if (b.isBorrowed()) {
                    throw new BookNotAvailableException("Book already borrowed");
                }
                repo.updateBorrowStatus(id, true);
                return;
            }
        }
    }

    public void returnBook(int id) {
        repo.updateBorrowStatus(id, false);
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }
}