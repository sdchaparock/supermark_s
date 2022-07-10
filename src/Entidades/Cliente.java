package Entidades;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cliente {
    
    private String nombre;
    private String apellido;
    private String domicilio;
    private String cuil;
    private String provincia;
    private String codigoPostal;
    private String telefono;
    private String nombre_usuario;
    private String pass;

    public Cliente(){
        
    }
    
    public Cliente(String nombre, String apellido, String domicilio, String cuil, String provincia, String codigoPostal, String telefono, String nombre_usuario, String pass ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.cuil = cuil;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.nombre_usuario=nombre_usuario;
        this.pass=pass;
    }
    
    
    
    
    
    public void registrar(Connection conexion) throws SQLException{
    	Scanner leer = new Scanner(System.in);
    	System.out.print("Nombre :");
    	this.nombre = leer.nextLine();
        System.out.print("Apellido :");
        this.apellido = leer.nextLine(); 
        System.out.print("CUIL :");
        this.cuil = leer.nextLine();
        System.out.print("Domicilio :");
        this.domicilio = leer.nextLine();
        System.out.print("Provincia :");
        this.provincia = leer.nextLine();
        System.out.print("Codigo Postal :");
        this.codigoPostal = leer.nextLine();
        System.out.print("Teléfono : ");
        this.telefono = leer.nextLine();
        System.out.print("Ingrese un nombre de usuario: ");
        this.nombre_usuario=leer.nextLine();
        System.out.print("Ingrese una contraseña para su cuenta: ");
        this.pass=leer.nextLine();
	
        PreparedStatement instrucciones=conexion.prepareStatement("INSERT INTO cliente VALUES(?,?,?,?,?,?,?,?,?,?)");
        instrucciones.setString(1, "0");
        instrucciones.setString(2, nombre);
        instrucciones.setString(3, apellido);
        instrucciones.setString(4, cuil);
        instrucciones.setString(5, domicilio);
        instrucciones.setString(6, provincia);
        instrucciones.setString(7, codigoPostal);
        instrucciones.setString(8, telefono);
        instrucciones.setString(9, nombre_usuario);
        instrucciones.setString(10, pass);
        instrucciones.executeUpdate();
                     
    }
    public void iniciar_sesion(Connection conexion) throws SQLException{
        Scanner leer = new Scanner(System.in);
	Statement declaracion = null;
	String sql;
	ResultSet consulta;
	       
        System.out.print("Ingrese su nombre de usuario: ");
        String usuario=leer.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String pass=leer.nextLine();
        
        declaracion=conexion.createStatement();
        sql = "SELECT nombre_usuario, pass FROM cliente";
        consulta=declaracion.executeQuery(sql);
        while(consulta.next()){
            if((usuario.equals(consulta.getString("nombre_usuario")))&&(pass.equals(consulta.getString("pass")))){
                System.out.println("SESIÓN INICIADA CON ÉXITO"); 
                System.out.println("");
                System.out.println("BIENVENIDO/A A SUPERMARKET");
                System.out.println("");
            }
            else System.out.println("Alguno de los valores ingresados es incorrecto");          
        }
    }
}