package Producto;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Carrito { // Clase publica Carrito
    ArrayList <Producto> carrito= new ArrayList<> (); // atributo principal array, contiene elementos de tipo Producto
    
    public Carrito(){      
    }

    public ArrayList<Producto> getCarrito() { //getter permite acceder al array Carrito de tipo producto
        return carrito;
    }
      
    public int tamano_carrito(){ //para saber el tamano del array carrito
        return this.carrito.size();
    }
    
    public void agregar_producto(Producto producto){     // para agregar producto o elemento de tipo producto al array carrito     
        this.carrito.add(producto); //
        
    }
    
    public void mostrar_carrito(){ // metodo void recorre el array carrito q no retorna nada pero lo muestra con un syso
        System.out.println("SU CARRITO DE COMPRA: "); // syso dedicado a la presentacion del carrito 
        double total=0; // variable para acumular el precio total de los productos comprados
        for(int i=0;i<carrito.size();i++){ // ciclo for recorre cada elemento del array depende del tamano del carrito
            System.out.println(carrito.get(i).getNombre()+" -- x"+(long)carrito.get(i).getCantidad()+" UNIDADES -- Total: $"+carrito.get(i).getPrecio()); //syso muestra nombre cantidad y subtotal de cada producto por separado
            total=total+(carrito.get(i).getPrecio());// variable acumulativa q guarda los precios subtotales de cada producto para mostrar el total a pagar cuando termine el ciclo for
        }
        System.out.println("TOTAL A PAGAR: $"+total); // syso del resultado del ciclo for
    }
    
    
        
}