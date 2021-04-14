import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea text_area_dest;

    @FXML
    private TextArea text_area_folder;

    @FXML
    private Button start_btn;

    @FXML
    void initialize() {
        assert text_area_dest != null : "fx:id=\"text_area_dest\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert text_area_folder != null : "fx:id=\"text_area_folder\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert start_btn != null : "fx:id=\"start_btn\" was not injected: check your FXML file 'RootLayout.fxml'.";
        start_btn.setOnAction(event -> {
            Filler filler = new Filler(text_area_dest.getText());
            try {
                filler.fill(text_area_folder.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            PathScanner.close();
        });
    }
}
