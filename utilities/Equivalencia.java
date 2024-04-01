package utilities;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
public class Equivalencia {
    public Equivalencia(Automato automatoLido)
    {
        this.automatoLido = automatoLido;
    }
    private Automato automatoLido;
    private ArrayList<Estado> estadosList;
    private int contParaPegarXY = 0;
    private ArrayList<EstadoComTransicoes> auxAutomatoSaida = new ArrayList<EstadoComTransicoes>();
    private final String LAMBDA = "";

    public void gerarAutomato()
    {
        gerarEstadoInicial();
        fazerEquivalencia();
    }

    public void fazerEquivalencia()
    {
        for(int i = 0;i < auxAutomatoSaida.size();i++)
        {
            Map<String,String> possiveisNovosEstados = pegarPossiveisNovoEstado(i);
            Set<String> readChaves = possiveisNovosEstados.keySet();
            if(readChaves.isEmpty())
                continue;

            for (String read : readChaves) {
                String auxReadLabel = Automato.removerVirgula(possiveisNovosEstados.get(read));
                if(temNovoEstadoParaCriar(auxReadLabel))
                    criarNovoEstado(auxReadLabel);
            }
            addTransicao(i,possiveisNovosEstados,readChaves);
        }
    }

    public void addTransicao(int index, Map<String,String> possiveisNovosEstados,Set<String> readChaves)
    {
        EstadoComTransicoes saidaFrom = auxAutomatoSaida.get(index);
        for (String read : readChaves)
        {
            String auxLabel = Automato.removerVirgula(possiveisNovosEstados.get(read));
            for (EstadoComTransicoes saidaTo : auxAutomatoSaida) {
                if(saidaTo.getLabelDoEstado().equals(auxLabel))
                {
                    String from = saidaFrom.getEstado().getId();
                    String to = saidaTo.getEstado().getId();
                    Transicao t = new Transicao(from,to,read);
                    saidaFrom.getTransicao().add(t);
                }
            }
        }
    }
    public void criarNovoEstado(String label)
    {
        String novoId = String.valueOf(auxAutomatoSaida.size());
        int tamEstados = automatoLido.getEstados().size();
        double x = 200,y = 200;
        if(contParaPegarXY < tamEstados -1)
        {
           x = estadosList.get(contParaPegarXY).getX();
           y = estadosList.get(contParaPegarXY).getY();
           System.err.println("### XY ###");
           System.err.println(x);
           System.err.println(y);
           contParaPegarXY++;
        }
        Estado novoEstado = new Estado(novoId,"q" + novoId,x,y,false,false,label);
        EstadoComTransicoes et = new EstadoComTransicoes(novoEstado);

        label = Automato.removerVirgula(label);
        for (Estado e : automatoLido.getEstados()) {
            if(label.contains(e.getId()) && e.isFinal())
            {
                et.getEstado().setFinal(true);
                break;
            }
        }
        auxAutomatoSaida.add(et);
    }
    public Map<String,String> pegarPossiveisNovoEstado(int index)
    {
        Map<String,String> possiveisNovosEstados = new HashMap<>();
        String auxLabel = Automato.removerVirgula(auxAutomatoSaida.get(index).getEstado().getLabel());
        for(int i=0;i<auxLabel.length();i++)
        {
            String id = String.valueOf(auxLabel.charAt(i));
            Estado estadoLido = automatoLido.getEstadoPorId(id);
            Set<Transicao> transicoesLido = automatoLido.getTransicoesPorEstado(estadoLido);
            for (Transicao t : transicoesLido) {
                if(!t.getRead().equals(LAMBDA))
                {
                    if(possiveisNovosEstados.get(t.getRead()) == null)
                        possiveisNovosEstados.put(t.getRead(), "");
                    possiveisNovosEstados.put(t.getRead(),addLabelsRecursivoComLetra(possiveisNovosEstados.get(t.getRead()), estadoLido,t.getRead()));
                }
            }
        }
        return possiveisNovosEstados;
    }
    public boolean temNovoEstadoParaCriar(String novoEstado)
    {
        if(novoEstado.length() == 0)
            return false;
        for (EstadoComTransicoes et : auxAutomatoSaida) {
            if(et.getEstado().getLabel().equals(novoEstado))
                return false;
        }
        return true;
    }
    
    public String addLabelsRecursivoComLetra(String label,Estado novoEstado,String letra)
    {
        limiteDeRecursao();
        Set<Transicao> transicoesNovoEstado = automatoLido.getTransicoesPorEstado(novoEstado);
        if(transicoesNovoEstado != null && transicoesNovoEstado.size() > 0)
        {
            for (Transicao t : transicoesNovoEstado) {
                if(t.getRead().equals(letra) && !label.contains(t.getTo()))
                {
                    label = label + (label.length() == 0 ? t.getTo() :  "," + t.getTo());
                    label = addLabelsRecursivoComLetra(label,automatoLido.getEstadoPorId(t.getTo()),LAMBDA);
                }
            }
        }
        countadorRecursao = 0;
        return Automato.ordenarLabel(label);
    }

    

    

    public void gerarEstadoInicial()
    {
        Estado primeiroEstado = automatoLido.getEstadoInicial().copyWith();
        if(primeiroEstado == null || !primeiroEstado.isInitial())
            Error.errorMessageAndExit("O automato não tem estado inicial. Não foi possível fazer a equivalência");
        primeiroEstado.setId(String.valueOf(auxAutomatoSaida.size())); // Sempre vai ser 0, pois n tem nenhum la dentro ainda
        primeiroEstado.setName("q" + String.valueOf(auxAutomatoSaida.size()));
        EstadoComTransicoes auxAutomatoInicial = new EstadoComTransicoes(primeiroEstado);
        auxAutomatoInicial.getEstado().addLabel(addLabelsRecursivoComLetra("0",auxAutomatoInicial.getEstado(),LAMBDA));
        String label = auxAutomatoInicial.getLabelDoEstado();
        label = Automato.removerVirgula(label);
        for (Estado e : automatoLido.getEstados()) {
            if(label.contains(e.getId()) && e.isFinal())
            {
                auxAutomatoInicial.getEstado().setFinal(true);
                break;
            }
        }
        contParaPegarXY++;
        estadosList = new ArrayList<>(automatoLido.getEstados());
        auxAutomatoSaida.add(auxAutomatoInicial);
    }
    private int countadorRecursao = 0;
    private final int MAX_CONTADOR_RECURSAO = 100;

    public void limiteDeRecursao()
    {
        countadorRecursao++;
        if(countadorRecursao > MAX_CONTADOR_RECURSAO)
        {
            Error.errorMessageAndExit("Há muitos estados em uma única transição. O programa será encerrado");
        }
    }
    
    public Automato getAutomatoGerado()
    {
        Automato automato = new Automato();
        for (EstadoComTransicoes estadoComTransicoes : auxAutomatoSaida) {
            automato.addEstado(estadoComTransicoes.getEstado());
            Set<Transicao> t = estadoComTransicoes.getTransicao();
            if(t != null)
            {
                for (Transicao transicao : t) {
                    automato.addTransicao(transicao);
                }
            }
        }
        return automato;
    }
}
