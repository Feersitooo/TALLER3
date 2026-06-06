package Dominio;

import java.util.ArrayList;
import java.util.Scanner;


public class Sistema {

	public  Scanner sc = new Scanner(System.in);
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

	public  void agregarMagos(Mago m) {
		magosTotales.add(m);
	}
	
	public  void agregarTipos(String tipo) {
		tipos.add(tipo);
	}
	
	public void agregarHechizo(Hechizo a) {
		hechizosTotales.add(a);
	}
	
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
		
	public void Burbuja(ArrayList<Integer> puntaje, ArrayList<Hechizo> magosTotales2, String bandera) {
		
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
				puntuacion = (tierra.getDaño() +  tierra.getMejoraDefensa())/2;

				 
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
				puntuacion = (tierra.getDaño() +  tierra.getMejoraDefensa())/2;
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
		Burbuja(puntuacionesOrdenada,hechizosOrdenados,"1");
		for(int top10 = 0; top10 < 10 ; top10++) {
			System.out.println(top10+1 + ")" + hechizosOrdenados.get(top10).getNombreHechizo() + " Puntuacion: " + puntuacionesOrdenada.get(top10));
			 
		}

	
	
	
	
	}
	
	public void mostrarHechizos(ArrayList<Hechizo> hechizosTotales) {
		System.out.println("Mostrando todos los Hechizos : ");
		for (Hechizo hechizo : hechizosTotales) {
			System.out.println(hechizo);
		}
	}
		
	public void eliminarHechizo(int posicion) {
		hechizosTotales.remove(posicion);
	}
	
	public void eliminarMagoPosicion(int posicion) {
		magosTotales.remove(posicion);
	}

	public void mostrarMagos(ArrayList<Mago> magosTotales) {
		System.out.println("Mostrando todos los Magos : ");
		for (Mago mago : magosTotales) {
			System.out.println(mago);
		}
	}

	public void MagosTop10() {
		ArrayList<Integer> puntajesMago = new ArrayList<>();
		for(int i = 0; i < magosTotales.size(); i++) {
			 ArrayList<Hechizo> hechizosmago = magosTotales.get(i).getHechizos();
			 

			 int puntajemago = HechizoListaPuntajes(hechizosmago, -2);
			 puntajesMago.add(puntajemago);

		}
		Burbuja(puntajesMago,magosTotales,1);
		int puntuaciones = 0;
		for(int j =0 ; j <  magosTotales.size() && j < 3; j++ ) {
			 System.out.println(j+1 + ") MAGO:" + magosTotales.get(j).getNombre() + " y su puntuacion fue de " + puntajesMago.get(j));
			
			
		}
		
		
		
		
		
		
		
		
		
	}

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
