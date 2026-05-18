package taller3;

import java.util.ArrayList;

public class Mago {
	private String nombre;
	private ArrayList<Hechizo> hechizos = new ArrayList<>();
	
	public Mago(String nombre, ArrayList<Hechizo> hechizos) {
		super();
		this.nombre = nombre;
		this.hechizos = hechizos;
	}

	@Override
	public String toString() {
		return "Mago [nombre=" + nombre + ", hechizos=" + hechizos + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
