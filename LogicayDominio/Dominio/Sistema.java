package taller3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Sistema {
	
	public Scanner sc = new Scanner(System.in);
	public static ArrayList<Hechizo> hechizosTotales = new ArrayList<>();
	public static ArrayList<Mago> magosTotales = new ArrayList<>();
	public Sistema() {
		super();
	}

	
	public void controlErrorLetras(int opcion) {
		try {
			opcion = Integer.parseInt(sc.nextLine());
			
		}catch (NumberFormatException e) {
			
		}
	}
	
	public static void cargarHechizos(String rutaHechizos, ArrayList<Hechizo> hechizosTotales) {
		try (Scanner sc1 = new Scanner(new File(rutaHechizos))){
			String linea;
			while(sc1.hasNextLine()) {
				linea = sc1.nextLine();
				String[] partes = linea.split(";");
				String nombreHechizo = partes[0];
				String tipo = partes[1].toLowerCase();
				int dano = Integer.parseInt(partes[2]);
				String[] partes2 = partes[3].split(",");
				switch (tipo) {
				
				case "agua":
					
					int cantidadHeal  = Integer.parseInt(partes2[0]);
					int presionDelAgua = Integer.parseInt(partes2[1]);
					HechizoAgua agua = new HechizoAgua(nombreHechizo, tipo, dano, cantidadHeal, presionDelAgua);
					hechizosTotales.add(agua);
					break;
				case "tierra":
					int mejoraDefensa = Integer.parseInt(partes[3]);
					HechizoTierra tierra = new HechizoTierra(nombreHechizo, tipo, dano, mejoraDefensa);
					hechizosTotales.add(tierra);
					break;
				case "planta":
					
					int duracionStun = Integer.parseInt(partes2[0]);
					int cantPlantas = Integer.parseInt(partes2[1]);
					HechizoPlanta planta = new HechizoPlanta(nombreHechizo, tipo, dano, duracionStun, cantPlantas);
					hechizosTotales.add(planta);
					break;
				case "fuego":
					int duracionQuemadura = Integer.parseInt(partes[3]);
					HechizoFuego fuego = new HechizoFuego(nombreHechizo, tipo, dano, duracionQuemadura);
					hechizosTotales.add(fuego);
					break;
				}		
			}		
		}catch (FileNotFoundException e) {
			
		}
		
		
		
	}
	
	public static void cargarMagos(String rutaMagos,ArrayList<Hechizo> hechizosTotales) {
		try (Scanner sc1 = new Scanner(new File(rutaMagos))){
			String linea;
			while(sc1.hasNextLine()) {
				linea = sc1.nextLine();
				ArrayList<Hechizo> hechizosTemp = new ArrayList<>();
				String[] partes = linea.split(";");
				String nombreMago = partes[0];
				String hechizos = partes[1];
				String[] partes2 = hechizos.split("\\|");
				for (int i = 0; i < partes2.length; i++) {
					for (Hechizo hechizo : hechizosTotales) {
						if(partes2[i].equals(hechizo.getNombreHechizo())) {
							hechizosTemp.add(hechizo);
						}
					}
				}
				Mago mago = new Mago(nombreMago,hechizosTemp);
				magosTotales.add(mago);
			}
		}catch (FileNotFoundException e) {
			
		}	
	}
	
	public static void eliminarMago(Scanner sc,ArrayList<Mago> magosTotales) {
		System.out.println("Que mago desea eliminar ? :");
		int i = 1;
		for (Mago mago : magosTotales) {
			System.out.println(i+")"+mago);
			i++;
		}
		int eliminar = sc.nextInt();
		System.out.println("Mago " + magosTotales.get(eliminar-1).getNombre() + " Eliminado...");
		magosTotales.remove(eliminar-1);
		
	}
	
	public static void mostrarHechizos(ArrayList<Hechizo> hechizosTotales) {
		System.out.println("Mostrando todos los Hechizos : ");
		for (Hechizo hechizo : hechizosTotales) {
			System.out.println(hechizo);
		}
		
	}
	
	
	public static void mostrarMagos(ArrayList<Mago> magosTotales) {
		System.out.println("Mostrando todos los Magos : ");
		for (Mago mago : magosTotales) {
			System.out.println(mago);
		}
	}
	
	
	
	
	
	public void menu() {
		
		String rutaHechizos = "src/Hechizos.txt";
		String rutaMagos = "src/Magos.txt";
		cargarHechizos(rutaHechizos, hechizosTotales);
		cargarMagos(rutaMagos, hechizosTotales);
		
		
		System.out.println("----Menu----");
		System.out.println("1) Administrador");
		System.out.println("2) Analista");
		int opcion1 = 0;
		
		//controlErrorLetras(opcion1);
		opcion1 = sc.nextInt();
		
		switch (opcion1){
		
		case 1:
			menuAdministrador();
			break;
		case 2:
			menuAnalista();
			break;
		
		}
		
		
	}
	
	
	public void menuAdministrador() {
		sc.nextLine();
		int opcionAdmin = 0;
		do {
			System.out.println("0. Salir");
			System.out.println("1. Agregar Mago");
			System.out.println("2. Modificar Mago");
			System.out.println("3. Eliminar Mago");
			System.out.println("4. Agregar Hechizo");
			System.out.println("5. Modificar Hechzo");
			System.out.println("6. Eliminar Hechizo");
			
			
			//controlErrorLetras(opcionAdmin);
			
			opcionAdmin = sc.nextInt();
			switch (opcionAdmin) {
			
			case 0:
				
				opcionAdmin = 0;
				break;
			case 1:
				
				break;
				
			case 2:
				
				break;
				
			case 3:
				eliminarMago(sc,magosTotales);
				break;
				
			case 4:
				
				break;
				
			case 5:
				
				break;
				
			case 6:
				
				break;
			}
		}while (opcionAdmin != 0);
		menu();
	}
	
	public void menuAnalista() {
		int opcionAnalista = 0;
		do{
			System.out.println();	
			System.out.println("1. Top 10 Mejores Hechizos");
			System.out.println("2. Top 3 Mejores Magos");
			System.out.println("3. Mostrar todos los Hechizos");
			System.out.println("4. Mostrar todos los Magos");
			System.out.println("5. Mostrar todos los Hechizos junto a su puntuacion");
			System.out.println("6. Mostrar todos los Magos junto a su puntuacion");
			
			opcionAnalista = sc.nextInt();
			
			switch (opcionAnalista) {
			
			case 1:
				
				break;
				
			case 2 :
				
				break;
				
			case 3:
				mostrarHechizos(hechizosTotales);
				break;
				
			case 4:
				mostrarMagos(magosTotales);
				break;
				
			case 5:
				
				break;
				
			case 6:
				
				break;
				
			}
			
			
		
		}while(opcionAnalista != 0);
	}
	
	
	
}
