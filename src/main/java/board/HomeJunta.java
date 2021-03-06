/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package board;

import home.*;
import patient.*;

/**
 *
 * @author rafael
 */
public class HomeJunta extends javax.swing.JFrame {

    /**
     * Singleton dude
     */
    private static final HomeJunta home = new HomeJunta();

    private HomeJunta() {
        initComponents();
    }
    
    public static HomeJunta getInstance() {
        return home; 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        RegistrarSucursalButton = new javax.swing.JButton();
        AsignarMedicoButton = new javax.swing.JButton();
        RegistrarMedicoButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("HOSPITAL XYZ");

        RegistrarSucursalButton.setText("Registrar Sucursal");
        RegistrarSucursalButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegistrarSucursalButtonMouseClicked(evt);
            }
        });
        RegistrarSucursalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarSucursalButtonActionPerformed(evt);
            }
        });

        AsignarMedicoButton.setText("Asignar M??dico a Sucursal");
        AsignarMedicoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AsignarMedicoButtonMouseClicked(evt);
            }
        });

        RegistrarMedicoButton.setText("Registrar M??dico");
        RegistrarMedicoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegistrarMedicoButtonMouseClicked(evt);
            }
        });

        jButton1.setText("Atr??s");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RegistrarSucursalButton, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(AsignarMedicoButton, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(RegistrarMedicoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RegistrarSucursalButton, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(RegistrarMedicoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AsignarMedicoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegistrarSucursalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarSucursalButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RegistrarSucursalButtonActionPerformed

    private void RegistrarSucursalButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarSucursalButtonMouseClicked
        
        
        
        RegistrarSucursal_IngresarDatos datosSucursal = RegistrarSucursal_IngresarDatos.getInstance();
        datosSucursal.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_RegistrarSucursalButtonMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        PaginaPrincipal home = PaginaPrincipal.getInstance();
        home.setVisible(true);
        this.home.setVisible(false);
    }//GEN-LAST:event_jButton1MouseClicked

    private void RegistrarMedicoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarMedicoButtonMouseClicked
        RegistrarMedico_IngresarDatos registrarMedico = RegistrarMedico_IngresarDatos.getInstance();
        registrarMedico.setVisible(true);
        home.setVisible(false);
    }//GEN-LAST:event_RegistrarMedicoButtonMouseClicked

    private void AsignarMedicoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AsignarMedicoButtonMouseClicked
        AsignarMedico_IngresarDatos asignarMedico = AsignarMedico_IngresarDatos.getInstance();
        asignarMedico.setVisible(true);
        home.setVisible(false);
    }//GEN-LAST:event_AsignarMedicoButtonMouseClicked

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeJunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeJunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeJunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeJunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeJunta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AsignarMedicoButton;
    private javax.swing.JButton RegistrarMedicoButton;
    private javax.swing.JButton RegistrarSucursalButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    // End of variables declaration//GEN-END:variables
}
