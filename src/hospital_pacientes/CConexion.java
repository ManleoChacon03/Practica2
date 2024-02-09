package hospital_pacientes;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class CConexion{
 
    Connection conectar =null;
    
    String usuario = "postgres";
    String contrasenia = "muni123$";
    String bd = "dbpacientes";
    String ip = "localhost";
    String puerto = "5432";
    
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd; 
    
    public Connection establecerconexion(){
        try {
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            
            JOptionPane.showMessageDialog(null,"Se conecto correctamente a la base de datos");
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Error al conectar" +e.toString());
        }
        
        return conectar;
}
        void establecerConexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

