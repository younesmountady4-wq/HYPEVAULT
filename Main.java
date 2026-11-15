import java.util.Scanner;

public class HypeVault {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Cambia in "false" se il prodotto torna disponibile
        boolean felpaVenduta = true; 

        System.out.println("=== CATALOGO HYPEVAULT ===");
        
        if (felpaVenduta) {
            System.out.println("Felpa Oversize HypeVault -> [SOLD OUT ❌]");
            System.out.println("Avviso: Questo articolo è stato venduto! Tornerà disponibile tra poco (Restock in arrivo).");
        } else {
            System.out.println("Felpa Oversize HypeVault -> 120€ [DISPONIBILE ✅]");
        }
        
        scanner.close();
    }
}