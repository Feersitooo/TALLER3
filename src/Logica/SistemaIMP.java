package Logica;

import java.util.ArrayList;
import Dominio.*;

public class SistemaIMP  {

	public  ArrayList<Hechizo> hechizosTotales = new ArrayList<>();
	public  ArrayList<Mago> magosTotales = new ArrayList<>();
	public  ArrayList<String> tipos = new ArrayList<>();
	
	
	
	public ArrayList<String> getTipos() {
		return tipos;
	}

	public  ArrayList<Mago> getMagosTotales() {
		return magosTotales;
	}

	public  ArrayList<Hechizo> getHechizosTotales() {
		return hechizosTotales;
	}
//	Agregar magos
//	agrega un mago m a el arraylist MagosTotales
	public  void agregarMagos(Mago m) {
		magosTotales.add(m);
	}
	
// Agregar tipos
//	agrega un tipo de hechizo a el arraylist tipos
	public  void agregarTipos(String tipo) {
		tipos.add(tipo);
	}
	
//	Agregar hechizo
//	agrega un hechizo a a el arraylist de HechizosTotales
	public void agregarHechizo(Hechizo a) {
		hechizosTotales.add(a);
	}
	
	
//	Burbuja
//	Ordena de mayor a menor el puntaje de los magos para clasificarlos del mejor al peor
	public void Burbuja(ArrayList<Integer> puntaje, ArrayList<Mago> magosTotales2, int bandera) {
		
	int n = puntaje.size();
	for (int i = 0; i < n - 1; i++) {
	        for (int j = 0; j < n - 1 - i; j++) {

	            if (puntaje.get(j) < puntaje.get(j + 1)) {
	            	int tmpNum = puntaje.get(j);
	                puntaje.set(j,     puntaje.get(j + 1));
	                puntaje.set(j + 1, tmpNum);
	                
	                Mago hechizostemp = magosTotales2.get(j);
	                magosTotales2.set(j,magosTotales2.get(j+1));
	                magosTotales2.set(j+1,hechizostemp);  
	            }
	        }
	    }		
	}
		
	
//	Burbuja
//	Ordena de mayor a menor el puntaje de los hechizos para clasificarlos del mejor al peor
	public void Burbuja1(ArrayList<Integer> puntaje, ArrayList<Hechizo> magosTotales2, String bandera) {
		
	int n = puntaje.size();
	 for (int i = 0; i < n - 1; i++) {
	        for (int j = 0; j < n - 1 - i; j++) {

	            if (puntaje.get(j) < puntaje.get(j + 1)) {
	            	int tmpNum = puntaje.get(j);
	                puntaje.set(j,     puntaje.get(j + 1));
	                puntaje.set(j + 1, tmpNum);
	                
	                Hechizo hechizostemp = magosTotales2.get(j);
	                magosTotales2.set(j,magosTotales2.get(j+1));
	                magosTotales2.set(j+1,hechizostemp);
	            	
	            	
	                
	            }
	        }
	    }
	}
	
	
//	Hechizo lista de puntajes
// Muestra todos los hechizos con sus respectivos puntajes
	public int HechizoListaPuntajes(ArrayList<Hechizo >hechizosTotales, int a) {
		int suma = 0;
		for(int i = 0 ; i < hechizosTotales.size(); i++) {
			int puntuacion = 0;
			
			switch(hechizosTotales.get(i).getTipo()) {
			
			case "fuego" :
				HechizoFuego fuego = (HechizoFuego) hechizosTotales.get(i);
			puntuacion= fuego.getDaño() * fuego.getDuracionQuemadura();

			
				break;
			case "agua" :
				HechizoAgua agua = (HechizoAgua) hechizosTotales.get(i);
				puntuacion = (agua.getDaño()+agua.getCantidadHeal()+agua.getPresionDelAgua())*2;

				 
				break;
			case "tierra":
				HechizoTierra tierra = (HechizoTierra) hechizosTotales.get(i);
				puntuacion = (tierra.getDaño() * tierra.getMejoraDefensa())/2;

				 
				break;
			case "planta":
				HechizoPlanta planta = (HechizoPlanta) hechizosTotales.get(i);
				puntuacion = planta.getDaño() + (planta.getDuracionStun() * planta.getCantPlantas());

				 
				break;
				
			}
			if(a == -1) {
				System.out.println( i+1+") "+ hechizosTotales.get(i).getNombreHechizo() + " Y su puntuacion es " + puntuacion);
				
				}	
			suma += puntuacion;
		}

		if(a == -2) { 
			return suma;
			
		}
		return 0;
	}
		
// Hechizos lista top 10
//	clasifica a los hechizos por puntuacion y muestra al top 10 mas altos
	public void HechizoListaTop10() {		
		ArrayList<Integer> puntuacionesOrdenada = new ArrayList<>();
		ArrayList<Hechizo> hechizosOrdenados = new ArrayList<>();
	
		for(int i = 0 ; i < hechizosTotales.size(); i++) {
			int puntuacion = 0;
			
			switch(hechizosTotales.get(i).getTipo()) {
			case "fuego" :
				HechizoFuego fuego = (HechizoFuego) hechizosTotales.get(i);
			puntuacion= fuego.getDaño() * fuego.getDuracionQuemadura();
			puntuacionesOrdenada.add(puntuacion);
			hechizosOrdenados.add(hechizosTotales.get(i));
				break;
			case "agua" :
				HechizoAgua agua = (HechizoAgua) hechizosTotales.get(i);
				puntuacion = (agua.getDaño()+agua.getCantidadHeal()+agua.getPresionDelAgua())*2;
				puntuacionesOrdenada.add(puntuacion);
				hechizosOrdenados.add(hechizosTotales.get(i));
				break;
			case "tierra":
				HechizoTierra tierra = (HechizoTierra) hechizosTotales.get(i);
				puntuacion = (tierra.getDaño() *  tierra.getMejoraDefensa())/2;
				puntuacionesOrdenada.add(puntuacion);
				hechizosOrdenados.add(hechizosTotales.get(i));
				break;
			case "planta":
				HechizoPlanta planta = (HechizoPlanta) hechizosTotales.get(i);
				puntuacion = planta.getDaño() + (planta.getDuracionStun() * planta.getCantPlantas());
				puntuacionesOrdenada.add(puntuacion);
				hechizosOrdenados.add(hechizosTotales.get(i));
				break;
		
				
			}
		
		
		}
		Burbuja1(puntuacionesOrdenada,hechizosOrdenados,"1");
		for(int top10 = 0; top10 < 10 ; top10++) {
			System.out.println(top10+1 + ")" + hechizosOrdenados.get(top10).getNombreHechizo() + " Puntuacion: " + puntuacionesOrdenada.get(top10));
			 
		}

	
	
	
	
	}
	
//	MostrarHechizos
//	despliega el arraylist HechizosTotales para mostrar todos los Hechizos
	public void mostrarHechizos(ArrayList<Hechizo> hechizosTotales) {
		System.out.println("Mostrando todos los Hechizos : ");
		for (Hechizo hechizo : hechizosTotales) {
			System.out.println(hechizo);
		}
	}
	
//	Eliminar hechizo
//	Elimina un hechizo en una posicion dada
	public void eliminarHechizo(int posicion) {
		hechizosTotales.remove(posicion);
	}
	
//	Eliminar mago posicion
//	Elimina un mago en una posicion dada
	public void eliminarMagoPosicion(int posicion) {
		magosTotales.remove(posicion);
	}

//	Mostrar magos
//	Despliega el arraylist MagosTotales para mostrar todos los magos
	public void mostrarMagos(ArrayList<Mago> magosTotales) {
		System.out.println("Mostrando todos los Magos : ");
		for (Mago mago : magosTotales) {
			System.out.println(mago);
		}
	}

//	Magos top 10
//	Muestra el top 10 de los magos segun los hechizos que contienen utilizando una burbuja
	public void MagosTop10() {
		ArrayList<Integer> puntajesMago = new ArrayList<>();
		for(int i = 0; i < magosTotales.size(); i++) {
			 ArrayList<Hechizo> hechizosmago = magosTotales.get(i).getHechizos();
			 
			 
			 int puntajemago = HechizoListaPuntajes(hechizosmago, -2);
			 puntajesMago.add(puntajemago);

		}
		Burbuja(puntajesMago,magosTotales,1);
		
		for(int j =0 ; j <  magosTotales.size() && j < 3; j++ ) {
			 System.out.println(j+1 + ") MAGO:" + magosTotales.get(j).getNombre() + " y su puntuacion fue de " + puntajesMago.get(j));
			
			
		}
		
		
		
		
		
		
		
		
		
	}

// 	Magos puntuacion
//	Muestra todos los magos con su respectiva puntuacion
	public void MagosPuntuacion() {
		ArrayList<Integer> puntajesMago = new ArrayList<>();
		for(int i = 0; i < magosTotales.size(); i++) {
			 ArrayList<Hechizo> hechizosmago = magosTotales.get(i).getHechizos();
			 

			 int puntajemago = HechizoListaPuntajes(hechizosmago, -2);
			 puntajesMago.add(puntajemago);

		}
		Burbuja(puntajesMago,magosTotales,1);
		int puntuaciones = 0;
		for(Mago a : magosTotales) {
			
			System.out.println("Puntuaciones " + puntajesMago.get(puntuaciones) + " MAGO " + a.getNombre());
			puntuaciones++;
		}
		
	}



}