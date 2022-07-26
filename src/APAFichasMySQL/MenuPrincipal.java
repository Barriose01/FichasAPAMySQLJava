package APAFichasMySQL;
import java.util.*;
public class MenuPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Scanner sc = new Scanner(System.in);
		MenuOpciones menu;
		String opcion;
		int parseOpcion;
		while(true) {
			System.out.println("\nElegir el medio que se quiere citar");
			System.out.println("(1): Libros");
			System.out.println("(2): Web");
			System.out.println("(3): Salir");
			
			try {
				opcion = sc.nextLine();
				parseOpcion = Integer.parseInt(opcion);
			}catch(Exception e) {
				System.out.println("Ingrese una opcion valida");
				continue;
			}
			if(parseOpcion == 3) {
				break;
			}else if(parseOpcion == 1) {
				menu = new MenuOpciones("fichasLibros");
				menu.menu();
			}else if(parseOpcion == 2) {
				menu = new MenuOpciones("fichasWeb");
				menu.menu();
			}else {
				System.out.println("Ingrese una opcion valida");
			}
		}
		sc.close();
	}

}
