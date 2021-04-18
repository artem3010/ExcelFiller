import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Filler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea text_area_dest;

    @FXML
    private Button start_btn;

    @FXML
    private Button next_btn;




    @FXML
    void initialize() {
        assert text_area_dest != null : "fx:id=\"text_area_dest\" was not injected: check your FXML file 'RootLayout.fxml'.";
        assert start_btn != null : "fx:id=\"start_btn\" was not injected: check your FXML file 'RootLayout.fxml'.";

        start_btn.setOnAction(event -> {
            Filler filler = new Filler(text_area_dest.getText());
            System.out.println("End");
        });
    }

    public void openNewStage(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ImgWork.fxml"));
        AnchorPane page = null;
        try {
            page = (AnchorPane) loader.load();
            Stage img_stage = new Stage();
            Stage stage = (Stage) start_btn.getScene().getWindow();
            stage.close();
            img_stage.setTitle("Img");
            Scene scene = new Scene(page);
            img_stage.setScene(scene);
            img_stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
