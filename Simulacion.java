import java.io.PrintWriter;
import java.util.Scanner;

public class Simulacion{

	//Numero de iteraciones para la simulacion
	final static int NO_ITERACIONES = 1000000;
	
	//Variable que contendra el tipo de broker
	static String broker = "";
	
	//Arreglo de procesadores a utilizar
	static Procesador [] procesadores; 
	
	//Para seleccionar el broker a utilizar
	private static void menuBroker(){
		System.out.println("===============================================================");
		System.out.println("Seleccione el número de opción para utilizar como corredor");
		System.out.println("de tareas ('broker')");
		System.out.println("1. ROUNDROBIN");
		System.out.println("2. LIST");
		System.out.println("3. PARETOFRACTAL");
		System.out.println("===============================================================");
		
		Scanner scan = new Scanner(System.in);
		int opcion = scan.nextInt();
		
		switch(opcion){
			case 1:
				broker="roundrobin";
				break;
			case 2:
				broker="list";
				break;
			case 3:
				broker="pareto";
				break;
			default:
				System.out.println("Opcion invlálida, seleccione un número del 1 al 3");
				menuBroker();
		}
		
		System.out.println("\n===============================================================");
		System.out.println("Ingrese el número de procesadores a utilizar (minimo 1)");
		System.out.println("===============================================================");
		
		opcion = scan.nextInt();
		if(opcion>=1){
			procesadores = new Procesador[opcion];
		}else{
			System.out.println("Ingrese  un número de procesadores mayor o igual a 1");
			menuBroker();
		}
	}
	
	//Algorito corredor de tareas
	private static void listBroker(Tarea tarea){
		
	}
	
	public static void main(String[] args) { 
		menuBroker();
	}
}
