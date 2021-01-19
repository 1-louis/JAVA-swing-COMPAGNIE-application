/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compagnieaerienneswing.principal;

import com.compagnieaerienneswing.dao.ClientDAO;
import com.compagnieaerienneswing.dao.MySQL;
import com.compagnieaerienneswing.dao.PaysDAO;
import com.compagnieaerienneswing.dao.PersonneDAO;
import com.compagnieaerienneswing.dao.ReservationDAO;
import com.compagnieaerienneswing.dao.VolDAO;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author damie
 */
public class Reservation extends javax.swing.JFrame {

    private int id;
    private int idClient;
    private String confirmation;
    private int idPersonne;
    
    public Reservation(int id, int idClient, String confirmation, int idPersonne) {       
        this.id = id;
        this.idClient = idClient;
        this.confirmation = confirmation;
        this.idPersonne = idPersonne;
    }
    
    @Override
    public String toString() {
        return "Réservation N° " + id;
    }

    public int getId() {
        return id;
    }
    
    public int getIdClient(){
        return idClient;
    }

    public String getConfirmation() {
        return confirmation;
    }
    
    public int getIdPersonne() {
        return idPersonne;
    }
    
    /**
     * Creates new form Reservation
     */
    public Reservation() {
        initComponents();
        
        loadFlightData();
        loadCountryData();
        
        // Hide userId field in order to store the current logged user's id
        textUserId.setVisible(false);
    }
    
    // Load flights data
    private void loadFlightData(){
        VolDAO vol = new VolDAO();
        String query = "SELECT * FROM vol WHERE date_depart > NOW()";
        ArrayList<Vol> volList = vol.select(query);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        // Alimente la combo box des vols
        for (int i = 0; i < volList.size(); i++){
            int id = volList.get(i).getId();
            int places = volList.get(i).getPlaces();
            String intitule = volList.get(i).getIntitule();
            int aeroportDepart = volList.get(i).getIdAeroportDepart();
            int aeroportArrivee = volList.get(i).getIdAeroportArrivee();
            String dateDepart = volList.get(i).getDateDepart();
            String dateArrivee = volList.get(i).getDateArrivee();
            int compagnie = volList.get(i).getIdCompagnie();
            
            model.addElement(new Vol(id, places, intitule, aeroportDepart, aeroportArrivee, dateDepart, dateArrivee, compagnie));
        }

        comboFlights.setModel(model);
    }
    
    // Load countries data
    private void loadCountryData() {      
        PaysDAO pays = new PaysDAO();
        String query = "SELECT * FROM pays ORDER BY nom";
        ArrayList<Pays> paysList = pays.select(query);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        // Alimente la combo box des pays
        for (int i = 0; i < paysList.size(); i++){
            int id = paysList.get(i).getId();
            String name = paysList.get(i).getName();
            
            model.addElement(new Pays(id, name));
        }

        comboPays.setModel(model);
    }
    
    // Load bookings data
    private void loadBookingsData(){
        
        String userId = textUserId.getText();
        
        ReservationDAO resa = new ReservationDAO();
        String query = String.format("SELECT * FROM reservation WHERE client_idpersonne = %s", userId);
        ArrayList<Reservation> reservationList = resa.select(query);
        DefaultTableModel model = (DefaultTableModel) tableResa.getModel();
        
        for (int i = 0; i < reservationList.size(); i++){
            int id = reservationList.get(i).getId();
            int idClient = reservationList.get(i).getIdClient();
            String confirmation = reservationList.get(i).getConfirmation();
            int idPersonne = reservationList.get(i).getIdPersonne();
            
            Object[] row = { id, idClient, confirmation, idPersonne};          
            model.addRow(row);
        }
        
        int row = model.getRowCount();
        
        if (row > 0){
            btnConfirm.setEnabled(true);
            btnDelete.setEnabled(true);
        }
    }
    
    private boolean isNullOrEmpty(String str){
        if (str == null || str.length() == 0){
            return true;
        }
        return false;
    }
    
    private void reset(){
        DefaultTableModel model = (DefaultTableModel) tableResa.getModel();
        int row = model.getRowCount();
        
        if (row > 0){
            for (int i = 0; i < row; i++){
                model.removeRow(0);
            }
        }
    }
    
