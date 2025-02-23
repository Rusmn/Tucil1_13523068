import java.util.*;

public class Solver {
    private long count = 0;
    private long solveAt;
    private long startTime;
    
    public char[][] solve(char[][] board, char[][][] pieces) {
        count = 0;
        solveAt = 0;
        
        if (bruteSolve(board, pieces, 0)) {
            return board;
        }
        return new char[0][0];
    }
    
    private boolean bruteSolve(char[][] board, char[][][] pieces, int index) {
        if (index == 0) startTime = System.nanoTime();
        
        if (index == pieces.length) {
            if (checkBoard(board)) {
                solveAt = count;
                long elapsed = System.nanoTime() - startTime;
                System.out.println("Solusi ditemukan setelah " + (elapsed / 1_000_000.0) + " ms\n");
                return true;
            }
            return false;
        }
        
        List<char[][]> transforms = getTransforms(pieces[index]);
        List<int[]> positions = getPositions(board);
        char pieceId = getId(pieces[index]);
        
        for (int i = 0; i < transforms.size(); i++) {
            char[][] trans = transforms.get(i);
            for (int j = 0; j < positions.size(); j++) {
                int[] pos = positions.get(j);
                if (tryPiece(board, pieces, trans, pos[0], pos[1], pieceId, index)) {
                    return true;
                }
            }
        }
        
        count++;
        // System.out.println("Mencoba kombinasi ke-" + count + "\n");
        // printBoard(board);
        return false;
    }
    
    private boolean tryPiece(char[][] board, char[][][] pieces, char[][] piece, int row, int col, char id, int idx) {
        if (canPlace(board, piece, row, col)) {
            putPiece(board, piece, row, col, id);
            if (bruteSolve(board, pieces, idx + 1)) {
                return true;
            }
            removePiece(board, piece, row, col);
        }
        return false;
    }
    
    private boolean canPlace(char[][] board, char[][] piece, int row, int col) {
        if (row + piece.length > board.length || col + piece[0].length > board[0].length) {
            return false;
        }
        
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (piece[i][j] != '.' && board[row + i][col + j] != '.') {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void putPiece(char[][] board, char[][] piece, int row, int col, char id) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (piece[i][j] != '.') {
                    board[row + i][col + j] = id;
                }
            }
        }
    }
    
    private void removePiece(char[][] board, char[][] piece, int row, int col) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (piece[i][j] != '.') {
                    board[row + i][col + j] = '.';
                }
            }
        }
    }
    
    private boolean checkBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') return false;
            }
        }
        return true;
    }
    
    private char getId(char[][] piece) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] != '.') return piece[i][j];
            }
        }
        return '.';
    }
    
    private char[][] rotate(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        char[][] rotated = new char[cols][rows];
        
        for (int i = 0; i < cols; i++) {
            Arrays.fill(rotated[i], '.');
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }
    
    private char[][] flip(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        char[][] flipped = new char[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            Arrays.fill(flipped[i], '.');
            for (int j = 0; j < cols; j++) {
                flipped[i][cols - 1 - j] = matrix[i][j];
            }
        }
        return flipped;
    }
    
    private List<char[][]> getTransforms(char[][] piece) {
        List<char[][]> transforms = new ArrayList<>();
        char[][] current = piece;
        
        for (int i = 0; i < 4; i++) {
            transforms.add(current);
            current = rotate(current);
        }
        
        current = flip(piece);
        for (int i = 0; i < 4; i++) {
            transforms.add(current);
            current = rotate(current);
        }
        
        return transforms;
    }
    
    private List<int[]> getPositions(char[][] board) {
        List<int[]> positions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                positions.add(new int[]{i, j});
            }
        }
        return positions;
    }
    
    public long getSolveAt() {
        return solveAt;
    }
    // private void printBoard(char[][] board) {
    //     for (int i = 0; i < board.length; i++) {
    //         for (int j = 0; j < board[i].length; j++) {
    //             System.out.print(board[i][j] + " ");
    //         }
    //         System.out.println();
    //     }
    //     System.out.println();
    // }
}