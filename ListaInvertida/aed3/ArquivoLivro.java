package aed3;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArquivoLivro {
    ListaInvertida listaInvertida;
    Set<String> stopWords; 

    public ArquivoLivro(int n, String nd, String nc) throws Exception {
        listaInvertida = new ListaInvertida(n, nd, nc);
        // Inicialize as stop words
        stopWords = new HashSet<>(Arrays.asList("a", "o", "e", "de", "do", "da", "em", "um", "uma", "para", "com"));
    }

    public boolean adicionarLivro(String titulo, String autor, int ano) throws Exception {
        // Adicionar livro ao arquivo de livro
        // Adicionar os termos do título à lista invertida, ignorando as stop words
        String[] palavras = limparTexto(titulo.split(" "));
        for (String palavra : palavras) {
            if (!stopWords.contains(palavra)) {
                listaInvertida.create(palavra, ano); 
            }
        }

        return true;
    }

    public boolean editarLivro(String tituloAntigo, String novoTitulo, String autor, int novoAno) throws Exception {
        // Editar livro no arquivo de livros
        // Remover os termos do título antigo da lista invertida, ignorando as stop words
        String[] palavrasAntigas = limparTexto(tituloAntigo.split(" "));
        for (String palavra : palavrasAntigas) {
            if (!stopWords.contains(palavra)) {
                listaInvertida.delete(palavra, novoAno); 
            }
        }

        // Adicionar os termos do novo título à lista invertida, ignorando as stop words
        String[] palavrasNovas = limparTexto(novoTitulo.split(" "));
        for (String palavra : palavrasNovas) {
            if (!stopWords.contains(palavra)) {
                listaInvertida.create(palavra, novoAno); 
            }
        }

        return true;
    }

    public boolean excluirLivro(String titulo, String autor, int ano) throws Exception {
        // Excluir livro do arquivo de livros
        // Remover os termo do título da lista invertida, ignorando as stop words
        String[] palavras = limparTexto(titulo.split(" "));
        for (String palavra : palavras) {
            if (!stopWords.contains(palavra)) {
                listaInvertida.delete(palavra, ano);
            }
        }

        return true;
    }

    public int[] buscarLivros(String termos) throws Exception {
        // Limpar e dividir os termos da busca
        String[] palavras = limparTexto(termos.split(" "));

        // Buscr os anos associados aos termos na lista invertida
        int[] anos = null;
        for (String palavra : palavras) {
            if (!stopWords.contains(palavra)) {
                if (anos == null) {
                    anos = listaInvertida.read(palavra);
                } else {
                    anos = intersecao(anos, listaInvertida.read(palavra));
                }
            }
        }

        return anos != null ? anos : new int[0];
    }

    private String[] limparTexto(String[] texto) {
        // Remove acentos e transforma para minúsculas
        for (int i = 0; i < texto.length; i++) {
            texto[i] = Normalizer.normalize(texto[i], Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();
        }
        return texto;
    }

    private int[] intersecao(int[] array1, int[] array2) {
        // Retorna a interseção de dois arrays de inteiros
        ArrayList<Integer> resultado = new ArrayList<>();
        for (int i : array1) {
            for (int j : array2) {
                if (i == j) {
                    resultado.add(i);
                    break;
                }
            }
        }
        int[] intersecao = new int[resultado.size()];
        for (int i = 0; i < resultado.size(); i++) {
            intersecao[i] = resultado.get(i);
        }
        return intersecao;
    }
}
