package utilities;

import java.util.ArrayList;

public class Automato {
    private ArrayList<Estado> estados;
    
    public Automato() {
    	setEstados(new ArrayList<>());
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
		return sb.toString();
	}
}