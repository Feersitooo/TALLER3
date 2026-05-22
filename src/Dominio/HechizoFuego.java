package Dominio;

public class HechizoFuego extends Hechizo {

	private int duracionQuemadura;

	public HechizoFuego(String nombreHechizo, String tipo, int daño, int duracionQuemadura) {
		super(nombreHechizo, tipo, daño);
		this.duracionQuemadura = duracionQuemadura;
	}

	public int getDuracionQuemadura() {
		return duracionQuemadura;
	}

	public void setDuracionQuemadura(int duracionQuemadura) {
		this.duracionQuemadura = duracionQuemadura;
	}
	
	
}
