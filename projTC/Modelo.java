package projTC;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

public class Modelo {
	private List<String> alfabeto;
    private List<Estado> estados;
    private List<Element> transicoes;
    ArrayList<ArrayList<Estado>> matrizEstados;
    private List<String> estadosIniciais;
    private List<String> estadosFinais;
    private List<String> estadosIniciais2;
    private List<String> estadosFinais2;
    
    public Modelo() {
    	setAlfabeto(new ArrayList<>());
    	setEstados(new ArrayList<>());
    	setTransicoes(new ArrayList<>());
    	setMatrizEstados(new ArrayList<>());
    	setEstadosIniciais(new ArrayList<>());
    	setEstadosFinais(new ArrayList<>());
    	setEstadosIniciais2(new ArrayList<>());
    	setEstadosFinais2(new ArrayList<>());
    }
    
	public List<String> getAlfabeto() {
		return alfabeto;
	}
	public void setAlfabeto(List<String> alfabeto) {
		this.alfabeto = alfabeto;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	public List<Element> getTransicoes() {
		return transicoes;
	}
	public void setTransicoes(List<Element> transicoes) {
		this.transicoes = transicoes;
	}
	public ArrayList<ArrayList<Estado>> getMatrizEstados() {
		return matrizEstados;
	}
	public void setMatrizEstados(ArrayList<ArrayList<Estado>> matrizEstados) {
		this.matrizEstados = matrizEstados;
	}
	public List<String> getEstadosIniciais() {
		return estadosIniciais;
	}
	public void setEstadosIniciais(List<String> estadosIniciais) {
		this.estadosIniciais = estadosIniciais;
	}
	public List<String> getEstadosFinais() {
		return estadosFinais;
	}
	public void setEstadosFinais(List<String> estadosFinais) {
		this.estadosFinais = estadosFinais;
	}
	public List<String> getEstadosIniciais2() {
		return estadosIniciais2;
	}
	public void setEstadosIniciais2(List<String> estadosIniciais2) {
		this.estadosIniciais2 = estadosIniciais2;
	}
	public List<String> getEstadosFinais2() {
		return estadosFinais2;
	}
	public void setEstadosFinais2(List<String> estadosFinais2) {
		this.estadosFinais2 = estadosFinais2;
	}
    
    
}
