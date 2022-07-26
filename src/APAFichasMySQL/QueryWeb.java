package APAFichasMySQL;
import java.util.*;

import org.apache.commons.lang3.text.WordUtils;

import com.mysql.jdbc.Statement;
public class QueryWeb {
	Scanner sc = new Scanner(System.in);
	ConexionBD bd;
	String tipoFicha;
	
	public QueryWeb(String tipoFicha) {
		this.tipoFicha = tipoFicha;
		this.bd = new ConexionBD(this.tipoFicha);
	}
	
	public void recolectarInfoFichaWeb() {
		String nombre,apellido,ano,titulo,fecha,url = "";
		String salida = "Q";
		int ano1 = 0;
		while(true) {
			System.out.println("\nGENERADOR DE FUENTE APA PARA WEB:");
			System.out.println("Presione (q) en cualquier momento para volver\n ");
			System.out.println("Nombre del autor (EJ: Carlos): ");
			nombre = WordUtils.capitalize(sc.nextLine().strip());
			if(nombre.equals(salida)) {break;}
			System.out.println("Apellido del autor (EJ: Delgado): ");
			apellido = WordUtils.capitalize(sc.nextLine().strip());
			if(apellido.equals(salida)) {break;}
			System.out.println("Año de publicacion (EJ: 2021 ): ");
			try {
				ano = sc.nextLine().strip();
				if(ano.equals("q")) {break;}
				ano1 = Integer.parseInt(ano);
				if(Integer.toString(ano1).length() > 4 || ano1 <= 0) {
					System.out.println("Debe ingresar un año valido");
					continue;
				}
			}catch(Exception e) {
				System.out.println("Debe ingresar un año valido");
				continue;
			}
			System.out.println("Titulo de la publicacion (EJ: Como citar en formato APA): ");
			titulo = WordUtils.capitalize(sc.nextLine().strip());
			if(titulo.equals(salida)) {break;}
			System.out.println("Fecha de recuperacion (EJ: julio 26, 2022): ");
			fecha = WordUtils.capitalize(sc.nextLine().strip());
			if(fecha.equals(salida)) {break;}
			System.out.println("Enlace de recuperacion (EJ: wikipedia.org): ");
			url = WordUtils.capitalize(sc.nextLine().strip());
			if(url.equals(salida)) {break;}
			this.generarFichaWeb(nombre,apellido,ano,titulo,fecha,url);
			break;
		}
		
	}
	private void generarFichaWeb(String nombre, String apellido, String ano, String titulo,
			String fecha, String url) {
		String ficha = "";
		System.out.println("\nFICHA BIBLIOGRAFICA:\n");
		if(nombre == "" && apellido == "" && ano == "") {
			ficha = titulo + ". (sf). Recuperado en " + fecha + ", de " + url;
		}else if(nombre == "" && apellido == "") {
			ficha = titulo + ". (" + ano + "). Recuperado en " + fecha + ", de" + url;
		}else if(ano == "") {
			ficha = apellido + ", " + nombre.charAt(0) + " (sf). " + titulo + ". Recuperado en " + fecha 
			+ ", de " + url;
		}else {
			ficha = apellido + ", " + nombre.charAt(0) + " (" + ano + "). " + titulo + ". Recuperado en " 
			+ fecha + ", de " + url; 
		}
		System.out.println(ficha);
		this.crearFichaWeb(nombre, apellido, ano, titulo, fecha, url, ficha);

	}
	
	private void crearFichaWeb(String nombre, String apellido, String ano, String titulo,
			String fecha, String url, String ficha) {
		try {
			String sql = "CALL insertarFichaWeb('" + nombre + "', '" + apellido + "', '" + ano + "', '" 
		+ titulo + "', '" + fecha + "', '" + url + "', '" + ficha + "')"; 
			Statement statement = (Statement) this.bd.cn.createStatement();
			statement.execute(sql);
			System.out.println("La ficha ha sido ingresada correctamente");
		}catch(Exception e) {
			System.out.println("Error al insertar la ficha");
		}
	}
	public void buscarFichaWeb(int opcion) {
		switch(opcion) {
		case 5:
			System.out.println("Ingrese la fecha de recuperacion (EJ: julio 12, 2022): ");
			String fecha = sc.nextLine().toLowerCase().strip();
			bd.buscarFicha("fechaRecuperacion", fecha);
			break;
		case 6:
			System.out.println("Ingrese el enlace de recuperacion: ");
			String enlace = sc.nextLine().toLowerCase().strip();
			bd.buscarFicha("enlaceRecuperacion", enlace);
			break;
		}
	}
}
