package frontend;

import backend.Book;
import backend.LibraryService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {

    private LibraryService service ;
    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    public void setService(LibraryService service) {
        this.service = service;
    }

    @FXML
    public void onAddBook() {

        String title = titleField.getText();
        String author = authorField.getText();


        Book book = new Book(title, author);

        service.addBook(book);

        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
