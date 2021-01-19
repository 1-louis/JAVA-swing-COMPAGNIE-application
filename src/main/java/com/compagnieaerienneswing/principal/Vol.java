/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compagnieaerienneswing.principal;

import com.compagnieaerienneswing.dao.AeroportDAO;
import com.compagnieaerienneswing.dao.CompagnieDAO;
import com.compagnieaerienneswing.dao.MySQL;
import com.compagnieaerienneswing.dao.VolDAO;
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
public class Vol extends javax.swing.JFrame {

    private int id;
    private int places;
    private String intitule;
    private int idAeroportDepart;
    private int idAeroportArrivee;
    private String dateDepart;
    private String dateArrivee;
    private int idCompagnie;
    private int idAeroportEscale;
    private String dateDepartEscale;
    private String dateArriveeEscale;
    
    public Vol(int id, int places, String intitule, int idAeroportDepart, int idAeroportArrivee, String dateDepart, String dateArrivee, int idCompagnie) {       
        this.id = id;
        this.places = places;
        this.intitule = intitule;
        this.idAeroportDepart = idAeroportDepart;
        this.idAeroportArrivee = idAeroportArrivee;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.idCompagnie = idCompagnie;
    }
    
    public Vol(int id, int places, String intitule, int idAeroportDepart, int idAeroportArrivee, String dateDepart, String dateArrivee, int idCompagnie, int idAeroportEscale, String dateDepartEscale, String dateArriveeEscale) {       
        this.id = id;
        this.places = places;
        this.intitule = intitule;
        this.idAeroportDepart = idAeroportDepart;
        this.idAeroportArrivee = idAeroportArrivee;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.idCompagnie = idCompagnie;
        this.idAeroportEscale = idAeroportEscale;
        this.dateDepartEscale = dateDepartEscale;
        this.dateArriveeEscale = dateArriveeEscale;
    }
    
    @Override
    public String toString() {
        return intitule + " du " + dateDepart;
    }

    public int getId() {
        return id;
    }

    public int getPlaces(){
        return places;
    }
    
    public String getIntitule(){
        return intitule;
    }
    
    public int getIdAeroportDepart() {
        return idAeroportDepart;
    }
    
    public int getIdAeroportArrivee() {
        return idAeroportArrivee;
    }
    
    public String getDateDepart(){
        return dateDepart;
    }
    
    public String getDateArrivee(){
        return dateArrivee;
    }
    
    public int getIdCompagnie(){
        return idCompagnie;
    }
    
    public int getIdAeroportEscale(){
        return idAeroportEscale;
    }
    
    public String getDateDepartEscale(){
        return dateDepartEscale;
    }
    
    public String getDateArriveeEscale(){
        return dateArriveeEscale;
    }
       
    /**
     * Creates new form Vol
     */
    public Vol() {
        initComponents();
        
        loadVolData();
        loadAeroportData();
        loadCompagnieData();
    }
    
    private boolean isNullOrEmpty(String str){
        if (str == null || str.length() == 0){
            return true;
        }
        return false;
    }
    
    // Load flights data into the table
    private void loadVolData(){
        
        VolDAO vol = new VolDAO();
        String query = "SELECT * FROM vol LEFT JOIN escale ON vol.idvol = escale.vol_idvol ORDER BY date_depart DESC";
        ArrayList<Vol> volList = vol.selectJoinEscale(query);
        DefaultTableModel model = (DefaultTableModel) tableVol.getModel();
        
        for (int i = 0; i < volList.size(); i++){
            int id = volList.get(i).getId();
            int places = volList.get(i).getPlaces();
            String intitule = volList.get(i).getIntitule();           
            int aeroportDepart = volList.get(i).getIdAeroportDepart();
            int aeroportArrivee = volList.get(i).getIdAeroportArrivee();
            String dateDepart = volList.get(i).getDateDepart();  
            String dateArrivee = volList.get(i).getDateArrivee();
            int idCompagnie = volList.get(i).getIdCompagnie();
            int idAeroportEscale = volList.get(i).getIdAeroportEscale();
            String dateArriveeEscale = volList.get(i).getDateArriveeEscale();
            String dateDepartEscale = volList.get(i).getDateDepartEscale();
            String aeroportEscale = "";

            if (idAeroportEscale != 0)
                aeroportEscale = String.valueOf(idAeroportEscale);
            if (dateArriveeEscale == "null")
                dateArriveeEscale = "";
            if (dateDepartEscale == "null")
                dateDepartEscale = "";

            Object[] row = { id, places, intitule, aeroportDepart, aeroportArrivee, dateDepart, dateArrivee, idCompagnie, aeroportEscale, dateArriveeEscale, dateDepartEscale };            
            model.addRow(row);
        }
        
    }
    
