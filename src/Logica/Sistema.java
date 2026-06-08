package Logica;

import java.util.ArrayList;

import Dominio.*;

public interface Sistema {
	
	
	void agregarMagos(Mago magos);
	void agregarHechizo(Hechizo Hechizo);
	void Burbuja(ArrayList<Integer> Puntajes,ArrayList<Mago> magos ,int interponermetodo );
	void Burbuja1(ArrayList<Integer> Puntajes,ArrayList<Hechizo> Hechizos,String interponermetodo );
	void eliminarHechizo(int i);
	void eliminarMagoPosicion(int posicion);
	ArrayList<Hechizo> getHechizosTotales();
	ArrayList<Mago> getMagosTotales();
	ArrayList<String> getTipos();
	int  HechizoListaPuntajes(ArrayList<Hechizo> hechizos, int metodo);
	void HechizoListaTop10();
	void MagosPuntuacion();
	void MagosTop10();
	void mostrarHechizos(ArrayList<Hechizo> hechizo);
	void mostrarMagos(ArrayList<Mago> magos);

}

