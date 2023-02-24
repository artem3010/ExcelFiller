package com.artem3010.excel.filler;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;
public class TextObserver {


    public static String recognize(BufferedImage image) throws TesseractException {

        Tesseract instance = new Tesseract();
        instance.setDatapath("src/main/resources/tessdata");
        instance.setLanguage("rus");


        String result = instance.doOCR(image,null);

        return result;

    }
}
