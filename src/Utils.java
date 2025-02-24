import java.io.*;

public class Utils{
    public static boolean cekFile(String path){
        File file = new File(path);
        return file.exists() && file.isFile() && path.toLowerCase().endsWith(".txt");
    }
}