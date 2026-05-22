package Dominio;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



import java.io.BufferedWriter;
import java.io.File;

public class Sistema {
	
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<Hechizo> hechizosTotales = new ArrayList<>();
	public static ArrayList<Mago> magosTotales = new ArrayList<>();
	
	
	
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
	
	public static void agregarMago() {
		sc.nextLine();
		System.out.println("Agregando nuevo mago....");
		System.out.println("Ingrese nombre del mago :");
		String nombreNuevoMago = sc.nextLine();
		ArrayList<Hechizo> hechizosVacio = new ArrayList<>();
		Mago magoNuevo = new Mago(nombreNuevoMago,hechizosVacio);
		magosTotales.add(magoNuevo);
		//se debe agregar un mago sin ningun hechizo ??
	}
	
	public static void modificarMago(Scanner sc,ArrayList<Mago> magosTotales) {
		
		System.out.println("Que mago desea modificar ?");
		int i = 1;
		for(Mago mago : magosTotales) {
			System.out.println(i + ") "+ mago);
			i++;
		}
		
		System.out.println("Que deseas modificar del mago " + magosTotales.get(i-1).getNombre());
		System.out.println("1) Nombre");
		System.out.println("2) Hechizos");
		
		int modificar = sc.nextInt();
		
		switch (modificar) {
		
		case 1:
			System.out.println("Ingresa nuevo nombre :");
			String nuevoNombre = sc.nextLine();
			magosTotales.get(i-1).setNombre(nuevoNombre);
			break;
			
		case 2:
			System.out.println("Que deseas hacer ?");
			System.out.println("1) Eliminar hechizo");
			System.out.println("2) Agregar hechizo");
			
			
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
	
	
	public static void agregarHechizo() {
		sc.nextLine();
		String tipohechizo;
		String hechizoNuevo;
		int dano = 0;
		
		
		System.out.println("Agregando nuevo hechizo");
		do {
		System.out.println("Ingrese nombre del hechizo o 0 para salir:");
		hechizoNuevo = sc.nextLine();
		if(hechizoNuevo.equals("0")) break;
	
		do {
			if(hechizoNuevo.equals("0")) break;
		System.out.println("Ingrese el tipo del hechizo o 0 para salir :");
		tipohechizo = sc.nextLine();
		if(tipohechizo.equals("0")) break;
		do {
			if(hechizoNuevo.equals("0")) break;
			if(tipohechizo.equals("0")) break;
			System.out.println("Ingrese el daño de el hechizo");
			 dano = sc.nextInt();
			 if(dano< -1) {
				 System.out.println("daño invalido intentar denuevo");
			 }
		}while(dano<0);
		
		switch(tipohechizo.toLowerCase().strip()) {
		case "agua":
		int cantcuracion ; 
		do {
			System.out.println("Ingrese cantidad de curacion");
			cantcuracion = sc.nextInt();
		}while(cantcuracion < 0);
		int presionDelAgua ;
		do {
			System.out.println("Ingrese presion del agua");
			presionDelAgua = sc.nextInt();
		}while (presionDelAgua < 0);
		HechizoAgua aguaNuevo = new HechizoAgua(hechizoNuevo, tipohechizo, dano,cantcuracion, presionDelAgua);
		hechizosTotales.add(aguaNuevo);
		break;
	case "tierra":
		int mejoraDefensa;
		do {
			System.out.println("seleccione la defensa");
			mejoraDefensa = sc.nextInt();
		}while(mejoraDefensa < 0);
		HechizoTierra tierraNuevo = new HechizoTierra(hechizoNuevo, tipohechizo, dano , mejoraDefensa);
		hechizosTotales.add(tierraNuevo);
		break;
	case "planta":
		
		int duracionStun ;
		do {
			System.out.println("Ingrese cantidad de stun (segundos que durara)");
			duracionStun = sc.nextInt();
		}while(duracionStun < 0);
		int cantPlantas = 0;
		do {
			System.out.println("Ingrese las plantas que invocara");
			cantPlantas = sc.nextInt();
		}while (cantPlantas < 0);
		HechizoPlanta plantaNuevo = new HechizoPlanta(hechizoNuevo, tipohechizo, dano , duracionStun, cantPlantas);
		hechizosTotales.add(plantaNuevo);
		break;
	case "fuego":
		int duracionQuemadura ;
		do {
			System.out.println("Ingrese la duracion de la quemadura");
			duracionQuemadura = sc.nextInt();
		}while (duracionQuemadura < 0);
		HechizoFuego fuego = new HechizoFuego(hechizoNuevo, tipohechizo, dano, duracionQuemadura);
		hechizosTotales.add(fuego);
		break;
	default: 
		System.out.println("ese tipo no existe trate denuevo");
		sc.nextLine();
		break;
		
		}
		
		
		
		
		}while(tipohechizo.equals("0"));
		
		}while(hechizoNuevo.equals("0") );
		
		
		
	}
	
	public static void GuardarCambiosHechizos() {
	try{
		BufferedWriter escritor = new BufferedWriter(new FileWriter("src/Hechizos.txt"));
		for (int i = 0; i < hechizosTotales.size(); i++) {
			escritor.write(hechizosTotales.get(i).getNombreHechizo() + ";" + hechizosTotales.get(i).getTipo() + ";" + Integer.toString(hechizosTotales.get(i).getDaño()) + ";");
			String tipohechizo = hechizosTotales.get(i).getTipo();
			switch(tipohechizo.toLowerCase()) {
			 case "fuego":
				 HechizoFuego fuego = (HechizoFuego) hechizosTotales.get(i); 
				escritor.write(Integer.toString(fuego.getDuracionQuemadura()));
				break;
			case "agua":
				HechizoAgua agua = (HechizoAgua) hechizosTotales.get(i);
				escritor.write(Integer.toString(agua.getCantidadHeal()) + "," + Integer.toString(agua.getPresionDelAgua()));
			break;
			case "tierra":
				HechizoTierra tierra = (HechizoTierra) hechizosTotales.get(i);
				escritor.write(Integer.toString(tierra.getMejoraDefensa()));
						
				break;
			case "planta":
				HechizoPlanta planta = (HechizoPlanta) hechizosTotales.get(i);
				escritor.write(Integer.toString(planta.getDuracionStun()) + "," + Integer.toString(planta.getCantPlantas()));
				
				
				break;
			}
			escritor.newLine();	
			 
		}
		
		escritor.close();
	}catch (IOException E) {
		
	}	
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
	
	public static void eliminarHechizo(Scanner sc,ArrayList<Hechizo> hechizosTotales) {
		System.out.println("Que hechizo desea eliminar ? :");
		int i = 1;
		for (Hechizo hechizo : hechizosTotales) {
			System.out.println(i+")"+ hechizo);
			i++;
		}
		int eliminar = sc.nextInt();
		System.out.println("hechizo " + hechizosTotales.get(eliminar-1).getNombreHechizo() + " Eliminado...");
		hechizosTotales.remove(eliminar-1);
		
	}
	
	public static void guardarCambiosMagos() {
		

		try{
			BufferedWriter escritor = new BufferedWriter(new FileWriter("src/Magos.txt"));
			for (int i = 0; i < magosTotales.size(); i++) {
				escritor.write(magosTotales.get(i).getNombre() + ";");
				for (int j = 0; j < magosTotales.get(i).getHechizos().size(); j++) {
					if(magosTotales.get(i).getHechizos().size()-1 == j) {
						escritor.write(magosTotales.get(i).getHechizos().get(j)+"");
					}else {
						escritor.write(magosTotales.get(i).getHechizos().get(j) + "|");
					}
				}
				escritor.newLine();	
			} 
			
			escritor.close();
		}catch (IOException e) {
			e.getMessage();
			
		}	
	}
	
	public void menu() {
		
		String rutaHechizos = "src/Hechizos.txt";
		String rutaMagos = "src/Magos.txt";
		hechizosTotales.clear();
		magosTotales.clear();
		
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
		int opcionAdmin = -3;
		do {
			System.out.println("0. Salir");
			System.out.println("1. Agregar Mago");
			System.out.println("2. Modificar Mago");
			System.out.println("3. Eliminar Mago");
			System.out.println("4. Agregar Hechizo");
			System.out.println("5. Modificar Hechizo");
			System.out.println("6. Eliminar Hechizo");
			
			
			//controlErrorLetras(opcionAdmin);
			
			opcionAdmin = sc.nextInt();
			switch (opcionAdmin) {
			
			case 0:
				System.out.println("Saliendo.......");
				break;
			case 1:
				agregarMago();
				guardarCambiosMagos();
				break;
				
			case 2:
				//modificar mago
				guardarCambiosMagos();
				break;
				
			case 3:
				eliminarMago(sc,magosTotales);
				guardarCambiosMagos();
				break;
				
			case 4:
				agregarHechizo();
				GuardarCambiosHechizos();
				continue;
				
			case 5:
				System.out.println("lol");
				break;
				
			case 6:
				eliminarHechizo(sc,hechizosTotales);
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
