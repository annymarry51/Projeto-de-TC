package utilities;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    	String caminho = Arquivo.obterCaminho();
    	Automato afn = new Automato(); 
    	afn = Arquivo.carregaArquivo(caminho);
    	Conversor conv = new Conversor();
    	Automato afd = conv.getAFDdoAFN(afn);
    	Arquivo.exportarAutomato(afd, caminho.replace(".jff", "ConvertidoEmAFD.jff"));
    }
}