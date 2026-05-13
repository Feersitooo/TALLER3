package taller3;

public class HechizoFuego extends Hechizo {

	private int duracionQuemadura;

	public HechizoFuego(String nombreHechizo, String tipo, int daño, int duracionQuemadura) {
		super(nombreHechizo, tipo, daño);
		this.duracionQuemadura = duracionQuemadura;
	}
	
	
}
