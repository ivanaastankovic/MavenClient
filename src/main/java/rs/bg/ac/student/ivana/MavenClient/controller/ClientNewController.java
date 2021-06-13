package rs.bg.ac.student.ivana.MavenClient.controller;

import rs.bg.ac.student.ivana.MavenClient.communication.Communication;
import rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator;
import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import rs.bg.ac.student.ivana.MavenCommon.domain.ContractType;
import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import rs.bg.ac.student.ivana.MavenClient.form.FrmClientNew;
import rs.bg.ac.student.ivana.MavenClient.form.table.TableModelContacts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ClientNewController {
     private final FrmClientNew frmClientNew;
     
    public ClientNewController(FrmClientNew frmClientNew) {
        this.frmClientNew= frmClientNew;
        addActionListener();
    }
    
    public void openForm(){
        prepareForm();
        frmClientNew.setVisible(true);
    }

    private void addActionListener() {
        frmClientNew.addBtnAddContactListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ClientContacts contact=new ClientContacts();
               TableModelContacts table=(TableModelContacts) frmClientNew.getTblContacts().getModel();
               table.addContact(contact);
            }
        });
        frmClientNew.addBtnAddClienttListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client cl=new Client();
                try {
                    validate(); cl.setFirstName(frmClientNew.getTxtFirstName().getText().trim());
                cl.setLastName(frmClientNew.getTxtLastName().getText().trim());
                cl.setJmbg(frmClientNew.getTxtJMBG().getText().trim());
                cl.setContractType((ContractType) frmClientNew.getCbConractType().getSelectedItem());
                cl.setSignatureDate(new Date());
                List<ClientContacts> contactsList=new ArrayList<>();
                cl.setContacts(contactsList);
                TableModelContacts table=(TableModelContacts) frmClientNew.getTblContacts().getModel();
                for (ClientContacts object : table.getList()) {
                    cl.getContacts().add(object);
                    object.setClient(cl);
                }
                Claim claim=new Claim();
                claim.setActivity(frmClientNew.getTxtActivity().getText().trim());
                claim.setRiskType((RiskType) frmClientNew.getCbRiskType().getSelectedItem());
                claim.setStatus(Status.FILED);
                claim.setPaymentSum(new BigDecimal(0l));
                claim.setFileDate(new Date());
                claim.setClient(cl);
                try {
                    Communication.getInstance().addClient(cl);
                     Communication.getInstance().addClaim(claim);
                     JOptionPane.showMessageDialog(frmClientNew, "Client added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmClientNew,ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmClientNew,ex, "error", JOptionPane.ERROR_MESSAGE);
                    
                }
               
                
               
                frmClientNew.refresh();
                prepareForm();
            }
            
            private void validate() throws Exception {
                String errorMessage = "";
                if (frmClientNew.getTxtFirstName().getText().trim().isEmpty()) {

                    errorMessage += "First Name can not be empty!\n";
                }
                if (frmClientNew.getTxtLastName().getText().trim().isEmpty()) {

                    errorMessage += "Last Name can not be empty!\n";
                }
                if (frmClientNew.getTxtJMBG().getText().trim().isEmpty()) {

                    errorMessage += "Jmbg can not be empty!\n";
                }
                 if (frmClientNew.getTxtJMBG().getText().trim().length()!=13 || !(frmClientNew.getTxtJMBG().getText().trim().matches("\\d+")) ) {

                    errorMessage += "Jmbg is in wrong format!\n";
                }
                if (frmClientNew.getTxtActivity().getText().trim().isEmpty()) {

                    errorMessage += "Activity can not be empty!\n";
                }
                if (((TableModelContacts) frmClientNew.getTblContacts().getModel()).getRowCount()<=0) {

                    errorMessage += "Contacts can not be empty!\n";
                }
                   TableModelContacts table=(TableModelContacts) frmClientNew.getTblContacts().getModel();
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
        
    }

    private void prepareForm() {
        List<ClientContacts> list=new ArrayList<>();
        TableModelContacts table=new TableModelContacts(list, true);
        frmClientNew.getTblContacts().setModel(table);
        frmClientNew.getCbConractType().removeAllItems();
        frmClientNew.getCbRiskType().removeAllItems();
        for (ContractType value : ContractType.values()) {
            frmClientNew.getCbConractType().addItem(value);
        }
        
        List<RiskType> list_risk=new ArrayList<>();
         try {
             list_risk=Communication.getInstance().getAllRiskTypes();
              for (RiskType riskType : list_risk) {
            frmClientNew.getCbRiskType().addItem(riskType);
        }
         } catch (Exception ex) {
              JOptionPane.showMessageDialog(frmClientNew,"server is not responding,bye!", "error", JOptionPane.ERROR_MESSAGE);
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
