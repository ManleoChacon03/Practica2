/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital_pacientes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

    

public class CPacientes {

    String cui;
    String nombre;
    String apellido;
    String fechadeingre;
    String fechadeegre;
    String patologia;

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechadeingre() {
        return fechadeingre;
    }

    public void setFechadeingre(String fechadeingre) {
        this.fechadeingre = fechadeingre;
    }

    public String getFechadeegre() {
        return fechadeegre;
    }

    public void setFechadeegre(String fechadeegre) {
        this.fechadeegre = fechadeegre;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }
    
    public void MostrarPacientes (JTable paramTablaTotalPacientes){
        
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="";
        
        modelo.addColumn("Cui");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Fecha de ingreso");
        modelo.addColumn("Fecha de egreso");
        modelo.addColumn("Patologia");
        
        paramTablaTotalPacientes.setModel(modelo);
        
        sql ="SELECT * FROM pacientes";
        
        String [] datos = new String[6];
        
        Statement st;
        
            try {
                st= objetoConexion.establecerconexion().createStatement();
                
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()){
                    
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);
                    datos[5] = rs.getString(6);
                    
                    modelo.addRow(datos);
                       
                }
                
                paramTablaTotalPacientes.setModel (modelo);
                
            }catch (Exception e){
                
                JOptionPane.showMessageDialog(null,"Error"+ e.toString());
            }   
    }
    
    public void insertarPaciente(JTextField paramcui,JTextField paramnombre, JTextField paramapellido,JTextField paramfechadeingre,JTextField paramfechadeegre,JTextField parampatologia){
        
       setCui(paramcui.getText());
       setNombre(paramnombre.getText());
       setApellido(paramapellido.getText());
       setFechadeingre(paramfechadeingre.getText());
       setFechadeegre(paramfechadeegre.getText());
       setPatologia(parampatologia.getText());
       
       CConexion objetoConexion = new CConexion();
       
       String consulta = "insert into pacientes (cui,nombre,apellido,fechadeingre,fechadeegre,patologia) values (?,?,?,?,?,?)";
       
       try{
           
           CallableStatement cs = objetoConexion.establecerconexion().prepareCall(consulta);
           cs.setString(1, getCui());
           cs.setString(2, getNombre());
           cs.setString(3, getApellido());
           cs.setString(4, getFechadeingre());
           cs.setString(5, getFechadeegre());
           cs.setString(6, getPatologia());
           
           cs.execute();
           
           JOptionPane.showMessageDialog(null,"Se inserto correctamente");
           
       }catch (Exception e){
           
           JOptionPane.showMessageDialog(null,"Error" +e.toString());
           
       }
      
    }
    
    public void SeleccionarPaciente(JTable paramTablapaciente, JTextField paramcui,JTextField paramnombre, JTextField paramapellido,JTextField paramfechadeingre,JTextField paramfechadeegre,JTextField parampatologia){
        
       try{
           int fila =paramTablapaciente.getSelectedRow();
           
           if (fila>=0){
               
               paramcui.setText(paramTablapaciente.getValueAt(fila, 0).toString());
               paramnombre.setText(paramTablapaciente.getValueAt(fila, 1).toString());
               paramapellido.setText(paramTablapaciente.getValueAt(fila, 2).toString());
               paramfechadeingre.setText(paramTablapaciente.getValueAt(fila, 3).toString());
               paramfechadeegre.setText(paramTablapaciente.getValueAt(fila, 4).toString());
               parampatologia.setText(paramTablapaciente.getValueAt(fila, 5).toString()); 
               
           }
           else{
              JOptionPane.showMessageDialog(null,"Fila no seleccionada");
           }
           
           
       }catch (Exception e){
           JOptionPane.showMessageDialog(null,"Error" +e.toString());
       }
        
    }
    
 
    
   public void modificarPaciente(JTextField paramcui,JTextField paramnombre, JTextField paramapellido,JTextField paramfechadeingre,JTextField paramfechadeegre,JTextField parampatologia){
      
       
       setCui(paramcui.getText());
       setNombre(paramnombre.getText());
       setApellido(paramapellido.getText());
       setFechadeingre(paramfechadeingre.getText());
       setFechadeegre(paramfechadeegre.getText());
       setPatologia(parampatologia.getText());
       
       CConexion objetoConexion = new CConexion();
       
       String consulta = "UPDATE pacientes SET nombre = ?, apellido = ?, fechadeingre = ?, fechadeegre = ?, patologia = ? WHERE cui= ?"; 
       
       try{
           
           CallableStatement cs = objetoConexion.establecerconexion().prepareCall(consulta);
           cs.setString(1, getCui());
           cs.setString(2, getNombre());
           cs.setString(3, getApellido());
           cs.setString(4, getFechadeingre());
           cs.setString(5, getFechadeegre());
           cs.setString(6, getPatologia());
           
           cs.execute();
           
           JOptionPane.showMessageDialog(null,"Se modifico correctamente");
           
       }catch (Exception e){
           
           JOptionPane.showMessageDialog(null,"Error" +e.toString());
           
       }
   }
       
   
       public void borrarPaciente(JTextField paramcui){
        
       setCui(paramcui.getText());
     
       CConexion objetoConexion = new CConexion();
       
       String consulta = "DELETE FROM pacientes WHERE cui= ?";
       
       try{
           
           CallableStatement cs = objetoConexion.establecerconexion().prepareCall(consulta);
           cs.setString(1, getCui());
           
           cs.execute();
           
           JOptionPane.showMessageDialog(null,"Se elimino correctamente");
           
       }catch (Exception e){
           
           JOptionPane.showMessageDialog(null,"Error" +e.toString());
           
       }
      
    }
}



    
