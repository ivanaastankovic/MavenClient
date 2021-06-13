
package rs.bg.ac.student.ivana.MavenClient.form.table;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.RiskType;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TableModelRiskTypes extends AbstractTableModel {
  List<RiskType> list;
    boolean edit;
    String[] columns=new String[]{"id","name", "domaine","description", "minSum", "maxSum"};

    public TableModelRiskTypes(List<RiskType> list, boolean edit) {
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
        RiskType c=list.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return c.getRiskTypeID();
            case 1:
                return c.getName();
            case 2:
                return c.getDomain();
            case 3:
                return c.getDescription();
            case 4:
                return c.getMinSum();
            case 5:
                return c.getMaxSum();
            default:
                return "";
                        
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(!edit){
            return false;
        }else if(columnIndex==0){
                return false;
            }
        else{
             return true;
           }
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        RiskType c=list.get(rowIndex);
        
        switch(columnIndex){
          case 1:
                c.setName((String) aValue);
                 break;
            case 2:
                c.setDomain((String) aValue);
                break;
            case 3:
                c.setDescription((String) aValue);
                break;
            case 4:
                c.setMinSum((BigDecimal) aValue);
                break;
            case 5:
                c.setMaxSum((BigDecimal) aValue);
                break;
            
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    
    public void addRiskType(RiskType r){
        list.add(r);
        fireTableCellUpdated(list.size()-1, list.size()-1);
    }
    
    
    public void deleteRiskType(RiskType r){
        list.remove(r);
        fireTableDataChanged();
    }

    public void deleteRiskType(int rt) {
       list.remove(rt);
        fireTableDataChanged();
    }

    public RiskType getRow(int rt) {
          return list.get(rt);
    }

    public List<RiskType> getList() {
        return list;
    }
    
    
    
    
}
