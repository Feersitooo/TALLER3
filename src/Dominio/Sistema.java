package Dominio;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



import java.io.BufferedWriter;
import java.io.File;

public class Sistema {

	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<Hechizo> hechizosTotales = new ArrayList<>();
	public static ArrayList<Mago> magosTotales = new ArrayList<>();
	private static ArrayList<String> tipos = new ArrayList<>();
	
	
	
	
	
	
	public static void cargarHechizos(String rutaHechizos, ArrayList<Hechizo> hechizosTotales) {
		try (Scanner sc1 = new Scanner(new File(rutaHechizos))) {
			String linea;
			while (sc1.hasNextLine()) {
				linea = sc1.nextLine();
				String[] partes = linea.split(";");
				String nombreHechizo = partes[0];

				String tipo = partes[1].toLowerCase();
				if (!tipos.contains(tipo)) {
					tipos.add(tipo);

				}
				int dano = Integer.parseInt(partes[2]);
				String[] partes2 = partes[3].split(",");
				switch (tipo) {

				case "agua":

					int cantidadHeal = Integer.parseInt(partes2[0]);
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
		} catch (FileNotFoundException e) {

		}

	}

	public static void cargarMagos(String rutaMagos, ArrayList<Hechizo> hechizosTotales) {
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
					for (Hechizo hechizo : hechizosTotales) {
						if (partes2[i].equals(hechizo.getNombreHechizo())) {
							hechizosTemp.add(hechizo);
						}
					}
				}
				Mago mago = new Mago(nombreMago, hechizosTemp);
				magosTotales.add(mago);
			}
		} catch (FileNotFoundException e) {

		}
	}

	public static void CambiarTipo(int i, Hechizo fuego) {
		sc.nextLine();
		System.out.println("a que tipo quiere cambiar su hechizo: ");

		String tiponuevo ;
		do {
			tiponuevo = sc.nextLine();
			if(!tipos.contains(tiponuevo)) {
				System.out.println("ese tipo no existe intente denuevo");
				
			}
		}while(!tipos.contains(tiponuevo));
		if (tipos.contains(tiponuevo.toLowerCase())) {
			String Nombrenuevo = hechizosTotales.get(i).getNombreHechizo();
			int danonuevo = hechizosTotales.get(i).getDaño();
			fuego.setTipo(tiponuevo);
			
			switch (tiponuevo) {
			case "agua":
				hechizosTotales.remove(i);
				int canthealnueva = -2 ;
				do {
				System.out.println("Ingrese cantidad de heal nueva: ");
				canthealnueva = sc.nextInt();
				if(canthealnueva < 0) {
					System.out.println("Ingrese un numero valido");
				}
				}while(canthealnueva < 0 );
				int presionagua = -2 ;
				do {
					System.out.println("Ingrese presion de agua nueva: ");
					presionagua = sc.nextInt();
					if( presionagua < 0 ) {
						System.out.println("Ingrese una cantidad valida");
					}
				}while(presionagua < 0);
				HechizoAgua aguanueva = new HechizoAgua(Nombrenuevo, tiponuevo, danonuevo, canthealnueva,
						presionagua);
				hechizosTotales.add(aguanueva);
				break;
			case "fuego":
				hechizosTotales.remove(i);
				int quemaduranueva;
				do {
					System.out.println("ingrese quemadura nueva");
					quemaduranueva = sc.nextInt();
					if(quemaduranueva < 0 ) {
						System.out.println("Invalido intente denuevo");
					}
				}while(quemaduranueva < 0);
				HechizoFuego fuegonuevo = new HechizoFuego(Nombrenuevo, tiponuevo, danonuevo, quemaduranueva);
				hechizosTotales.add(fuegonuevo);

				break;

			case "tierra":
				hechizosTotales.remove(i);
				int defensanueva;
				do {
					System.out.println("Ingrese cantidad de defensa: ");
					defensanueva = sc.nextInt();
					if (defensanueva < 0) {
						System.out.println("Numero invalido ingrese su numero denuevo: ");
					}
				} while (defensanueva < 0);
				HechizoTierra tierranueva = new HechizoTierra(Nombrenuevo, tiponuevo, danonuevo, defensanueva);
				hechizosTotales.add(tierranueva);

				break;
			case "planta":
				int stunuevo;
				do {
					System.out.println("Ingrese cantidad de stun: ");
					stunuevo = sc.nextInt();
					if (stunuevo < 0) {
						System.out.println("Numero invalido ingrese su numero denuevo: ");
					}
				} while (stunuevo < 0);
				int cantplantasnueva = -9;
				do {
					System.out.println("Ingrese cantidad de plantas: ");
					try {cantplantasnueva = sc.nextInt();} catch (InputMismatchException e) {System.out.println("Ingrese un numero..."); }
					if (cantplantasnueva < 0) {
						System.out.println("Numero invalido ingrese su numero denuevo: ");
					}
				} while (cantplantasnueva < 0);
				HechizoPlanta plantanueva = new HechizoPlanta(Nombrenuevo, tiponuevo, danonuevo,stunuevo, cantplantasnueva);
				hechizosTotales.add(plantanueva);
				break;

			}
		}
			
		

	}
	
	
	public static void Burbuja(ArrayList<Integer> puntaje, ArrayList<Mago> magosTotales2, int bandera) {
		
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
	
	
	
	
	
	
	

		
	public static void Burbuja(ArrayList<Integer> puntaje, ArrayList<Hechizo> magosTotales2, String bandera) {
		
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
	
	
	
	
	public static int HechizoListaPuntajes(ArrayList<Hechizo >hechizosTotales, int a) {
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
	
	
	
	
	
	
		
		public static void HechizoListaTop10() {		
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
		
		
		
		
		
		
		
public static void mostrarHechizos(ArrayList<Hechizo> hechizosTotales) {
			System.out.println("Mostrando todos los Hechizos : ");
			for (Hechizo hechizo : hechizosTotales) {
				System.out.println(hechizo);
			}

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		private static void agregarHechizo() {
			sc.nextLine();
			String tipohechizo;
			String hechizoNuevo;
			int dano = 0 ;

			System.out.println("Agregando nuevo hechizo");
			do {
				System.out.println("Ingrese nombre del hechizo o 0 para salir:");
				hechizoNuevo = sc.nextLine();
				if (hechizoNuevo.equals("0"))
					break;

				do {
					if (hechizoNuevo.equals("0"))break;
					
					
					System.out.println("Ingrese el tipo del hechizo o 0 para salir :");
					tipohechizo = sc.nextLine();
					if (tipohechizo.equals("0"))break;
					
					do {
						if (hechizoNuevo.equals("0"))break;
						if (tipohechizo.equals("0"))break;
						
						System.out.println("Ingrese el daño de el hechizo");
						dano = sc.nextInt();
						if (dano < -1) {
							System.out.println("daño invalido intentar denuevo");
						}
					} while (dano < 0);

					switch (tipohechizo.toLowerCase().strip()) {
					case "agua":
						int cantcuracion;
						do {
							System.out.println("Ingrese cantidad de curacion");
							cantcuracion = sc.nextInt();
						} while (cantcuracion < 0);
						int presionDelAgua;
						do {
							System.out.println("Ingrese presion del agua");
							presionDelAgua = sc.nextInt();
						} while (presionDelAgua < 0);
						HechizoAgua aguaNuevo = new HechizoAgua(hechizoNuevo, tipohechizo, dano, cantcuracion,
								presionDelAgua);
						hechizosTotales.add(aguaNuevo);
						break;
					case "tierra":
						int mejoraDefensa;
						do {
							System.out.println("seleccione la defensa");
							mejoraDefensa = sc.nextInt();
						} while (mejoraDefensa < 0);
						HechizoTierra tierraNuevo = new HechizoTierra(hechizoNuevo, tipohechizo, dano, mejoraDefensa);
						hechizosTotales.add(tierraNuevo);
						break;
					case "planta":

						int duracionStun;
						do {
							System.out.println("Ingrese cantidad de stun (segundos que durara)");
							duracionStun = sc.nextInt();
						} while (duracionStun < 0);
						int cantPlantas = 0;
						do {
							System.out.println("Ingrese las plantas que invocara");
							cantPlantas = sc.nextInt();
						} while (cantPlantas < 0);
						HechizoPlanta plantaNuevo = new HechizoPlanta(hechizoNuevo, tipohechizo, dano, duracionStun,
								cantPlantas);
						hechizosTotales.add(plantaNuevo);
						break;
					case "fuego":
						int duracionQuemadura;
						do {
							System.out.println("Ingrese la duracion de la quemadura");
							duracionQuemadura = sc.nextInt();
						} while (duracionQuemadura < 0);
						HechizoFuego fuego = new HechizoFuego(hechizoNuevo, tipohechizo, dano, duracionQuemadura);
						hechizosTotales.add(fuego);
						break;
						
					default:
						System.out.println("ese tipo no existe trate denuevo");
						sc.nextLine();
						break;

					}

				} while (tipohechizo.equals("0"));

			} while (hechizoNuevo.equals("0"));

		}
	
		private static void ModificarHechizo() {

			String Hechizomod ;
			sc.nextLine();
			do {
				
				System.out.println("Que hechizo desea modificar?(0 para salir)");
				int indice = 1;
				for (int j = 0; j < hechizosTotales.size(); j++) {

					System.out.println(indice + ")" + hechizosTotales.get(j).getNombreHechizo());
					indice++;
				}

				Hechizomod = sc.nextLine();
				for (int i = 0; i < hechizosTotales.size(); i++) {
					if (hechizosTotales.get(i).getNombreHechizo().toLowerCase().equals(Hechizomod.toLowerCase())) {
						switch (hechizosTotales.get(i).getTipo().toLowerCase()) {

						case "fuego":
							HechizoFuego tipo = (HechizoFuego) hechizosTotales.get(i);
							System.out.println("1)Nombre del Hechizo: " + tipo.getNombreHechizo());
							System.out.println("2)Tipo del Hechizo: " + tipo.getTipo());
							System.out.println("3)Daño: " + tipo.getDaño());
							System.out.println("4)Duracion quemadura: " + tipo.getDuracionQuemadura());
							System.out.println("5)Salir ");
							System.out.println("¿Que desea cambiar?");

							int opcionesCambio = sc.nextInt();
							switch (opcionesCambio) {
							case 1:
								sc.nextLine();
								String Nombrenuevo = sc.nextLine();
								tipo.setNombreHechizo(Nombrenuevo);
								break;
							case 2:
								CambiarTipo(i, tipo);
								break;
							case 3:
								int danonuevo;
								do {
									System.out.println("Ingrese daño nuevo");
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
							break;
						case "agua":
							HechizoAgua tipo1 = (HechizoAgua) hechizosTotales.get(i);
							System.out.println("1)Nombre del Hechizo: " + tipo1.getNombreHechizo());
							System.out.println("2)Tipo del Hechizo: " + tipo1.getTipo());
							System.out.println("3)Daño: " + tipo1.getDaño());
							System.out.println("4)Cantidad Curacion: " + tipo1.getCantidadHeal());
							System.out.println("5)Cantidad de presion de agua " + tipo1.getPresionDelAgua());
							System.out.println("6)Salir ");
							System.out.println("¿Que desea cambiar?");

							 opcionesCambio = sc.nextInt();
							switch (opcionesCambio) {
							case 1:
								String Nombrenuevo = sc.nextLine();
								tipo1.setNombreHechizo(Nombrenuevo);
								break;
							case 2:
								CambiarTipo(i, tipo1);
								break;
							case 3:
								int danonuevo = sc.nextInt();
								while (danonuevo <= 0) {
									System.out.println("Ingrese un numero mayor a 0");
									danonuevo = sc.nextInt();
								}
								tipo1.setDaño(danonuevo);
								break;
							case 4:
								int Canthealnueva;
								do { 
									System.out.println("Ingrese cantidad de curacion nueva");
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
									CantPresionNueva = sc.nextInt();
									if(CantPresionNueva < 0 ) {
										System.out.println("Numero invalido intentelo denuevo");
									}
									
								}while(CantPresionNueva <0);
								tipo1.setPresionDelAgua(CantPresionNueva);
								break;
							case 6:
								System.out.println("Volviendo al menu...");
								opcionesCambio = 0;

								break;
							default:
								System.out.println("este no es un cambio valido");
								continue;
							}


						case "planta":
							HechizoPlanta tipo2 = (HechizoPlanta) hechizosTotales.get(i);
							System.out.println("1)Nombre del Hechizo: " + tipo2.getNombreHechizo());
							System.out.println("2)Tipo del Hechizo: " + tipo2.getTipo());
							System.out.println("3)Daño: " + tipo2.getDaño());
							System.out.println("4)Cantidad de plantas: " + tipo2.getCantPlantas());
							System.out.println("5)Salir ");
							System.out.println("¿Que desea cambiar?");

							int opcionesCambio2 = sc.nextInt();
							switch (opcionesCambio2) {
							case 1:
								String Nombrenuevo = sc.nextLine();
								tipo2.setNombreHechizo(Nombrenuevo);
								break;
							case 2:
								CambiarTipo(i, tipo2);
								break;
							case 3:
								int danonuevo;
								do {
									System.out.println("Ingrese daño nuevo");
									danonuevo = sc.nextInt();
									if(danonuevo < 0 ) {
										System.out.println("Ingrese un numero valido");
									}
								}while(danonuevo < 0);
								break;
							case 4:
								int CantPlantasN;
								do {
									System.out.println("Ingrese cantidad de plantas nueva");
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
								opcionesCambio = 0;

								break;
							default:
								System.out.println("este no es un cambio valido");
								continue;
							}
							break;

						case "tierra":

						}

					}

				}

			} while (!Hechizomod.equals("0"));
		}

		
		
		
		
		
		
		public static void eliminarHechizo(ArrayList<Hechizo> hechizosTotales) {
			System.out.println("Que hechizo desea eliminar ? :");
			int i = 1;
			for (Hechizo hechizo : hechizosTotales) {
				System.out.println(i + ")" + hechizo);
				i++;
			}
			int eliminar = sc.nextInt();
			System.out.println("hechizo " + hechizosTotales.get(eliminar - 1).getNombreHechizo() + " Eliminado...");
			hechizosTotales.remove(eliminar - 1);

		}
		
	
		
		
		
		
		
		
		
		
		
		
		
		private static void GuardarCambiosHechizos() {
			try {
				BufferedWriter escritor = new BufferedWriter(new FileWriter("src/Hechizos.txt"));
				for (int i = 0; i < hechizosTotales.size(); i++) {
					escritor.write(hechizosTotales.get(i).getNombreHechizo() + ";" + hechizosTotales.get(i).getTipo() + ";"
							+ Integer.toString(hechizosTotales.get(i).getDaño()) + ";");
					String tipohechizo = hechizosTotales.get(i).getTipo();
					switch (tipohechizo.toLowerCase()) {
					case "fuego":
						HechizoFuego fuego = (HechizoFuego) hechizosTotales.get(i);
						escritor.write(Integer.toString(fuego.getDuracionQuemadura()));
						break;
					case "agua":
						HechizoAgua agua = (HechizoAgua) hechizosTotales.get(i);
						escritor.write(Integer.toString(agua.getCantidadHeal()) + ","
								+ Integer.toString(agua.getPresionDelAgua()));
						break;
					case "tierra":
						HechizoTierra tierra = (HechizoTierra) hechizosTotales.get(i);
						escritor.write(Integer.toString(tierra.getMejoraDefensa()));

						break;
					case "planta":
						HechizoPlanta planta = (HechizoPlanta) hechizosTotales.get(i);
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	public static void agregarMago() {
		sc.nextLine();
		System.out.println("Agregando nuevo mago....");
		System.out.println("Ingrese nombre del mago :");
		String nombreNuevoMago = sc.nextLine();
		ArrayList<Hechizo> hechizosNuevo = new ArrayList<>();
		int opcionHechizos = -10;
		
		do {
			System.out.println("Cargando lista de hechizos");
			for(int i = 0 ; i < hechizosTotales.size(); i++ ) {
				System.out.println(i+1 + ")" +hechizosTotales.get(i));
			}
			System.out.println("Que hechizo desea agregar ?");
			opcionHechizos = sc.nextInt();
			if(opcionHechizos > 0 && opcionHechizos < hechizosTotales.size()) {
			hechizosNuevo.add(hechizosTotales.get(opcionHechizos-1));
			}else if(opcionHechizos == 0 ) {
				System.out.println("Saliendo...");
				
			}else {
				System.out.println("Ingrese un numero valido!!");
			}
			
			
		}while(opcionHechizos != 0);
		
		
		
		
		Mago magoNuevo = new Mago(nombreNuevoMago, hechizosNuevo);
		
		
		
		magosTotales.add(magoNuevo);

	}

	public static void modificarMago() {

		System.out.println("Que mago desea modificar ?");
		int i = 1;
		for (Mago mago : magosTotales) {
			System.out.println(i + ") " + mago);
			i++;
		}
		int magomodificar = sc.nextInt();

		System.out.println("Que deseas modificar del mago " + magosTotales.get(i - 1).getNombre());
		System.out.println("1) Nombre");
		System.out.println("2) Hechizos");

		int modificar = sc.nextInt();

		switch (modificar) {

		case 1:
			System.out.println("Ingresa nuevo nombre :");
			String nuevoNombre = sc.nextLine();
			magosTotales.get(magomodificar - 1).setNombre(nuevoNombre);
			break;

		case 2:
			System.out.println("Que deseas hacer ?");
			System.out.println("1) Eliminar hechizo");
			System.out.println("2) Agregar hechizo");

		}

	}

	public static void eliminarMago(ArrayList<Mago> magosTotales) {
		System.out.println("Que mago desea eliminar ? :");
		int i = 1;
		for (Mago mago : magosTotales) {
			System.out.println(i + ")" + mago);
			i++;
		}
		int eliminar = sc.nextInt();
		System.out.println("Mago " + magosTotales.get(eliminar - 1).getNombre() + " Eliminado...");
		magosTotales.remove(eliminar - 1);

	}








	public static void mostrarMagos(ArrayList<Mago> magosTotales) {
		System.out.println("Mostrando todos los Magos : ");
		for (Mago mago : magosTotales) {
			System.out.println(mago);
		}
	}



	public static void guardarCambiosMagos() {

		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("src/Magos.txt"));
			for (int i = 0; i < magosTotales.size(); i++) {
				escritor.write(magosTotales.get(i).getNombre() + ";");
				for (int j = 0; j < magosTotales.get(i).getHechizos().size(); j++) {
					if (magosTotales.get(i).getHechizos().size() - 1 == j) {
						escritor.write(magosTotales.get(i).getHechizos().get(j) + "");
					} else {
						escritor.write(magosTotales.get(i).getHechizos().get(j) + "|");
					}
				}
				escritor.newLine();
			}

			escritor.close();
		} catch (IOException e) {
			e.getMessage();
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

	
	public void menu() {

		String rutaHechizos = "src/Hechizos.txt";
		String rutaMagos = "src/Magos.txt";
		hechizosTotales.clear();
		magosTotales.clear();

		cargarHechizos(rutaHechizos, hechizosTotales);
		cargarMagos(rutaMagos, hechizosTotales);
		int opcion1 = -2;
		do {
		System.out.println("----Menu----");
		System.out.println("0. Cerrar Programa");
		System.out.println("1. Administrador");
		System.out.println("2. Analista");


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
				// modificar mago
				guardarCambiosMagos();
				break;

			case 3:
				eliminarMago(magosTotales);
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
				eliminarHechizo(hechizosTotales);
				GuardarCambiosHechizos();
				break;
			}
		} while (opcionAdmin != 0);
		menu();
	}
	

	public void menuAnalista() {
		int opcionAnalista = -1;
		do {
			System.out.println();
			System.out.println("0. Salir al menu");
			System.out.println("1. Top 10 Mejores Hechizos");
			System.out.println("2. Top 3 Mejores Magos");
			System.out.println("3. Mostrar todos los Hechizos");
			System.out.println("4. Mostrar todos los Magos");
			System.out.println("5. Mostrar todos los Hechizos junto a su puntuacion");
			System.out.println("6. Mostrar todos los Magos junto a su puntuacion");

			opcionAnalista = sc.nextInt();

			switch (opcionAnalista) {

			case 1:
				HechizoListaTop10();

				break;
				

			case 2:
				MagosTop10();

				break;

			case 3:
				mostrarHechizos(hechizosTotales);
				break;

			case 4:
				mostrarMagos(magosTotales);
				break;

			case 5:
				HechizoListaPuntajes(hechizosTotales, -1);

				break;

			case 6:
				MagosPuntuacion();

				break;

			}

		} while (opcionAnalista != 0);
		menu();
	}

}