    private int createNewPersonne(){
        Object item = comboPays.getSelectedItem();
        int idPays = ((Pays)item).getId();
        
        String nom = textNom.getText();
        String prenom = textPrenom.getText();
        String dateNaiss = textDateNaiss.getText();
        String adresse = textAdresse.getText();
        String ville = textVille.getText();
        String cp = textCp.getText();
        
        Pattern datePattern = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");
        Matcher matcher = datePattern.matcher(dateNaiss);
        boolean isDateValid = matcher.matches();
        
        try{
            if (isNullOrEmpty(nom) || isNullOrEmpty(prenom) || isNullOrEmpty(dateNaiss) || isNullOrEmpty(adresse) || isNullOrEmpty(ville) || isNullOrEmpty(cp)){
                throw new Exception("Erreur : tous les champs sont requis !");
            }
            
            if (!isDateValid)
                throw new Exception("Le format de la date de naissance est incorrect ! Le format doit être de type yyyy-MM-dd");
            
            String sql = String.format("INSERT INTO personne VALUES (default, '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                nom, prenom, dateNaiss, adresse, ville, cp, idPays);

            PersonneDAO personne = new PersonneDAO();
            int personneId = personne.insert(sql);

            return personneId;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableResa = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        textCp = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        comboFlights = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        checkOtherPassenger = new javax.swing.JCheckBox();
        textEmail = new javax.swing.JTextField();
        textNom = new javax.swing.JTextField();
        textPrenom = new javax.swing.JTextField();
        textDateNaiss = new javax.swing.JTextField();
        textAdresse = new javax.swing.JTextField();
        textVille = new javax.swing.JTextField();
        comboPays = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnBook = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        textUserId = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        textPassword = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
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

        tableResa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vol", "Client", "Confirmation", "Personne"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableResa);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 360, 260));

        jLabel1.setText("Connectez-vous");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, 20));

        textCp.setEnabled(false);
        getContentPane().add(textCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, 200, 40));

