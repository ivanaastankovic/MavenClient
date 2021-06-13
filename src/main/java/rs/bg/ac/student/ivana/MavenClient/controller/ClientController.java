package rs.bg.ac.student.ivana.MavenClient.controller;

import com.sun.org.apache.bcel.internal.generic.FMUL;
import rs.bg.ac.student.ivana.MavenClient.communication.Communication;
import rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenClient.form.FrmClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ClientController {

    private final FrmClient frmClient;

    public ClientController(FrmClient frmClient) {
        this.frmClient= frmClient;
        addActionListener();
    }
    
    public void openForm(){
        frmClient.setVisible(true);
    }

    private void addActionListener() {
         frmClient.addBtnShowClientListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                  try {
                     validate();
                 String jmbg=frmClient.getTxtJMBG().getText().trim();
                 try {
                     Client client=Communication.getInstance().getClientByJMBG(jmbg);
                     Cordinator.getInstance().addParam("Client", client);
                 } catch (Exception ex) {
                     JOptionPane.showMessageDialog(frmClient,ex, "error", JOptionPane.ERROR_MESSAGE);
                 }
                 Cordinator.getInstance().openFrmShowClient(jmbg);
             }  catch (Exception ex) {
                      JOptionPane.showMessageDialog(frmClient,ex, "error", JOptionPane.ERROR_MESSAGE);
                }
             }
              private void validate() throws Exception{
                String errorMessage = "";
                if (frmClient.getTxtJMBG().getText().trim().isEmpty()) {

                    errorMessage += "jmbg can not be empty!\n";
                }

                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
             }
             
         });
         
         
         frmClient.addBtnArchiveListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     validate();
                     String jmbg=frmClient.getTxtJMBG().getText().trim();
                     try {
                         Client client=Communication.getInstance().getClientByJMBG(jmbg);
                         Cordinator.getInstance().addParam("Client", client);
                         Cordinator.getInstance().openFrmClientArchive(jmbg);
                     } catch (Exception ex) {
                         JOptionPane.showMessageDialog(frmClient,ex, "error", JOptionPane.ERROR_MESSAGE);
                     }
                 } catch (Exception ex) {
                      JOptionPane.showMessageDialog(frmClient,ex, "error", JOptionPane.ERROR_MESSAGE);
                }
                
                    
                 
             }
             
             
             private void validate() throws Exception{
                String errorMessage = "";
                if (frmClient.getTxtJMBG().getText().trim().isEmpty()) {

                    errorMessage += "jmbg can not be empty!\n";
                }

                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
             }
         });
         
         
         frmClient.addBtnAddClientListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                  Cordinator.getInstance().openFrmClientNew();
             }
         });
         
         
         frmClient.addBtnAddClaimListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                  try {
                     validate();
               String jmbg=frmClient.getTxtJMBG().getText().trim();
                 try {
                     Client client=Communication.getInstance().getClientByJMBG(jmbg);
                     Cordinator.getInstance().addParam("Client", client);
                 } catch (Exception ex) {
                     JOptionPane.showMessageDialog(frmClient, ex.getMessage());
                     try{
                      String admin = (String) Cordinator.getInstance().getParam("ADMIN");
                       Socket socket = rs.bg.ac.student.ivana.MavenClient.communication.Communication.getInstance().logout(admin);
                       socket.close();
                       System.exit(0);
                      }catch(Exception exl){
                        System.out.println(exl.getMessage());
                         }
                 }
                 Cordinator.getInstance().openFrmAddClaim();
             }catch (Exception ex) {
                      JOptionPane.showMessageDialog(frmClient,ex, "error", JOptionPane.ERROR_MESSAGE);
                }
             }
         
             private void validate() throws Exception{
                String errorMessage = "";
                if (frmClient.getTxtJMBG().getText().trim().isEmpty()) {

                    errorMessage += "jmbg can not be empty!\n";
                }else if(frmClient.getTxtJMBG().getText().length()!=13){
                    errorMessage += "jmbg is in wrong format!\n";
                }

                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
             }     
                 
});
    }

    public FrmClient getFrmClient() {
        return frmClient;
    }
    
    
    

}
