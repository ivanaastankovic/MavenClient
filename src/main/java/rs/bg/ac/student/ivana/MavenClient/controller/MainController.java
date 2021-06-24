package rs.bg.ac.student.ivana.MavenClient.controller;

import rs.bg.ac.student.ivana.MavenClient.communication.Communication;
import rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator;
import rs.bg.ac.student.ivana.MavenCommon.domain.Admin;
import rs.bg.ac.student.ivana.MavenClient.form.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;


public class MainController {
    private FrmMain frmMain;

    public MainController(FrmMain frmMain) {
        this.frmMain= frmMain;
        addActionListener();
    }
    
    public void openForm(){
       frmMain.getLblUsername().setText((String) Cordinator.getInstance().getParam("ADMIN"));
        frmMain.setVisible(true);
      //  frmMain.getLblEUR().
    }

    private void addActionListener() {
        
        frmMain.addBtnMenuItemClaim(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator.getInstance().openFrmClaim();
            }
        });
        
        
         frmMain.addBtnMenuItemClient(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator.getInstance().openFrmClient();
            }
        });
         
          frmMain.addBtnMenuItemRiskType(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator.getInstance().openFrmRiskType();
            }
        });
          
         
        
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }
    
    
    
    
}
