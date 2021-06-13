package rs.bg.ac.student.ivana.MavenClient.controller;

import rs.bg.ac.student.ivana.MavenClient.communication.Communication;
import rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator;
import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import rs.bg.ac.student.ivana.MavenClient.form.FrmShowClient;
import rs.bg.ac.student.ivana.MavenClient.form.table.TableModelClaims;
import rs.bg.ac.student.ivana.MavenClient.form.table.TableModelContacts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ShowClientController {
    private FrmShowClient frmShowClient;

    public ShowClientController(FrmShowClient frmShowClient) {
        this.frmShowClient= frmShowClient;
        addActionListener();
    }
    
    public void openForm(){
        prepareView();
        frmShowClient.setVisible(true);
    }

    private void addActionListener() {
        frmShowClient.addBtnShowClaimsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmShowClient.getTblClaims().setVisible(true);
            }
        });
        
        
        frmShowClient.addBtnAddContactListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientContacts contact=new ClientContacts();
                Client client=(Client) Cordinator.getInstance().getParam("Client");
                contact.setClient(client);
                TableModelContacts model=(TableModelContacts) frmShowClient.getTblContacts().getModel();
                model.addContact(contact);
            }
        });
        
        frmShowClient.addBtnDeleteContactListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 int rowindex=frmShowClient.getTblContacts().getSelectedRow();
                 TableModelContacts model=(TableModelContacts) frmShowClient.getTblContacts().getModel();
                 if(model.getRowCount()>1){
                 model.deleteRow(rowindex);
                 }else{
                   JOptionPane.showMessageDialog(frmShowClient, "this is only contact!", "error", JOptionPane.ERROR_MESSAGE);

                 }
            }
        });
        
        
        
        frmShowClient.addBtnSaveChangesListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    validate();
                TableModelContacts model=(TableModelContacts) frmShowClient.getTblContacts().getModel();
                List<ClientContacts> contacts=model.getList();
                Client client=(Client) Cordinator.getInstance().getParam("Client");
                client.setContacts(contacts);
                try {
                    Communication.getInstance().editClient(client);
                    JOptionPane.showMessageDialog(frmShowClient, "Client contacts edited successfully!");
                } catch (Exception ex) {
                  JOptionPane.showMessageDialog(frmShowClient, "can not edit contacts!"+ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frmShowClient, ex, "error", JOptionPane.ERROR_MESSAGE);
                   
            }}
            
            
            private void validate() throws Exception{
                String errorMessage="";
                 TableModelContacts table=(TableModelContacts) frmShowClient.getTblContacts().getModel();
                for (ClientContacts object : table.getList()) {
                    if(object.getAddress()==null || object.getEmail()==null || object.getTown()==null || object.getPhone()==null){
                         errorMessage += "Contact info can not be empty!\n";
                    }else if(!object.getPhone().matches("\\d+") || !object.getEmail().contains("@")){
                        errorMessage += "Invalid contact info!\n";
                    }
                    
                }
               
                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
            }
        });
        
        
        frmShowClient.btnAddDeleteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client client=(Client) Cordinator.getInstance().getParam("Client");
                    Communication.getInstance().deleteClient(client);
                    JOptionPane.showMessageDialog(frmShowClient, "Client deleted successfully!");
                } catch (Exception ex) {
                   JOptionPane.showMessageDialog(frmShowClient, "client has active claims", "error", JOptionPane.ERROR_MESSAGE);
                 
                }
            }
        });
        
        
        
}

    private void prepareView() {
         Client client=(Client) Cordinator.getInstance().getParam("Client");
         frmShowClient.getTblClaims().setVisible(false);
         frmShowClient.getLblClientName().setText(client.getFirstName()+" "+client.getLastName());
         frmShowClient.getLblJMBG().setText(client.getJmbg());
         DateFormat df=new SimpleDateFormat("dd.MM.yyyy.");
         frmShowClient.getLblDate().setText(df.format(client.getSignatureDate()));
         frmShowClient.getLblSignantureType().setText(client.getContractType().toString());
         TableModelContacts modelContacts=new TableModelContacts(client.getContacts(), true);
         frmShowClient.getTblContacts().setModel(modelContacts);
        try {
            List<Claim> claims=Communication.getInstance().getAllClaimsByClient(client);
            List<Claim> active=new ArrayList<>(); 
            for (Claim claim : claims) {
                if(claim.getStatus().equals(Status.FILED) || claim.getStatus().equals(Status.PENDING)){
                    active.add(claim); 
                }
            }
             TableModelClaims modelClaims=new TableModelClaims(active, false);
            frmShowClient.getTblClaims().setModel(modelClaims);
        } catch (Exception ex) {
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