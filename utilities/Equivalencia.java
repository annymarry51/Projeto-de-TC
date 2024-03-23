package utilities;

import java.util.ArrayList;
import java.util.Set;

public class Equivalencia {
    private ArrayList<ArrayList<Estado>> tabelaTransicao = new ArrayList<>();
    //para guardar os estados finais;
    private ArrayList<Estado> estadosFinais = new ArrayList<>();
    //para a matriz de transicoes;
    private int linha, coluna, idAux = 0;

    //para conferir as transições com deterinada letra do alfabeto...
   private ArrayList<Transicao> confereTransicao(Set<Character> alfabeto, ArrayList<Transicao> transicoes) {
    ArrayList<Transicao> auxiliarTransicoes = new ArrayList<>();
    for (Transicao transicao : transicoes) {
        for (Character c : alfabeto) {
            if (transicao.getRead().equalsIgnoreCase(String.valueOf(c))) {
                auxiliarTransicoes.add(transicao);
            }
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

    //Implementação impcompleta...
    private void adicionarNaTabela(Estado estado)
    {
        for(int i=0; i<coluna; i++)
        {

        }
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

    public void novoEstado(Automato afn, ArrayList<Transicao> transicoes)
    {
        Estado novoEstado = new Estado();
        ArrayList<Transicao> auxiliarTransicoes= new ArrayList<>();
        String nome = " ";
        for(int i=0; i<coluna; i++)
        {
            auxiliarTransicoes = confereTransicao(afn.getAlfabeto(), transicoes);
            if(existeLambida(auxiliarTransicoes))
            {
                casoLambida(auxiliarTransicoes, nome, 0);
            }
            else
            {
                //to do
                //implementar a lógica caso o estado não possua transição lambda...
            }
            if(!(existeEstado(nome)))
            {
              novoEstado.setName(nome);
              novoEstado.setId(idAux);
              idAux++;
            }
        }
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
        //a coluna recebe a quantidade de letras do alfabeto
        this.coluna = afn.getAlfabeto().size();
        buscarEstadoFinal(afn);
        
        //vai buscar o estado inicial e em seguida suas transições
        transicoes = descobertaDeTransicao(afn, buscarEstadoInicial(afn).getId());
        novoEstado(afn,transicoes);
        //to do
        //implementar a lógica de passar o conteúdo da tabela de transição para o novo automato
    }  
}

