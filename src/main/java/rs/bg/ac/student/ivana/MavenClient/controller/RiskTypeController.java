/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bg.ac.student.ivana.MavenClient.controller;

import rs.bg.ac.student.ivana.MavenClient.communication.Communication;
import rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator;
import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenClient.form.FrmRiskType;
import rs.bg.ac.student.ivana.MavenClient.form.table.TableModelRiskTypes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Olivera
 */
public class RiskTypeController {
     private FrmRiskType frmRiskType;
      List<RiskType> deleted;
       List<RiskType> added;
       
    public RiskTypeController(FrmRiskType frmRiskType) {
        this.frmRiskType= frmRiskType;
        deleted=new ArrayList<>();
        added=new ArrayList<>();
        addActionListener();
    }
    
    public void openForm(){
        prepareView();
        frmRiskType.setVisible(true);
    }

    private void addActionListener() {
        frmRiskType.addBtnAddlistener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                getFrmRiskType().getTxtMinSum().setText(String.valueOf(0));
                getFrmRiskType().getTxtMaxSum().setText(String.valueOf(0));
                getFrmRiskType().getPanelAdd().setVisible(true);
                
                
            }
        });
        
        frmRiskType.addBtndeleteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int rt=getFrmRiskType().getTblRiskTypes().getSelectedRow();
               TableModelRiskTypes table=(TableModelRiskTypes) getFrmRiskType().getTblRiskTypes().getModel();
               if(table.getRowCount()>1){
               RiskType risk=table.getRow(rt);
               table.deleteRiskType(rt);
               if(added.contains(risk)){
                   added.remove(risk);
               }else{
                   deleted.add(risk);
               }
       
               }
            }
        });
         frmRiskType.addBtnSaveTypeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 RiskType rt=new RiskType();
                 try{
                     validate();
                rt.setMinSum(new BigDecimal(frmRiskType.getTxtMinSum().getText()));
                rt.setMaxSum(new BigDecimal(frmRiskType.getTxtMaxSum().getText()));
                rt.setName(frmRiskType.getTxtName().getText().trim());
                rt.setDomain(frmRiskType.getTxtDomain().getText().trim());
                rt.setDescription(frmRiskType.getTxtDescription().getText().trim());
                rt.setRiskTypeID(-1l);
               TableModelRiskTypes table=(TableModelRiskTypes) getFrmRiskType().getTblRiskTypes().getModel();
               table.addRiskType(rt);
               added.add(rt);
                getFrmRiskType().getPanelAdd().setVisible(false);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frmRiskType, ex);
            }}
            
            private void validate()throws Exception{
                
                String errorMessage = "";
                if (frmRiskType.getTxtName().getText().trim().isEmpty()) {

                    errorMessage += "name can not be empty!\n";
                }
                if (frmRiskType.getTxtDomain().getText().trim().isEmpty()) {

                    errorMessage += "risk type can not be empty!\n";
                }
                 if (frmRiskType.getTxtDescription().getText().trim().isEmpty()) {

                    errorMessage += "description can not be empty!\n";
                }
                 if (frmRiskType.getTxtMinSum().getText().isEmpty() || frmRiskType.getTxtMaxSum().getText().isEmpty()) {

                    errorMessage += "sums can not be empty!\n";
                }else if(new BigDecimal(frmRiskType.getTxtMinSum().getText()).compareTo(new BigDecimal(frmRiskType.getTxtMaxSum().getText()))>0){
                    errorMessage += "max sum can not be smaller!\n";
                }
                
                
                
               
                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
            }
               
        });
         
         
         frmRiskType.addBtnSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                for (RiskType riskType : deleted) {
                    
                        Communication.getInstance().deleteRiskType(riskType);
                    } 
                    
                    deleted=new ArrayList<>();
                
                
                for (RiskType riskType : added) {
                    
                        riskType.setRiskTypeID(null);
                        Communication.getInstance().addRiskType(riskType);
                   
                }
                
                added=new ArrayList<>();
                 JOptionPane.showMessageDialog(frmRiskType, "Risk types saved successfully!");
                }catch(Exception ex){
                     JOptionPane.showMessageDialog(frmRiskType, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                }
               
                prepareView();
            }
         });
        
    }

    private void prepareView() {
         List<RiskType> list=new ArrayList<>();
         try {
             list=Communication.getInstance().getAllRiskTypes();
             TableModelRiskTypes table=new TableModelRiskTypes(list, false);
             frmRiskType.getTblRiskTypes().setModel(table);
             frmRiskType.getPanelAdd().setVisible(false);
                     
         } catch (Exception ex) {
              JOptionPane.showMessageDialog(frmRiskType, "server is not responding,bye!", "error", JOptionPane.ERROR_MESSAGE);
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

    public FrmRiskType getFrmRiskType() {
        return frmRiskType;
    }
    
    

}
