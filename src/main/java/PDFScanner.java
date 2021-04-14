import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.robot.Robot;
import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.ss.formula.functions.Even;

public class PDFScanner {
    public static String scan(String path) {
        //TODO: method for open PDF and define text in it
        List<File> file = Arrays.asList(Filler.getResourceFiles(path));
        file.forEach(x -> {
            openAreaSelector(x);
        });
        return null;
    }

    public static void openAreaSelector(File file) {
        try (PDDocument pdfDocument = Loader.loadPDF(file)
        ) {
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);
            BufferedImage bimg = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);


    }

    public static WritableImage doScreenshot(double x1, double y1, double x2, double y2){
        return new Robot().getScreenCapture(null, new Rectangle2D(x1, y1, x2-x1, y2-y1));
    }
}
