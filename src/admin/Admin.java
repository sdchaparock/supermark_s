package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import compra.Compra;
import java.util.Scanner;
import conexion.Conexion;
import java.sql.SQLException;

public class Admin {
	private String userOk="admin";// Se declara la variable String como userOk para tener en cuenta q es el usuario correcto
	private String passOk="admin";// Se declara la variable String como passOk para tener en cuenta q es la contrasena correcta
	
	public void bienvenido_admin() throws ClassNotFoundException, SQLException{// constructor dedicado a la validacion del admin por medio de dos scanners de tipo String
		//this.passOk=passOk;
		//this.userOk=userOk;
		System.out.println("************Bienvenido Administrador***********");
		System.out.println("ingrese usuario: ");
		Scanner teclado=new Scanner(System.in);
		String user=teclado.next();
		System.out.println("ingrese contrasena: ");
		String pass=teclado.next();
		if(user.equals(userOk)&&pass.equals(passOk)){
			System.out.println("*Administrador validado*");
			this.menu_admin();
		}
		else {
			System.out.println("xxxxxxxxx No se ha validado las credenciales del Administrador xxxxxxxxxxxxx");
			
		}
	}
	
	public void menu_admin() throws ClassNotFoundException, SQLException{
		System.out.println("+-----------------------+");
		System.out.println("| 1. Cargar prod        |");
		System.out.println("| 2. Modificar prod     |");
		System.out.println("| 3. Ver todas compras  |");
		System.out.println("| 4. Ver compra usuario |");
		System.out.println("+-----------------------+");
		Scanner teclado=new Scanner(System.in);
		int opc=teclado.nextInt();
		switch(opc) {
		case 1:{
			Conexion conexion=new Conexion();
			Cargar cargar=new Cargar();
        	cargar.cargar_producto(conexion.conectar());
			break;
		}
		case 2:{
			break;
		}
		case 3:{
			Conexion conexion=new Conexion();
			Compra compra = new Compra();
			compra.consultar_compras(conexion.conectar());
			break;
		}
		case 4:{
			Conexion conexion=new Conexion();
			compra_usuario(conexion.conectar());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + opc);
		}
	}
	
	public void compra_usuario(Connection conexion)throws SQLException{
        Statement declaracion = null;
        String sql;
        ResultSet consulta;
        declaracion=conexion.createStatement(); 
        Scanner teclado=new Scanner(System.in);
        System.out.print("ELIJA UN ID DE COMPRA PARA VER SUS DETALLES: ");
        int id=teclado.nextInt();
        sql="SELECT * FROM compra WHERE id="+id;
        consulta=declaracion.executeQuery(sql);
        while(consulta.next()){
            id=consulta.getInt("id");
            String fecha=consulta.getString("fecha");
            String productos=consulta.getString("productos");
            double total=consulta.getDouble("total");
        
            System.out.println("ID de compra: "+id+" -- Fecha: "+fecha);        
            System.out.println("Detalle de productos: "+'\n'+productos);
            System.out.println("### Total de la venta: "+total+" ###");
        
        }
            System.out.println("");
            System.out.println("FIN DE CONSULTA");
	
        }
	/*// 
	public void compra_usuario(Connection conexion)throws SQLException{
		Statement declaracion = null;
		String sql;
		ResultSet consulta;
		declaracion=conexion.createStatement();
		sql="SELECT id, fecha FROM compra";
		consulta=declaracion.executeQuery(sql);
		while(consulta.next()){
			
			int id=consulta.getInt("id");
			String fecha=consulta.getString("fecha");
			System.out.println("COMPRA ID: "+id+" FECHA: "+fecha);
		}
		System.out.println("FIN DE CONSULTA");
	*/
	/*
	public void menu_admin() throws ClassNotFoundException, SQLException{
		System.out.println("+-----------------------+");
		System.out.println("| 1. Cargar prod        |");
		System.out.println("| 2. Modificar prod     |");
		System.out.println("| 3. Ver todas compras  |");
		System.out.println("| 4. Ver compra usuario |");
		System.out.println("+-----------------------+");
		Scanner teclado=new Scanner(System.in);
		int opc=teclado.nextInt();
		switch(opc) {
		case 1:{
			Conexion conexion=new Conexion();
			Cargar cargar=new Cargar();
        	cargar.cargar_producto(conexion.conectar());
			break;
		}
		case 2:{
			break;
		}
		case 3:{
			Conexion conexion=new Conexion();
			Compra compra = new Compra();
			compra.consultar_compras(conexion.conectar());
			break;
		}
		case 4:{
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + opc);
		}
	}
	*/
}