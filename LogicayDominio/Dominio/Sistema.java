package taller3;

import java.util.Scanner;

public class Sistema {

	private Scanner sc = new Scanner(System.in);
	
	public Sistema() {
		super();
	}

	
	public void controlErrorLetras(int opcion) {
		try {
			opcion = Integer.parseInt(sc.nextLine());
			
		}catch (NumberFormatException e) {
			
		}
	}
	
	
	public void menu() {
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
				
				break;
				
			case 4:
				
				break;
				
			case 5:
				
				break;
				
			case 6:
				
				break;
				
			}
			
			
		
		}while(opcionAnalista != 0);
	}
	
	
	
}
