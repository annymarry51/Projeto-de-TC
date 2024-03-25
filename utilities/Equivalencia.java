package utilities;

import java.util.ArrayList;
//import java.util.Set;

/*public class Equivalencia {
    private ArrayList<ArrayList<Estado>> tabelaTransicao = new ArrayList<>();
    //para guardar os estados finais;
    private ArrayList<Estado> estadosFinais = new ArrayList<>();
    //para a matriz de transicoes;
    private ArrayList<Transicao> aux;
   // private ArrayList<Transicao> aux2;
    private int linha, coluna, idAux = 0;

    //para conferir as transições com deterinada letra do alfabeto...
   private ArrayList<Transicao> confereTransicao(char letra, ArrayList<Transicao> transicoes) {
    ArrayList<Transicao> auxiliarTransicoes = new ArrayList<>();
    for (Transicao transicao : transicoes) {
            if (transicao.getRead() == letra) {
                auxiliarTransicoes.add(transicao);
            }
        }
    return auxiliarTransicoes;
}

    private Estado buscarEstadoInicial(Automato afn)
    {
        for(int i = 0; i<afn.getEstados().size(); i++)
        {
            if(afn.getEstados().get(i).isInitial())
            {
                return afn.getEstados().get(i);
            }
        }
        return null;
    }

    private void buscarEstadoFinal(Automato afn)
    {
        int j = 0;
        for(int i = 0; i<afn.getEstados().size(); i++)
        {
            if(!(afn.getEstados().get(i).isInitial()))
            {
                estadosFinais.add(afn.getEstados().get(i));
                j++;
            }
        }
    }

    private boolean existeEstado (String nomeEstado)
    {
        for(int i = 0; i<tabelaTransicao.size();i++)
        {
            for(int j = 0; j<coluna; j++)
            {
                if(nomeEstado.equalsIgnoreCase(tabelaTransicao.get(i).get(j).getName()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    //procurar as transições de determinado estado
    private ArrayList<Transicao> descobertaDeTransicao(Automato automatoAFN, int id) 
    {
        ArrayList<Transicao> transicoes = new ArrayList<>();
        for (int i = 0; i < automatoAFN.getTransicoes().size(); i++)
         {
            if (automatoAFN.getTransicoes().get(i).getFrom()==id){
                transicoes.add(automatoAFN.getTransicoes().get(i));
            }
        }
        return transicoes;
    }

    //para conferir se existe transicao lambida
    private boolean existeLambida(ArrayList<Transicao> transicoes)
    {
        for(int i = 0; i<transicoes.size(); i++)
        {
            if(transicoes.get(i).getRead().isBlank())
            {
                return true;
            }
        }
        return false;
    }


    public boolean novoEstado(Automato afn, ArrayList<Transicao> transicoes)
    {
        Estado novoEstado = new Estado();
        ArrayList<Transicao> auxiliarTransicoes= new ArrayList<>();
        String nome = "";

        //para cada letra do alfabeto conferir para onde vai....
        for(int i=0; i<coluna; i++)
        {
            auxiliarTransicoes = confereTransicao(afn.getAlfabeto().get(i), transicoes);
            if(existeLambida(auxiliarTransicoes))
            {
                casoLambida(auxiliarTransicoes, nome, 0);
            }
            else
            {
                //lembrar que caso não exista transição lambida no estado inicial começa por ele...
                if(auxiliarTransicoes.getFrom().isInitial)
                {
                    novoEstado.setName(transicoes.getFrom());
                    novoEstado.setId(idAux);
                    tabelaTransicao.get(idAux).add(i, novoEstado);
                }
                
                //para o próximo estado...
                for(int j = 0; j<auxiliarTransicoes.size(); j++)
                {
                    nome += auxiliarTransicoes.getTo();
                    aux.add(auxiliarTransicoes.getTo());
                }

                //caso o estado não exista cria um novo estado
                if(!(existeEstado(nome)))
                {
                novoEstado.setName(nome);
                novoEstado.setId(idAux);
                tabelaTransicao.get(idAux).add(i+1, novoEstado);
                idAux++;
                return true;
                }
             }
         }
         return false;
     }
    //to do
    // terminar de implementar caso tenha alguma transição lambda
    public void casoLambida(ArrayList<Transicao> transicoes,String nome, int i)
    {
        if(nome.contains(Integer.toString(transicoes.get(i).getFrom())))
        {
            return;
        }
        
    }

    //é a partir dessa classe que começará o processo de conversão 
    public void converteAfnParaAfd(Automato afn)
    {
        ArrayList<Transicao> transicoes = new ArrayList<>();
        Automato novAutomato = new Automato();
        int cont = 0;
        Estado estadoAux = new Estado();
        Transicao transicaoAux;
        //a coluna recebe a quantidade de letras do alfabeto
        this.coluna = afn.getAlfabeto().size();
        buscarEstadoFinal(afn);
        
        //vai buscar o estado inicial e em seguida suas transições
        transicoes = descobertaDeTransicao(afn, buscarEstadoInicial(afn).getId());
        novoEstado(afn,transicoes);

        while(cont < afn.alfabeto.size())
        {
            for(int i = 0; i<aux.size();i++)
            {
                transicoes = descobertaDeTransicao(afn, aux.from());
                if(!(novoEstado(afn, transicoes)))
                {
                    cont++;
                }
            }
            aux.clear();
        }

        //traduzir a tabela transição
        for(int i=0; i<tabelaTransicao.size();i++)
        {
            for(int j=0; j<coluna; j++)
            {
                if(j==0)
                {
                    estadoAux.setId(i);
                }
                estadoAux.setName(tabelaTransicao.get(i).get(j));
                novAutomato.estados.add(estadoAux);
                transicaoAux.setFrom(i);
                transicaoAux.setTo(tabelaTransicao.get(i).get(j).getId());
                transicaoAux.setRead(afn.getAlfabeto().get(j));
                novAutomato.transicoes.add(transicaoAux);
            }
        }

       
    }  
}
*/

