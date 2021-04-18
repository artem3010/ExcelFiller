import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import Model.ConverterBufferedImageToWritableImage;
import Model.PDFScanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;

public class ImgViewController {

    Canvas canvas = new Canvas();
    double x1, y1;
    double x2, y2;
    BufferedImage bufferedImage;

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

        for (File f : Model.Filler.getResourceFiles()
        ) {
            bufferedImage = PDFScanner.openAreaSelector(f);
        }

        WritableImage  image = ConverterBufferedImageToWritableImage.convert(bufferedImage);
        img_view_pdf.setImage(image);

    }

    public void nextImg(ActionEvent actionEvent) {
    }

    public void setFirstPoint(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
    }


    public void setSecondPoint(MouseEvent mouseEvent) {
        x2 = mouseEvent.getX();
        y2 = mouseEvent.getY();
        canvas.setHeight(mouseEvent.getY() - canvas.getLayoutY());
        canvas.setWidth(mouseEvent.getX() - canvas.getLayoutX());
        System.out.println(x1);
        System.out.println(y1);
        System.out.println(x2);
        System.out.println(y2);
        bufferedImage = bufferedImage.getSubimage((int) x1, (int) y1, (int) (x2 - x1), (int) (y2 - y1));

        PixelReader reader = img_view_pdf.getImage().getPixelReader();
        WritableImage newImage = new WritableImage(reader, (int) x1, (int) y1, (int) (x2 - x1), (int) (y2 - y1));
        WritableImage wrImg = img_view_pdf.snapshot(new SnapshotParameters(), newImage);
        img_view_area.setImage(newImage);
    }
}
