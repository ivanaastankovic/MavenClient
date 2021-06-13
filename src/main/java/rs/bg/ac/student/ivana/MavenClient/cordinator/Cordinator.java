
package rs.bg.ac.student.ivana.MavenClient.cordinator;

import rs.bg.ac.student.ivana.MavenClient.controller.AddClaimController;
import rs.bg.ac.student.ivana.MavenClient.controller.ClaimController;
import rs.bg.ac.student.ivana.MavenClient.controller.ClientArchiveController;
import rs.bg.ac.student.ivana.MavenClient.controller.ClientController;
import rs.bg.ac.student.ivana.MavenClient.controller.ClientNewController;
import rs.bg.ac.student.ivana.MavenClient.controller.EditClaimController;
import rs.bg.ac.student.ivana.MavenClient.controller.LoginController;
import rs.bg.ac.student.ivana.MavenClient.controller.MainController;
import rs.bg.ac.student.ivana.MavenClient.controller.RiskTypeController;
import rs.bg.ac.student.ivana.MavenClient.controller.ShowClientController;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import rs.bg.ac.student.ivana.MavenClient.form.FrmAddClaim;
import rs.bg.ac.student.ivana.MavenClient.form.FrmClaim;
import rs.bg.ac.student.ivana.MavenClient.form.FrmClient;
import rs.bg.ac.student.ivana.MavenClient.form.FrmClientArchive;
import rs.bg.ac.student.ivana.MavenClient.form.FrmClientNew;
import rs.bg.ac.student.ivana.MavenClient.form.FrmEditClaim;
import rs.bg.ac.student.ivana.MavenClient.form.FrmLogin;
import rs.bg.ac.student.ivana.MavenClient.form.FrmMain;
import rs.bg.ac.student.ivana.MavenClient.form.FrmRiskType;
import rs.bg.ac.student.ivana.MavenClient.form.FrmShowClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cordinator {
    private MainController mainController;
    private static Cordinator instance;
    private Map<String,Object> params;
    private ClientController clientController;
    private ClaimController claimController;
    private RiskTypeController riskTypeController;
    
    private Cordinator() {
        mainController = new MainController(new FrmMain());
        params = new HashMap<>();
    }
    
    public static Cordinator getInstance(){
        if(instance==null){
            instance=  new Cordinator();
        }
        return instance;
    }
    
    public void addParam(String s, Object o){
        params.put(s, o);
    }
    
    public Object getParam(String s){
        return params.get(s);
    }

    public void openFrmShowClient(String jmbg) {
        ShowClientController showClientController=new ShowClientController(new FrmShowClient(clientController.getFrmClient(), true));
        showClientController.openForm();
    }

    public void openFrmClientArchive(String jmbg) {
        ClientArchiveController clientArchiveController=new ClientArchiveController(new FrmClientArchive(clientController.getFrmClient(), true));
        clientArchiveController.openForm();
    }

    public void openFrmClientNew() {
         ClientNewController clientNewController=new ClientNewController(new FrmClientNew(clientController.getFrmClient(), true));
         clientNewController.openForm();
    }

  

    public void openFrmEditClaim() {
        EditClaimController editClaimController=new EditClaimController(new FrmEditClaim(claimController.getFrmClaim(), true));
        editClaimController.openForm();
    }

    public void openMainForm() {
       mainController.openForm();
       
    }

    public void openFrmClaim() {
        claimController=new ClaimController(new FrmClaim());
        claimController.openForm();
        
    }

    public void openFrmClient() {
       clientController=new ClientController(new FrmClient());
       clientController.openForm();
    }

    public void openFrmRiskType() {
       riskTypeController=new RiskTypeController(new FrmRiskType());
       riskTypeController.openForm();
    }
    
     public void openLoginForm() {
        LoginController loginContoller = new LoginController(new FrmLogin());
        loginContoller.openForm();
    }

    public void openFrmAddClaim() {
        AddClaimController addClaimController=new AddClaimController(new FrmAddClaim(clientController.getFrmClient(), true));
        addClaimController.openForm();
    }

    
}
