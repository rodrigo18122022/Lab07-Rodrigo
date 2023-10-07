import java.util.*;
public class VideoJuego4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String iter = "";
		while(!iter.equals("no")) {
			ArrayList<ArrayList<Soldado>> tablero = new ArrayList<ArrayList<Soldado>>();
			inicializarTablero(tablero);
			ArrayList<Soldado> ejercito0 = crearSoldados(tablero, 0);  // Creo y obtengo los
			ArrayList<Soldado> ejercito1 = crearSoldados(tablero, 1);  // soldados del tablero
			imprimirTablero(tablero);
			
			System.out.println("\nEJERCITO 0\n");
			imprimirSoldados(ejercito0);
			soldadoMayorVida(ejercito0);
			promedioNivelVida(ejercito0);
			ordBurbuja(ejercito0);
			ordPorSeleccion(ejercito0);
			imprimirSoldadosOrdenados(ejercito0);
			System.out.println("\nEJERCITO 1\n");
			imprimirSoldados(ejercito1);
			soldadoMayorVida(ejercito1);
			promedioNivelVida(ejercito1);
			ordBurbuja(ejercito1);
			ordPorSeleccion(ejercito1);
			imprimirSoldadosOrdenados(ejercito1);
			
			ganadorDeBatalla(ejercito0, ejercito1);
			
			System.out.print("Desea volver a jugar? (si/no) : ");
			iter = sc.next();
			System.out.println("-----------------------------------------");
			System.out.println("-----------------------------------------\n");
		}
	}
	
	public static void inicializarTablero(ArrayList<ArrayList<Soldado>> x) {
		for(int i = 0;i < 10; i++) {
			x.add(new ArrayList<Soldado>());
			for(int j = 0;j < 10; j++) {
				Soldado a = new Soldado();
				x.get(i).add(a);
			}
		}
	}
	
	public static void imprimirTablero(ArrayList<ArrayList<Soldado>> x) {
		System.out.println("---------------------------------------------------");
		for(int fila = 0; fila < 10; fila++) {
            for(int columna = 0; columna < 10; columna++) {
                if(x.get(fila).get(columna).getNombre() != null) {
                	if(x.get(fila).get(columna).getNombre().charAt(7) == '0')
                		System.out.print("| " + "X" +  x.get(fila).get(columna).getNivelVida() + " ");
                	else
                		System.out.print("| " + "Y" +  x.get(fila).get(columna).getNivelVida() + " ");
                }
                else
                    System.out.print("| __ ");
            }
            System.out.println("|");
		}
		System.out.println("---------------------------------------------------");
    }
	
	public static ArrayList<Soldado> crearSoldados(ArrayList<ArrayList<Soldado>> x, int p) {
		int numeroSoldados = (int)(Math.random() * (10 - 1 + 1) + 1);
		if(p == 0)
			System.out.println("# de soldados del Ejercito " + p + " (X) => " + numeroSoldados);
    	else
    		System.out.println("# de soldados del Ejercito " + p + " (Y)=> " + numeroSoldados);
		ArrayList<Soldado> sold = new ArrayList<Soldado>();
		for (int i = 0; i < numeroSoldados; i++) {
			Soldado soldado = new Soldado();
            soldado.setNombre("Soldado" + p +"X" + (i + 1));
            soldado.setNivelVida((int)(Math.random() * (5 - 1 + 1) + 1));
            int fila, columna;
            do {
            	fila = (int)(Math.random() * (9 - 0 + 1));
                columna = (int)(Math.random() * (9 - 0 + 1));
            } while (x.get(fila).get(columna).getNombre() != null);
            soldado.setFila(fila);
            soldado.setColumna(columna);
            x.get(fila).set(columna, soldado);
            sold.add(soldado);
        }
		return sold;
	}
	
	public static void imprimirSoldados(ArrayList<Soldado> x) {
		for(int i = 0; i < x.size(); i++) {
			System.out.println("Soldado => " + x.get(i).getNombre());
			System.out.println("Nivel de Vida => " + x.get(i).getNivelVida());
			System.out.println("Fila => " + x.get(i).getFila());
			System.out.println("Columna => " + x.get(i).getColumna());
		}
		System.out.println("-----------------------------------");
	}
		
	public static void soldadoMayorVida(ArrayList<Soldado> x) {
		Soldado soldado = new Soldado();
		int vida = 0;
		for(int i = 0; i < x.size(); i++)
			if(vida <= x.get(i).getNivelVida()) {
				vida = x.get(i).getNivelVida();
				soldado = x.get(i);
			}
		System.out.println("Soldado con mayor vida del Ejercito " + soldado.getNombre().charAt(7));
		System.out.println("Nombre => " + soldado.getNombre());
		System.out.println("Nivel de Vida => " + soldado.getNivelVida());
		System.out.println("Fila => " + soldado.getFila());
		System.out.println("Columna => " + soldado.getColumna());
		System.out.println("-----------------------------------------");
	}
	
	public static void promedioNivelVida(ArrayList<Soldado> x) {
		int suma = 0;
		for(int i = 0; i < x.size(); i++)
			suma = suma + x.get(i).getNivelVida();
		System.out.println("Promedio de puntos de vida del Ejercito " 
							+ x.get(0).getNombre().charAt(7) + " => " + (suma / x.size()));
		System.out.println("-----------------------------------------");
	}
	
	public static void ordBurbuja(ArrayList<Soldado> x) {
		for(int i = 1; i < x.size(); i++)
			for(int j = 0; j < x.size() - 1; j++) 
				if(x.get(i).getNivelVida() < x.get(j).getNivelVida()) {
					Soldado aux = x.get(i);
					x.set(i, x.get(j));
					x.set(j, aux);
				}
	}
	
	public static void ordPorSeleccion(ArrayList<Soldado> x) {
        int min, ind;
        for (int i = 0; i < x.size() - 1; i++) {      
              min = x.get(i).getNivelVida();
              ind = i;     
              for (int j = i + 1; j < x.size(); j++)
                    if (x.get(j).getNivelVida() < min) {         
                        min = x.get(j).getNivelVida();            
                        ind = j;
                    }
              if (ind != i){  
            	  Soldado aux = x.get(i);
				  x.set(i, x.get(ind));
				  x.set(ind, aux);
              }
        }
	}
	
	public static void imprimirSoldadosOrdenados(ArrayList<Soldado> x) {
		System.out.println("RANKING\n");
		for(int i = x.size() - 1; i >= 0; i--) {
			System.out.println("Soldado => " + x.get(i).getNombre());
			System.out.println("Nivel de Vida => " + x.get(i).getNivelVida());
		}
		System.out.println("-----------------------------------");
	}
	
	
	// La métrica usada para decidir al GANADOR de la batalla es:
	// La mayor sumatoria del nivel de vida de los soldados de cada ejercito
	
	public static void ganadorDeBatalla(ArrayList<Soldado> x0, ArrayList<Soldado> x1) {
		int sumaVidaEjercito0 = 0;
		System.out.println("\nRESULTADO FINAL\n");
		for(int i = 0; i < x0.size(); i++)
			sumaVidaEjercito0 = sumaVidaEjercito0 + x0.get(i).getNivelVida();
		int sumaVidaEjercito1 = 0;
		for(int i = 0; i < x1.size(); i++)
			sumaVidaEjercito1 = sumaVidaEjercito1 + x1.get(i).getNivelVida();
		if(sumaVidaEjercito0 == sumaVidaEjercito1)
			System.out.println("La batalla termino en EMPATE !!!");
		else {
			if(sumaVidaEjercito0 > sumaVidaEjercito1)
				System.out.println("EJERCITO 0 GANO LA BATALLA !!!");
			if(sumaVidaEjercito0 < sumaVidaEjercito1)
				System.out.println("EJERCITO 1 GANO LA BATALLA !!!");
		}
		System.out.println("-----------------------------------");
	}
}

