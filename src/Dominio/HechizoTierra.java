package Dominio;

public class HechizoTierra extends Hechizo {

	
	private int mejoraDefensa;

	public HechizoTierra(String nombreHechizo, String tipo, int daño, int mejoraDefensa) {
		super(nombreHechizo, tipo, daño);
		this.mejoraDefensa = mejoraDefensa;
	}

	public int getMejoraDefensa() {
		return mejoraDefensa;
	}

	public void setMejoraDefensa(int mejoraDefensa) {
		this.mejoraDefensa = mejoraDefensa;
	}
	
	
	
	
}