public class Equivalencia {
    private ArrayList<ArrayList<Estado>> tabelaTransicao = new ArrayList<>();
    private ArrayList<Estado> estadosFinais = new ArrayList<>();
    private ArrayList<Transicao> aux = new ArrayList<>();
    private int coluna, idAux = 0;

    private ArrayList<Transicao> confereTransicao(char letra, ArrayList<Transicao> transicoes) {
        ArrayList<Transicao> auxiliarTransicoes = new ArrayList<>();
        for (Transicao transicao : transicoes) {
            if (transicao.getRead().charAt(0) == letra) {
                auxiliarTransicoes.add(transicao);
            }
        }
        return auxiliarTransicoes;
    }

    private Estado buscarEstadoInicial(Automato afn) {
        for (Estado estado : afn.getEstados()) {
            if (estado.isInitial()) {
                return estado;
            }
        }
        return null;
    }

    private void buscarEstadoFinal(Automato afn) {
        for (Estado estado : afn.getEstados()) {
            if (!estado.isInitial()) {
                estadosFinais.add(estado);
            }
        }
    }

    private boolean existeEstado(String nomeEstado) {
        for (ArrayList<Estado> linha : tabelaTransicao) {
            for (Estado estado : linha) {
                if (estado.getName().equalsIgnoreCase(nomeEstado)) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<Transicao> descobertaDeTransicao(Automato automatoAFN, int id) {
        ArrayList<Transicao> transicoes = new ArrayList<>();
        for (Transicao transicao : automatoAFN.getTransicoes()) {
            if (transicao.getFrom() == id) {
                transicoes.add(transicao);
            }
        }
        return transicoes;
    }

    private boolean existeLambida(ArrayList<Transicao> transicoes) {
        for (Transicao transicao : transicoes) {
            if (transicao.getRead().isBlank()) {
                return true;
            }
        }
        return false;
    }

    public boolean novoEstado(Automato afn, ArrayList<Transicao> transicoes,int idInicio) {
        Estado novoEstado = new Estado();
        ArrayList<Transicao> auxiliarTransicoes = new ArrayList<>();
        String nome = "";

        for (int i = 0; i < coluna; i++) {
            auxiliarTransicoes = confereTransicao((char) (i + 'a'), transicoes);
            if (existeLambida(auxiliarTransicoes)) {
                casoLambida(auxiliarTransicoes, nome, 0);
            } else {
                if (auxiliarTransicoes.get(0).getFrom()== idInicio) {
                    novoEstado.setName(Integer.toString(auxiliarTransicoes.get(0).getFrom()));
                    novoEstado.setId(idAux);
                    tabelaTransicao.get(idAux).add(novoEstado);
                }
                for (Transicao transicao : auxiliarTransicoes) {
                    nome += transicao.getTo();
                    aux.add(transicao);
                }
                if (!existeEstado(nome)) {
                    novoEstado.setName(nome);
                    novoEstado.setId(idAux);
                    tabelaTransicao.get(idAux).add(novoEstado);
                    idAux++;
                    return true;
                }
            }
        }
        return false;
    }

    public void casoLambida(ArrayList<Transicao> transicoes, String nome, int i) {
        if (nome.contains(Integer.toString(transicoes.get(i).getFrom()))) {
            return;
        }
    }

     void converte(Automato automatoAFN)
    {
        ArrayList<Transicao> transicoes = new ArrayList<>();
        Automato novAutomato = new Automato();
        int cont = 0;
        Estado estadoAux = new Estado();
        Transicao transicaoAux = new Transicao();
        //a coluna recebe a quantidade de letras do alfabeto
        this.coluna = automatoAFN.getAlfabeto().size();
        buscarEstadoFinal(automatoAFN);
       int idInicio = buscarEstadoInicial(automatoAFN).getId();
        
        //vai buscar as transições do estado inicial...
        transicoes = descobertaDeTransicao(automatoAFN, idInicio);
        novoEstado(automatoAFN,transicoes, idInicio);

        while(cont < automatoAFN.getAlfabeto().size())
        {
            for(int i = 0; i<aux.size();i++)
            {
                transicoes = descobertaDeTransicao(automatoAFN, aux.get(i).getFrom());
                if(!(novoEstado(automatoAFN, transicoes, idInicio)))
                {
                    cont++;
                }
            }
            aux.clear();
        }

        //traduzir a tabela transição
        int index = 0; // Índice para acessar o conjunto de alfabeto
        for (int i = 0; i < tabelaTransicao.size(); i++) {
            for (int j = 0; j < coluna; j++) {
                if (j == 0) {
                    estadoAux.setId(i);
                }
                estadoAux.setName(tabelaTransicao.get(i).get(j).getName());
                novAutomato.getEstados().add(estadoAux);
                transicaoAux.setFrom(i);
                transicaoAux.setTo(tabelaTransicao.get(i).get(j).getId());
                
                // Acessando o conjunto de alfabeto usando um índice
                Character letra = automatoAFN.getAlfabeto().toArray(new Character[0])[index];
                transicaoAux.setRead(String.valueOf(letra));
                
                novAutomato.getTransicoes().add(transicaoAux);
                
                // Atualizando o índice para acessar o próximo elemento do conjunto de alfabeto
                index = (index + 1) % automatoAFN.getAlfabeto().size();
            }
        }
        
    }

}

