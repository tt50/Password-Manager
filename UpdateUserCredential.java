import java.io.IOException;
import java.io.RandomAccessFile;

public class UpdateUserCredential {
    // Replaces line using random access file
    public static boolean replaceLine(String filename, String oldLine, String newLine) throws IOException {
        if (newLine.length() != oldLine.length()) {
            throw new IllegalArgumentException("New line content must be the same length as the old line content");
        }
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
            String line;
            long pointerPosition = 0;

            while ((line = file.readLine()) != null) {
                if (line.equals(oldLine)) {
                    // Move pointer to the start of this line
                    file.seek(pointerPosition);
                    // Overwrite with new line content
                    file.writeBytes(newLine);
                    return true; // old line was replaced with new line
                }
                // Update pointer position to the beginning of the next line
                pointerPosition = file.getFilePointer();
            }
            return false; // old line not found
        }
    }
}
