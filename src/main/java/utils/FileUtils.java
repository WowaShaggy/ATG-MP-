package utils;
import java.io.File;

public class FileUtils {
    public static boolean isFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
