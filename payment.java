
package Payment;

import Pay.pay;
import Service_List.list;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class payment extends javax.swing.JFrame {
    
    private Connection conn;

    public payment(String clientName) {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        connect();
        loadServices(clientName);
        updateTotal();
        txtNAMEEE.setText(clientName);
    }
    
    private void connect() {
        String url = "jdbc:mysql://localhost:3306/salon";
        String user = "root";
        String password = "";

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage());
        }
    }

    private void loadServices(String clientName) {
        String query = "SELECT Attendant, Services, Price FROM lists WHERE Client = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, clientName);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ATTENDANT");
            model.addColumn("SERVICE");
            model.addColumn("PRICE");

            while (rs.next()) {
                String attendant = rs.getString("Attendant");
                String service = rs.getString("Services");
                double price = rs.getDouble("Price");
                model.addRow(new Object[]{attendant, service, "₱ " + String.format("%.2f", price)});
            }

            Stable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading services: " + e.getMessage());
        }
    }
    
    private void updateTotal() {
        double total = 0.0;
        DefaultTableModel model = (DefaultTableModel) Stable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String priceText = model.getValueAt(i, 2).toString().replace("₱ ", "").replace(",", "");
            total += Double.parseDouble(priceText);
        }
        txtTOTAL.setText("₱ " + String.format("%.2f", total));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtNAMEEE = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Stable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnADD = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTOTAL = new javax.swing.JTextField();
        btnPAYMENT = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDATE = new javax.swing.JTextField();
        txtTIME = new javax.swing.JTextField();
        txtPERIOD = new javax.swing.JTextField();
        btnREM = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel23.setText("CLIENT NAME: ");

        txtNAMEEE.setBackground(new java.awt.Color(255, 204, 204));
        txtNAMEEE.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtNAMEEE.setBorder(null);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 50)); // NOI18N
        jLabel2.setText("CONFIRM YOUR APPPOINTMENT HERE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNAMEEE, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(txtNAMEEE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        Stable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Stable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ATTENDANT", "SERVICE", "PRICE"
            }
        ));
        jScrollPane1.setViewportView(Stable);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        jLabel1.setText("YOUR SELECTED SERVICES");

        btnADD.setBackground(new java.awt.Color(255, 204, 204));
        btnADD.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnADD.setText("ADD");
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("TOTAL:");

        txtTOTAL.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTOTAL.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTOTAL.setBorder(null);

        btnPAYMENT.setBackground(new java.awt.Color(255, 204, 204));
        btnPAYMENT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPAYMENT.setText("PROCEED TO PAYMENT");
        btnPAYMENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPAYMENTActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("SELECT AN APPOINTMENT DATE:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("DATE: (YYYY-MM-DD)");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("TIME: (HH:MM)");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("PERIOD: (AM or PM)");

        txtDATE.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDATE.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtTIME.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTIME.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtPERIOD.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtPERIOD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jLabel7))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTIME, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPERIOD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8)))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTIME, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPERIOD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btnREM.setBackground(new java.awt.Color(255, 102, 102));
        btnREM.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnREM.setText("REMOVE");
        btnREM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnREMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127)
                        .addComponent(btnREM, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(172, 172, 172))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnPAYMENT)
                                .addGap(283, 283, 283))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(298, 298, 298))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnREM, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPAYMENT, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1540, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPAYMENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPAYMENTActionPerformed
        String clientName = txtNAMEEE.getText();
        String date = txtDATE.getText();
        String time = txtTIME.getText();
        String period = txtPERIOD.getText();

        if (date.isEmpty() || time.isEmpty() || period.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the fields for your appointment date before proceeding.", "Data Missing", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String checkQuery = "SELECT COUNT(*) FROM appointments WHERE Date = ? AND Time = ? AND Period = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setString(1, date);
            checkStmt.setString(2, time);
            checkStmt.setString(3, period);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "This date and time is already booked. Please enter a different appointment date and time.", "Date/Time Exists", JOptionPane.WARNING_MESSAGE);
                txtDATE.setText("");
                txtTIME.setText("");
                txtPERIOD.setText("");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error checking date and time: " + e.getMessage());
            return;
        }

        int response = JOptionPane.showConfirmDialog(this, "Proceed to payment?", "Confirm Payment", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {

            DefaultTableModel model = (DefaultTableModel) Stable.getModel();
            try {
                String insertQuery = "INSERT INTO appointments (Client, Attendant, Services, Price, Date, Time, Period) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertQuery);

                for (int i = 0; i < model.getRowCount(); i++) {
                    String attendant = model.getValueAt(i, 0).toString();
                    String service = model.getValueAt(i, 1).toString();
                    String priceText = model.getValueAt(i, 2).toString().replace("₱ ", "").replace(",", "");
                    double price = Double.parseDouble(priceText);

                    pstmt.setString(1, clientName);
                    pstmt.setString(2, attendant);
                    pstmt.setString(3, service);
                    pstmt.setDouble(4, price);
                    pstmt.setString(5, date);
                    pstmt.setString(6, time);
                    pstmt.setString(7, period);
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
                JOptionPane.showMessageDialog(this, "Payment details saved successfully!");

                String total = txtTOTAL.getText();
                pay p = new pay(txtNAMEEE.getText(), total);
                p.setVisible(true);

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving payment details: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Payment cancelled.");
        }
    }//GEN-LAST:event_btnPAYMENTActionPerformed

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        String clientName = txtNAMEEE.getText();
        list li = new list(clientName);
        li.setVisible(true);
        
        loadServices(clientName);
        updateTotal();
    }//GEN-LAST:event_btnADDActionPerformed

    private void btnREMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnREMActionPerformed
        int selectedRow = Stable.getSelectedRow();
        if (selectedRow != -1) {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure to remove this from your booked appointments?", "Confirm Remove", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) Stable.getModel();
                String service = model.getValueAt(selectedRow, 1).toString();
                String attendant = model.getValueAt(selectedRow, 0).toString();

                String deleteQuery = "DELETE FROM lists WHERE Client = ? AND Attendant = ? AND Services = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                    pstmt.setString(1, txtNAMEEE.getText());
                    pstmt.setString(2, attendant);
                    pstmt.setString(3, service);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error removing service from database: " + e.getMessage());
                }

                model.removeRow(selectedRow);
                updateTotal();
                JOptionPane.showMessageDialog(this, "Service removed successfully!");
            } 
        } 
        else {
            JOptionPane.showMessageDialog(this, "Please select a service to remove.", "No Service Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnREMActionPerformed

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
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new payment("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Stable;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnPAYMENT;
    private javax.swing.JButton btnREM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDATE;
    private javax.swing.JTextField txtNAMEEE;
    private javax.swing.JTextField txtPERIOD;
    private javax.swing.JTextField txtTIME;
    private javax.swing.JTextField txtTOTAL;
    // End of variables declaration//GEN-END:variables
}
