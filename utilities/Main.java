package utilities;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    	Automato a = Arquivo.carregaArquivo("afn.jff");
        System.out.println(a);
    }
}