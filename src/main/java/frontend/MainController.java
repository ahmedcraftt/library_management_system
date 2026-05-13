package frontend;

import backend.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import backend.Book;
import backend.LibraryService;
import javafx.stage.Stage;

import java.util.List;

public class MainController {

    private BookRepository repository;
    private LibraryService service;
    private ObservableList<Book> books;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> statusColumn;

    @FXML
    private TextField searchField;

    public void setRepository(BookRepository repository) {
        this.repository = repository;

    }

    public void setService(LibraryService service) {
        this.service = service;
        System.out.println(service.getAllBooks());
        loadBooks();
    }

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        books = FXCollections.observableArrayList();
        bookTable.setItems(books);
    }

    public void loadBooks() {
        if (service == null) return;
        books.setAll(service.getAllBooks());
    }

    public void onAddBook() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/AddBookView.fxml")
            );

            Parent root = loader.load();
            AddBookController controller = loader.getController();
            controller.setService(service);

            Stage stage = new Stage();
            stage.setTitle("Add Book");
            stage.setScene(new Scene(root));

            stage.showAndWait();

            loadBooks();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDeleteBook() {
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.deleteBook(selected.getId());
            loadBooks();
        }
    }

    public void onBorrowBook() {
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.borrowBook(selected.getId());
            loadBooks();
        }
    }

    public void onReturnBook() {
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.returnBook(selected.getId());
            loadBooks();
        }
    }

    public void onSearch() {
        String keyword = searchField.getText();

        List<Book> filtered = service.getAllBooks()
                .stream()
                .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        books.setAll(filtered);
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
