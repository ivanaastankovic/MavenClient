package rs.bg.ac.student.ivana.MavenClient.communication;

import rs.bg.ac.student.ivana.MavenCommon.communication.Operation;
import rs.bg.ac.student.ivana.MavenCommon.communication.Receiver;
import rs.bg.ac.student.ivana.MavenCommon.communication.Request;
import rs.bg.ac.student.ivana.MavenCommon.communication.Response;
import rs.bg.ac.student.ivana.MavenCommon.communication.Sender;
import rs.bg.ac.student.ivana.MavenCommon.domain.Admin;
import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Client;
import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import java.net.Socket;
import java.util.List;

public class Communication {
    
	private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private static Communication instance;
    
    private Communication() throws Exception{
        socket=new Socket("localhost", 9000);
        sender=new Sender(socket);
        receiver=new Receiver(socket);
    }
    public static Communication getInstance() throws Exception{
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }
    
    public Admin login(String username, String password) throws Exception {
        Admin admin=new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        Request request=new Request(Operation.LOGIN, admin);
        sender.send(request);
        Response response=(Response)receiver.receive();
        
        if(response.getException()==null){
                return (Admin)response.getResult();
        }else{
            throw response.getException();
        }
    }

    

    public List<Claim> getAllClaimsByClient(Client cl) throws Exception{
        Request request=new Request(Operation.GETALLBYCLIENT_CLAIMS, cl.getJmbg());
        sender.send(request);
        Response response=(Response)receiver.receive();
          if(response.getException()==null){
            List<Claim> list=(List<Claim>) response.getResult();
            return list;
        }else{
            throw response.getException();
        }
    }

   

    public List<Claim> getAllClaims() throws Exception {
       Request request=new Request(Operation.GETALL_CLAIMS, null);
        sender.send(request);
        Response response=(Response)receiver.receive();
          if(response.getException()==null){
            List<Claim> list=(List<Claim>) response.getResult();
            return list;
        }else{
            throw response.getException();
        }
    }

    public void addClaim(Claim cl) throws Exception {
       Request request=new Request(Operation.ADD_CLAIM, cl);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            Claim newClaim=(Claim)response.getResult();
            cl=newClaim;
        }else{
            throw response.getException();
        }
    }

    public List<RiskType> getAllRiskTypes() throws Exception {
        Request request=new Request(Operation.GETALL_RISKTYPES, null);
        sender.send(request);
        Response response=(Response)receiver.receive();
          if(response.getException()==null){
            List<RiskType> list=(List<RiskType>) response.getResult();
            return list;
        }else{
            throw response.getException();
        }
    }

 

    

    public void deleteClient(Client client) throws Exception {
        Request request=new Request(Operation.DELETE_CLIENT, client);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }

    public void deleteRiskType(RiskType riskType) throws Exception {
         Request request=new Request(Operation.DELETE_RISKTYPE, riskType);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }

    public void addRiskType(RiskType riskType) throws Exception {
        Request request=new Request(Operation.ADD_RISKTYPE, riskType);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            RiskType newRiskType=(RiskType)response.getResult();
            riskType.setRiskTypeID(newRiskType.getRiskTypeID());
        }else{
            throw response.getException();
        }
    }

    public Client getClientByJMBG(String jmbg) throws Exception {
         Request request=new Request(Operation.GETBYID_CLIENT, jmbg);
        sender.send(request);
        Response response=(Response)receiver.receive();
          if(response.getException()==null){
            Client client=(Client) response.getResult();
            return client;
        }else{
            throw response.getException();
        }
    }

    public Claim getClaimByID(Long id) throws Exception {
         Request request=new Request(Operation.GETBYID_CLAIM, id);
        sender.send(request);
        Response response=(Response)receiver.receive();
          if(response.getException()==null){
            Claim claim=(Claim) response.getResult();
            return claim;
        }else{
            throw response.getException();
        }
    }

    public void editClaim(Claim claim) throws Exception {
        Request request=new Request(Operation.EDIT_CLAIM, claim);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }

    public void addClient(Client cl) throws Exception {
         Request request=new Request(Operation.ADD_CLIENT, cl);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            Client newClient=(Client)response.getResult();
            cl.setClientID(newClient.getClientID());
        }else{
            throw response.getException();
        }
    }

    public Socket logout(String admin) throws Exception {
        Request request = new Request(Operation.LOGOUT,admin);
        try{
        sender.send(request);
        }catch(Exception ex){
            System.exit(0);
        }
            
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return socket;
        } else throw response.getException();
    }

    
}
