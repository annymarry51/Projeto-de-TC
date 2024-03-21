import java.util.ArrayList;

public class Equivalecia {

    ArrayList<Transicao> auxiliarTransicoes = new ArrayList<>();
    ArrayList<Estado> auxiliarEstados = new ArrayList<>();

    public void novosEstados ()
    {

    }

    public void formarNovosEstados()
    {
        int x = 0;
        ArrayList<String> strLida = new ArrayList<>();
        strLida.add(0,auxiliarTransicoes.get(0).read);

        //para descobrir as transições com os mesmos valores lidos...
        for(int i=0; i<auxiliarTransicoes.size();i++)
        {
            for(int j=0; j<strLida.size();j++)
            {
                if(!(auxiliarTransicoes.get(i).read.equalsIgnoreCase(strLida.get(j))))
                {
                    strLida.add(auxiliarTransicoes.get(i).read);
                }
            }
        }
        
        //para cada valor lido descobrir para qual estado irá
        //consertar...
        for(int i=0; i<strLida.size();i++)
        {
            for(int j=0; j<auxiliarTransicoes.size();j++)
            {
                if(auxiliarTransicoes.get(j).read.equalsIgnoreCase(strLida.get(i)))
                {
                    auxiliarEstados.add(x,new Estado());
                    auxiliarEstados.get(x).id = Integer.toString(x);
                    auxiliarEstados.get(x).name = Integer.toString(x);
                    x++;
                    auxiliarTransicoes.get(j).to = Integer.parseInt(auxiliarEstados.get(x).id);
                }
            }
        }
    }

    //para descobrir as transições de determinado estado...
    //não conta para onde vai...
    public void descobertaDeTransicao(Automato automatoAFN, int id)
    {
        int cont = 0;
        for(int i=0; i<automatoAFN.transicoes.size();i++)
        {
            if(automatoAFN.transicoes.get(i).from == id)
            {
                    auxiliarTransicoes.get(cont).from = id;
                    auxiliarTransicoes.get(cont).read = automatoAFN.transicoes.get(i).read;
            }
        }
    }

    public void converterAfnParaAfd(Automato automatoAFN) {
        boolean achou = true;
        int i = 0;
        Automato novoAutomato = new Automato();
        ArrayList<Estado> novosEstados = new ArrayList<>();
        ArrayList<Transicao> transicoes = new ArrayList<>();

        //conferir se há alguma trasição com lambida
        for(int i=0; i<automatoAFN.transicoes.size();i++)
        {
            if(!(automatoAFN.transicoes.get(i).read.equalsIgnoreCase(null)))
            {
                achou = false;
            }
        }
        
        if(achou)
        {
            //fazer a lógica para caso o automato possua alguma transição com lambida
        }
        else
        {
            //para procurar o estado inicial 
            for(i=0; i<automatoAFN.estados.size();i++)
            {
                if(automatoAFN.estados.get(i).isInitial)
                {
                    novosEstados.add(automatoAFN.estados.get(i));
                    break;
                }
            }

            //começar do estado inicial...
            descobertaDeTransicao(automatoAFN, i);
            
        }

    }
}
