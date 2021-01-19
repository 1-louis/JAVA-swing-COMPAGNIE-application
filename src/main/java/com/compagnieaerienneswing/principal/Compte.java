/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compagnieaerienneswing.principal;

import com.compagnieaerienneswing.dao.ClientDAO;
import com.compagnieaerienneswing.dao.MySQL;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author damie
 */
public class Compte extends javax.swing.JFrame {
    
    private int id;
    private String nom;
    private String prenom;
    private String dateNaiss;
    private String adresse;
    private String ville;
    private String cp;
    private int idPays;
    private String email;
    private String password;
    
    public Compte(int id, String nom, String prenom, String dateNaiss, String adresse, String ville, String cp, int idPays, String email, String password) {       
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.adresse = adresse;
        this.ville = ville;
        this.cp = cp;
        this.idPays = idPays;
        this.email = email;
        this.password = password;
    }
    
    @Override
    public String toString() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return nom;
    }
    
    public String getFirstname() {
        return prenom;
    }
    
    public String getBirthdate() {
        return dateNaiss;
    }
    
    public String getAddress() {
        return adresse;
    }
    
    public String getCity() {
        return ville;
    }
    
    public String getZipcode() {
        return cp;
    }
    
    public int getIdPays() {
        return idPays;
    }
    
    public String getMail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }

    /**
     * Creates new form GestionDuCompte
     */
    public Compte() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textMail = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        textPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCreateAccount = new javax.swing.JMenu();
        menuManageAccount = new javax.swing.JMenu();
        menuAirport = new javax.swing.JMenu();
        menuAirline = new javax.swing.JMenu();
        menuCountry = new javax.swing.JMenu();
        menuBooking = new javax.swing.JMenu();
        menuFlight = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(textMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 111, 220, 40));

        btnLogin.setText("Se connecter");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, 100, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Connexion");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 100, 40));

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nom", "Prénom", "Date de naissance", "Adresse", "Ville", "CP", "Pays", "Email", "Mot de passe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableUser);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 800, 470));

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 300, 100, 40));

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 360, 100, 40));
        getContentPane().add(textPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 220, 40));

        jLabel2.setText("Email");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, -1, -1));

        jLabel3.setText("Mot de passe");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, -1, -1));

        menuCreateAccount.setText("Créer un compte client");
        menuCreateAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCreateAccountMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuCreateAccount);

        menuManageAccount.setText("Gestion du compte");
        menuManageAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuManageAccountMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuManageAccount);

        menuAirport.setText("Aéroport");
        menuAirport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAirportMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuAirport);

        menuAirline.setText("Compagnie");
        menuAirline.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuAirlineMenuSelected(evt);
            }
        });
        jMenuBar1.add(menuAirline);

        menuCountry.setText("Pays");
        menuCountry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCountryMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuCountry);

        menuBooking.setText("Réservation");
        menuBooking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuBookingMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuBooking);

        menuFlight.setText("Vol");
        menuFlight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuFlightMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuFlight);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean isNullOrEmpty(String str){
        if (str == null || str.length() == 0){
            return true;
        }
        return false;
    }
    
    private void reset(){
        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
        int row = model.getRowCount();
        
        if (row > 0){
            for (int i = 0; i < row; i++){
                model.removeRow(0);
            }
        }
    }
    
    private void loadUserData(ArrayList<Compte> compteList){
        // Load user's data into the table
        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();

        int id = compteList.get(0).getId();
        String nom = compteList.get(0).getName();
        String prenom = compteList.get(0).getFirstname();
        String dateNaiss = compteList.get(0).getBirthdate();
        String adresse = compteList.get(0).getAddress();
        String ville = compteList.get(0).getCity();
        String cp = compteList.get(0).getZipcode();
        int idPays = compteList.get(0).getIdPays();
        String email = compteList.get(0).getMail();
        String password = compteList.get(0).getPassword();

        Object[] row = { id, nom, prenom, dateNaiss, adresse, ville, cp, idPays, email, password };          
        model.addRow(row);
    }
    
    // Show user's data once logged in.
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String mail = textMail.getText();
        String password = textPassword.getText();
        
        try{
            if (isNullOrEmpty(mail) || isNullOrEmpty(password))
                throw new Exception("Veuillez saisir vos identifiants.");
            
            ClientDAO client = new ClientDAO();
            String query = String.format("SELECT * FROM personne INNER JOIN client on personne.idpersonne = client.personne_idpersonne WHERE email = '%s'", mail);
            ArrayList<Compte> compteList = client.select(query);
            
            if (compteList.size() == 0)
                throw new Exception("Identifiant incorrect");
            
            String storedPassword = compteList.get(0).getPassword();
            
            if (!password.equals(storedPassword))
                throw new Exception("Mot de passe incorrect");
            
            JOptionPane.showMessageDialog(null, "Bienvenue " + compteList.get(0).getFirstname() + " !");
            
            loadUserData(compteList);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    // Update current user's data.
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try{
            int row = tableUser.getSelectedRow();
            int idCol = tableUser.getColumn("ID").getModelIndex();
            int nomCol = tableUser.getColumn("Nom").getModelIndex();
            int prenomCol = tableUser.getColumn("Prénom").getModelIndex();
            int dateNaissCol = tableUser.getColumn("Date de naissance").getModelIndex();
            int adresseCol = tableUser.getColumn("Adresse").getModelIndex();
            int villeCol = tableUser.getColumn("Ville").getModelIndex();
            int cpCol = tableUser.getColumn("CP").getModelIndex();
            int paysCol = tableUser.getColumn("Pays").getModelIndex();
            int emailCol = tableUser.getColumn("Email").getModelIndex();
            int passwordCol = tableUser.getColumn("Mot de passe").getModelIndex();

            String id = tableUser.getModel().getValueAt(row, idCol).toString();
            String nom = tableUser.getModel().getValueAt(row, nomCol).toString();
            String prenom = tableUser.getModel().getValueAt(row, prenomCol).toString();
            String dateNaiss = tableUser.getModel().getValueAt(row, dateNaissCol).toString();
            String adresse = tableUser.getModel().getValueAt(row, adresseCol).toString();
            String ville = tableUser.getModel().getValueAt(row, villeCol).toString();
            String cp = tableUser.getModel().getValueAt(row, cpCol).toString();
            String pays = tableUser.getModel().getValueAt(row, paysCol).toString();
            String email = tableUser.getModel().getValueAt(row, emailCol).toString();
            String password = tableUser.getModel().getValueAt(row, passwordCol).toString();

            MySQL mySql = new MySQL();
            String queryPersonne = String.format("UPDATE personne SET nom = '%s', prenom = '%s', dateNaiss = '%s', adresse = '%s', ville = '%s', zipcode = '%s' WHERE idpersonne = %s", nom, prenom, dateNaiss, adresse, ville, cp, id);
            String queryClient = String.format("UPDATE client SET email = '%s', password = '%s' WHERE personne_idpersonne = %s", email, password, id);
            String personneInsert = mySql.update(queryPersonne);
            String clientInsert = mySql.update(queryClient);
            
            if(personneInsert != "OK" || clientInsert != "OK"){
                throw new Exception(personneInsert);
            }
            
            ClientDAO client = new ClientDAO();
            String query = String.format("SELECT * FROM personne INNER JOIN client on personne.idpersonne = client.personne_idpersonne WHERE idpersonne = %s", id);
            ArrayList<Compte> compteList = client.select(query);
            
            reset();
            loadUserData(compteList);
            JOptionPane.showMessageDialog(null, "L'utilisateur a correctement été mis à jour !");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    // Delete current user's account.
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try{
            int row = tableUser.getSelectedRow();
            int idCol = tableUser.getColumn("ID").getModelIndex();
            String id = tableUser.getModel().getValueAt(row, idCol).toString();

            MySQL mySql = new MySQL();
            String sql = String.format("DELETE FROM personne WHERE idpersonne = %s", id);
            String str = mySql.delete(sql);
            
            if(str != "OK"){
                throw new Exception(str);
            }
            
            reset();
            JOptionPane.showMessageDialog(null, "L'utilisateur a correctement été supprimé !");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void menuCreateAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCreateAccountMouseClicked
        new Client().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_menuCreateAccountMouseClicked

    private void menuManageAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuManageAccountMouseClicked
        new Compte().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_menuManageAccountMouseClicked

    private void menuAirportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAirportMouseClicked
        new Aeroport().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_menuAirportMouseClicked

    private void menuAirlineMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuAirlineMenuSelected
        new Compagnie().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_menuAirlineMenuSelected

    private void menuCountryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCountryMouseClicked
        new Pays().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_menuCountryMouseClicked

    private void menuBookingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBookingMouseClicked
        new Reservation().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_menuBookingMouseClicked

    private void menuFlightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuFlightMouseClicked
        new Vol().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_menuFlightMouseClicked

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
            java.util.logging.Logger.getLogger(Compte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuAirline;
    private javax.swing.JMenu menuAirport;
    private javax.swing.JMenu menuBooking;
    private javax.swing.JMenu menuCountry;
    private javax.swing.JMenu menuCreateAccount;
    private javax.swing.JMenu menuFlight;
    private javax.swing.JMenu menuManageAccount;
    private javax.swing.JTable tableUser;
    private javax.swing.JTextField textMail;
    private javax.swing.JPasswordField textPassword;
    // End of variables declaration//GEN-END:variables
}
