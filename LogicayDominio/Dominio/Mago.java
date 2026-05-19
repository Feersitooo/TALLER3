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
		return  nombre + ", hechizos:" + hechizos + "";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Hechizo> getHechizos() {
		return hechizos;
	}
	
	
	
	
	
	
	
	
	
	

}
