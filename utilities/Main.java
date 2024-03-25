package utilities;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    	Automato a = Arquivo.carregaArquivo("automato2.jff");
        System.out.println(a);
    }
}