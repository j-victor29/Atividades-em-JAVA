import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Atividade2Tabuada {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite um número para ler a tabuada: ");
        int numero = scanner.nextInt();
        
        String nomeArquivo = "tabuada_" + numero + ".txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            System.out.println("\n--- Conteúdo do arquivo ---\n");
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro: O arquivo '" + nomeArquivo + "' não existe ou não pode ser lido.");
        } finally {
            scanner.close();
        }
    }
}