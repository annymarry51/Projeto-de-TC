package utilities;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    	try {
			String caminho = Arquivo.obterCaminho();
			Automato afn = new Automato(); 
			afn = Arquivo.carregaArquivo(caminho);
			//Conversor conv = new Conversor();
			if(afn == null)
				System.exit(0);
			Equivalencia conv = new Equivalencia(afn);
			conv.gerarAutomato();
			Arquivo.exportarAutomato(conv.getAutomatoGerado(), caminho.replace(".jff", "ConvertidoEmAFD.jff"));
		} catch (IOException | NullPointerException e) {
			System.out.println("Arquivo não carregado: " + e.getMessage());
			e.printStackTrace();
		}
    }
}