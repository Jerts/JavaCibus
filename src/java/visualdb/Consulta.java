/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualdb;
import Servlets.PonerOrden;
import coneccion.Conect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * Nombre: Luis Mario Trejo Hinojosa
 * Tema del programa: Clase Alumno
 * Descripcion: Clase que permite crear un alumno y calcular su edad
 * Fecha 2/06/2018
 */
public class Consulta {
    //Atributos
    private final Conect con;
    private ResultSet Clientes;
    private ResultSet Productos;
    private ResultSet Compras;
    private ResultSet Proveedores;
    //Constructor
    public Consulta(){
        con = new Conect("cibusv2","root","");
    }
    //Métodos: Regresan objetos resultSet

    public ResultSet getClientes() {
        try {
            String queryClientes ="SELECT * FROM `cliente`";
            Statement stm = con.getConneccion().createStatement();
            Clientes = stm.executeQuery(queryClientes);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Clientes;
    }
    public ResultSet getClientes(String cliente) {
        try {
            String queryClientes ="SELECT * FROM `cliente` WHERE `ID_CLIENTE` = \""+cliente+"\" ";
            Statement stm = con.getConneccion().createStatement();
            Clientes = stm.executeQuery(queryClientes);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Clientes;
    }


    public ResultSet getProductos() {
        try {
            String queryProductos ="SELECT * FROM `producto`";
            Statement stm = con.getConneccion().createStatement();
            Productos = stm.executeQuery(queryProductos);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Productos;
    }
    public ResultSet getProductos(String tipoProducto) {
        try {
            String queryProductos ="SELECT * FROM `producto` WHERE `ID_TIPO` = \""+tipoProducto+"\" ";
            Statement stm = con.getConneccion().createStatement();
            Productos = stm.executeQuery(queryProductos);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Productos;
    }

    public ResultSet getCompras() {
        try {
            String queryCompras ="SELECT * FROM `compra`";
            Statement stm = con.getConneccion().createStatement();
            Compras = stm.executeQuery(queryCompras);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Compras;
    }
    
    public ResultSet getCompras(String usuario,String fecha) {
        try {
            String queryCompras ="SELECT * FROM `compra` WHERE `ID_CLIENTE`=\""+usuario+"\" AND `FECHA`=\""+fecha+"\"";
            Statement stm = con.getConneccion().createStatement();
            Compras = stm.executeQuery(queryCompras);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Compras;
    }

    public ResultSet getProveedores() {
        try {
            String queryProveedores ="SELECT * FROM `proveedor`";
            Statement stm = con.getConneccion().createStatement();
            Proveedores = stm.executeQuery(queryProveedores);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Proveedores;
    }
    public void setProducto(String tipo, String nombre, String presentacion, float precio,int cantidad){
        try {
            Statement stm = con.getConneccion().createStatement();
            String id_producto = genIdProducto(con.getConneccion(),stm);
            String queryInsertProducto = "INSERT INTO `producto` (`ID_PRODUCTO`, `ID_TIPO`, `ID_PROVEEDOR`, `NOMBRE`,"
                    + "`RUTA_IMG`, `PRESENTACION`, `PRECIO`, `CANTIDAD`)"
                    + "VALUES (\""+id_producto+"\","
                    + "\""+tipo+"\",\""+"0001"+"\",\""+nombre+"\",\"img/productos/"+id_producto+".jpg\",\""+presentacion+"\","
                    + "\""+precio+"\",\""+cantidad+"\")";
            System.out.println(queryInsertProducto);
            stm.executeUpdate(queryInsertProducto);
            
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private String genIdProducto(Connection con,Statement stat){
        String producto="";
        int numero;
        try {
            String queryProductoActual = "SELECT DISTINCT `ID_PRODUCTO` FROM `producto` ORDER BY `ID_PRODUCTO` DESC";
            ResultSet set = stat.executeQuery(queryProductoActual);
            set.next();
            producto = set.getString("ID_PRODUCTO");
            System.out.println(producto);
            producto = producto.substring(0,4);
            System.out.println(producto);
            numero = Integer.parseInt(producto)+1;
            System.out.println(numero);
            producto = String.format("%04d", numero);
            System.out.println(producto);
            
        } catch (SQLException ex) {
            Logger.getLogger(PonerOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }
    
    /*public static void main (String args[]){
        Consulta consulta = new Consulta();
        ResultSet res=consulta.getClientes("Jerts25");
        Boolean b1 = true;
        try {
            if(res.next()){
                System.out.println(res.getString("CONTRASENA"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
