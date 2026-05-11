package backend;

import java.util.List;

public interface BookRepository {
    void add(Book book);
    void delete(int id);
    List<Book> findAll();
    void updateBorrowStatus(int id, boolean status);
}