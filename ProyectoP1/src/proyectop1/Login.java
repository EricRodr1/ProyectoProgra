package proyectop1;

import com.sun.tools.javac.Main;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    ManejoUsuario BD;

    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);

    }
    
    public void setBD(ManejoUsuario BD) {
    this.BD = BD;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        contra = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        ocultar = new javax.swing.JCheckBox();
        ingresar = new javax.swing.JButton();
        logearse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 204));

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre de Usuario:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));

        nombre.setBackground(new java.awt.Color(255, 255, 255));
        nombre.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 167, -1));

        contra.setBackground(new java.awt.Color(255, 255, 255));
        contra.setForeground(new java.awt.Color(0, 0, 0));
        contra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contraActionPerformed(evt);
            }
        });
        jPanel1.add(contra, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 167, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Contrasenna: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        ocultar.setBackground(new java.awt.Color(255, 255, 204));
        ocultar.setForeground(new java.awt.Color(0, 0, 0));
        ocultar.setText("Ver/Ocultar");
        ocultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarActionPerformed(evt);
            }
        });
        jPanel1.add(ocultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, -1, -1));

        ingresar.setBackground(new java.awt.Color(255, 255, 255));
        ingresar.setForeground(new java.awt.Color(0, 0, 0));
        ingresar.setText("ingresar");
        ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarActionPerformed(evt);
            }
        });
        jPanel1.add(ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 110, -1));

        logearse.setBackground(new java.awt.Color(255, 255, 255));
        logearse.setForeground(new java.awt.Color(0, 0, 0));
        logearse.setText("registrarse");
        logearse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logearseActionPerformed(evt);
            }
        });
        jPanel1.add(logearse, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logearseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logearseActionPerformed

        new Registro(this).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logearseActionPerformed

    private void ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarActionPerformed
        String user = nombre.getText();
        String pass = new String(contra.getPassword());

        usuario local = BD.buscar(user);

        if(local != null){
            if(local.contra.equals(pass)){
                System.out.println("Usuario correcto");
                
                Menu m1 = new Menu();
                m1.setBD(BD); 
                m1.setVisible(true);
                this.setVisible(false);
            } else {
                System.out.println("Contraseña errónea");
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                nombre.setText("");
                contra.setText("");
            }
        } else {
            System.out.println("Usuario incorrecto");
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            nombre.setText("");
            contra.setText("");
        }
    }//GEN-LAST:event_ingresarActionPerformed

    private void ocultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ocultarActionPerformed
        
        if (ocultar.isSelected()) {
            contra.setEchoChar((char) 0); 
        } else {
        contra.setEchoChar('*'); 
            }
        
        
    }//GEN-LAST:event_ocultarActionPerformed

    private void contraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contraActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField contra;
    private javax.swing.JButton ingresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logearse;
    private javax.swing.JTextField nombre;
    private javax.swing.JCheckBox ocultar;
    // End of variables declaration//GEN-END:variables
}
