

package coneccion;
import java.sql.*;
/**
 *
 * Nombre: Luis Mario Trejo Hinojosa
 * Tema del programa: Clase Alumno
 * Descripcion: Clase que permite crear un alumno y calcular su edad
 * Fecha 22/05/2018
 */
public class Conect {
    //Atributos necesarios para la conexion
    private static final String controlador = "com.mysql.jdbc.Driver";
    private String usuario = null;
    private String contra = null;
    private String url = null;
    private Connection con=null;
    //Constructor
    public Conect(String base,String usr, String pass){
       this.url="jdbc:mysql://localhost:3306/"+base;
       this.usuario=usr;
       this.contra=pass;
       
       try{
           Class.forName(controlador).newInstance();
           
       }catch(ClassNotFoundException | IllegalAccessException | InstantiationException e){
           System.out.println("No se puede cargar el controlador");
       }
       
       
       try{
           con = DriverManager.getConnection(url,usuario,contra);
           if(con!=null){
               System.out.println("Conexion establecida");
           } 
           
       }catch(SQLException e){
           System.out.println("No se pudo conectar a la base de datos");
       }
    }
    //Metodo regresar la conexion
    public Connection getConneccion(){
        return con;
    }
    //Metodo para finalizar la conexion
    public void endConeccion(){
        try{
            con.close();
        }catch(SQLException e){
            System.out.println("Error al cerrar la conexion");
        }
    }
    
    
}
