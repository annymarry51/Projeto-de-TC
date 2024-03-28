package utilities;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    	try {
			String caminho = Arquivo.obterCaminho();
			Automato afn = new Automato(); 
			afn = Arquivo.carregaArquivo(caminho);
			Conversor conv = new Conversor();
			Automato afd = conv.getAFDdoAFN(afn);
			System.out.println(afd);
			Arquivo.exportarAutomato(afd, caminho.replace(".jff", "ConvertidoEmAFD.jff"));
		} catch (IOException | NullPointerException e) {
			System.out.println("Arquivo não carregado: " + e.getMessage());
			e.printStackTrace();
		}
    }
}