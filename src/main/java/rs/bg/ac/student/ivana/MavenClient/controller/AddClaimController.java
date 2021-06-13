
package rs.bg.ac.student.ivana.MavenClient.controller;

import rs.bg.ac.student.ivana.MavenClient.communication.Communication;
import rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator;
import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import rs.bg.ac.student.ivana.MavenClient.form.FrmAddClaim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Olivera
 */
public class AddClaimController {
     FrmAddClaim frmAddClaim;

    public AddClaimController(FrmAddClaim frmAddClaim) {
        this.frmAddClaim= frmAddClaim;
        addActionListener();
    }
    
    public void openForm(){
        prepareView();
        frmAddClaim.setVisible(true);
    }

    private void addActionListener() {
        frmAddClaim.addBtnAddClaimListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Claim cl=new Claim();
                try {
                    validate();
                
                Client client=(Client) Cordinator.getInstance().getParam("Client");
                cl.setActivity(frmAddClaim.getTxtActivity().getText().trim());
                cl.setRiskType((RiskType) frmAddClaim.getCbRiskType().getSelectedItem());
                cl.setClient(client);
                DateFormat df=new SimpleDateFormat("dd.MM.yyyy.");
                cl.setFileDate(new Date());
                cl.setPaymentSum(new BigDecimal(0));
                cl.setStatus(Status.FILED);
                try {
                    Communication.getInstance().addClaim(cl);
                    JOptionPane.showMessageDialog(frmAddClaim, "Claim added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmAddClaim,"claim can not be added", "error", JOptionPane.ERROR_MESSAGE);
                   
                       
                   
                }}catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmAddClaim, ex, "error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
            
            
             private void validate() throws Exception {
                String errorMessage = "";
                if (frmAddClaim.getTxtActivity().getText().trim().isEmpty()) {

                    errorMessage += "Activity can not be empty!\n";
                }
               
                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
                
            }
        });

                }
    private void prepareView() {
        frmAddClaim.getCbRiskType().removeAllItems();
         try {
             List<RiskType> list=Communication.getInstance().getAllRiskTypes();
             for (RiskType riskType : list) {
                 frmAddClaim.getCbRiskType().addItem(riskType);
                         
             }
         } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmAddClaim,"server is not responding,bye!", "error", JOptionPane.ERROR_MESSAGE);
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



}