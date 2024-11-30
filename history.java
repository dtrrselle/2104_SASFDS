
package History;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import salon.HomePage;

public class history extends javax.swing.JFrame {

    Connection conn;
    
    public history() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        connect();
        loadTransactionHistory();
        loadDates();
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

    private void loadTransactionHistory(){
        String query = "SELECT Client, Attendant, Services, Price, Date, Time, Period, Date_and_Time FROM appointments";

        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                String client = rs.getString("Client");
                String appointmentDate = rs.getString("Attendant");
                String service = rs.getString("Services");
                double price = rs.getDouble("Price");
                Date date = rs.getDate("Date");
                String timeString = rs.getString("Time");
                String period = rs.getString("Period");
                String dateTime = rs.getString("Date_and_Time");
                
                Time time = null;
                try {
                    if (timeString != null && timeString.matches("\\d{2}:\\d{2}:\\d{2}")) {
                        time = Time.valueOf(timeString);
                    } else if (timeString != null && timeString.matches("\\d{1,2}:\\d{2}")) {
                        time = Time.valueOf(timeString + ":00");
                    } else {
                        System.out.println("Invalid time format: " + timeString);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error converting time: " + e.getMessage());
                }
                
                model.addRow(new Object[]{client, appointmentDate, service, price, date, time, period, dateTime});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading transaction history: " + e.getMessage());
        }
    }
    
    private void loadDates() {
        cbDATE.removeAllItems();
        String query = "SELECT DISTINCT DATE(Date_and_Time) AS HistoryDate FROM appointments";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            
            while (rs.next()) {
                Date historyDate = rs.getDate("HistoryDate");
                cbDATE.addItem(historyDate.toString());
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading dates: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblHA = new javax.swing.JLabel();
        btnback = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSEARCH = new javax.swing.JTextField();
        btnSEARCH = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        SearchTable = new javax.swing.JTable();
        cbDATE = new javax.swing.JComboBox<>();
        txtNOA = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSDATE = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtINCOME = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        lblHA.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblHA.setText("HISTORY OF APPOINTMENTS");

        btnback.setBackground(new java.awt.Color(255, 204, 204));
        btnback.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnback.setForeground(new java.awt.Color(51, 51, 51));
        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FACIAL/back (1).png"))); // NOI18N
        btnback.setBorder(null);
        btnback.setBorderPainted(false);
        btnback.setContentAreaFilled(false);
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(319, 319, 319)
                .addComponent(lblHA)
                .addContainerGap(418, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHA))
                .addGap(28, 28, 28))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1550, 120));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CLIENTS", "ATTENDANTS", "SERVICES", "PRICE", "DATE OF APPOINTMENT", "TIME", "PERIOD", "DATE OF BOOKING"
            }
        ));
        jScrollPane1.setViewportView(historyTable);
        if (historyTable.getColumnModel().getColumnCount() > 0) {
            historyTable.getColumnModel().getColumn(0).setMinWidth(150);
            historyTable.getColumnModel().getColumn(0).setMaxWidth(150);
            historyTable.getColumnModel().getColumn(3).setMinWidth(110);
            historyTable.getColumnModel().getColumn(3).setMaxWidth(110);
            historyTable.getColumnModel().getColumn(5).setMinWidth(100);
            historyTable.getColumnModel().getColumn(5).setMaxWidth(100);
            historyTable.getColumnModel().getColumn(6).setMinWidth(75);
            historyTable.getColumnModel().getColumn(6).setMaxWidth(75);
        }

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("SEARCH A NAME HERE:");

        txtSEARCH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtSEARCH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSEARCH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnSEARCH.setBackground(new java.awt.Color(255, 204, 204));
        btnSEARCH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSEARCH.setText("SEARCH");
        btnSEARCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSEARCHActionPerformed(evt);
            }
        });

        SearchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ATTENDANTS", "SERVICES", "PRICE", "DATE OF APPOINTMENT", "TIME", "PERIOD"
            }
        ));
        jScrollPane2.setViewportView(SearchTable);
        if (SearchTable.getColumnModel().getColumnCount() > 0) {
            SearchTable.getColumnModel().getColumn(2).setMinWidth(100);
            SearchTable.getColumnModel().getColumn(2).setMaxWidth(100);
            SearchTable.getColumnModel().getColumn(3).setMinWidth(200);
            SearchTable.getColumnModel().getColumn(3).setMaxWidth(200);
            SearchTable.getColumnModel().getColumn(4).setMinWidth(65);
            SearchTable.getColumnModel().getColumn(4).setMaxWidth(65);
            SearchTable.getColumnModel().getColumn(5).setMinWidth(65);
            SearchTable.getColumnModel().getColumn(5).setMaxWidth(65);
        }

        cbDATE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtNOA.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtNOA.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("DATE :");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("TOTAL NO. OF APPOINTMENTS :");

        btnSDATE.setBackground(new java.awt.Color(255, 204, 204));
        btnSDATE.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSDATE.setText("SEARCH");
        btnSDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSDATEActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("TOTAL INCOME :");

        txtINCOME.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtINCOME.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSEARCH))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(118, 118, 118)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtINCOME, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnSDATE, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNOA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNOA, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtINCOME, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1550, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        HomePage hp = new HomePage();
        hp.setVisible(true);
    }//GEN-LAST:event_btnbackActionPerformed

    private void btnSEARCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSEARCHActionPerformed
        String clientName = txtSEARCH.getText().trim();

        if (clientName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a client name to search.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String query = "SELECT Attendant, Services, Price, Date, Time, Period FROM appointments WHERE Client = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, clientName);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel searchModel = (DefaultTableModel) SearchTable.getModel();
            searchModel.setRowCount(0);

            if (rs.next()) {
                do {
                    String attendant = rs.getString("Attendant");
                    String service = rs.getString("Services");
                    double price = rs.getDouble("Price");
                    Date date = rs.getDate("Date");
                    String time = rs.getString("Time");
                    String period = rs.getString("Period");

                    searchModel.addRow(new Object[]{attendant, service, price, date, time, period});
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(this, "No client found with the name: " + clientName, "No Match", JOptionPane.INFORMATION_MESSAGE);
                txtSEARCH.setText("");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching for client: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSEARCHActionPerformed

    private void btnSDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSDATEActionPerformed
        String selectedDate = (String) cbDATE.getSelectedItem();

        if (selectedDate == null || selectedDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a date.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String query = "SELECT COUNT(*) AS appointmentCount, SUM(Price) AS totalPrice FROM appointments WHERE DATE(Date_and_Time) = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, selectedDate);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int appointmentCount = rs.getInt("appointmentCount");
                double totalPrice = rs.getDouble("totalPrice");

                txtNOA.setText(String.valueOf(appointmentCount)); // Set the appointment count
                txtINCOME.setText("₱ " + String.format("%.2f", totalPrice)); // Set the total price with peso sign
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error counting appointments: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSDATEActionPerformed

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
            java.util.logging.Logger.getLogger(history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new history().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable SearchTable;
    private javax.swing.JButton btnSDATE;
    private javax.swing.JButton btnSEARCH;
    private javax.swing.JButton btnback;
    private javax.swing.JComboBox<String> cbDATE;
    private javax.swing.JTable historyTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHA;
    private javax.swing.JTextField txtINCOME;
    private javax.swing.JTextField txtNOA;
    private javax.swing.JTextField txtSEARCH;
    // End of variables declaration//GEN-END:variables
}
