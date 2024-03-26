package projTC;

import java.util.ArrayList;
import java.util.List;

public class Estado {
	String id;
	String nome;
	String tipo;
	List<Transicao> transicoes;
	
	public Estado(String id, String nome, String tipo, List<Transicao> transicoes) {
		setId(id);
		setNome(nome);
		setTipo(tipo);
		setTransicoes(transicoes);
	}
	
	public Estado() {
		this("", "", "", new ArrayList<>());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Transicao> getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(List<Transicao> transicoes) {
		this.transicoes = transicoes;
	}
	
	
}
