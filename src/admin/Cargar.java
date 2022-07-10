package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import Producto.Producto;

public class Cargar {
	
	public void cargar_producto(Connection conexion) throws SQLException{
		Scanner leer = new Scanner(System.in);
		Producto producto=new Producto();
		
		System.out.println("*************** SE CARGARAN PRODUCTOS *****************");
		
    	System.out.print("Ingrese nombre de producto: ");
    	producto.setNombre(leer.nextLine());
    	
        System.out.print("Ingrese el precio unitario del producto: ");
        producto.setPrecio(leer.nextDouble());
        
        System.out.println("Ingrese un numero de categoria: ");
        producto.setIdCategoria(leer.nextInt());
        
        System.out.print("Ingrese el stock disponible: ");
        producto.setStock(leer.nextInt());
	
        PreparedStatement instrucciones=conexion.prepareStatement("INSERT INTO producto VALUES(?,?,?,?,?)");
        instrucciones.setString(1, "0");
        instrucciones.setString(2, producto.getNombre());
        instrucciones.setDouble(3, producto.getPrecio());
        instrucciones.setInt   (4, producto.getIdCategoria());
        instrucciones.setInt   (5, producto.getStock());
        instrucciones.executeUpdate();
        
        System.out.println("");
        System.out.println("*************PRODUCTO CARGADO CON EXITO****************");
	}
}
