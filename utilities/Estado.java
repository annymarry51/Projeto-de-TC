package utilities;

import java.util.ArrayList;

public class Estado {
    private int id;
    private String name;
    private double x;
    private double y;
    private boolean isInitial;
    private boolean isFinal;
    ArrayList<Transicao> transicoes;
    
    public Estado() {
    }

    public Estado(int id, String name, double x, double y, boolean isInitial, boolean isFinal) {
    	setId(id);
    	setName(name);
    	setX(x);
    	setY(y);
    	setInitial(isInitial);
    	setFinal(isFinal);
    	setTransicoes(new ArrayList<>());
    }
    
    void addTransicao(Transicao transicao) {
		transicoes.add(transicao);
	}
    
    private void setTransicoes(ArrayList<Transicao> transicoes) {
		this.transicoes = transicoes;
	}
    
    private ArrayList<Transicao> getTransicoes() {
    	return transicoes;
    }
    
    int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private double getX() {
		return x;
	}

	private void setX(double x) {
		this.x = x;
	}

	private double getY() {
		return y;
	}

	private void setY(double y) {
		this.y = y;
	}

	private boolean isInitial() {
		return isInitial;
	}

	private void setInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}

	private boolean isFinal() {
		return isFinal;
	}

	private void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("ID: %d\n", getId()));
		sb.append(String.format("Name: %s\n", getName()));
		sb.append(String.format("X: %f\n", getX()));
		sb.append(String.format("Y: %f\n", getY()));
		sb.append(String.format("Inicial: %s\n", isInitial()));
		sb.append(String.format("Final: %s\n", isFinal()));
		sb.append("Transições: \n");
		for (Transicao t : getTransicoes()) {
			sb.append(t);
		}
		return sb.toString();
	}
}