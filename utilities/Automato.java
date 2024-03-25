package utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Automato {
    private ArrayList<Estado> estados;
    private ArrayList<Transicao> transicoes;
    private Set<Character> alfabeto;
    
	public Automato() {
    	setEstados(new ArrayList<>());
    	setTransicoes(new ArrayList<>());
    	setAlfabeto(new HashSet<>());
    }
    
	Set<Character> getAlfabeto() {
		return alfabeto;
	}
	
	private void setAlfabeto(Set<Character> alfabeto) {
		this.alfabeto = alfabeto;
	}
	
    void addTransicao(Transicao transicao) {
		transicoes.add(transicao);
	}
    
    public void setTransicoes(ArrayList<Transicao> transicoes) {
		this.transicoes = transicoes;
	}
    
    public ArrayList<Transicao> getTransicoes() {
    	return transicoes;
    }
    
    public Automato(ArrayList<Estado> estados) {
    	setEstados(estados);
    }

	ArrayList<Estado> getEstados() {
		return estados;
	}
	
	private void setEstados(ArrayList<Estado> estados) {
		this.estados = estados;
	}
	
	void addEstado(Estado estado) {
		estados.add(estado);
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


}