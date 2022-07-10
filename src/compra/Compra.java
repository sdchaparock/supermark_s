package compra;

import Producto.Carrito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Compra {   
    private int id ;
    private String fecha;
    private int id_cliente;
    private int id_producto;
    private double catidad;
    private double total;
    
    public Compra(){                
    }
    
    

    public Compra(int id, String fecha, int id_cliente, int id_producto, double catidad, double total) {
        this.id = id;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
        this.catidad = catidad;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public double getCatidad() {
        return catidad;
    }

    public void setCatidad(double catidad) {
        this.catidad = catidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    public void confirmar_compra(Connection conexion, Carrito carrito)throws SQLException{       
        Statement declaracion = null;
	String sql;
	ResultSet consulta;
        String productos="";
        double total=0;
        declaracion=conexion.createStatement();
        sql = "SELECT id FROM cliente";
        consulta=declaracion.executeQuery(sql);
        consulta.next();
        int id=consulta.getInt("id");
        PreparedStatement instrucciones=conexion.prepareStatement("INSERT INTO compra VALUES(?,?,?,?,?)");
        instrucciones.setString(1, "0");
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());                         
        instrucciones.setString(2, fecha);
        instrucciones.setInt(3, id);       
        for(int i=0;i<carrito.tamano_carrito();i++){           
            productos=productos+carrito.getCarrito().get(i).getNombre()+" -- x"+carrito.getCarrito().get(i).getCantidad()+" UNIDADES -- SUBTOTAL$ :"+carrito.getCarrito().get(i).getPrecio()+'\r'+'\n';
            total=total+carrito.getCarrito().get(i).getPrecio();
        }
        instrucciones.setString(4, productos);      
        instrucciones.setDouble(5, total);
        instrucciones.executeUpdate();     
    }
    
    public void consultar_compras(Connection conexion) throws SQLException{
        Statement declaracion = null;
	String sql;
	ResultSet consulta;      
        declaracion=conexion.createStatement();
        sql = "SELECT compra.id_cliente, cliente.nombre FROM compra INNER JOIN cliente ON compra.id_cliente=cliente.id";
        consulta=declaracion.executeQuery(sql);  
        int id_cliente=0;
        String nombre="";
        while(consulta.next()){
            id_cliente=consulta.getInt("id_cliente");
            nombre=consulta.getString("nombre");
            //System.out.println("-- ID de cliente: "+id_cliente+" , Nombre: "+ nombre+" --");       
        }
        
        sql = "SELECT id, productos, fecha, total FROM compra";
        consulta=declaracion.executeQuery(sql);          
        String productos="";
        while(consulta.next()){
            int id_compra=consulta.getInt("id");
            String fecha=consulta.getString("fecha");
            productos=consulta.getString("productos");
            double total=consulta.getDouble("total");
            System.out.println("-- ID de cliente: "+id_cliente+" , Nombre: "+ nombre+" --");  
            System.out.println("-- COMPRA N°: "+id_compra+" -- "+"Fecha "+fecha+" --"+'\n'+"Productos: "+'\n'+productos);
            System.out.println(" ### TOTAL DE LA COMPRA: "+total+" ###");
        }
        
    }
}