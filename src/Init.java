import java.util.*;

public class Init{
    public static Object[] initPuzzle(String file){
        String[] input = IOHandler.readFile(file);
        
        if (input == null || input.length < 2){
            System.out.println("File input tidak valid. Minimal harus memiliki 2 baris");
            return null;
        }

        String[] size = input[0].trim().split("\\s+");
        if (size.length != 3) {
            System.out.println("Format dimensi tidak valid. Harus berisi 3 angka");
            return null;
        }
        
        try{
            int rows = Integer.parseInt(size[0]);
            int cols = Integer.parseInt(size[1]);
            int totalPiece = Integer.parseInt(size[2]);
            
            if (rows <= 0 || cols <= 0 || totalPiece <= 0){
                System.out.println("Dimensi dan jumlah piece harus positif");
                return null;
            }
            
            char[][] board = new char[rows][cols];
            for (int i = 0; i < rows; i++){
                Arrays.fill(board[i], '.');
            }
            
            if (!input[1].trim().equals("DEFAULT")){
                System.out.println("Mode tidak valid. Saat ini hanya mendukung mode DEFAULT");
                return null;
            }
            
            char[][][] pieces = new char[totalPiece][][];
            ArrayList<String> tempLine = new ArrayList<>();
            int idxPiece = 0;
            char pieceChar = ' ';

            for (int i = 2; i < input.length; i++){
                String line = input[i];
                
                if (line.isEmpty()) continue;
                
                char first = ' ';
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    if (!Character.isWhitespace(c)) {
                        first = c;
                        break;
                    }
                }
                
                if (first != pieceChar && first != ' '){
                    if (!tempLine.isEmpty()) {
                        pieces[idxPiece] = buatPiece(tempLine);
                        idxPiece++;
                        tempLine.clear();
                    }
                    pieceChar = first;
                }
                
                tempLine.add(line);
            }
            
            if (!tempLine.isEmpty()){
                pieces[idxPiece] = buatPiece(tempLine);
                idxPiece++;
            }
            
            if (idxPiece != totalPiece){
                System.out.println("Jumlah piece tidak sesuai dengan input");
                return null;
            }
            
            return new Object[]{board, pieces};
            
        } catch (NumberFormatException e) {
            System.out.println("Dimensi harus berupa angka");
            return null;
        }
    }
    
    private static char[][] buatPiece(ArrayList<String> lines){
        if (lines.isEmpty()) {
            System.out.println("Piece kosong");
            return null;
        }
        
        int maxLen = 0;
        for (int i = 0; i < lines.size(); i++){
            maxLen = Math.max(maxLen, lines.get(i).length());
        }
        
        char[][] piece = new char[lines.size()][maxLen];
        
        for (int i = 0; i < piece.length; i++) {
            Arrays.fill(piece[i], '.');
        }
        
        for (int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++){
                char c = line.charAt(j);
                piece[i][j] = Character.isWhitespace(c) ? '.' : c;
            }
        }
        
        // System.out.println("Piece dibaca sebagai:\n");
        // for (int i = 0; i < piece.length; i++) {
        //     for (int j = 0; j < piece[i].length; j++) {
        //         System.out.print(piece[i][j]);
        //     }
        //     System.out.println();
        // }
        
        return piece;
    }
}