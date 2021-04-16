import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ImgViewController {

    Canvas canvas = new Canvas();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img_view_pdf;

    @FXML
    private ImageView img_view_area;

    @FXML
    private Button btn_select_area;

    @FXML
    private Button btn_next_img;



    @FXML
    void initialize() {
        assert img_view_pdf != null : "fx:id=\"img_view_pdf\" was not injected: check your FXML file 'ImgWork.fxml'.";
        assert btn_select_area != null : "fx:id=\"btn_select_area\" was not injected: check your FXML file 'ImgWork.fxml'.";
        assert btn_next_img != null : "fx:id=\"btn_next_img\" was not injected: check your FXML file 'ImgWork.fxml'.";
        Image image = new Image("https://im0-tub-ru.yandex.net/i?id=6bd3f7dffd28139269eb2da3aa9e18d7-sr&n=13");

        img_view_pdf.setImage(image);

    }

    public void nextImg(ActionEvent actionEvent) {
    }

    public void setFirstPoint(MouseEvent mouseEvent) {
        for (int i = 0 ; i< 2; i++){
            if(i == 0){
                canvas.setLayoutX(mouseEvent.getX());
                canvas.setLayoutY(mouseEvent.getY());
            } else {
                canvas.setHeight(mouseEvent.getY()-canvas.getLayoutY());
                canvas.setWidth(mouseEvent.getX()-canvas.getLayoutX());
                canvas.getGraphicsContext2D().drawImage(img_view_pdf.getImage(), canvas.getLayoutX()
                        ,canvas.getLayoutY(),canvas.getWidth(),canvas.getHeight());
                WritableImage wrImg = canvas.snapshot(new SnapshotParameters(), null);
                img_view_area.setImage(wrImg);
                i = 0;
            }
        }

    }

    public void setSecondPoint(MouseEvent mouseEvent) {



    }
}
