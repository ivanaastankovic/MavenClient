
package rs.bg.ac.student.ivana.MavenClient.form;

import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTable;

public class FrmShowClient extends javax.swing.JDialog {

    /**
     * Creates new form ShowClient
     */
    public FrmShowClient(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        lblClientName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblJMBG = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblSignantureType = new javax.swing.JLabel();
        btnShowClaims = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContacts = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnDeleteContact = new javax.swing.JButton();
        btnEditClient = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClaims = new javax.swing.JTable();
        btnAddContact = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblClientName.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblClientName.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel1.setText("JMBG");

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel2.setText("Signature date");

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel3.setText("Signature type");

        lblJMBG.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblJMBG.setText("jLabel4");

        lblDate.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblDate.setText("jLabel5");

        lblSignantureType.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblSignantureType.setText("jLabel6");

        btnShowClaims.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnShowClaims.setText("Show claims");

        btnDelete.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnDelete.setText("DELETE ");

        tblContacts.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        tblContacts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblContacts);

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel4.setText("Contacts:");

        btnDeleteContact.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnDeleteContact.setText("Delete contact");

        btnEditClient.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnEditClient.setText("Save changes");

        jLabel5.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel5.setText("Active claims:");

        tblClaims.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        tblClaims.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblClaims);

        btnAddContact.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnAddContact.setText("Add contact");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblClientName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblJMBG, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                    .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblSignantureType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                        .addComponent(btnShowClaims)
                        .addGap(26, 26, 26)
                        .addComponent(btnDelete)
                        .addGap(123, 123, 123))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddContact)
                        .addGap(42, 42, 42)
                        .addComponent(btnDeleteContact)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditClient))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowClaims)
                    .addComponent(btnDelete))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblJMBG))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblDate))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblSignantureType))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteContact)
                    .addComponent(btnAddContact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditClient)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    public JLabel getLblClientName() {
        return lblClientName;
    }

    public JTable getTblClaims() {
        return tblClaims;
    }

    public JLabel getLblDate() {
        return lblDate;
    }

    public JLabel getLblJMBG() {
        return lblJMBG;
    }

    public JLabel getLblSignantureType() {
        return lblSignantureType;
    }

    /**
     * @param args the command line arguments
     */
    public JTable getTblContacts() {
        return tblContacts;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddContact;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteContact;
    private javax.swing.JButton btnEditClient;
    private javax.swing.JButton btnShowClaims;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClientName;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblJMBG;
    private javax.swing.JLabel lblSignantureType;
    private javax.swing.JTable tblClaims;
    private javax.swing.JTable tblContacts;
    // End of variables declaration//GEN-END:variables

    public void addBtnShowClaimsListener(ActionListener actionListener) {
       btnShowClaims.addActionListener(actionListener);
    }

    public void addBtnDeleteContactListener(ActionListener actionListener) {
        btnDeleteContact.addActionListener(actionListener);
    }

    public void addBtnSaveChangesListener(ActionListener actionListener) {
      btnEditClient.addActionListener(actionListener);
    }

    public void btnAddDeleteListener(ActionListener actionListener) {
        btnDelete.addActionListener(actionListener);
    }

    public void addBtnAddContactListener(ActionListener actionListener) {
      btnAddContact.addActionListener(actionListener);
    }
}