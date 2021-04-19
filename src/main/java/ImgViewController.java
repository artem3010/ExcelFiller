import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.ConverterBufferedImageToWritableImage;
import Model.Filler;
import Model.PDFScanner;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import net.sourceforge.tess4j.TesseractException;

public class ImgViewController {
    private double x1, y1, x2, y2;
    private int i = 0;
    boolean flag;
    BufferedImage bufferedImage;
    Filler filler = new Filler();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgViewPdf;

    @FXML
    private TextField textFieldForOCR;


    @FXML
    private Button btnNext;

    @FXML
    private Rectangle snapshotRectangle;


    @FXML
    private ImageView imageViewArea;

    @FXML
    void getFirstCoordinates(MouseEvent event) {
        x1 = event.getX();
        y1 = event.getY();
    }

    @FXML
    void dragArea(MouseEvent event) {
        snapshotRectangle.relocate(event.getSceneX(), event.getSceneY());
    }

    @FXML
    void getSecondCoordinates(MouseEvent event) {

        x2 = event.getX();
        y2 = event.getY();
        System.out.println(x1 + " " + y1 + "\n"
                + x2 + " " + y2);
        System.out.println((int) (x2 - x1) + " " + (int) (y2 - y1));

        BufferedImage tempImage = bufferedImage.getSubimage((int) x1, (int) y1, (int) (x2 - x1), (int) (y2 - y1));

        WritableImage image = ConverterBufferedImageToWritableImage.convert(tempImage);
        try {
            textFieldForOCR.setText(TextObserver.recognize(tempImage));
        } catch (TesseractException e) {
            System.out.println(e.getMessage());
        }

        imageViewArea.setFitHeight(image.getHeight());
        imageViewArea.setFitWidth(image.getWidth());
        imageViewArea.setImage(image);

            snapshotRectangle.relocate(x1, y1);
            snapshotRectangle.setWidth(x2 - x1);
            snapshotRectangle.setHeight(y2 - y1);
            snapshotRectangle.setVisible(true);
            flag = false;



    }

    @FXML
    void nextPdf(ActionEvent event) {
        File[] files = Filler.getResourceFiles();
        try {
            filler.fill(i, textFieldForOCR.getText());
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (i < files.length) {
            bufferedImage = PDFScanner.scan(files[i]);
            WritableImage image = ConverterBufferedImageToWritableImage.convert(bufferedImage);
            imgViewPdf.setFitHeight(image.getHeight());
            imgViewPdf.setFitWidth(image.getWidth());
            imgViewPdf.setImage(image);
            i++;
        }

    }

    @FXML
    void initialize() {
        assert imgViewPdf != null : "fx:id=\"imgViewPdf\" was not injected: check your FXML file 'ImageViewerLayout.fxml'.";
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'ImageViewerLayout.fxml'.";
        assert imageViewArea != null : "fx:id=\"imageViewArea\" was not injected: check your FXML file 'ImageViewerLayout.fxml'.";
        assert textFieldForOCR != null : "fx:id=\"textFieldForOCR\" was not injected: check your FXML file 'ImageViewerLayout.fxml'.";
        assert snapshotRectangle != null : "fx:id=\"snapshotRectangle\" was not injected: check your FXML file 'ImageViewerLayout.fxml'.";
        flag = true;
        bufferedImage = PDFScanner.scan(Filler.getResourceFiles()[0]);

        WritableImage image = ConverterBufferedImageToWritableImage.convert(bufferedImage);
        imgViewPdf.setFitHeight(image.getHeight());
        imgViewPdf.setFitWidth(image.getWidth());
        imgViewPdf.setImage(image);

    }

}
