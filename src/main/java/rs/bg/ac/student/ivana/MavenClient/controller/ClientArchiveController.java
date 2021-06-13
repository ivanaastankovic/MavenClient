package rs.bg.ac.student.ivana.MavenClient.controller;

import rs.bg.ac.student.ivana.MavenClient.communication.Communication;
import rs.bg.ac.student.ivana.MavenClient.cordinator.Cordinator;
import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenClient.form.FrmClientArchive;
import rs.bg.ac.student.ivana.MavenClient.form.table.TableModelClaims;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ClientArchiveController {
    FrmClientArchive frmClientArchive;

    public ClientArchiveController(FrmClientArchive frmClientArchive) {
        this.frmClientArchive= frmClientArchive;
       
    }
    
    public void openForm(){
        prepareView();
        frmClientArchive.setVisible(true);
    }

  

    private void prepareView() {
       List<Claim> list=new ArrayList<>();
       Client cl=(Client) Cordinator.getInstance().getParam("Client");
        try {
            list=Communication.getInstance().getAllClaimsByClient(cl);
            TableModelClaims table=new TableModelClaims(list, false);
            frmClientArchive.getTblClaims().setModel(table);
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(frmClientArchive, "Server is closed, Goodbye");
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