        btnLogin.setText("Connexion");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, 30));

        comboFlights.setEnabled(false);
        getContentPane().add(comboFlights, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 250, 40));

        jLabel2.setText("Sélectionnez le vol que vous souhaitez réserver");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, -1, 20));

        checkOtherPassenger.setText("Vous réservez pour un autre passager ?");
        checkOtherPassenger.setEnabled(false);
        checkOtherPassenger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOtherPassengerActionPerformed(evt);
            }
        });
        getContentPane().add(checkOtherPassenger, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, -1, 20));
        getContentPane().add(textEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 200, 40));

        textNom.setEnabled(false);
        getContentPane().add(textNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 200, 40));

        textPrenom.setEnabled(false);
        getContentPane().add(textPrenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, 200, 40));

        textDateNaiss.setEnabled(false);
        getContentPane().add(textDateNaiss, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 200, 40));

        textAdresse.setEnabled(false);
        getContentPane().add(textAdresse, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 200, 40));

        textVille.setEnabled(false);
        getContentPane().add(textVille, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 550, 200, 40));

        comboPays.setEnabled(false);
        getContentPane().add(comboPays, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 630, 200, 40));

        jLabel3.setText("Pays");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 610, -1, -1));

        jLabel4.setText("Nom");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, -1, -1));

        jLabel5.setText("Prénom");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, -1, -1));

        jLabel6.setText("Date de naissance");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, -1, -1));

        jLabel7.setText("Adresse");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 460, -1, -1));

        jLabel8.setText("Ville");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, -1, -1));

        jLabel9.setText("Code postal");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 530, -1, -1));

        btnBook.setText("Réserver");
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });
        getContentPane().add(btnBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 630, 90, 40));

        btnDelete.setText("Annuler la réservation");
        btnDelete.setActionCommand("");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 140, 170, 40));

        textUserId.setEditable(false);
        textUserId.setEnabled(false);
        textUserId.setFocusable(false);
        getContentPane().add(textUserId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1524, 820, 120, 30));

        btnConfirm.setText("Confirmer la réservation");
        btnConfirm.setEnabled(false);
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 90, 170, 40));
        getContentPane().add(textPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 200, 40));

        jLabel10.setText("Email");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, -1));

        jLabel11.setText("Mot de passe");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

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

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String mail = textEmail.getText();
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
            
            comboFlights.setEnabled(true);
            checkOtherPassenger.setEnabled(true);
            
            String userId = String.valueOf(compteList.get(0).getId());
            textUserId.setText(userId);
            
            loadBookingsData();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void checkOtherPassengerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOtherPassengerActionPerformed
        
        boolean isChecked = checkOtherPassenger.isSelected();       
        
        textNom.setEnabled(isChecked);
        textPrenom.setEnabled(isChecked);
        textDateNaiss.setEnabled(isChecked);
        textAdresse.setEnabled(isChecked);
        textVille.setEnabled(isChecked);
        textCp.setEnabled(isChecked);
        comboPays.setEnabled(isChecked);
    }//GEN-LAST:event_checkOtherPassengerActionPerformed

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed
        
        Object item = comboFlights.getSelectedItem();       
        int idVol = ((Vol)item).getId();
        int userId = parseInt(textUserId.getText());
        int idPersonne = userId;
        
        try{
            // En cas de réservation pour le client actuellement connecté
            if (!checkOtherPassenger.isSelected()){
                ReservationDAO resa = new ReservationDAO();
                String sql = String.format("SELECT * FROM reservation WHERE idvol = %s AND personne_idpersonne = %s", idVol, userId);
                ArrayList<Reservation> reservationList = resa.select(sql);

                if (reservationList.size() > 0)
                    throw new Exception("Une réservation à votre nom existe déjà pour ce vol !");
            }
            else { // En cas de réservation pour un autre passager
                idPersonne = createNewPersonne();
                if (idPersonne == 0)
                    throw new Exception ("Erreur : impossible d'effectuer la réservation pour ce passager.");
            }
            
            MySQL mySql = new MySQL();
            String createResa = String.format("INSERT INTO reservation VALUES (%s, %s, 'Pending', %s)", idVol, userId, idPersonne);
            String resaInsert = mySql.insert(createResa);
            
            if (resaInsert != "OK")
                throw new Exception(resaInsert);
            
            String createPassenger = String.format("INSERT INTO passager VALUES (default, %s)", idPersonne);
            String passengerInsert = mySql.insert(createPassenger);
            
            if (passengerInsert != "OK")
                throw new Exception(passengerInsert);
            
            JOptionPane.showMessageDialog(null, "Votre réservation a été enregistrée et est en attente. Veuillez la confirmer ou l'annuler.");
            
            reset();
            loadBookingsData();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnBookActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        try{
            int row = tableResa.getSelectedRow();
            int idVolCol = tableResa.getColumn("Vol").getModelIndex();
            int idPersonneCol = tableResa.getColumn("Personne").getModelIndex();

            String idVol = tableResa.getModel().getValueAt(row, idVolCol).toString();
            String idPersonne = tableResa.getModel().getValueAt(row, idPersonneCol).toString();

            MySQL mySql = new MySQL();
            String sql = String.format("UPDATE reservation SET confirmation = 'Confirm' WHERE idvol = %s AND personne_idpersonne = %s", idVol, idPersonne);
            String str = mySql.update(sql);
            
            if(str != "OK"){
                throw new Exception(str);
            }
            
            reset();
            loadBookingsData();
            JOptionPane.showMessageDialog(null, "Votre réservation est confirmée !");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try{
            int row = tableResa.getSelectedRow();          
            int idVolCol = tableResa.getColumn("Vol").getModelIndex();
            int idPersonneCol = tableResa.getColumn("Personne").getModelIndex();
            
            String idVol = tableResa.getModel().getValueAt(row, idVolCol).toString();
            String idPersonne = tableResa.getModel().getValueAt(row, idPersonneCol).toString();

            MySQL mySql = new MySQL();
            String sql = String.format("DELETE FROM reservation WHERE idvol = %s AND personne_idpersonne = %s", idVol, idPersonne);
            String str = mySql.delete(sql);
            
            if(str != "OK"){
                throw new Exception(str);
            }
            
            reset();
            loadBookingsData();
            JOptionPane.showMessageDialog(null, "La réservation a été annulée !");
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
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reservation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox checkOtherPassenger;
    private javax.swing.JComboBox<String> comboFlights;
    private javax.swing.JComboBox<String> comboPays;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuAirline;
    private javax.swing.JMenu menuAirport;
    private javax.swing.JMenu menuBooking;
    private javax.swing.JMenu menuCountry;
    private javax.swing.JMenu menuCreateAccount;
    private javax.swing.JMenu menuFlight;
    private javax.swing.JMenu menuManageAccount;
    private javax.swing.JTable tableResa;
    private javax.swing.JTextField textAdresse;
    private javax.swing.JTextField textCp;
    private javax.swing.JTextField textDateNaiss;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textNom;
    private javax.swing.JPasswordField textPassword;
    private javax.swing.JTextField textPrenom;
    private javax.swing.JTextField textUserId;
    private javax.swing.JTextField textVille;
    // End of variables declaration//GEN-END:variables
}
