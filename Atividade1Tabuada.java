import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Atividade1Tabuada {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite um número para gerar a tabuada: ");
        int numero = scanner.nextInt();
        
        String nomeArquivo = "tabuada_" + numero + ".txt";
        
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write("Tabuada do " + numero + "\n");
            writer.write("=".repeat(20) + "\n\n");
            
            for (int i = 1; i <= 10; i++) {
                String linha = numero + " x " + i + " = " + (numero * i) + "\n";
                writer.write(linha);
                System.out.println(linha);
            }
            
            System.out.println("\nTabuada salva em '" + nomeArquivo + "'");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}