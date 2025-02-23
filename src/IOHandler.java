import java.io.*;
import java.util.*;

public class IOHandler {
    private static final Map<Character, String> colors = new HashMap<>();
    private static final String RESET = "\u001B[0m";
    
    static {
        String[] colorCode = {
            "\u001B[38;5;196m", "\u001B[38;5;46m", "\u001B[38;5;226m", 
            "\u001B[38;5;21m", "\u001B[38;5;201m", "\u001B[38;5;51m",
            "\u001B[38;5;208m", "\u001B[38;5;165m", "\u001B[38;5;118m",
            "\u001B[38;5;199m", "\u001B[38;5;111m", "\u001B[38;5;202m",
            "\u001B[38;5;92m", "\u001B[38;5;76m", "\u001B[38;5;209m",
            "\u001B[38;5;135m", "\u001B[38;5;198m", "\u001B[38;5;160m",
            "\u001B[38;5;33m", "\u001B[38;5;154m", "\u001B[38;5;91m",
            "\u001B[38;5;124m", "\u001B[38;5;23m", "\u001B[38;5;166m",
            "\u001B[38;5;57m", "\u001B[38;5;106m"
        };
        
        for (int i = 0; i < 26; i++) {
            colors.put((char)('A' + i), colorCode[i]);
        }
    }

    public static String[] readFile(String file) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error membaca file: " + e.getMessage());
            return null;
        }
        
        if (lines.isEmpty()) {
            System.out.println("File kosong");
            return null;
        }
        
        return lines.toArray(new String[0]);
    }
    
    public static void showSolution(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    String color = colors.getOrDefault(board[i][j], RESET);
                    System.out.print(color + board[i][j] + " " + RESET);
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
    public static void saveSolution(char[][] board, String file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    writer.print(board[i][j] + " ");
                }
                writer.println();
            }
            System.out.println("Solusi berhasil disimpan di " + file);
        } catch (IOException e) {
            System.out.println("Error menyimpan file: " + e.getMessage());
        }
    }
}