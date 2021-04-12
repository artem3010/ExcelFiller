import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class Filler {
    private Workbook workbook;

    public Filler(String path) {
        try {
            workbook = WorkbookFactory.create(new File(path + "\\output.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fill() {
        Sheet sheet = workbook.createSheet("Sheet1");
        File[] files = getResourceFiles();
        for (int i = 0; i < files.length; i++) {
            sheet.createRow(i).createCell(0, CellType.NUMERIC)//Column for counter
                    .setCellValue(i++);

            sheet.createRow(i).createCell(1,CellType.STRING) // Column for number
                    .setCellValue(StringTransformation.transform(files[i].getName()));

            sheet.createRow(i).createCell(2,CellType.STRING) // Column for name
                    .setCellValue(PDFScanner.scan(files[i].getAbsolutePath()));

            try {
                addLink(sheet.getRow(i).getCell(2), files[i].getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void addLink(Cell cell, String address) throws IOException {
        CreationHelper createHelper = workbook.getCreationHelper();
        Hyperlink link = createHelper.createHyperlink(HyperlinkType.FILE);
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

    private static File[] getResourceFiles() {
        System.out.print("Input pdf files directory:");
        String path = PathScanner.scan();
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();

        return arrFiles;
    }
}
