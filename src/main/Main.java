package main;

import admin.Admin; // importar el paquete admin
import admin.Cargar; // importa
import Entidades.Cliente;
import Producto.Carrito;
import Producto.Producto;
import compra.Compra;
import conexion.Conexion;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.protocol.SocksProxySocketFactory;


public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("#####################");
        System.out.println("| SISTEMA SUPERMARK |");
	    System.out.println("#####################");
  	    Scanner teclado1=new Scanner(System.in);
	    Conexion conexion=new Conexion(); // Se crea objeto de tipo conexion para realizar cualquier operacion N
        Carrito carrito=new Carrito(); // Se crea objeto llamado carrito que va a contener elementos del tipo producto que guarda los productos elegidos por el usuario
        int opcion=1; // tipo de dato int = 1 para ejecutar el ciclo while
        String salida="";
        boolean salir=false; // tipo de dato booleano false para usar en el final de la compra
        while(opcion==1) { // ciclo while 
            System.out.println("----------------------");
            System.out.println("|  1-Iniciar sesion  |"); // menu principal
            System.out.println("|  2-Crear usuario   |");
            System.out.println("|  3-Administrador   |");
            System.out.println("----------------------");
            Scanner teclado2=new Scanner(System.in);// scanner q guardara el dato en el int opcionMenuPrincipal
            int opcionMenuPrincipal=teclado2.nextInt();// variable tipo int que contiene el scanner teclado2 para ejecutar una eleccion del menu principal
            switch(opcionMenuPrincipal) {//switch que recibe como parametro la opcion elegida
            case 1:{// primer caso
            	Cliente cliente=new Cliente();// se crea un objeto cliente para llamar a la clase Cliente
            	cliente.iniciar_sesion(conexion.conectar()); // el objeto cliente llama al constructor iniciar_sesion para validar credenciales del usuario, los parametros de conexion realizan la consulta a la base de datos
            	Producto producto=new Producto(); // se creara un objeto producto para llamar a la clase Producto para luego llamar a los metodos
            	while(salir==false){ //ciclo while false, ahora q lo veo es redundante porque se puede poner false y listo :D
            	producto.mostar_categorias(conexion.conectar()); // objeto producto llama al metodo mostrar_categorias con parametros de conexion para realizar consulta de la tabla categoria en workbench
            	producto.elegir_productos(conexion.conectar(), carrito); // objeto producto llama al metodo elegir_productos con parametros de conexion para realizar la eleccion de producto 
            	System.out.println("");
            	System.out.println("QUIERE REALIZAR OTRA COMPRA? \n s=SI -- n=NO");
            	salida=teclado1.nextLine();
            	if(salida.equals("n")){
            		salir=true;
            		}
            	else {
            		System.out.println("Seleccione la categoria de producto");
            	}
            	}
            System.out.println("");
            carrito.mostrar_carrito();
            System.out.println("+------------------------+");
            System.out.println("|   CONFIRMAR COMPPRA?   |");
            System.out.println("|           si           |");
            System.out.println("|           no           |");
            System.out.println("+------------------------+");
            String opc;
            opc=teclado1.next();
            if(opc.equals("si")){
            	Compra compra=new Compra();
                compra.confirmar_compra(conexion.conectar(), carrito);
                System.out.println("+------------------------+");
                System.out.println("|        SU COMPRA       |");
                System.out.println("|           FUE          |");
                System.out.println("|        CONFIRMADA      |");
                System.out.println("+------------------------+");
            }
            break;
            }
            case 2:{ 
            	Cliente cliente=new Cliente();
            	cliente.registrar(conexion.conectar());
            	break;
            	}
            case 3:{ // dirige al caso 3 
            	Admin admin=new Admin(); //se crea un objeto tipo Admin para poder usar los metodos de su clase
            	admin.bienvenido_admin(); // metodo dentro de clase Admin que ejecuta el inicio de sesion del admin
            	// XDD ESTO ESTABA MAL admin.menu_admin(); // QUE ES ESOOOOOO metodo dentro de clase Admin muestra y ejecuta las distintas operaciones que realizara el administrador
            	
            	//Cargar cargar=new Cargar();
            	//cargar.cargar_producto(conexion.conectar());
			break; 
			}
            }
            System.out.println("######################");
            System.out.println("| Salir del sistema? |");
            System.out.println("|     0 - SI         |");
            System.out.println("|     1 - NO         |");
            System.out.println("######################");
            opcion=teclado1.nextInt();
            teclado1.nextLine();
            }
        System.out.println("+------------------------+");
        System.out.println("| GRACIAS POR SU COMPRA  |");
        System.out.println("|      EN SUPERMARK      |");
        System.out.println("|           :)           |");
        System.out.println("+------------------------+");
        }
    }