public class List extends Broker{


	public void asignaTarea(Tarea t){
		Procesador.sortByQueueSize();
        
        for(int i=0; i<t.getNum(); i++){
			
			if(Simulacion.procesadores[i].isEmpty()){
				//Hacer set de departure
				
			}

				Simulacion.procesadores[i].pushTarea(t);
				System.out.println("Se metio la tarea al Proc: "+i);


		}
	}
}