
package rs.bg.ac.student.ivana.MavenClient.form.table;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TableModelClaims extends AbstractTableModel {
    List<Claim> list;
    boolean edit;
    String[] columns=new String[]{"id","jmbg", "risk type","activity", "status", "sum"};

    public TableModelClaims(List<Claim> list, boolean edit) {
        this.list = list;
        this.edit=edit;
    }
    
    
    
        
    @Override
    public int getRowCount() {
        if(list==null || list.isEmpty()){
            return 0;
        }
        
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Claim c=list.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return c.getClaimID();
            case 1:
                return c.getClient().getJmbg();
            case 2:
                return c.getRiskType().getName();
            case 3:
                return c.getActivity();
            case 4:
                return c.getStatus();
            case 5:
                return c.getPaymentSum();
            default:
                return "";
                        
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(!edit){
            return false;
        }else if(columnIndex==4 || columnIndex==5){
                return true;
            }
        else{
             return false;
           }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Claim cl=list.get(rowIndex);
        
        switch(columnIndex){
            case 4:
                cl.setStatus((Status)aValue);
                break;
            case 5:
                cl.setPaymentSum(BigDecimal.valueOf(Long.valueOf((String)aValue)));
                break;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    
    public void addClaim(Claim claim){
        list.add(claim);
        fireTableCellUpdated(list.size()-1, list.size()-1);
    }
    
    
  
        
    
    
    
    
     
   
    
}
