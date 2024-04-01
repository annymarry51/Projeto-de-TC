package utilities;

import java.util.HashSet;
import java.util.Set;

public class Estado {
    private String id;
    private String name;
    private double x;
    private double y;
    private boolean isInitial;
    private boolean isFinal;
    private Set<Estado> estadosCombinados;
	private String label = "";

	public void addLabel(String label)
	{
		this.label = this.label + label;
	}
    
	public Estado copyWith()
	{
		Estado copyWith = new Estado(id,name,x,y,isInitial,isFinal);
		copyWith.label = label;

		return copyWith;
	}

    public Estado() {
    }
    
    public Estado(String id, String name) {
    	setId(id);
    	setName(name);
    }
	public Estado(String id, String name, double x, double y, boolean isInitial, boolean isFinal,String label) {
    	this(id, name,x,y,isInitial,isFinal);
    	this.label = label;
    }

    public Estado(String id, String name, double x, double y, boolean isInitial, boolean isFinal) {
    	this(id, name);
    	setX(x);
    	setY(y);
    	setInitial(isInitial);
    	setFinal(isFinal);
    }
    
    String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	double getX() {
		return x;
	}

	void setX(double x) {
		this.x = x;
	}

	double getY() {
		return y;
	}

	void setY(double y) {
		this.y = y;
	}

	boolean isInitial() {
		return isCombinado() ? verificaEstadoCombinadoInicial() : isInitial;
	}

	void setInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}

	boolean isFinal() {
		return isCombinado() ? verificaEstadosCombinados() : isFinal;
	}

	void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	Set<Estado> getEstadosCombinados() {
		return estadosCombinados;
	}
	
	void setEstadosCombinados(Set<Estado> estadosCombinados) {
		this.estadosCombinados = estadosCombinados;
	}
	
	boolean isCombinado() {
		if (getEstadosCombinados() != null && getEstadosCombinados().size() > 0)
			return true;
		return false;
	}
	
	boolean verificaEstadoCombinadoInicial() {
		for (Estado e : getEstadosCombinados()) {
			if (e.isInitial())
				return getEstadosCombinados().size() == 1;
		}
		return false;
	}
	
	boolean verificaEstadosCombinados() {
		for (Estado e : getEstadosCombinados()) {
			if (e.isFinal())
				return true;
		}
		return false;
	}
	
	void addEstadoCombinado(Estado estado) {
		if (getEstadosCombinados() == null)
			setEstadosCombinados(new HashSet<>());
		getEstadosCombinados().add(estado);
	}

	public String getLabel()
	{
		return label;
	}
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("ID: %s\n", getId()));
		sb.append(String.format("Name: %s\n", getName()));
		sb.append(String.format("X: %f\n", getX()));
		sb.append(String.format("Y: %f\n", getY()));
		sb.append(String.format("Inicial: %s\n", isInitial()));
		sb.append(String.format("Final: %s\n", isFinal()));
		if(label != null && label.length() > 0)
		{
			sb.append(String.format("Label: %s\n", label));
		}
		return sb.toString();
	}
}