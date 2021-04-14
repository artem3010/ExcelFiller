import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter {

    public static void write(Workbook workbook, String dest) throws IOException {
        workbook.write(new FileOutputStream(dest));
    }
}