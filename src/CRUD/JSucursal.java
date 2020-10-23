
package CRUD;

import Conexion.Conexion;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronal
 */
public class JSucursal extends javax.swing.JInternalFrame {

    /**
     * Creates new form JSucursal
     */
    public JSucursal() {
        initComponents();
        String id = String.valueOf(Id_incrementable());
        txt_id.setText(id);
        txt_id.enable(false);
        Mostrar();
    }

   
      
        //Variables 
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pst =null;
        CallableStatement cStmt = null;
        ResultSet res=null;
         String qry = null;
        int resul; 
        int fila;
      
       
        //ID INCREMENTABLE
        public int Id_incrementable()
        {
            int id=1;
            try 
            {         
             pst=Conexion.getConexion().prepareStatement("SELECT MAX(Id_Suc) FROM Sucursales");
             res=pst.executeQuery();
             while(res.next())
             {
             id=res.getInt(1)+1;
             
             }
         } catch (Exception e) 
         {
             JOptionPane.showInputDialog(this, e);
         }
         
         return id;
     }   
     
    
     //METODO PARA INSERTAR
     public void Insertar()
     {   
         int id = Id_incrementable();
         
      String insert =  "INSERT INTO Sucursales  VALUES (?,?,?,?)";
               try { 
              conn =Conexion.getConexion();
              pst =conn.prepareStatement(insert);
              
              pst.setInt(1,id);              
              pst.setString(2,txt_departamento.getText());
              pst.setString(3,txt_ciudad.getText());
              pst.setString(4,txt_direccion.getText());
             
              resul = pst.executeUpdate();
              if (resul==1){
              JOptionPane.showMessageDialog(null,"Dato guardado");
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"Dato no guardado");
              }
              pst.close();
          } catch (SQLException | HeadlessException e) {
              JOptionPane.showMessageDialog(null,"erro"+e);
          }
      }
    
    //Mostrar
    public void Mostrar()  
        {
         String [] titulo = {"Id_Sucursal","Departamento","Ciudad","Direccion"};
         String [] dato = new String[4];
         DefaultTableModel  modelo = new DefaultTableModel(null, titulo);
          String qyl = "select * from Sucursales ORDER BY Id_Suc"; 
       try {
          Connection cn = Conexion.getConexion();
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          while(rs.next()){
              dato[0]=rs.getString("Id_Suc");
              dato[1]=rs.getString("Departamento");
              dato[2]=rs.getString("Ciudad");
              dato[3]=rs.getString("Direccion");
              
              modelo.addRow(dato);
             
              TB_Mostrar.setModel(modelo);
          }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
       
    }
    
    //Modificar
     public void Modificar()
        {
       try {
           qry = "UPDATE Sucursales SET  Departamento=?, Ciudad=?, Direccion=? WHERE Id_Suc=?";
           
              fila = TB_Mostrar.getSelectedRow();
              String dat = (String)TB_Mostrar.getValueAt(fila,0);
              conn = Conexion.getConexion();
              pst =conn.prepareStatement(qry);
              
              
              pst.setString(1,txt_departamento.getText());             
              pst.setString(2,txt_ciudad.getText());
              pst.setString(3,txt_direccion.getText());           
              pst.setString(4,dat);
              
              resul=pst.executeUpdate();
              
              if (resul == 1) {
                JOptionPane.showMessageDialog(null, "Dato Actualizado");
            } else {
                JOptionPane.showMessageDialog(null, "No se Pudo Actualizar");
            }
              
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"error  "+e);
       }
   }
    
    //Eliminar
        public void Eliminar (){
        
           qry = "delete from Sucursales where Id_Suc=?";
           try {
            conn = Conexion.getConexion(); 
            pst=conn.prepareStatement(qry);
            pst.setInt(1,Integer.parseInt(txt_id.getText()));
            resul = pst.executeUpdate();
            
            if (resul==1)
            {
            JOptionPane.showMessageDialog(null, "Dato Eliminado");
            }
            
            else 
            {
             JOptionPane.showMessageDialog(null, "Dato no eliminado");
            }
            pst.close();
            
           } catch (SQLException e) {
           System.out.println("Error"+e);
           }
         }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_departamento = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_ciudad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TB_Mostrar = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID_Sucursal");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 70, 40));
        jPanel1.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 160, 30));

        jLabel2.setText("Departamento");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 80, 20));
        jPanel1.add(txt_departamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 160, 30));

        jLabel3.setText("Ciudad");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));
        jPanel1.add(txt_ciudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 170, 30));

        jLabel4.setText("Direccion");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));
        jPanel1.add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 170, 30));

        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 80, -1));

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, -1, -1));

        TB_Mostrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TB_Mostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TB_MostrarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TB_Mostrar);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 710, 310));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
       Insertar();
       Mostrar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TB_MostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_MostrarMouseClicked
        // TODO add your handling code here:
           int fila = TB_Mostrar.rowAtPoint(evt.getPoint());
        
         txt_id.setText(TB_Mostrar.getValueAt(fila, 0).toString());
         txt_departamento.setText(TB_Mostrar.getValueAt(fila, 1).toString());
         txt_ciudad.setText(TB_Mostrar.getValueAt(fila, 2).toString());
         txt_direccion.setText(TB_Mostrar.getValueAt(fila, 3).toString());
      
    }//GEN-LAST:event_TB_MostrarMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Modificar();
        Mostrar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Eliminar();
        Mostrar();
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TB_Mostrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_ciudad;
    private javax.swing.JTextField txt_departamento;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables
}
