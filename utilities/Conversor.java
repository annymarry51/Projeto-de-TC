package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Conversor {
    private HashMap<Estado, HashMap<String, Set<Estado>>> tabelaAFN;
    private HashMap<Estado, HashMap<String, Estado>> tabelaAFD;
    
    private void renomearIds(Automato automato) {
    	for (Transicao t : automato.getTransicoes()) {
    		Estado from = automato.getEstadoPorId(t.getFrom());
    		Estado to = automato.getEstadoPorId(t.getTo());
    		t.setFrom(inverterString(t.getFrom().replace(",", "").replace("-", from.isInitial() ? "-" : "")));
    		t.setTo(inverterString(t.getTo().replace(",", "").replace("-", to.isInitial() ? "-" : "")));
    	}
        for (Estado e : automato.getEstados()) {
        	e.setName(e.getName().replace(",", "").replace("-1", "0"));
        	e.setId(inverterString(e.getId()).replace(",", "").replace("-", e.isInitial() ? "-" : ""));
        }
    }
    
    private String inverterString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    private Set<Estado> getEstadosPorLetra(Estado estado, String letra, Automato afn) {
    	Set<Transicao> transicoes = afn.getTransicoesPorEstadoELetra(estado, letra);
    	Set<Estado> estados = new HashSet<>();
    	for (Transicao t : transicoes) {
    		estados.add(afn.getEstadoPorId(t.getTo()));
    	}
    	return estados;
    }
    
    private Estado getEstadoCombinado(Set<Estado> estados) {
    	String ids = "";
    	Estado estado = new Estado();
    	if (estados == null || estados.size() == 0)
    		return null;
    	for (Estado e : estados) {
    		estado.addEstadoCombinado(e);
    		ids += e.getId() + ",";
    	}
    	estado.setName(String.format("%s", ids.substring(0, ids.length() - 1)));
    	estado.setId(ids);;
    	return estado;
    }
    
    private Estado getEstadoCombinado(String nome, String letra) {
    	List<String> ids = Arrays.asList(nome.split(","));
    	Set<Estado> estados = new HashSet<>();
    	for (Map.Entry<Estado, HashMap<String, Set<Estado>>> i : tabelaAFN.entrySet()) {
    		if (ids.contains(i.getKey().getId()) && i.getValue().get(letra).size() > 0) {
    			estados.addAll(new ArrayList<>(i.getValue().get(letra)));
    		}
    	}
    	return estados.size() > 0 ? getEstadoCombinado(estados) : null;
    }
    
    private void preencherTabelaAFN(Automato afn) {
    	tabelaAFN = new HashMap<>();
    	for (Estado estado : afn.getEstados()) {
    		HashMap<String, Set<Estado>> mapaSimbolos = new HashMap<>();
    		for (String letra : afn.getAlfabeto()) {
    			mapaSimbolos.put(letra, getEstadosPorLetra(estado, letra, afn));
    		}
    		tabelaAFN.put(estado, mapaSimbolos);
    	}
    }
    
    private List<Estado> removerIniciais(List<Estado> estadosAFD, List<String> auxNomes) {
    	List<Estado> estados = new ArrayList<>();
    	for (Estado estado : estadosAFD) {
    		if (auxNomes.contains(estado.getName())) {
    			estados.add(estado);
    		}
    	}
    	return estados;
    }
    
    private List<Estado> verificarEstadosInicialEFinal(List<Estado> estados, Automato afn) {
    	for (Estado estado : estados) {
    		for (Estado e : estado.getEstadosCombinados()) {
    			if (e.isFinal())
    				estado.setFinal(true);
    			if (e.getId().equals(afn.getEstadoInicial().getId()) && estado.getEstadosCombinados().size() == 1)
    				estado.setInitial(true);
    		}
    	}
    	if (afn.temLoop(afn.getEstadoInicial())) {
    		estados.remove(afn.getEstadoInicial());
    		tabelaAFD.remove(afn.getEstadoInicial());
    	}
    	return estados;
    }
    
    private List<Estado> construirTabelaAFDEPegaEstados(Automato afn) {
    	List<Estado> estadosAFD = new ArrayList<>();
    	estadosAFD.add(afn.getEstadoInicial());
    	tabelaAFD = new HashMap<>();
    	List<String> nomes = new ArrayList<>();
    	List<String> auxNomes = new ArrayList<>();
    	for (int estado = 0; estado < estadosAFD.size(); estado++) {
    		Estado estadoAtual = estadosAFD.get(estado);
    		HashMap<String, Estado> valor = new HashMap<>();
    		for (String letra : afn.getAlfabeto()) {
    			Estado novoEstado = tabelaAFN.containsKey(estadoAtual) ?
    					getEstadoCombinado(tabelaAFN.get(estadoAtual).get(letra)) :
    					getEstadoCombinado(estadoAtual.getName(), letra);
    			if (novoEstado != null) {
    				if (!auxNomes.contains(novoEstado.getName())) {
    					auxNomes.add(novoEstado.getName());
    					estadosAFD.add(novoEstado);
    					valor.put(letra, novoEstado);
    				} else {
    					valor.put(letra, novoEstado);
    				}
    			}
    		}
    		
    		if (!nomes.contains(estadoAtual.getName())) {
    			tabelaAFD.put(estadoAtual, valor);
    			nomes.add(estadoAtual.getName());
    		} else {
    			tabelaAFD.get(estadoAtual).putAll(valor);
    		}
    	}
    	return verificarEstadosInicialEFinal(removerIniciais(estadosAFD, auxNomes), afn);
    }
    
    private List<Transicao> getTransicoes(List<Estado> estadosAFD) {
    	List<Transicao> transicoes = new ArrayList<>();
    	for (Map.Entry<Estado, HashMap<String, Estado>> i : tabelaAFD.entrySet()) {
    		for (Map.Entry<String, Estado> j : i.getValue().entrySet()) {
    			transicoes.add(new Transicao(i.getKey().getId(), j.getValue().getId(), j.getKey()));
    		}
    	}
    	return transicoes;
    }
    
    private Automato converterAFNParaAFD(Automato afn) {
    	Automato afd = new Automato();
    	preencherTabelaAFN(afn);
    	List<Estado> estadosAFD = construirTabelaAFDEPegaEstados(afn);
    	if (!afn.temLoop(afn.getEstadoInicial()))
    		estadosAFD.add(afn.getEstadoInicial());
    	List<Transicao> transicoesAFD = getTransicoes(estadosAFD);
    	afd.setEstados(new HashSet<>(estadosAFD));
    	afd.setAlfabeto(afn.getAlfabeto());
    	afd.removeSimbolo("");
    	afd.setTransicoes(new HashSet<>(transicoesAFD));
    	afd.preencheEstadosIniciaisEFinais();
    	afd.preencherPosicoes();
    	renomearIds(afd);
    	return afd;
    }
    
    public Automato getAFDdoAFN(Automato afn) {
    	if (afn.isAFN(afn))
    		return converterAFNParaAFD(afn);
    	return afn;
    }
}
