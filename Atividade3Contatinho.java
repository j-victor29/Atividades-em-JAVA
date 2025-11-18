import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Atividade3Contatinho {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();

        int opc;
        do {
            System.out.println("\n====== AGENDA ======");
            System.out.println("1 - Adicionar Contatinho");
            System.out.println("2 - Salvar lista");
            System.out.println("3 - Buscar Contatinho por nome");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opc = Integer.parseInt(scanner.nextLine());

            switch (opc) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine().trim();

                    System.out.print("Email: ");
                    String email = scanner.nextLine().trim();

                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine().trim();

                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine().trim();

                    agenda.addContatinho(new Contatinho(nome, email, telefone, categoria));
                    System.out.println("Contatinho adicionado!");
                    break;

                case 2:
                    if (agenda.salvarLista("contatinhos.txt")) {
                        System.out.println("Lista salva com sucesso!");
                    } else {
                        System.out.println("Erro ao salvar.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome para busca: ");
                    String busca = scanner.nextLine().trim();

                    Contatinho encontrado = agenda.buscarContatinhoPorNome(busca);

                    if (encontrado != null) {
                        System.out.println("Contatinho encontrado:");
                        System.out.println(encontrado.toLine());
                    } else {
                        System.out.println("Contatinho não encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opc != 0);

        scanner.close();
    }
}

class Contatinho {
    private String nome;
    private String email;
    private String telefone;
    private String categoria;

    public Contatinho(String nome, String email, String telefone, String categoria) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public String toLine() {
        return String.join("#", nome, email, telefone, categoria);
    }
}

class Agenda {
    private List<Contatinho> lista = new ArrayList<>();

    public void addContatinho(Contatinho c) {
        lista.add(c);
    }

    public void ordenarLista() {
        Collections.sort(lista, Comparator.comparing(c -> c.getNome().toLowerCase()));
    }

    public boolean salvarLista(String nomeArquivo) {
        ordenarLista();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Contatinho c : lista) {
                writer.write(c.toLine());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
            return false;
        }
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public Contatinho buscarContatinhoPorNome(String nomeProcurado) {
        try (Scanner scanner = new Scanner(new File("contatinhos.txt"))) {

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split("#");

                if (dados.length == 4 && dados[0].equalsIgnoreCase(nomeProcurado)) {
                    return new Contatinho(dados[0], dados[1], dados[2], dados[3]);
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }

        return null;
    }
}
