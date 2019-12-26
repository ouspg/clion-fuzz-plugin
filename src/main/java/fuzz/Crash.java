package fuzz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Crash {
    private byte[] crashInput;
    public Crash(String filePath){
        this.crashInput = readBytesFromFilePath(filePath);
    };
    private static byte[] readBytesFromFilePath(String filePath) {
        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytesArray;
    }

    public byte[] getCrashInput() {
        return crashInput;
    }
}
