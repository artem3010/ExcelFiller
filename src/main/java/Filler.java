import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Filler {
    private Workbook workbook;

    public Filler(String path) {
        try {
            workbook = WorkbookFactory.create(new File(path + "\\output.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fill(String path) throws IOException {
        Sheet sheet = workbook.createSheet("Sheet1");
        File[] files = getResourceFiles(path);
        for (int i = 0; i < files.length; i++) {
            sheet.createRow(i).createCell(0, CellType.NUMERIC)//Column for counter
                    .setCellValue(i++);

            sheet.createRow(i).createCell(1,CellType.STRING) // Column for number
                    .setCellValue(StringTransformation.transform(files[i].getName()));

//            sheet.createRow(i).createCell(2,CellType.STRING) // Column for name
//                    .setCellValue(PDFScanner.scan(files[i].getAbsolutePath()));

                addLink(sheet.getRow(i).getCell(2), "input.xls");

        }

        ExcelWriter.write(workbook);


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

    private static File[] getResourceFiles(String path) {
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        return arrFiles;
    }
}
