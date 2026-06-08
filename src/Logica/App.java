package Logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Dominio.*;

public class App {
	public static Scanner sc = new Scanner(System.in);
	public static SistemaImp sistema = new SistemaImp();
	public static String rutaHechizos = "src/Hechizos.txt"; 
	public static String rutaMagos = "src/Magos.txt";
	
	public static void menuAdministrador() {
		sc.nextLine();
		int opcionAdmin = -10;
		do {
			System.out.println("0. Salir");
			System.out.println("1. Agregar Mago");
			System.out.println("2. Modificar Mago");
			System.out.println("3. Eliminar Mago");
			System.out.println("4. Agregar Hechizo");
			System.out.println("5. Modificar Hechizo");
			System.out.println("6. Eliminar Hechizo");

			if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); opcionAdmin = -10; continue; }
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
				modificarMago();
				guardarCambiosMagos();
				
				break;

			case 3:
				eliminarMago();
				guardarCambiosMagos();
				
				break;

			case 4:
				agregarHechizo();
				GuardarCambiosHechizos();
				
				break;

			case 5:
				ModificarHechizo();
				GuardarCambiosHechizos();

				break;

			case 6:
				eliminarHechizo();
				GuardarCambiosHechizos();
				
				break;
				
			}
		} while (opcionAdmin != 0);
		menu();
	}
	
	public static void menu() {
		sistema.hechizosTotales.clear();
		sistema.magosTotales.clear();
		

		cargarHechizos(rutaHechizos);
		cargarMagos(rutaMagos);
		int opcion1 = -10;
		do {
		System.out.println("----Menu----");
		System.out.println("0. Cerrar Programa");
		System.out.println("1. Administrador");
		System.out.println("2. Analista");

		if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); opcion1 = -10; continue; }
		opcion1 = sc.nextInt();

		switch (opcion1) {

		case 1:
			menuAdministrador();
			break;
		case 2:
			menuAnalista();
			break;
		}
		}while(opcion1 != 0);
		System.out.println("Saliendo...");

	}
	
	public static void menuAnalista() {
		int opcionAnalista = -10;
		do {
			System.out.println();
			System.out.println("0. Salir al menu");
			System.out.println("1. Top 10 Mejores Hechizos");
			System.out.println("2. Top 3 Mejores Magos");
			System.out.println("3. Mostrar todos los Hechizos");
			System.out.println("4. Mostrar todos los Magos");
			System.out.println("5. Mostrar todos los Hechizos junto a su puntuacion");
			System.out.println("6. Mostrar todos los Magos junto a su puntuacion");
			
			if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); opcionAnalista = -10; continue; }

			opcionAnalista = sc.nextInt();

			switch (opcionAnalista) {

			case 1:
				sistema.HechizoListaTop10();

				break;
				

			case 2:
				sistema.MagosTop10();

				break;

			case 3:
				sistema.mostrarHechizos(sistema.getHechizosTotales());
				break;

			case 4:
				sistema.mostrarMagos(sistema.getMagosTotales());
				break;

			case 5:
				sistema.HechizoListaPuntajes(sistema.getHechizosTotales(), -1);

				break;

			case 6:
				sistema.MagosPuntuacion();

				break;

			}

		} while (opcionAnalista != 0);
		menu();
	}
	
	public static void cargarHechizos(String rutaHechizos) {
		try (Scanner sc1 = new Scanner(new File(rutaHechizos))) {
			String linea;
			while (sc1.hasNextLine()) {
				linea = sc1.nextLine();
				String[] partes = linea.split(";");
				String nombreHechizo = partes[0];

				String tipo = partes[1].toLowerCase();
				if (!sistema.tipos.contains(tipo)) {
					sistema.agregarTipos(tipo);

				}
				int dano = Integer.parseInt(partes[2]);
				String[] partes2 = partes[3].split(",");
				Hechizo a = null;
				switch (tipo) {

				case "agua":

					int cantidadHeal = Integer.parseInt(partes2[0]);
					int presionDelAgua = Integer.parseInt(partes2[1]);
					a = new HechizoAgua(nombreHechizo, tipo, dano, cantidadHeal, presionDelAgua);
					
					break;
				case "tierra":
					int mejoraDefensa = Integer.parseInt(partes[3]);
					 a = new HechizoTierra(nombreHechizo, tipo, dano, mejoraDefensa);
					
					break;
				case "planta":

					int duracionStun = Integer.parseInt(partes2[0]);
					int cantPlantas = Integer.parseInt(partes2[1]);
					 a = new HechizoPlanta(nombreHechizo, tipo, dano, duracionStun, cantPlantas);
				
					break;
				case "fuego":
					int duracionQuemadura = Integer.parseInt(partes[3]);
					 a = new HechizoFuego(nombreHechizo, tipo, dano, duracionQuemadura);
					
					break;
				}
				if (a != null) {
					sistema.agregarHechizo(a);
				}
			}
		} catch (FileNotFoundException e) {

		}

	}
	
	public static void cargarMagos(String rutaMagos) {
			try (Scanner sc1 = new Scanner(new File(rutaMagos))) {
				String linea;
				while (sc1.hasNextLine()) {
					linea = sc1.nextLine();
					ArrayList<Hechizo> hechizosTemp = new ArrayList<>();
					String[] partes = linea.split(";");
					String nombreMago = partes[0];
					String hechizos = partes[1];
					String[] partes2 = hechizos.split("\\|");
					for (int i = 0; i < partes2.length; i++) {
						for (Hechizo hechizo : sistema.getHechizosTotales()) {
							if (partes2[i].equals(hechizo.getNombreHechizo())) {
								hechizosTemp.add(hechizo);
							}
						}
					}
					Mago mago = new Mago(nombreMago, hechizosTemp);
					sistema.agregarMagos(mago);
				}
			} catch (FileNotFoundException e) {

			}
		
		
	}

	public static void CambiarTipo(int i, Hechizo fuego) {
		sc.nextLine();
		String Nombrenuevo = sistema.getHechizosTotales().get(i).getNombreHechizo();
		int danonuevo = sistema.getHechizosTotales().get(i).getDaño();
		ArrayList<Mago> MagosConHechizoeliminado = new ArrayList<>();
		for(int j = 0 ; j < sistema.getMagosTotales().size() ; j++) {
			for(int hechizos = 0 ; hechizos < sistema.getMagosTotales().get(j).getHechizos().size(); hechizos++ ) {
				if(sistema.getMagosTotales().get(j).getHechizos().get(hechizos).equals(sistema.hechizosTotales.get(i))) {
					System.out.println("Eliminando el hechizo de  " + sistema.getMagosTotales().get(j) + "Para despues agregarlo con su nuevo tipo");
					sistema.getMagosTotales().get(j).getHechizos().remove(hechizos);
					MagosConHechizoeliminado.add(sistema.getMagosTotales().get(j));
				}
			}
		}
		sistema.hechizosTotales.remove(i);
		System.out.println("a que tipo quiere cambiar su hechizo: ");

		String tiponuevo ;
		do {
			tiponuevo = sc.nextLine();
			if(!sistema.getTipos().contains(tiponuevo)) {
				System.out.println("ese tipo no existe intente denuevo");
				
			}
		}while(!sistema.getTipos().contains(tiponuevo));
		if (sistema.getTipos().contains(tiponuevo.toLowerCase())) {
			
			fuego.setTipo(tiponuevo);
			
			switch (tiponuevo) {
			
			case "agua":
				
				
				int canthealnueva = -10 ;
				do {
				System.out.println("Ingrese cantidad de heal nueva: ");
				if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); canthealnueva= -10; continue; }
				canthealnueva = sc.nextInt();
				if(canthealnueva < 0) {
					System.out.println("Ingrese un numero valido");
				}
				}while(canthealnueva < 0 );
				int presionagua = -10;
				do {
					System.out.println("Ingrese presion de agua nueva: ");
					if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); presionagua = -1; continue; }
					presionagua = sc.nextInt();
					if( presionagua < 0 ) {
						System.out.println("Ingrese una cantidad valida");
					}
				}while(presionagua < 0);
				HechizoAgua aguanueva = new HechizoAgua(Nombrenuevo, tiponuevo, danonuevo, canthealnueva,
						presionagua);
				
				sistema.agregarHechizo(aguanueva);
				for(int indicemagosremplazarHechizos = 0 ; indicemagosremplazarHechizos < MagosConHechizoeliminado.size(); indicemagosremplazarHechizos++) {
					for(int Indicemago= 0; Indicemago< sistema.magosTotales.size(); Indicemago++) {
						if(MagosConHechizoeliminado.get(indicemagosremplazarHechizos).equals(sistema.magosTotales.get(Indicemago))) {
							sistema.magosTotales.get(Indicemago).getHechizos().add(aguanueva);
							
						}
						
					}
				}
				break;
			case "fuego":
				
				sistema.eliminarHechizo(i);
				int quemaduranueva;
				do {
					System.out.println("ingrese quemadura nueva");
					if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); quemaduranueva = -10; continue; }
					quemaduranueva = sc.nextInt();
					if(quemaduranueva < 0 ) {
						System.out.println("Invalido intente denuevo");
					}
				}while(quemaduranueva < 0);
				HechizoFuego fuegonuevo = new HechizoFuego(Nombrenuevo, tiponuevo, danonuevo, quemaduranueva);
				sistema.agregarHechizo(fuegonuevo);
				for(int indicemagosremplazarHechizos = 0 ; indicemagosremplazarHechizos < MagosConHechizoeliminado.size(); indicemagosremplazarHechizos++) {
					for(int Indicemago= 0; Indicemago< sistema.magosTotales.size(); Indicemago++) {
						if(MagosConHechizoeliminado.get(indicemagosremplazarHechizos).equals(sistema.magosTotales.get(Indicemago))) {
							sistema.magosTotales.get(Indicemago).getHechizos().add(fuegonuevo);
							
						}
						
					}
				}

				break;

			case "tierra":
				
				
				int defensanueva;
				do {
					System.out.println("Ingrese cantidad de defensa: ");
					if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); defensanueva = -10; continue; }
					defensanueva = sc.nextInt();
					if (defensanueva < 0) {
						System.out.println("Numero invalido ingrese su numero denuevo: ");
					}
				} while (defensanueva < 0);
				HechizoTierra tierranueva = new HechizoTierra(Nombrenuevo, tiponuevo, danonuevo, defensanueva);
				sistema.agregarHechizo(tierranueva);
				for(int indicemagosremplazarHechizos = 0 ; indicemagosremplazarHechizos < MagosConHechizoeliminado.size(); indicemagosremplazarHechizos++) {
					for(int Indicemago= 0; Indicemago< sistema.magosTotales.size(); Indicemago++) {
						if(MagosConHechizoeliminado.get(indicemagosremplazarHechizos).equals(sistema.magosTotales.get(Indicemago))) {
							sistema.magosTotales.get(Indicemago).getHechizos().add(tierranueva);
							
						}
						
					}
				}

				break;
			case "planta":
				int stunuevo = -10;
				do {
					System.out.println("Ingrese cantidad de stun: ");
					if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); stunuevo = -10; continue; }
					stunuevo = sc.nextInt();
					if (stunuevo < 0) {
						System.out.println("Numero invalido ingrese su numero denuevo: ");
					}
				} while (stunuevo < 0);
				int cantplantasnueva = -10;
				do {
					System.out.println("Ingrese cantidad de plantas: ");
					if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); cantplantasnueva = -1; continue; }
					cantplantasnueva = sc.nextInt();
					if (cantplantasnueva < 0) {
						System.out.println("Numero invalido ingrese su numero denuevo: ");
					}
				} while (cantplantasnueva < 0);
				HechizoPlanta plantanueva = new HechizoPlanta(Nombrenuevo, tiponuevo, danonuevo,stunuevo, cantplantasnueva);
				sistema.agregarHechizo(plantanueva);
				for(int indicemagosremplazarHechizos = 0 ; indicemagosremplazarHechizos < MagosConHechizoeliminado.size(); indicemagosremplazarHechizos++) {
					for(int Indicemago= 0; Indicemago< sistema.magosTotales.size(); Indicemago++) {
						if(MagosConHechizoeliminado.get(indicemagosremplazarHechizos).equals(sistema.magosTotales.get(Indicemago))) {
							sistema.magosTotales.get(Indicemago).getHechizos().add(plantanueva);
							
						}
						
					}
				}
				
				break;

			}
		}
	}

	public static void eliminarHechizo() {
		int eliminar = 10;
		do {
		System.out.println("Que hechizo desea eliminar ? (0 para salir)");
		int i = 1;
		for (Hechizo hechizo : sistema.getHechizosTotales()) {
			System.out.println(i + ")" + hechizo);
			i++;
		}
		
		
		if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); eliminar = -10; continue; }
		 eliminar = sc.nextInt();
		 if(eliminar==0) break;
		System.out.println("hechizo " + sistema.getHechizosTotales().get(eliminar - 1).getNombreHechizo() + " Eliminado...");
		sistema.eliminarHechizo(eliminar -1);
		}while(eliminar != 0);
	}
	
	public static void eliminarMago() {
		System.out.println("Que mago desea eliminar ? :");
		
		
		int eliminar = -10;

		do {
			int i = 1;
			
			
			for (Mago mago : sistema.getMagosTotales()) {
				System.out.println(i + ")" + mago);
				i++;
			}
		
		if (!sc.hasNextInt()) {
		    sc.next();
		    System.out.println("Ingrese solo números!");
		    eliminar = -10; 
		    continue;
		}
		if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); eliminar = -10; continue; }
		eliminar = sc.nextInt();
		if(eliminar > 0 && eliminar <= sistema.getMagosTotales().size()) {
			System.out.println("Mago " + sistema.getMagosTotales().get(eliminar - 1).getNombre() + " Eliminado...");
			
			sistema.eliminarMagoPosicion(eliminar-1);
		}else {
			System.out.println("Ingrese un numero valido");
		}
		}while(eliminar != 0);
	}
	
	public static void modificarMago() {
		int magomodificar = -10;
		do {
		System.out.println("Que mago desea modificar ?(0 para salir)");
		int i = 1;
		for (Mago mago : sistema.getMagosTotales()) {
			System.out.println(i + ") " + mago);
			i++;
		}
		
		if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); magomodificar = -10; continue; }
		 magomodificar = sc.nextInt();
		 if(magomodificar == 0 ) {
			 break;
		 }

		System.out.println("Que deseas modificar del mago " + sistema.getMagosTotales().get(magomodificar - 1).getNombre());
		System.out.println("1) Nombre");
		System.out.println("2) Hechizos");
		int modificar = -10;
		if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); modificar = -10; continue; }
		modificar = sc.nextInt();

		switch (modificar) {

		case 1:
			sc.nextLine();
			System.out.println("Ingresa nuevo nombre :");
			String nuevoNombre = sc.nextLine();
			while(nuevoNombre.isBlank() || nuevoNombre.isEmpty()) {
				System.out.println("Ingresa un nombre valido");
				nuevoNombre = sc.nextLine();
				
			}
			sistema.getMagosTotales().get(magomodificar - 1).setNombre(nuevoNombre);
			break;
			
		case 2:
			int opcionhechizos = -10;
			do {
			System.out.println("Que deseas hacer ? (0 para salir)");
			System.out.println("1) Eliminar hechizo");
			System.out.println("2) Agregar hechizo");
			
			
			
			if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); opcionhechizos = -10; continue; }
			opcionhechizos = sc.nextInt();
			switch(opcionhechizos) {
			case 1:
				System.out.println("Que hechizo desea eliminar?(0 para salir)");
				int indice=1;
				for(Hechizo hechizos : sistema.magosTotales.get(magomodificar-1).getHechizos()) {
					
					System.out.println(indice+")"+hechizos);
					indice++;
				}
				int Hechizoeliminado = -10;
				if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); Hechizoeliminado = -10; continue; }
				 Hechizoeliminado = sc.nextInt();
				while(Hechizoeliminado <0 || Hechizoeliminado > sistema.magosTotales.get(magomodificar-1).getHechizos().size()) {
					System.out.println("Ingrese un numero valido");
					if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); Hechizoeliminado = -10; continue; }
					
					Hechizoeliminado = sc.nextInt();
				}
				if(Hechizoeliminado == 0) {
					System.out.println("Saliendo...");
					break;
				}
				
				sistema.magosTotales.get(magomodificar-1).getHechizos().remove(Hechizoeliminado-1);
				
				
				
				break;
			case 2:
				indice = 1;
				for(Hechizo hechizos : sistema.hechizosTotales) {
					 
					 System.out.println(indice + ")" + hechizos);
					 
					 indice++;
				}
				System.out.println("Que hechizo desea agregar?(0 para salir)");
				if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); Hechizoeliminado = -10; continue; }
				int Hechizoagregado = sc.nextInt();
				if(Hechizoagregado== 0) break;
				while(Hechizoagregado < 0 || Hechizoagregado > sistema.hechizosTotales.size()) {
					System.out.println("Ingrese un numero valido");
					if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); Hechizoeliminado = -10; continue; }
					
					Hechizoagregado = sc.nextInt();	
				}
				if(sistema.magosTotales.get(magomodificar-1).getHechizos().contains(sistema.hechizosTotales.get(Hechizoagregado-1))) {
					System.out.println("Ese hechizo ya lo tiene el mago, no lo puedes agregar 2 veces");
					break;
				}else {
					sistema.magosTotales.get(magomodificar-1).getHechizos().add(sistema.hechizosTotales.get(Hechizoagregado-1));
					System.out.println("Agregado con exito");
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			case 0:
				break;
			
			}
			if(opcionhechizos == 0) {
				System.out.println("Saliendo...");
			}
			}while(opcionhechizos != 0);
			
			
			
			
			
			
			
			
			
			break;
		default:
			System.out.println("Ingrese un numero valido");

		}

	}while(magomodificar != 0 );
	}
	
	public static void agregarMago() {
		
		sc.nextLine();
		System.out.println("Agregando nuevo mago....");
		System.out.println("Ingrese nombre del mago :");
		String nombreNuevoMago = sc.nextLine();
		while(nombreNuevoMago.isEmpty() || nombreNuevoMago.isBlank()) {
			System.out.println("Ingrese un nombre valido");
			nombreNuevoMago = sc.nextLine();
		}
		ArrayList<Hechizo> hechizosNuevo = new ArrayList<>();
		int opcionHechizos = -10;
		
		do {
			boolean parar = false;
			System.out.println("Cargando lista de hechizos");
			for(int i = 0 ; i < sistema.getHechizosTotales().size(); i++ ) {
				System.out.println(i+1 + ")" + sistema.getHechizosTotales().get(i));
			}
			System.out.println("Que hechizo desea agregar ? (0 para salir)" );
			if (!sc.hasNextInt()) {
			    sc.next();  
			    System.out.println("Ingrese solo números!");
			    opcionHechizos = -10; 
			    continue;  
			}
			if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); opcionHechizos = -10; continue; }
			opcionHechizos = sc.nextInt();
			if(opcionHechizos > 0 && opcionHechizos <= sistema.getHechizosTotales().size()) {
			for(int j = 0; j < hechizosNuevo.size(); j++) {	
			if(hechizosNuevo.get(j).equals(sistema.getHechizosTotales().get(opcionHechizos-1))) {
				System.out.println("ese hechizo ya esta en la lista que quieres agregar");
				System.out.println();
				parar = true;
			}
			if(parar == true) { break;}
			}
			if(!parar) {
				System.out.println("Agregado con exito");
				System.out.println();
				hechizosNuevo.add(sistema.getHechizosTotales().get(opcionHechizos-1));
				
			}
			
			
			
			}else if(opcionHechizos == 0 ) {
				if(hechizosNuevo.isEmpty()) {
					System.out.println("Debes agregar algun hechizo ");
					opcionHechizos = -20;
				} else {
					System.out.println("Saliendo...");
					System.out.println();
				}
				
			}else {
				System.out.println("Ingrese un numero valido!!");
				System.out.println();
			}

			
			
		}while(opcionHechizos != 0 );
	
		Mago magoNuevo = new Mago(nombreNuevoMago, hechizosNuevo);
		sistema.agregarMagos(magoNuevo);
	}

	public static void ModificarHechizo() {

		String Hechizomod ;
		sc.nextLine();
		do {
			
			System.out.println("Que hechizo desea modificar?(0 para salir)");
			int indice = 1;
			for (int j = 0; j < sistema.getHechizosTotales().size(); j++) {

				System.out.println(indice + ")" + sistema.getHechizosTotales().get(j).getNombreHechizo());
				indice++;
			}
			System.out.println("Ingresar el nombre del hechizo tal cual en la lista.");
			Hechizomod = sc.nextLine();
			for (int i = 0; i < sistema.getHechizosTotales().size(); i++) {
				if (sistema.getHechizosTotales().get(i).getNombreHechizo().toLowerCase().equals(Hechizomod.toLowerCase())) {
					switch (sistema.getHechizosTotales().get(i).getTipo().toLowerCase()) {

					case "fuego":
						int opcionesCambio = -10;
						HechizoFuego tipo = (HechizoFuego) sistema.getHechizosTotales().get(i);
						System.out.println("1)Nombre del Hechizo: " + tipo.getNombreHechizo());
						System.out.println("2)Tipo del Hechizo: " + tipo.getTipo());
						System.out.println("3)Daño: " + tipo.getDaño());
						System.out.println("4)Duracion quemadura: " + tipo.getDuracionQuemadura());
						System.out.println("5)Salir ");
						System.out.println("¿Que desea cambiar?");
	
						do {
						
							if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); opcionesCambio = -10; continue; }
						 opcionesCambio = sc.nextInt();
						switch (opcionesCambio) {
						case 1:
							sc.nextLine();
							System.out.println("Ingrese nombre nuevo");
							String Nombrenuevo = sc.nextLine();
							tipo.setNombreHechizo(Nombrenuevo);
							break;
						case 2:
							CambiarTipo(i, tipo);
							break;
						case 3:
							int danonuevo = -10;
							do {
								System.out.println("Ingrese daño nuevo");
								if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); danonuevo = -10; continue; }
								danonuevo = sc.nextInt();
								
								if(danonuevo < 0 ) {
									System.out.println("Ingrese un numero valido");
								}else {
									tipo.setDaño(danonuevo);
								}
							}while(danonuevo < 0);
								sc.nextLine();
							break;
						case 4:
							int duracionQuemaduraN;
							do {
								System.out.println("Ingrese duracion quemadura");
								if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); duracionQuemaduraN= -10; continue; }
								 duracionQuemaduraN = sc.nextInt();
								 if(duracionQuemaduraN<0) {
									 System.out.println("Ingrese un valor valido");
								 }
							}while (duracionQuemaduraN < 0);
							
						
								System.out.println("Ingrese un valor valido");
							tipo.setDuracionQuemadura(duracionQuemaduraN);
							break;
						case 5:
							System.out.println("Volviendo al menu...");
							opcionesCambio = 0;

							break;
						default:
							System.out.println("este no es un cambio valido");
							continue;
						}
						}while(opcionesCambio != 0);
						break;
					case "agua":
						int opcionesCambio1;
						HechizoAgua tipo1 = (HechizoAgua) sistema.getHechizosTotales().get(i);
						System.out.println("1)Nombre del Hechizo: " + tipo1.getNombreHechizo());
						System.out.println("2)Tipo del Hechizo: " + tipo1.getTipo());
						System.out.println("3)Daño: " + tipo1.getDaño());
						System.out.println("4)Cantidad Curacion: " + tipo1.getCantidadHeal());
						System.out.println("5)Cantidad de presion de agua " + tipo1.getPresionDelAgua());
						System.out.println("6)Salir ");
						System.out.println("¿Que desea cambiar?");
						if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); opcionesCambio1 = -10; continue; }
						 opcionesCambio1 = sc.nextInt();
						switch (opcionesCambio1) {
						case 1:
							sc.nextLine();
							System.out.println("Ingrese nombre nuevo: ");
							String Nombrenuevo = sc.nextLine();
							tipo1.setNombreHechizo(Nombrenuevo);
							break;
						case 2:
							CambiarTipo(i, tipo1);
							break;
						case 3:
							int danonuevo = -10;
							if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); danonuevo = -10; continue; }
							 danonuevo = sc.nextInt();
							while (danonuevo < 0) {
								System.out.println("Ingrese un numero mayor a 0");
								danonuevo = sc.nextInt();
							}
							tipo1.setDaño(danonuevo);
							break;
						case 4:
							int Canthealnueva;
							do { 
								System.out.println("Ingrese cantidad de curacion nueva");
								if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); Canthealnueva = -10; continue; }
								
								Canthealnueva = sc.nextInt();
								if(Canthealnueva <0) {
									System.out.println("Tu curacion no puede ser negativa");
								}
							}while(Canthealnueva <0) ;
							
							
							tipo1.setCantidadHeal(Canthealnueva);
							
							break;
						case 5:
							int CantPresionNueva;
							do {
								System.out.println("ingrese cantidad de presion nueva");
								if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); CantPresionNueva = -10; continue; }
								CantPresionNueva = sc.nextInt();
								if(CantPresionNueva < 0 ) {
									System.out.println("Numero invalido intentelo denuevo");
								}
								
							}while(CantPresionNueva <0);
							tipo1.setPresionDelAgua(CantPresionNueva);
							break;
						case 6:
							System.out.println("Volviendo al menu...");
							opcionesCambio1 = 0;

							break;
						default:
							System.out.println("este no es un cambio valido");
							continue;
						}

						break;
					case "planta":
						HechizoPlanta tipo2 = (HechizoPlanta) sistema.getHechizosTotales().get(i);
						System.out.println("1)Nombre del Hechizo: " + tipo2.getNombreHechizo());
						System.out.println("2)Tipo del Hechizo: " + tipo2.getTipo());
						System.out.println("3)Daño: " + tipo2.getDaño());
						System.out.println("4)Cantidad de plantas: " + tipo2.getCantPlantas());
						System.out.println("5)Salir ");
						System.out.println("¿Que desea cambiar?");
						int opcionesCambio2 = -10;
						if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); opcionesCambio2 = -10; continue; }
						opcionesCambio2 = sc.nextInt();
						switch (opcionesCambio2) {
						case 1:
							sc.nextLine();
							System.out.println("Ingrese nombre nuevo: ");
							String Nombrenuevo = sc.nextLine();
							tipo2.setNombreHechizo(Nombrenuevo);
							break;
						case 2:
							CambiarTipo(i, tipo2);
							break;
						case 3:
							int danonuevo;
							do {
								if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); danonuevo = -10; continue; }
								System.out.println("Ingrese daño nuevo");
							
								danonuevo = sc.nextInt();
								if(danonuevo < 0 ) {
									System.out.println("Ingrese un numero valido");
								} else {
									tipo2.setDaño(danonuevo);
								}
							}while(danonuevo < 0);
							break;
						case 4:
							int CantPlantasN;
							do {
								System.out.println("Ingrese cantidad de plantas nueva");
								if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); CantPlantasN = -10; continue; }
								CantPlantasN = sc.nextInt();
								if(CantPlantasN < 0) {
									System.out.println();
									System.out.println("Esto no es un cambio valido, el numero no puede ser negativo");
									System.out.println();
								} else {
									tipo2.setCantPlantas(CantPlantasN);
								}
								
							}while(CantPlantasN< 0);
							break;
						case 5:
							System.out.println("Volviendo al menu...");
							opcionesCambio2 = 0;

							break;
						default:
							System.out.println("este no es un cambio valido");
							continue;
						}
						break;

					case "tierra":
						HechizoTierra tipo3 = (HechizoTierra) sistema.getHechizosTotales().get(i);
						System.out.println("1)Nombre del Hechizo: " + tipo3.getNombreHechizo());
						System.out.println("2)Tipo del Hechizo: " + tipo3.getTipo());
						System.out.println("3)Daño: " + tipo3.getDaño());
						System.out.println("4)Cantidad de Mejora defensa: " + tipo3.getMejoraDefensa());
						System.out.println("5)Salir ");
						System.out.println("¿Que desea cambiar?");
						int opcionesCambio3 = -10;
						if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); opcionesCambio3 = -10; continue; }
						opcionesCambio3 = sc.nextInt();
						switch (opcionesCambio3) {
						case 1:
							sc.nextLine();
							String Nombrenuevo = sc.nextLine();
							tipo3.setNombreHechizo(Nombrenuevo);
							break;
						case 2:
							CambiarTipo(i, tipo3);
							break;
						case 3:
							int danonuevo;
							do {
								if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); danonuevo = -10; continue; }
								System.out.println("Ingrese daño nuevo");
							
								danonuevo = sc.nextInt();
								if(danonuevo < 0 ) {
									System.out.println("Ingrese un numero valido");
								} else {
									tipo3.setDaño(danonuevo);
								}
							}while(danonuevo < 0);
							break;
						case 4:
							int Mejoradefensa;
							do {
								System.out.println("Ingrese cantidad de plantas nueva");
								if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); Mejoradefensa = -10; continue; }
								Mejoradefensa = sc.nextInt();
								if(Mejoradefensa < 0) {
									System.out.println();
									System.out.println("Esto no es un cambio valido, el numero no puede ser negativo");
									System.out.println();
								} else {
									tipo3.setMejoraDefensa(Mejoradefensa);
								}
								
							}while(Mejoradefensa< 0);
							break;
						case 5:
							System.out.println("Volviendo al menu...");
							opcionesCambio3 = 0;

							break;
						default:
							System.out.println("este no es un cambio valido");
							continue;
						}
						break;


						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					}
				}

			}

		} while (!Hechizomod.equals("0"));
	}
	
	public static void agregarHechizo() {
		sc.nextLine();
		String tipohechizo;
		String hechizoNuevo;
		int dano = 0;
		boolean agregarOtro = true;

		System.out.println("Agregando nuevo hechizo");

		do {
		    System.out.println("Ingrese nombre del hechizo o 0 para salir:");
		    hechizoNuevo = sc.nextLine();
		    
		    while (hechizoNuevo.isBlank()) {
		        System.out.println("Ingrese un nombre valido");
		        hechizoNuevo = sc.nextLine();
		    }
		    
		    if (hechizoNuevo.equals("0")) break;

		    // Pedir tipo
		    System.out.println("Ingrese el tipo del hechizo:");
		    tipohechizo = sc.nextLine();
		    while (!sistema.tipos.contains(tipohechizo)) {
		        System.out.println("Ingrese un tipo valido " + sistema.tipos);
		        tipohechizo = sc.nextLine();
		    }
		    if (tipohechizo.equals("0")) break;

		    
		    do {
		        System.out.println("Ingrese el daño del hechizo:");
		        if (!sc.hasNextInt()) {
		            sc.next();
		            System.out.println("Ingrese solo numeros!!");
		            dano = -1;
		            continue;
		        }
		        
		        dano = sc.nextInt();
		        sc.nextLine();
		        if (dano < 0) System.out.println("Daño invalido, intente de nuevo");
		    } while (dano < 0);

		   
		    switch (tipohechizo.toLowerCase().strip()) {
		        case "agua":
		            int cantcuracion;
		            do {
		                System.out.println("Ingrese cantidad de curacion:");
		                if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); cantcuracion = -1; continue; }
		                cantcuracion = sc.nextInt(); sc.nextLine();
		            } while (cantcuracion < 0);

		            int presionDelAgua;
		            do {
		                System.out.println("Ingrese presion del agua:");
		                if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); presionDelAgua = -1; continue; }
		                presionDelAgua = sc.nextInt(); sc.nextLine();
		            } while (presionDelAgua < 0);

		            sistema.agregarHechizo(new HechizoAgua(hechizoNuevo, tipohechizo, dano, cantcuracion, presionDelAgua));
		            break;

		        case "tierra":
		            int mejoraDefensa;
		            do {
		                System.out.println("Ingrese la mejora de defensa:");
		                if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); mejoraDefensa = -1; continue; }
		                mejoraDefensa = sc.nextInt(); sc.nextLine();
		            } while (mejoraDefensa < 0);

		            sistema.agregarHechizo(new HechizoTierra(hechizoNuevo, tipohechizo, dano, mejoraDefensa));
		            break;

		        case "planta":
		            int duracionStun;
		            do {
		                System.out.println("Ingrese duracion del stun (segundos):");
		                if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); duracionStun = -1; continue; }
		                duracionStun = sc.nextInt(); sc.nextLine();
		            } while (duracionStun < 0);

		            int cantPlantas;
		            do {
		                System.out.println("Ingrese las plantas que invocara:");
		                if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); cantPlantas = -1; continue; }
		                cantPlantas = sc.nextInt(); sc.nextLine();
		            } while (cantPlantas < 0);

		            sistema.agregarHechizo(new HechizoPlanta(hechizoNuevo, tipohechizo, dano, duracionStun, cantPlantas));
		            break;

		        case "fuego":
		            int duracionQuemadura;
		            do {
		                System.out.println("Ingrese la duracion de la quemadura:");
		                if (!sc.hasNextInt()) { sc.next(); System.out.println("Ingrese solo numeros!!"); duracionQuemadura = -1; continue; }
		                duracionQuemadura = sc.nextInt(); sc.nextLine();
		            } while (duracionQuemadura < 0);

		            sistema.agregarHechizo(new HechizoFuego(hechizoNuevo, tipohechizo, dano, duracionQuemadura));
		            break;

		        default:
		            System.out.println("Ese tipo no existe, trate de nuevo");
		            break;
		    }

		    System.out.println("Hechizo agregado con exito!");
		    System.out.println("Ingrese 0 para salir o cualquier tecla para agregar otro:");
		    agregarOtro = !sc.nextLine().equals("0");

		} while (agregarOtro);
	}
	
	public static void GuardarCambiosHechizos() {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("src/Hechizos.txt"));
			for (int i = 0; i < sistema.getHechizosTotales().size(); i++) {
				escritor.write(sistema.getHechizosTotales().get(i).getNombreHechizo() + ";" + sistema.getHechizosTotales().get(i).getTipo() + ";"
						+ Integer.toString(sistema.getHechizosTotales().get(i).getDaño()) + ";");
				String tipohechizo = sistema.getHechizosTotales().get(i).getTipo();
				switch (tipohechizo.toLowerCase()) {
				case "fuego":
					HechizoFuego fuego = (HechizoFuego) sistema.getHechizosTotales().get(i);
					escritor.write(Integer.toString(fuego.getDuracionQuemadura()));
					break;
				case "agua":
					HechizoAgua agua = (HechizoAgua) sistema.getHechizosTotales().get(i);
					escritor.write(Integer.toString(agua.getCantidadHeal()) + ","
							+ Integer.toString(agua.getPresionDelAgua()));
					break;
				case "tierra":
					HechizoTierra tierra = (HechizoTierra) sistema.getHechizosTotales().get(i);
					escritor.write(Integer.toString(tierra.getMejoraDefensa()));

					break;
				case "planta":
					HechizoPlanta planta = (HechizoPlanta) sistema.getHechizosTotales().get(i);
					escritor.write(Integer.toString(planta.getDuracionStun()) + ","
							+ Integer.toString(planta.getCantPlantas()));

					break;
				}
				escritor.newLine();

			}

			escritor.close();
		} catch (IOException E) {

		}
	}
	
	public static void guardarCambiosMagos() {

		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("src/Magos.txt"));
			for (int i = 0; i < sistema.getMagosTotales().size(); i++) {
				escritor.write(sistema.getMagosTotales().get(i).getNombre() + ";");
				for (int j = 0; j < sistema.getMagosTotales().get(i).getHechizos().size(); j++) {
					if (sistema.getMagosTotales().get(i).getHechizos().size() - 1 == j) {
						escritor.write(sistema.getMagosTotales().get(i).getHechizos().get(j) + "");
					} else {
						escritor.write(sistema.getMagosTotales().get(i).getHechizos().get(j) + "|");
					}
				}
				escritor.newLine();
			}

			escritor.close();
		} catch (IOException e) {
			e.getMessage();
			}
	}
	
	public static void main(String[] args) {
		cargarHechizos(rutaHechizos);
		cargarMagos(rutaMagos);
		menu();	
		
	}
}
