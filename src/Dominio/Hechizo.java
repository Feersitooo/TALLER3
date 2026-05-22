package Dominio;

public abstract class Hechizo {
	
	private String nombreHechizo;
	private String tipo;
	private int daño;
	
	public Hechizo(String nombreHechizo, String tipo, int daño) {
		super();
		this.nombreHechizo = nombreHechizo;
		this.tipo = tipo;
		this.daño = daño;
	}

	public String getNombreHechizo() {
		return nombreHechizo;
	}

	public String getTipo() {
		return tipo;
	}

	public int getDaño() {
		return daño;
	}

	@Override
	public String toString() {
		return  nombreHechizo ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
