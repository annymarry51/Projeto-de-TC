package utilities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Automato {
    private Set<Estado> estados;
    private Set<Transicao> transicoes;
    private Set<String> alfabeto;
    private Set<Estado> estadosFinais;
    private Estado estadoInicial;
    
	public Automato() {
    	setEstados(new HashSet<>());
    	setTransicoes(new HashSet<>());
    	setAlfabeto(new HashSet<>());
    }
	
	Estado getEstadoInicial() {
		return estadoInicial;
	}
	
	void setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	
	void addEstadoInicial(Estado estado) {
		if (estadoInicial == null)
			estadoInicial = estado;
	}
	
	Set<Estado> getEstadosFinais() {
		return estadosFinais;
	}
	
	void setEstadosFinais(Set<Estado> estadosFinais) {
		this.estadosFinais = estadosFinais;
	}
	
	void addEstadoFinal(Estado e) {
		if (estadosFinais == null)
			setEstadosFinais(new HashSet<>());
		estadosFinais.add(e);
	}
	
	Set<String> getAlfabeto() {
		return alfabeto;
	}
	
	void setAlfabeto(Set<String> alfabeto) {
		this.alfabeto = alfabeto;
	}
	
	void addSimbolo(String s) {
		if (getAlfabeto() == null)
			setAlfabeto(new HashSet<>());;
		getAlfabeto().add(s);
	}
	
	void removeSimbolo(String s) {
		getAlfabeto().remove(s);
	}
	
	void addTransicao(Transicao t) {
		if (transicoes == null)
			setTransicoes(new HashSet<>());
		transicoes.add(t);
	}
    
    void setTransicoes(Set<Transicao> transicoes) {
		this.transicoes = transicoes;
	}
    
    Set<Transicao> getTransicoes() {
    	return transicoes;
    }
    
    public Automato(Set<Estado> estados) {
    	setEstados(estados);
    }

	Set<Estado> getEstados() {
		return estados;
	}
	
	void setEstados(Set<Estado> estados) {
		this.estados = estados;
	}
	
	void addEstado(Estado e) {
		if (getEstados() == null)
			setEstados(new HashSet<>());
		estados.add(e);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Automato:\n");
		for (Estado e : getEstados()) {
			sb.append(e);
		}
		sb.append("Transições: \n");
		for (Transicao t : getTransicoes()) {
			sb.append(t);
		}
		sb.append("Alfabeto: " + getAlfabeto());
		
		return sb.toString();
	}

	Estado getEstadoPorId(String id) {
		for (Estado e : getEstados()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		return null;
	}
	
	void preencheEstadosIniciaisEFinais() {
		for (Estado e : getEstados()) {
			if (e.isFinal())
				addEstadoFinal(e);
			if (e.isInitial()) {
				setEstadoInicial(e);
			}
		}
	}
	
	Set<Transicao> getTransicoesPorEstadoELetra(Estado e, String l) {
		Set<Transicao> transicoes = new HashSet<>();
		for (Transicao t : getTransicoes()) {
			if (t.getFrom().equals(e.getId()) && t.getRead().equals(l))
				transicoes.add(t);
		}
		return transicoes;
	}
	
	Set<Transicao> getTransicoesPorEstado(Estado e) {
		Set<Transicao> transicoes = new HashSet<>();
		for (Transicao t : getTransicoes()) {
			if (t.getFrom().equals(e.getId()))
				transicoes.add(t);
		}
		return transicoes;
	}

	//para conferir se o estado recebe alguma transição...
	Set<Transicao> getTransicoesParaEstado(Estado e) {
		Set<Transicao> transicoes = new HashSet<>();
		for (Transicao t : getTransicoes()) {
			if (t.getTo().equals(e.getId()))
				transicoes.add(t);
		}
		return transicoes;
	}
	
	boolean temLoop(Estado estadoInicial) {
		Set<Transicao> transicoes = getTransicoesPorEstado(estadoInicial);
		for (Transicao t : transicoes) {
			if (t.getTo().equals(estadoInicial.getId()))
				return true;
		}
		return false;
	}
	
	boolean isAFN(Automato afn) {
		  for (Transicao t : afn.getTransicoes()) {
		    for (Transicao tr : afn.getTransicoes()) {
		      if (t != tr &&  // Evita comparar a mesma transição consigo mesma
		          t.getFrom().equals(tr.getFrom()) && 
		          (t.getRead().equals(tr.getRead()) || t.getRead().isEmpty() && !tr.getRead().isEmpty())) {
		        return true;
		      }
		    }
		  }
		  return false;
		}
	
	void preencherPosicoes() {
		for (Estado e : getEstados()) {
			e.setX((int) ((Math.random() * 100) * Math.random() * 10));
			e.setY((int) ((Math.random() * 100) * Math.random() * 10));
		}
	}

}