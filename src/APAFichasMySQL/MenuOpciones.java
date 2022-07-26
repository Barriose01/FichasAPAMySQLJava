package APAFichasMySQL;
import java.util.*;
public class MenuOpciones {
	Scanner sc = new Scanner(System.in);
	String tipoFicha;
	String opcion;
	ConexionBD conexion;
	QueryLibros ql;
	QueryWeb qw;
	int parseOpcion;
	
	public MenuOpciones(String tipoFicha) {
		this.tipoFicha = tipoFicha;
		this.conexion = new ConexionBD(this.tipoFicha);
		this.ql = new QueryLibros(this.tipoFicha);
		this.qw = new QueryWeb(this.tipoFicha);
	}
	
	public void menu() {
		int salida = 0;
		while(salida == 0) {
			System.out.println("\nUSTED ELIGIO: " + this.tipoFicha);
			System.out.println("(1): Crear ficha");
			System.out.println("(2): Mostrar fichas");
			System.out.println("(3): Buscar fichas");
			System.out.println("(4): Eliminar fichas");
			System.out.println("(5): Volver");
			try {
				opcion = sc.nextLine();
				parseOpcion = Integer.parseInt(opcion);
			}catch(Exception e) {
				System.out.println("Ingrese una opcion valida");
				continue;
			}
			switch(parseOpcion) {
			case 5:
				salida = 1;
				break;
			case 1:
				if(this.tipoFicha == "fichasLibros") {
					ql.recolectarInfoFichaLibro();
				}else {
					qw.recolectarInfoFichaWeb();
				}
				break;
				
			case 2:
				this.conexion.mostrarFichas();
				break;
			case 3:
				this.buscar();
				break;
			case 4:
				this.eliminarFichas();
				break;
			default:
				System.out.println("Ingrese una opcion valida");
				break;
			}
		}
	}
	private void buscar() {
		if(this.tipoFicha == "fichasLibros") {
			this.buscarLibro();
		}else {
			this.buscarWeb();
		}
	}
	private void buscarLibro() {
		int salida = 0;
		while(salida == 0) {
			System.out.println("\nBUSQUEDA DE FICHA");
			System.out.println("(1): Buscar por el nombre del autor");
			System.out.println("(2): Buscar por el apellido del autor");
			System.out.println("(3): Buscar por año de publicacion");
			System.out.println("(4): Buscar por titulo del libro");
			System.out.println("(5): Buscar por lugar de publicacion");
			System.out.println("(6): Buscar por pais de publicacion");
			System.out.println("(7): Buscar por editorial");
			System.out.println("(8): Volver");
			try {
				opcion = sc.nextLine();
				parseOpcion = Integer.parseInt(opcion);
			}catch(Exception e) {
				System.out.println("Ingrese una opcion valida");
				continue;
			}
			switch(parseOpcion) {
			case 8:
				salida = 1;
				break;
			case 1,2,3,4:
				try {
					this.conexion.busquedaGeneral(parseOpcion);
				}catch(Exception e) {
					System.out.println("Error al realizar la busqueda");
				}
				
				break;
			case 5,6,7:
				try {
					this.ql.buscarLibro(parseOpcion);
				}catch(Exception e) {
					System.out.println("Error al realizar la busqueda");
				}
				
				break;
			default:
				System.out.println("Ingrese una opcion valida");
			}
		}
		
	}
	private void buscarWeb() {
		int salida = 0;
		while(salida == 0) {
			System.out.println("\nBUSQUEDA DE FICHA");
			System.out.println("(1): Buscar por el nombre del autor");
			System.out.println("(2): Buscar por el apellido del autor");
			System.out.println("(3): Buscar por año de publicacion");
			System.out.println("(4): Buscar por titulo de la publicacion");
			System.out.println("(5): Buscar por fecha de recuperacion");
			System.out.println("(6): Buscar por enlace de recuperacion");
			System.out.println("(7) Volver");
			try {
				opcion = sc.nextLine();
				parseOpcion = Integer.parseInt(opcion);
			}catch(Exception e) {
				System.out.println("Ingrese una opcion valida");
				continue;
			}
			switch(parseOpcion) {
			case 7:
				salida = 1;
				break;
			case 1,2,3,4:
				try {
					this.conexion.busquedaGeneral(parseOpcion);
				}catch(Exception e) {
					System.out.println("Error al realizar la busqueda");
				}
				break;
			case 5,6:
				try {
					this.qw.buscarFichaWeb(parseOpcion);
				}catch(Exception e) {
					System.out.println("Error al realizar la busqueda");
				}
				break;
			default:
				System.out.println("Ingrese una opcion valida");
				break;
			}
		}

	}
	private void eliminarFichas() {
		String opcion;
		int parseOpcion;
		try {
			while(true) {
				ArrayList<String> fichas = this.conexion.obtenerIDFicha();
				if(fichas.size() > 0) {
					System.out.println("Ingrese el id de la ficha que desea eliminar");
					for(String ficha:fichas) {
						System.out.println(ficha);
					}
					System.out.println("(0): Volver");
					opcion = sc.nextLine().toLowerCase().strip();
					parseOpcion = Integer.parseInt(opcion);
					if(parseOpcion == 0) {
						break;
					}else {
						this.conexion.eliminarFicha(opcion);
					}
				}else {
					System.out.println("No hay fichas para eliminar");
					break;
				}
							}
		}catch(Exception e) {
			System.out.println("Error al intentar eliminar una ficha");
		}
	}

}
