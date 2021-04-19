package Model;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Filler implements Closeable {
    private static String path;
    private Workbook workbook;
    private Sheet sheet;

    public Filler() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Sheet1");
    }

    public static void setPath(String path) {
        Filler.path = path;
    }

    public void fill(int i,String name) throws IOException, URISyntaxException {

        File[] files = getResourceFiles();
        File folder = new File(path + "\\..");//for relative link
        int count = 0;

            Row tempRow = sheet.createRow(i);
            tempRow.createCell(0, CellType.STRING)//Column for counter
                    .setCellValue(++count);

            tempRow.createCell(1, CellType.STRING) // Column for number
                    .setCellValue(StringTransformation.transform(files[i].getName()));

            tempRow.createCell(2, CellType.STRING) // Column for name
                    .setCellValue(name);

            addLink(sheet.getRow(i).getCell(2), folder.toURI().relativize(files[i].toURI()).toString());

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

    @Override
    public void close() throws IOException {
        workbook.close();
    }
}
