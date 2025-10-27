package IO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for file writing operations with UTF-8 encoding support.
 * Provides abstracted file writing functionality with proper resource management.
 * 
 * Features:
 * - UTF-8 character encoding
 * - Automatic directory creation
 * - Append mode support
 * - Resource cleanup
 * - Exception handling
 * 
 * Usage Example:
 * StreamWriter writer = new StreamWriter("path/to/dir", "filename", ".txt");
 * writer.writeFile("content", false); // false for overwrite, true for append
 * 
 * @author UNDERSTANDON
 * @version 1.0
 */
public class StreamWriter {
    private String dir;
    private String fileName;
    private String fileType;
    

    public StreamWriter(String dir, String fileName, String fileType) {
        this.dir = dir;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void writeFile(String content, boolean append) {
        try {
            Path directory = Paths.get(this.dir);
            Files.createDirectories(directory);
            Path filePath = directory.resolve(this.fileName + this.fileType);

            try (FileOutputStream fos = new FileOutputStream(filePath.toFile(), append);
                 OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
                writer.write(content);
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file", e);
        }
    }
}
