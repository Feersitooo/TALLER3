package Dominio;

public class HechizoAgua extends Hechizo {

	private  int cantidadHeal;
	private int presionDelAgua;
	
	public HechizoAgua(String nombreHechizo, String tipo, int daño, int cantidadHeal, int presionDelAgua) {
		super(nombreHechizo, tipo, daño);
		this.cantidadHeal = cantidadHeal;
		this.presionDelAgua = presionDelAgua;
	}

	public int getCantidadHeal() {
		
		return cantidadHeal;
	}

	public int getPresionDelAgua() {
		return presionDelAgua;
	}
	
	
	
	
}
