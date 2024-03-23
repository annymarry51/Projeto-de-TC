package utilities;

public class Estado {
    private int id;
    private String name;
    private double x;
    private double y;
    private boolean isInitial;
    private boolean isFinal;
    
    public Estado() {
    }

    public Estado(int id, String name, double x, double y, boolean isInitial, boolean isFinal) {
    	setId(id);
    	setName(name);
    	setX(x);
    	setY(y);
    	setInitial(isInitial);
    	setFinal(isFinal);
    }
    
    int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
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

	public boolean isInitial() {
		return isInitial;
	}

	public void setInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
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
		return sb.toString();
	}
}