import java.io.PrintWriter;
import java.util.Scanner;

public class Simulacion{

	//Numero de iteraciones para la simulacion
	final static int NO_ITERACIONES = 1000000;
	
	//Variable que contendra el tipo de broker
	static String broker = "";
	
	//Para seleccionar el broker a utilizar
	private static void menuBroker(){
		System.out.println("===============================================================");
		System.out.println("Seleccione el número de opción para utilizar como corredor");
		System.out.println("tareas ('broker')");
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
	}
	
	public static void main(String[] args) { 
		menuBroker();
	}
}
