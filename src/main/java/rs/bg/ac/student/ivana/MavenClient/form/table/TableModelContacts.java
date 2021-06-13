
package rs.bg.ac.student.ivana.MavenClient.form.table;

import rs.bg.ac.student.ivana.MavenCommon.domain.Claim;
import rs.bg.ac.student.ivana.MavenCommon.domain.ClientContacts;
import rs.bg.ac.student.ivana.MavenCommon.domain.Status;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelContacts extends AbstractTableModel {
  List<ClientContacts> list;
    boolean edit;
    String[] columns=new String[]{ "address","town", "phone", "email"};

    public TableModelContacts(List<ClientContacts> list, boolean edit) {
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
        ClientContacts c=list.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return c.getAddress();
            case 1:
                return c.getTown();
            case 2:
                return c.getPhone();
            case 3:
                return c.getEmail();
         
            default:
                return "";
                        
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(!edit){
            return false;
        }else return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ClientContacts c=list.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                c.setAddress((String) aValue);
                break;
            case 1:
                c.setTown((String) aValue);
                break;
            case 2:
                c.setPhone((String) aValue);
                break;
            case 3:
                c.setEmail((String) aValue);
                break;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    
    public void addContact(ClientContacts contact){
        list.add(contact);
         fireTableRowsInserted(list.size(), list.size());
    }
    
     public void deleteContact(ClientContacts c){
        list.remove(c);
        fireTableDataChanged();
    }

    public List<ClientContacts> getList() {
        return list;
    }

    public void addTableModelListener(int rowindex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteRow(int rowindex) {
        list.remove(rowindex);
        fireTableDataChanged();
    }
     
     
    
}
