
package rs.bg.ac.student.ivana.MavenClient.controller;

import rs.bg.ac.student.ivana.MavenClient.communication.Communication;
import rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator;
import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import rs.bg.ac.student.ivana.MavenClient.form.FrmEditClaim;
import rs.bg.ac.student.ivana.MavenClient.form.table.TableModelContacts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.media.MediaPlayer;  								PROVERITI 
import javax.swing.JOptionPane;


public class EditClaimController {

    private final FrmEditClaim frmEditClaim;
     
    public EditClaimController(FrmEditClaim frmEditClaim) {
        this.frmEditClaim= frmEditClaim;
        addActionListener();
    }
    
    public void openForm(){
        frmEditClaim.getPnlEdit().setVisible(false);
        frmEditClaim.setVisible(true);
    }

    private void addActionListener() {
    frmEditClaim.addBtnShowListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Claim claim=new Claim();
            try{
                validate();
            
            Long id=Long.valueOf(frmEditClaim.getTxtClaimID().getText());
            try {
                claim=Communication.getInstance().getClaimByID(id);
                
                prepareForm(claim);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frmEditClaim,ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                   
            }
        }catch(Exception eexcep){
             JOptionPane.showMessageDialog(frmEditClaim,eexcep, "error", JOptionPane.ERROR_MESSAGE);
                   
        }}
        
        private void validate() throws Exception{
            String errorMessage="";
            
            if(frmEditClaim.getTxtClaimID().getText().isEmpty()){
                errorMessage+="id can not be empty!";
            }
            
            if(!errorMessage.isEmpty()){
                throw new Exception(errorMessage);
            }
        }
    });
    
    
    
    frmEditClaim.addBtnEditClaimListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Claim claim=new Claim();
            try {
                    validate();
                

            claim.setId(Long.valueOf(frmEditClaim.getTxtClaimID().getText()));
            claim.setStatus((Status) frmEditClaim.getCbStatus().getSelectedItem());
            claim.setPaymentSum(new BigDecimal(frmEditClaim.getTxtSum().getText()));
            System.out.println(claim.getPaymentSum());
            try {
                Communication.getInstance().editClaim(claim);
                JOptionPane.showMessageDialog(frmEditClaim, "Claim edited successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frmEditClaim,ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);

            }}
            catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(frmEditClaim, ex, "error", JOptionPane.ERROR_MESSAGE);
                }
        }
        
        private void validate() throws Exception {
           
                String errorMessage = "";
                if (frmEditClaim.getTxtClaimID().getText().trim().isEmpty()) {

                    errorMessage += "id can not be empty!\n";
                }
                if (frmEditClaim.getTxtSum().getText().isEmpty()) {

                    errorMessage += "sum can not be empty!\n";
                }
                
                
               
                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
                
                
            }
        
        
        
    });
}

    private void prepareForm(Claim claim) {
        frmEditClaim.getLblJMBG().setText(claim.getClient().getJmbg());
        frmEditClaim.getLblActivity().setText(claim.getActivity());
        DateFormat df=new SimpleDateFormat("dd.MM.yyyy.");
        frmEditClaim.getLblDate().setText(df.format(claim.getFileDate()));
        frmEditClaim.getLblRiskType().setText(claim.getRiskType().getName());
        frmEditClaim.getCbStatus().removeAllItems();
        for (Object value : Status.values()) {
            frmEditClaim.getCbStatus().addItem(value);
        }
        
        frmEditClaim.getCbStatus().setSelectedItem(claim.getStatus());
        frmEditClaim.getPnlEdit().setVisible(true);
    }
}