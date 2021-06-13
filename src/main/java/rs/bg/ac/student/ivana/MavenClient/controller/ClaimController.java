package rs.bg.ac.student.ivana.MavenClient.controller;

import rs.bg.ac.student.ivana.MavenClient.communication.Communication;
import rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator;
import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import rs.bg.ac.student.ivana.MavenClient.form.FrmClaim;
import rs.bg.ac.student.ivana.MavenClient.form.table.TableModelClaims;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ClaimController {
     FrmClaim frmClaim;

    public ClaimController(FrmClaim frmClaim) {
        this.frmClaim= frmClaim;
        addActionListener();
    }
    
    public void openForm(){
        prepareView();
        frmClaim.setVisible(true);
    }

    private void addActionListener() {
        frmClaim.addBtnEditClaimListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cordinator.getInstance().openFrmEditClaim();
            }
        });
        
        
        frmClaim.addBtnArchiveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 frmClaim.getTblArchive().setVisible(true);
            }
        });
        
        
    }

    private void prepareView() {
        List<Claim> list=new ArrayList<>();
         try {
             list=Communication.getInstance().getAllClaims();
             List<Claim> active=new ArrayList<>();
             List<Claim> finished=new ArrayList<>();
             for (Claim claim : list) {
                 if(claim.getStatus().equals(Status.FILED) || claim.getStatus().equals(Status.PENDING)){
                     active.add(claim);
                 }else{
                     finished.add(claim);
                 }
                 
                 TableModelClaims tableActive=new TableModelClaims(active, false);
                 TableModelClaims tableArchive=new TableModelClaims(finished, false);
                 frmClaim.getTblActive().setModel(tableActive);
                 frmClaim.getTblArchive().setModel(tableArchive);
                 frmClaim.getTblArchive().setVisible(false);
                 
             }
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(frmClaim, "server is not responding,bye!", "error", JOptionPane.ERROR_MESSAGE);
             try{
                      String admin = (String) Cordinator.getInstance().getParam("ADMIN");
                       Socket socket = rs.bg.ac.student.ivana.MavenClient.communication.Communication.getInstance().logout(admin);
                       socket.close();
                       System.exit(0);
                      }catch(Exception exl){
                        System.out.println(exl.getMessage());
                         }
         }
        
    }

    public FrmClaim getFrmClaim() {
        return frmClaim;
    }
    
    
}
