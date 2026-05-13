package taller3;

public class HechizoAgua extends Hechizo {

	private  int cantidadHeal;
	private int presionDelAgua;
	
	public HechizoAgua(String nombreHechizo, String tipo, int daño, int cantidadHeal, int presionDelAgua) {
		super(nombreHechizo, tipo, daño);
		this.cantidadHeal = cantidadHeal;
		this.presionDelAgua = presionDelAgua;
	}
	
	
	
	
}
