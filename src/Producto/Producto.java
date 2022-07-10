package Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Producto { // Clase publica Producto con 6 atributos
    private int id;
    private String nombre;
    private double precio;
    private double cantidad;
    private int id_categoria;
    private int stock;
    
    
    public Producto(){
        
    }
    
    public Producto(int id, String nombre, double precio, double cantidad, int id_categoria, int stock) { // metodo publico sirve para que sea llamado por otras clases
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad=cantidad;
        this.id_categoria = id_categoria;
        this.stock = stock;
        
    }

    public int getId() { // acceso a id del atributo id privado de la clase Producto
        return id;
    }

    public void setId(int id) { // modifica el atributo id de la clase Producto
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public double getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(double cantidad){
        this.cantidad=cantidad;
    }
 
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public int getIdCategoria() {
        return id_categoria;
    }
    
    public void setIdCategoria(int id_categoria) {
    	this.id_categoria=id_categoria;
    }
    
  
    public void mostar_categorias(Connection conexion) throws SQLException{ // metodo void no retorna nada solo realiza acciones operaciones, en este caso mostrara las categorias a traves de la conexion a la base de datos por medio de los parametros Connection conexion
        Statement declaracion = null; // Statement sirve para procesar una sentencia sql estatica y obtener los resultados q ella produce
        declaracion = conexion.createStatement(); // createStatement se utiliza para crear un objeto que modela a una sentencia sql
        String sql;
        sql = "SELECT nombre, id FROM categoria"; // instrucciones para el sql que seleccionaran el nombre e id desde categoria guardadas en un string llamado sql
        ResultSet consulta = declaracion.executeQuery(sql); // objeto ResultSet proporciona varios metodos para obtener los datos de columna correspondientes a un fila
        while(consulta.next()){ // mientras haya datos en consulta para leer, entonces siguiente
            int id=consulta.getInt("id");
            String nombre=consulta.getString("nombre");
            System.out.println(id+") "+nombre);
        }
    }
        
    public double cantidad(double cantidad){
        return cantidad;
    }
    
    public void elegir_productos(Connection conexion, Carrito carrito) throws SQLException{ // metodo void
    	//La sentencia throw se utiliza para lanzar explícitamente una excepción. En primer lugar se debe obtener un descriptor de un objeto Throwable, bien mediante un parámetro en una cláusula catch o, se puede crear utilizando el operador new
        Statement declaracion = null; //Statement sirve para procesar una sentencia sql estatica y obtener los resultados q ella produce
        declaracion = conexion.createStatement(); //createStatement se utiliza para crear un objeto que modela a una sentencia sql
        String sql;
        Scanner leer=new Scanner(System.in);
        
        System.out.print("SELECCCIONE UNA CATEGORÍA PARA EMPEZAR CON SU COMPRA: ");
        int opc=leer.nextInt();
        leer.nextLine();
        switch(opc){
            case 1: {   sql = "SELECT nombre, precio, id FROM producto WHERE id_categoria=1"; //variable sql ejecuta la consulta a la BDD seleccionando nombre, precio , id desde producto en donde esta la categoria 1, y asi sucesivamente en los otros casos, no se podran anadir categorias salvo q el codigo no este anadido el caso.
                        ResultSet consulta = declaracion.executeQuery(sql);   // guarda las instrucciones ejecutadas por el string sql al consultar la base de datos          
                        while(consulta.next()){  // si la consulta contiene datos entonces siguiente                                                                                    
                            Producto producto=new Producto();// se crea el objeto producto para llamar a la clase Producto
                            producto.setId(consulta.getInt("id")); // llamada la Clase producto dentro del objeto producto settea el id del objeto producto
                            producto.setNombre(consulta.getString("nombre")); // settea el nombre del objeto producto
                            producto.setPrecio(consulta.getDouble("precio")); // setea el precio del objeto producto
                            System.out.println("Producto: "+producto.getNombre()+'\n'+"Precio: $"+producto.getPrecio()); // imprime nombre y precio del objeto producto
                            System.out.println("") ;  
                            System.out.println("DESEA AGREGAR EL PRODUCTO A SU CARRITO?");
                            System.out.println("s=SI");
                            String opc2=leer.nextLine();
                            if(opc2.equals("s")){ // scanner de opcion para entrar al if
                                System.out.print("INDIQUE LA CANTIDAD DE ESTE ARTICULO QUE DESEA COMPRAR: ");
                                producto.setCantidad(leer.nextDouble()); // guarda la cantidad del producto                                                                      
                                producto.setPrecio(producto.getPrecio()*producto.getCantidad()); // guarda el precio total segun la cantidad ingresada por el usuario                              
                                carrito.agregar_producto(producto); // anade el producto al carrito
                                System.out.println("PORDUCTO/S AGREGADOS AL CARRITO CON ÉXITO");
                            }                                                    
                        }   
            } break;                      
            
            
        
            case 2: {  sql = "SELECT nombre, precio, id FROM producto WHERE id_categoria=2";
                        ResultSet consulta = declaracion.executeQuery(sql);                   
                        while(consulta.next()){                                                                                      
                            Producto producto=new Producto();
                            producto.setId(consulta.getInt("id"));
                            producto.setNombre(consulta.getString("nombre"));
                            producto.setPrecio(consulta.getDouble("precio"));
                            System.out.println("Producto: "+producto.getNombre()+'\n'+"Precio: $"+producto.getPrecio());
                            System.out.println("") ;  
                            System.out.println("DESEA AGREGAR EL PRODUCTO A SU CARRITO?");
                            System.out.println("s=SI");
                            String opc2=leer.nextLine();
                            if(opc2.equals("s")){
                                System.out.print("INDIQUE LA CANTIDAD DE ESTE ARTICULO QUE DESEA COMPRAR: ");
                                producto.setCantidad(leer.nextDouble());                                                                                          
                                producto.setPrecio(producto.getPrecio()*producto.getCantidad());                               
                                carrito.agregar_producto(producto);
                                System.out.println("PORDUCTO/S AGREGADOS AL CARRITO CON ÉXITO");
                            }                                                    
                        }  
            } break;           
            
            case 3: {   sql = "SELECT nombre, precio, id FROM producto WHERE id_categoria=3";
                        ResultSet consulta = declaracion.executeQuery(sql);                   
                        while(consulta.next()){                                                                                      
                            Producto producto=new Producto();
                            producto.setId(consulta.getInt("id"));
                            producto.setNombre(consulta.getString("nombre"));
                            producto.setPrecio(consulta.getDouble("precio"));
                            System.out.println("Producto: "+producto.getNombre()+'\n'+"Precio: $"+producto.getPrecio());
                            System.out.println("") ;  
                            System.out.println("DESEA AGREGAR EL PRODUCTO A SU CARRITO?");
                            System.out.println("s=SI");
                            String opc2=leer.nextLine();
                            if(opc2.equals("s")){
                                System.out.print("INDIQUE LA CANTIDAD DE ESTE ARTICULO QUE DESEA COMPRAR: ");
                                producto.setCantidad(leer.nextDouble());                                                                                          
                                producto.setPrecio(producto.getPrecio()*producto.getCantidad());                               
                                carrito.agregar_producto(producto);
                                System.out.println("PORDUCTO/S AGREGADOS AL CARRITO CON ÉXITO");
                            }                                                    
                        }  
            } break;           
            
            case 4: {   sql = "SELECT nombre, precio, id FROM producto WHERE id_categoria=4";
                        ResultSet consulta = declaracion.executeQuery(sql);                   
                        while(consulta.next()){                                                                                      
                            Producto producto=new Producto();
                            producto.setId(consulta.getInt("id"));
                            producto.setNombre(consulta.getString("nombre"));
                            producto.setPrecio(consulta.getDouble("precio"));
                            System.out.println("Producto: "+producto.getNombre()+'\n'+"Precio: $"+producto.getPrecio());
                            System.out.println("") ;  
                            System.out.println("DESEA AGREGAR EL PRODUCTO A SU CARRITO?");
                            System.out.println("s=SI");
                            String opc2=leer.nextLine();
                            if(opc2.equals("s")){
                                System.out.print("INDIQUE LA CANTIDAD DE ESTE ARTICULO QUE DESEA COMPRAR: ");
                                producto.setCantidad(leer.nextDouble());                                                                                          
                                producto.setPrecio(producto.getPrecio()*producto.getCantidad());                               
                                carrito.agregar_producto(producto);
                                System.out.println("PORDUCTO/S AGREGADOS AL CARRITO CON ÉXITO");
                            }                                                    
                        }  
            } break;           
            
            case 5: {   sql = "SELECT nombre, precio, id FROM producto WHERE id_categoria=5";
                        ResultSet consulta = declaracion.executeQuery(sql);                   
                        while(consulta.next()){                                                                                      
                            Producto producto=new Producto();
                            producto.setId(consulta.getInt("id"));
                            producto.setNombre(consulta.getString("nombre"));
                            producto.setPrecio(consulta.getDouble("precio"));
                            System.out.println("Producto: "+producto.getNombre()+'\n'+"Precio: $"+producto.getPrecio());
                            System.out.println("") ;  
                            System.out.println("DESEA AGREGAR EL PRODUCTO A SU CARRITO?");
                            System.out.println("s=SI");
                            String opc2=leer.nextLine();
                            if(opc2.equals("s")){
                                System.out.print("INDIQUE LA CANTIDAD DE ESTE ARTICULO QUE DESEA COMPRAR: ");
                                producto.setCantidad(leer.nextDouble());                                                                                          
                                producto.setPrecio(producto.getPrecio()*producto.getCantidad());                               
                                carrito.agregar_producto(producto);
                                System.out.println("PORDUCTO/S AGREGADOS AL CARRITO CON ÉXITO");
                            }                                                    
                        }  
            } break;           
            
            case 6: {
            	sql = "SELECT nombre, precio, id FROM producto WHERE id_categoria=6";
            	ResultSet consulta = declaracion.executeQuery(sql);
            	while(consulta.next()){
            		Producto producto=new Producto();
            		producto.setId(consulta.getInt("id"));
            		producto.setNombre(consulta.getString("nombre"));
            		producto.setPrecio(consulta.getDouble("precio"));
            		System.out.println("Producto: "+producto.getNombre()+'\n'+"Precio: $"+producto.getPrecio());
            		System.out.println("");
            		System.out.println("DESEA AGREGAR EL PRODUCTO A SU CARRITO?");
            		System.out.println("s=SI");
            		String opc2=leer.nextLine();
            		if(opc2.equals("s")){
            			System.out.print("INDIQUE LA CANTIDAD DE ESTE ARTICULO QUE DESEA COMPRAR: ");
            			producto.setCantidad(leer.nextDouble());
            			producto.setPrecio(producto.getPrecio()*producto.getCantidad());
            			carrito.agregar_producto(producto);
            			System.out.println("PORDUCTO/S AGREGADOS AL CARRITO CON ÉXITO");
            			}
            		}  
            } break;           
            
            case 7: {   
            	sql = "SELECT nombre, precio, id FROM producto WHERE id_categoria=7";
            	ResultSet consulta = declaracion.executeQuery(sql);
            	while(consulta.next()){
            		Producto producto=new Producto();
            		producto.setId(consulta.getInt("id"));
            		producto.setNombre(consulta.getString("nombre"));
            		producto.setPrecio(consulta.getDouble("precio"));
            		System.out.println("Producto: "+producto.getNombre()+'\n'+"Precio: $"+producto.getPrecio());
            		System.out.println("");
            		System.out.println("DESEA AGREGAR EL PRODUCTO A SU CARRITO?");
            		System.out.println("s=SI");
            		String opc2=leer.nextLine();
            		if(opc2.equals("s")){
            			System.out.print("INDIQUE LA CANTIDAD DE ESTE ARTICULO QUE DESEA COMPRAR: ");
            			producto.setCantidad(leer.nextDouble());                                                                                          
            			producto.setPrecio(producto.getPrecio()*producto.getCantidad());
            			carrito.agregar_producto(producto);
            			System.out.println("PORDUCTO/S AGREGADOS AL CARRITO CON ÉXITO");
            			}
            		}  
            } break;           
            
            case 8: {
            	sql = "SELECT nombre, precio, id FROM producto WHERE id_categoria=8";
            	ResultSet consulta = declaracion.executeQuery(sql);
            	while(consulta.next()){
            		Producto producto=new Producto();
            		producto.setId(consulta.getInt("id"));
            		producto.setNombre(consulta.getString("nombre"));
            		producto.setPrecio(consulta.getDouble("precio"));
            		System.out.println("Producto: "+producto.getNombre()+'\n'+"Precio: $"+producto.getPrecio());
            		System.out.println("");
            		System.out.println("DESEA AGREGAR EL PRODUCTO A SU CARRITO?");
            		System.out.println("s=SI");
            		String opc2=leer.nextLine();
            		if(opc2.equals("s")){
            			System.out.print("INDIQUE LA CANTIDAD DE ESTE ARTICULO QUE DESEA COMPRAR: ");
            			producto.setCantidad(leer.nextDouble());
            			producto.setPrecio(producto.getPrecio()*producto.getCantidad());
            			carrito.agregar_producto(producto);
            			System.out.println("PORDUCTO/S AGREGADOS AL CARRITO CON ÉXITO");
            			}
            		}  
            } break;           
            
            case 9: {
            	sql = "SELECT nombre, precio, id FROM producto WHERE id_categoria=9";
            	ResultSet consulta = declaracion.executeQuery(sql);                   
            	while(consulta.next()){
            		Producto producto=new Producto();
            		producto.setId(consulta.getInt("id"));
            		producto.setNombre(consulta.getString("nombre"));
            		producto.setPrecio(consulta.getDouble("precio"));
            		System.out.println("Producto: "+producto.getNombre()+'\n'+"Precio: $"+producto.getPrecio());
            		System.out.println("");
            		System.out.println("DESEA AGREGAR EL PRODUCTO A SU CARRITO?");
            		System.out.println("s=SI");
            		String opc2=leer.nextLine();
            		if(opc2.equals("s")){
            			System.out.print("INDIQUE LA CANTIDAD DE ESTE ARTICULO QUE DESEA COMPRAR: ");
            			producto.setCantidad(leer.nextDouble());
            			producto.setPrecio(producto.getPrecio()*producto.getCantidad());
            			carrito.agregar_producto(producto);
            			System.out.println("PORDUCTO/S AGREGADOS AL CARRITO CON ÉXITO");
            			}
            		}
            	} break;
            	
            case 10:{
            	
            	sql = "SELECT nombre, precio, id FROM producto WHERE id_categoria=10";
            	ResultSet consulta = declaracion.executeQuery(sql);                   
            	while(consulta.next()){
            		Producto producto=new Producto();
            		producto.setId(consulta.getInt("id"));
            		producto.setNombre(consulta.getString("nombre"));
            		producto.setPrecio(consulta.getDouble("precio"));
            		System.out.println("Producto: "+producto.getNombre()+'\n'+"Precio: $"+producto.getPrecio());
            		System.out.println("");
            		System.out.println("DESEA AGREGAR EL PRODUCTO A SU CARRITO?");
            		System.out.println("s=SI");
            		String opc2=leer.nextLine();
            		if(opc2.equals("s")){
            			System.out.print("INDIQUE LA CANTIDAD DE ESTE ARTICULO QUE DESEA COMPRAR: ");
            			producto.setCantidad(leer.nextDouble());
            			producto.setPrecio(producto.getPrecio()*producto.getCantidad());
            			carrito.agregar_producto(producto);
            			System.out.println("PORDUCTO/S AGREGADOS AL CARRITO CON ÉXITO");
            	}break;
            	}
            }
        }       
    }   
}
        