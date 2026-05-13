package taller3;

public class HechizoPlanta extends Hechizo {

	private int duracionStun;
	private int cantPlantas;
	
	public HechizoPlanta(String nombreHechizo, String tipo, int daño, int duracionStun, int cantPlantas) {
		super(nombreHechizo, tipo, daño);
		this.duracionStun = duracionStun;
		this.cantPlantas = cantPlantas;
	}
	
	
	
}
