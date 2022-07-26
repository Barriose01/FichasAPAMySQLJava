package APAFichasMySQL;
import org.apache.commons.lang3.text.WordUtils;
import com.mysql.jdbc.Statement;
import java.util.*;

public class QueryLibros {
	Scanner sc = new Scanner(System.in);
	ConexionBD bd;
	String tipoFicha;
	public QueryLibros(String tipoFicha) {
		this.tipoFicha = tipoFicha;
		this.bd = new ConexionBD(this.tipoFicha);
		
	}
	public void recolectarInfoFichaLibro() {
		String nombre,apellido,ano,titulo,lugar,pais,editorial= "";
		String salida = "Q";
		int ano1 = 0;
		while(true) {
			System.out.println("\nGENERADOR DE FUENTE APA PARA LIBROS:");
			System.out.println("Presione (q) en cualquier momento para volver\n ");
			System.out.println("Nombre del autor (EJ: Miguel): ");
			nombre = WordUtils.capitalize(sc.nextLine().strip());
			if(nombre.equals(salida)) {break;}
			System.out.println("Apellido del autor (EJ: De Cervantes): ");
			apellido = WordUtils.capitalize(sc.nextLine().strip());
			if(apellido.equals(salida)) {break;}
			try {
				System.out.println("Año de publicacion (EJ: 1605): ");
				ano = sc.nextLine().strip();
				if(ano.equals("q")) {break;}
				ano1 = Integer.parseInt(ano);
				if(Integer.toString(ano1).length() > 4 || ano1 <= 0) {
					System.out.println("Debe ingresar un año valido");
					continue;
				}
			}catch(Exception e){
				System.out.println("Debe ingresar un año valido");
				continue;
			}
			System.out.println("Titulo del libro (EJ: Don Quijote de La Mancha): ");
			titulo = WordUtils.capitalize(sc.nextLine().strip());
			if(titulo.equals(salida)) {break;}
			System.out.println("Lugar de publicacion (EJ: Madrid): ");
			lugar = WordUtils.capitalize(sc.nextLine().strip());
			if(lugar.equals(salida)) {break;}
			System.out.println("Pais de publicacion (EJ: España): ");
			pais = WordUtils.capitalize(sc.nextLine().strip());
			if(pais.equals(salida)) {break;}
			System.out.println("Editorial (EJ: Santillana): ");
			editorial = WordUtils.capitalize(sc.nextLine().strip());
			if(editorial.equals(salida)) {break;}
			this.generarFichaLibro(nombre, apellido, ano, titulo, lugar, pais, editorial);
			break;
		}
	}
	
	private void generarFichaLibro(String nombre, String apellido, String ano, String titulo, String lugar,
			String pais, String editorial) {
		String ficha = "";
		System.out.println("\nFICHA BIBLIOGRAFICA:\n");
		if(nombre == "" && apellido == "" && ano == "") {
			ficha = titulo + ". (sf). " + lugar + ", " 
		+ pais + ": " + editorial;
		}else if(nombre == "" && apellido == "") {
			ficha = titulo + ". (" + ano + "). " + lugar + ", " + pais + ": " + editorial;
		}else if(ano == "") {
			ficha = apellido + ", " + nombre.charAt(0) + ". (sf). " + titulo + ". " + lugar + ", " + pais 
			+ ": " + editorial;
		}else {
			ficha = apellido + ", " + nombre.charAt(0) + ". (" + ano + "). " + titulo + ". " + lugar + ", " 
		+ pais + ": " + editorial;
		}
		System.out.println(ficha);
		this.crearFichaLibro(nombre, apellido, ano, titulo, lugar, pais, editorial, ficha);
	}
	
	private void crearFichaLibro(String nombre, String apellido, String ano, String titulo, String lugar,
			String pais, String editorial, String ficha) {
		try {
			String sql = "CALL insertarFichaLibro('" + nombre + "', '" + apellido + "', '" + ano + "', '" + titulo 
			+ "', '" + lugar + "', '" + pais + "', '" + editorial + "', '" + ficha + "')";
			Statement statement = (Statement) this.bd.cn.createStatement();
			statement.execute(sql);
			System.out.println("La ficha ha sido ingresada correctamente");
		}catch(Exception e) {
			System.out.println("Error al insertar la ficha");
		}
	}
	
	public void buscarLibro(int opcion) {
		switch(opcion) {
		case 5:
			System.out.println("Ingrese el lugar de publicacion: ");
			String lugar = sc.nextLine().toLowerCase().strip();
			bd.buscarFicha("lugarPublicacion", lugar);
			break;
		case 6:
			System.out.println("Ingrese el pais de publicacion: ");
			String pais = sc.nextLine().toLowerCase().strip();
			bd.buscarFicha("paisPublicacion", pais);
			break;
		case 7:
			System.out.println("Ingrese la editorial del libro: ");
			String editorial = sc.nextLine().toLowerCase().strip();
			bd.buscarFicha("editorial", editorial);
			break;
		}
	}

}
