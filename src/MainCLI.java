import java.util.*;

public class MainCLI {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("====================================================");
        System.out.println("                 SELAMAT DATANG :))");
        System.out.println("====================================================\n");
        
        try {
            String file;
            do {
                System.out.print("Masukkan nama file input (.txt): ");
                file = scan.nextLine().trim();
                
                if (!Utils.cekFile("test/" + file)) {
                    System.out.println("File tidak ditemukan atau bukan file .txt");
                }
            } while (!Utils.cekFile("test/" + file));
            
            System.out.println("Memulai pencarian solusi...\n");
            
            Object[] dataPuzzle = Init.initPuzzle("test/" + file);
            if (dataPuzzle == null) return;
            
            char[][] board = (char[][]) dataPuzzle[0];
            char[][][] pieces = (char[][][]) dataPuzzle[1];
            
            Solver solver = new Solver();
            char[][] solution = solver.solve(board, pieces);
            
            if (solution.length > 0) {
                System.out.println("Solusi ditemukan pada kombinasi ke-" + (solver.getSolveAt()+1) + ":\n");
                IOHandler.showSolution(solution);
                
                System.out.print("\nApakah anda ingin menyimpan solusi? (Y/N): ");
                String input = scan.nextLine().trim().toUpperCase();
                if (input.equals("Y")) {
                    IOHandler.saveSolution(solution, "test/output_" + file);
                }
            } else {
                System.out.println("Total waktu : " + (solver.getTime()) + "ms\n");
                System.out.println("Jumlah pengujian : " + (solver.getTotalCheck()) + "\n");
                System.out.println("Tidak ditemukan solusi untuk puzzle ini.\n");
            }
        
            System.out.println("====================================================\n");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scan.close();
        }
    }
}