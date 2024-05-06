import java.util.Scanner;
import java.io.File;
import aed3.*;

public class Main {

    // Método principal apenas para testes
    public static void main(String[] args) {

        ArquivoLivro arquivoLivro;
        Scanner console = new Scanner(System.in);

        try {
            File d = new File("dados");
            if (!d.exists())
                d.mkdir();
            arquivoLivro = new ArquivoLivro(4, "dados/dicionario.listainv.db", "dados/blocos.listainv.db");

            int opcao;
            do {
                System.out.println("\n\n-------------------------------");
                System.out.println("              MENU");
                System.out.println("-------------------------------");
                System.out.println("1 - Inserir");
                System.out.println("2 - Buscar");
                System.out.println("3 - Excluir");
                System.out.println("0 - Sair");
                try {
                    opcao = Integer.valueOf(console.nextLine());
                } catch (NumberFormatException e) {
                    opcao = -1;
                }

                switch (opcao) {
                    case 1: {
                        System.out.println("\nINCLUSÃO");
                        System.out.print("Título: ");
                        String titulo = console.nextLine();
                        System.out.print("Autor: ");
                        String autor = console.nextLine();
                        System.out.print("Ano: ");
                        int ano = Integer.valueOf(console.nextLine());
                        arquivoLivro.adicionarLivro(titulo, autor, ano);
                    }
                    break;
                    case 2: {
                        System.out.println("\nBUSCA");
                        System.out.print("Termos de busca: ");
                        String termos = console.nextLine();
                        int[] anosEncontrados = arquivoLivro.buscarLivros(termos);
                        System.out.print("Anos encontrados: ");
                        for (int i = 0; i < anosEncontrados.length; i++)
                            System.out.print(anosEncontrados[i] + " ");
                    }
                    break;
                    case 3: {
                        System.out.println("\nEXCLUSÃO");
                        System.out.print("Título: ");
                        String titulo = console.nextLine();
                        System.out.print("Autor: ");
                        String autor = console.nextLine();
                        System.out.print("Ano: ");
                        int ano = Integer.valueOf(console.nextLine());
                        arquivoLivro.excluirLivro(titulo, autor, ano);
                    }
                    break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } while (opcao != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        console.close();
    }
}
