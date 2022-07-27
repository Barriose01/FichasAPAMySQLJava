package APAFichasMySQL;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.Statement;
import java.sql.*;
public class ConexionBD {
	Connection cn = this.conectar();
	Scanner sc = new Scanner(System.in);
	String tipoFicha;
	public ConexionBD(String tipoFicha) {
		this.tipoFicha = tipoFicha;
	}
	
	public Connection conectar() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/APAFichas","root",""); 
		}catch(Exception e) {
			System.out.println("Error de conexion con la base de datos");
		}
		return con;
		
	}
	public ArrayList<String> obtenerID(){
		ArrayList<String> id = new ArrayList<String>();
		try {
			String sql = "SELECT id FROM " + this.tipoFicha;
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				id.add(rs.getString("id"));
			}
		}catch(Exception e) {
			System.out.println("Error al realizar la operacion");
		}
		return id;
	}
	public ArrayList<String> obtenerIDFicha() {
		ArrayList<String> fichas = new ArrayList<String>();
		try {
			String sql = "SELECT id,ficha FROM " + this.tipoFicha;
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				fichas.add( "(" + rs.getString("id") + "). " + rs.getString("ficha"));
			}
		}catch(Exception e) {
			System.out.println("Error al realizar la operacion");
		}
		return fichas;
		
	}
	public void mostrarFichas() {
		ArrayList<String> fichas = new ArrayList<String>();
		try {
			String sql = "SELECT ficha FROM " + this.tipoFicha;
			Statement st = (Statement) cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				fichas.add(rs.getString("ficha"));
			
			}
			if(fichas.size() > 0) {
				for(String ficha:fichas) {
					System.out.println("-" + ficha);
				}
			}else {
				System.out.println("No hay fichas para mostrar");
			}
		}catch(Exception e) {
			System.out.println("Error al mostrar las fichas");
		}
		
	}
	public void busquedaGeneral(int opcion) {
		switch(opcion) {
		case 1:
			System.out.println("Ingrese el nombre del autor: ");
			String nombre = sc.nextLine().toLowerCase().strip();
			this.buscarFicha("nombre", nombre);
			break;
		case 2:
			System.out.println("Ingrese el apellido del autor: ");
			String apellido = sc.nextLine().toLowerCase().strip();
			this.buscarFicha("apellido", apellido);
			break;
		case 3:
			System.out.println("Ingrese el año de publicacion: ");
			try {
				String ano = sc.nextLine().toLowerCase().strip();
				int parseAno = Integer.parseInt(ano);
				this.buscarFicha("ano", String.valueOf(parseAno) );
			}catch(Exception e) {
				System.out.println("Ingrese un año valido");
			}	
			break;
		case 4:
			System.out.println("Ingrese el titulo de la publicacion: ");
			String titulo = sc.nextLine();
			this.buscarFicha("titulo", titulo);
			break;
				
		}
		
	}
	public void buscarFicha(String campo, String filtroBusqueda) {
		ArrayList<String> fichas = new ArrayList<String>();
		try {
			String sql = "SELECT ficha FROM " + this.tipoFicha + " WHERE " + campo + " LIKE '%" 
		+ filtroBusqueda + "%'";
			Statement statement = (Statement) cn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				fichas.add(rs.getString("ficha"));
			}
			if(fichas.size() > 0) {
				for(String ficha : fichas) {
					System.out.println("-" + ficha);
				}
			}else {
				System.out.println("No hay fichas para mostrar");
			}
		}catch(Exception e) {
			System.out.println("Error al realizar la busqueda");
		}
	}
	public void eliminarFicha(String idIngresado) {
		try {
			ArrayList<String> id = this.obtenerID();
			if(id.contains(idIngresado)) {
				String sql = "DELETE FROM " + this.tipoFicha + " WHERE id = " + idIngresado;
				Statement statement = (Statement) cn.createStatement();
				statement.execute(sql);
				System.out.println("La ficha ha sido eliminada con exito");
			}else {
				
				System.out.println("Ingrese una opcion valida");
			}
		}catch(Exception e) {
			System.out.println("Error al eliminar la ficha");
		}

	}

}
