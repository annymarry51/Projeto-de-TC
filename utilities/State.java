package utilities;

import java.util.ArrayList;

public class State {
	private int id;
	private String name;
	private int posX, posY;
	private ArrayList<Transition> listTransitions;
	private Boolean isInitial;
	private Boolean isFinal;
	
	public State(int id, String name, int posX, int posY, ArrayList<Transition> listTransitions, Boolean isInitial, Boolean isFinal) {
		this.id = id;
		this.name = name;
		this.listTransitions = listTransitions;
		this.isInitial = isInitial;
		this.isFinal = isFinal;
	}
	
	private Boolean getIsInitial() {
		return isInitial;
	}
	
	private Boolean getIsFinal() {
		return isFinal;
	}
	
	private void setListTransitions(ArrayList<Transition> listTransitions) {
		this.listTransitions = listTransitions;
	}
	
	private int getId() {
		return id;
	}

	private String getName() {
		return name;
	}

	private ArrayList<Transition> getListTransitions() {
		return listTransitions;
	}
}