    // Load airports data into the comboboxes
    private void loadAeroportData(){
        AeroportDAO aeroport = new AeroportDAO();
        String query = "SELECT * FROM aeroport ORDER BY nom";
        ArrayList<Aeroport> aeroportList = aeroport.select(query);
        DefaultComboBoxModel aeroportDepartModel = new DefaultComboBoxModel();
        DefaultComboBoxModel aeroportArriveeModel = new DefaultComboBoxModel();
        DefaultComboBoxModel aeroportEscaleModel = new DefaultComboBoxModel();
        
        // Alimente la combo box des pays
        for (int i = 0; i < aeroportList.size(); i++){
            int id = aeroportList.get(i).getId();
            String nom = aeroportList.get(i).getName();
            String ville = aeroportList.get(i).getCity();
            int idPays = aeroportList.get(i).getIdPays();;
            
            aeroportDepartModel.addElement(new Aeroport(id, nom, ville, idPays));
            aeroportArriveeModel.addElement(new Aeroport(id, nom, ville, idPays));
            aeroportEscaleModel.addElement(new Aeroport(id, nom, ville, idPays));
        }

        comboAeroportDepart.setModel(aeroportDepartModel);
        comboAeroportArrivee.setModel(aeroportArriveeModel);
        comboAeroportEscale.setModel(aeroportEscaleModel);
    }
    
    // Load airlines data into the combobox
    private void loadCompagnieData(){
        CompagnieDAO compagnie = new CompagnieDAO();
        String query = "SELECT * FROM compagnie ORDER BY nom";
        ArrayList<Compagnie> compagnieList = compagnie.select(query);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        // Alimente la combo box des pays
        for (int i = 0; i < compagnieList.size(); i++){
            int id = compagnieList.get(i).getId();
            String nom = compagnieList.get(i).getName();
            
            model.addElement(new Compagnie(id, nom));
        }

        comboCompagnie.setModel(model);
    }
    
    // Reset the table
    private void reset(){
        DefaultTableModel model = (DefaultTableModel) tableVol.getModel();
        int row = model.getRowCount();
        
        if (row > 0){
            for (int i = 0; i < row; i++){
                model.removeRow(0);
            }
        }
    }
    
    private boolean isInteger(String value){
        try{
            Integer.parseInt(value);
            return true;
        }
        catch (Exception e){
            return false;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        textDateArrivee = new javax.swing.JTextField();
        textPlace = new javax.swing.JTextField();
        textIntitule = new javax.swing.JTextField();
        textDateDepart = new javax.swing.JTextField();
        comboCompagnie = new javax.swing.JComboBox<>();
        comboAeroportDepart = new javax.swing.JComboBox<>();
        comboAeroportArrivee = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVol = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        checkEscale = new javax.swing.JCheckBox();
        comboAeroportEscale = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textDateDepartEscale = new javax.swing.JTextField();
        textDateArriveeEscale = new javax.swing.JTextField();
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

        jLabel1.setText("Ajouter un vol");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 80, 50));

