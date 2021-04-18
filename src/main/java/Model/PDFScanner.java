package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.WritableImage;
import javafx.scene.robot.Robot;
import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PDFScanner {

    public static BufferedImage scan(File file) {
        BufferedImage bimg = null;
        try (PDDocument pdfDocument = Loader.loadPDF(file)
        ) {
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);
            bimg = pdfRenderer.renderImage(0, 1,ImageType.RGB);// renderImageWithDPI(0, 100, ImageType.RGB);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bimg;
    }

    public static WritableImage doScreenshot(double x1, double y1, double x2, double y2){
        return new Robot().getScreenCapture(null, new Rectangle2D(x1, y1, x2-x1, y2-y1));
    }
}
