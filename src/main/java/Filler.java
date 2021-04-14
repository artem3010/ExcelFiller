import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class Filler {
    private Workbook workbook;

    public Filler(String path) {
        try {
            workbook = WorkbookFactory.create(new File(path + "\\intput.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fill(String path, String dest) throws IOException {
        Sheet sheet = workbook.createSheet("Sheet1");
        File[] files = getResourceFiles(path);
        int count = 0;
        for (int i = 0; i < files.length; i++) {

            Row tempRow = sheet.createRow(i);
            tempRow.createCell(0, CellType.STRING)//Column for counter
                    .setCellValue(++count);

            tempRow.createCell(1, CellType.STRING) // Column for number
                    .setCellValue(StringTransformation.transform(files[i].getName()));

            tempRow.createCell(2, CellType.STRING) // Column for name
                    .setCellValue(PDFScanner.scan(path));


            addLink(sheet.getRow(i).getCell(2), files[i].getCanonicalPath());
        }
        System.out.println(dest);
        ExcelWriter.write(workbook, dest + "\\output.xlsx");


    }

    private void addLink(Cell cell, String address) throws IOException {
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

    public static File[] getResourceFiles(String path) {
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        return arrFiles;
    }
}