        jLabel2.setText("Compagnie");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, -1, -1));

        jLabel3.setText("Nombre de places");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabel4.setText("Intitulé");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        jLabel5.setText("Aéroport de départ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLabel6.setText("Aéroport d'arrivée");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        jLabel7.setText("Date de départ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        jLabel9.setText("Date d'arrivée");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, -1, -1));
        getContentPane().add(textDateArrivee, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 210, 40));
        getContentPane().add(textPlace, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 210, 40));
        getContentPane().add(textIntitule, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 210, 40));
        getContentPane().add(textDateDepart, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 210, 40));

        getContentPane().add(comboCompagnie, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 210, 40));

        getContentPane().add(comboAeroportDepart, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 210, 40));

        getContentPane().add(comboAeroportArrivee, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 210, 40));

        btnAdd.setText("Ajouter le vol");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 720, 140, 40));

        tableVol.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Places", "Intitulé", "Aéroport de départ", "Aéroport d'arrivée", "Date de départ", "Date d'arrivée", "Compagnie", "Aéroport d'escale", "Date d'arrivée escale", "Date de départ escale"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, true, true, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableVol);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 1240, 440));

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));

        checkEscale.setText("Le vol comprend-il une escale ?");
        checkEscale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEscaleActionPerformed(evt);
            }
        });
        getContentPane().add(checkEscale, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, -1, -1));

        comboAeroportEscale.setEnabled(false);
        getContentPane().add(comboAeroportEscale, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 210, 40));

        jLabel8.setText("Aéroport d'escale");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, -1, -1));

        jLabel10.setText("Date d'arrivée escale");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, -1, -1));

        jLabel11.setText("Date de départ escale");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 660, -1, -1));

        textDateDepartEscale.setEnabled(false);
        getContentPane().add(textDateDepartEscale, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 650, 210, 40));

        textDateArriveeEscale.setEnabled(false);
        getContentPane().add(textDateArriveeEscale, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 590, 210, 40));

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

    private void createEscale(int volId){
        
        Object item = comboAeroportEscale.getSelectedItem();
        
        int idAeroport = ((Aeroport)item).getId();
        String dateArrivee = textDateArriveeEscale.getText();
        String dateDepart = textDateDepartEscale.getText();
        
        try{
            String sql = String.format("INSERT INTO escale VALUES(%s, %s, '%s', '%s');", volId, idAeroport, dateDepart, dateArrivee);
            
            MySQL mySql = new MySQL();
            String escale = mySql.insert(sql);
            
            if (escale != "OK")
                throw new Exception("Une erreur s'est produite lors de la création du vol !");
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    // Insert new flight into the database
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        Object itemAeroportDepart = comboAeroportDepart.getSelectedItem();
        Object itemAeroportArrivee = comboAeroportArrivee.getSelectedItem(); 
        Object itemCompagnie = comboCompagnie.getSelectedItem();
        
        String places = textPlace.getText();
        String intitule = textIntitule.getText();
        int aeroportDepart = ((Aeroport)itemAeroportDepart).getId();
        int aeroportArrivee = ((Aeroport)itemAeroportArrivee).getId();
        String dateDepart = textDateDepart.getText();
        String dateArrivee = textDateArrivee.getText();
        int compagnie = ((Compagnie)itemCompagnie).getId();
        
        String dateDepartEscale = textDateDepartEscale.getText();
        String dateArriveeEscale = textDateArriveeEscale.getText();

        Pattern datePattern = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01]) (\\d{2}):(\\d{2})$");
        
        Matcher matcher = datePattern.matcher(dateDepart);
        boolean isDateDepartValid = matcher.matches();
        
        matcher = datePattern.matcher(dateArrivee);
        boolean isDateArriveeValid = matcher.matches();
        
        matcher = datePattern.matcher(dateDepartEscale);
        boolean isDateDepartEscaleValid = matcher.matches();
        
        matcher = datePattern.matcher(dateArriveeEscale);
        boolean isDateArriveeEscale = matcher.matches();
        
        try{
            if (isNullOrEmpty(places) || isNullOrEmpty(intitule) || isNullOrEmpty(dateDepart) || isNullOrEmpty(dateArrivee))
                throw new Exception("Erreur : tous les champs sont requis !");
            
            if (!isInteger(places))
                throw new Exception("Les places doivent représenter un entier !");
            
            if (!isDateDepartValid || !isDateArriveeValid)
                throw new Exception("Le format de date doit être comme ceci : yyyy-MM-dd hh:mm");
            
            if (checkEscale.isSelected() && (!isDateDepartEscaleValid || !isDateArriveeEscale))
                throw new Exception("Le format de date doit être comme ceci : yyyy-MM-dd hh:mm");
            
            String sql = String.format("INSERT INTO vol VALUES(default, %s, '%s', %s, %s, '%s', '%s', %s);",
                    places, intitule, aeroportDepart, aeroportArrivee, dateDepart, dateArrivee, compagnie);
            
            VolDAO vol = new VolDAO();
            int volId = vol.insert(sql);
            
            if (volId == 0)
                throw new Exception("Une erreur s'est produite lors de la création du vol !");
            
            if (checkEscale.isSelected())
                createEscale(volId);
            
            JOptionPane.showMessageDialog(null, "Le vol a correctement été ajouté !");
            reset();
            loadVolData();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    // Update an existing flight from the database
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try{
            int row = tableVol.getSelectedRow();
            int idCol = tableVol.getColumn("ID").getModelIndex();
            int placeCol = tableVol.getColumn("Places").getModelIndex();
            int intituleCol = tableVol.getColumn("Intitulé").getModelIndex();
            int aeroportDepartCol = tableVol.getColumn("Aéroport de départ").getModelIndex();
            int aeroportArriveeCol = tableVol.getColumn("Aéroport d'arrivée").getModelIndex();
            int dateDepartCol = tableVol.getColumn("Date de départ").getModelIndex();
            int dateArriveeCol = tableVol.getColumn("Date d'arrivée").getModelIndex();
            int compagnieCol = tableVol.getColumn("Compagnie").getModelIndex();

            String id = tableVol.getModel().getValueAt(row, idCol).toString();
            String place = tableVol.getModel().getValueAt(row, placeCol).toString();
            String intitule = tableVol.getModel().getValueAt(row, intituleCol).toString();
            String aeroportDepart = tableVol.getModel().getValueAt(row, aeroportDepartCol).toString();
            String aeroportArrivee = tableVol.getModel().getValueAt(row, aeroportArriveeCol).toString();
            String dateDepart = tableVol.getModel().getValueAt(row, dateDepartCol).toString();
            String dateArrivee = tableVol.getModel().getValueAt(row, dateArriveeCol).toString();
            String compagnie = tableVol.getModel().getValueAt(row, compagnieCol).toString();

            MySQL mySql = new MySQL();
            String sql = String.format("UPDATE vol SET place = %s, intitule = '%s', date_depart = '%s', date_arrive = '%s' WHERE idvol = %s",
                    place, intitule, dateDepart, dateArrivee, id);
            String str = mySql.update(sql);
            
            if(str != "OK"){
                throw new Exception(str);
            }
            
            reset();
            loadVolData();
            JOptionPane.showMessageDialog(null, "Le vol a correctement été mis à jour !");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    // Delete an existing flight from the database
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try{
            int row = tableVol.getSelectedRow();
            int idCol = tableVol.getColumn("ID").getModelIndex();
            String id = tableVol.getModel().getValueAt(row, idCol).toString();

            MySQL mySql = new MySQL();
            String sql = String.format("DELETE FROM vol WHERE idvol = %s", id);
            String str = mySql.delete(sql);
            
            if(str != "OK"){
                throw new Exception(str);
            }
            
            reset();
            loadVolData();
            JOptionPane.showMessageDialog(null, "Le vol a correctement été supprimé !");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void checkEscaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEscaleActionPerformed
        boolean isChecked = checkEscale.isSelected();
        
        comboAeroportEscale.setEnabled(isChecked);
        textDateArriveeEscale.setEnabled(isChecked);
        textDateDepartEscale.setEnabled(isChecked);
    }//GEN-LAST:event_checkEscaleActionPerformed

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
            java.util.logging.Logger.getLogger(Vol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vol().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox checkEscale;
    private javax.swing.JComboBox<String> comboAeroportArrivee;
    private javax.swing.JComboBox<String> comboAeroportDepart;
    private javax.swing.JComboBox<String> comboAeroportEscale;
    private javax.swing.JComboBox<String> comboCompagnie;
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
    private javax.swing.JTable tableVol;
    private javax.swing.JTextField textDateArrivee;
    private javax.swing.JTextField textDateArriveeEscale;
    private javax.swing.JTextField textDateDepart;
    private javax.swing.JTextField textDateDepartEscale;
    private javax.swing.JTextField textIntitule;
    private javax.swing.JTextField textPlace;
    // End of variables declaration//GEN-END:variables
}
