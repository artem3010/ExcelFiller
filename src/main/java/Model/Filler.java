package Model;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Filler {
    public static String path;
    private Workbook workbook;

    public Filler(String path) {
        try {
            this.path = path;
            workbook = new XSSFWorkbook();
            fill();
            workbook.close();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void fill() throws IOException, URISyntaxException {
        Sheet sheet = workbook.createSheet("Sheet1");
        File[] files = getResourceFiles();
        File folder = new File(path + "\\..");//for relative link
        int count = 0;
        for (int i = 0; i < files.length; i++) {

            Row tempRow = sheet.createRow(i);
            tempRow.createCell(0, CellType.STRING)//Column for counter
                    .setCellValue(++count);

            tempRow.createCell(1, CellType.STRING) // Column for number
                    .setCellValue(StringTransformation.transform(files[i].getName()));

            tempRow.createCell(2, CellType.STRING) // Column for name
                    .setCellValue("Temp");

            addLink(sheet.getRow(i).getCell(2), folder.toURI().relativize(files[i].toURI()).toString());
        }
        System.out.println(path);
        ExcelWriter.write(workbook, path + "\\..\\output.xlsx");
    }

    private void addLink(Cell cell, String address) {
        Hyperlink link = workbook.getCreationHelper().createHyperlink(HyperlinkType.FILE);
        link.setAddress(address);
        cell.setHyperlink(link);
        cell.setCellStyle(getStyle());
    }

    private CellStyle getStyle() {
        Font font = workbook.createFont();
        font.setColor(IndexedColors.BLUE.getIndex());
        font.setUnderline((byte) 1);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public static File[] getResourceFiles() {
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        return arrFiles;
    }
}
