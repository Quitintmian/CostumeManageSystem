package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import bean.Supplier;

/**
 * 供应商Dao类
 * @author computer
 *
 */
public class SupplierDao {
	
		public boolean isExsist(Connection conn,int supplierId)throws Exception{
			String sql = "select * from t_supplier where supplierId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, supplierId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}else {
				return false;
			}
		}

		public int add(Connection conn,Supplier supplier)throws Exception{
			String sql = "insert into t_supplier(supplierName,supplierPhone,address,contactName,contactPhone,comment) values(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, supplier.getSupplierName());
			ps.setString(2, supplier.getSupplierPhone());
			ps.setString(3, supplier.getAddress());
			ps.setString(4, supplier.getContactName());
			ps.setString(5, supplier.getSupplierPhone());
			ps.setString(6, supplier.getComment());
			return ps.executeUpdate();
		}
		
		public int delete(Connection conn,int supplierId)throws Exception{
			String sql = "delete from t_supplier where supplierId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, supplierId);
			return ps.executeUpdate();
		}
		
		public int update(Connection conn,Supplier supplier)throws Exception{
			String sql = "update t_supplier set supplierName=?,supplierPhone=?,address=?,contactName=?,contactPhone=?,comment=? where supplierId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, supplier.getSupplierName());
			ps.setString(2, supplier.getSupplierPhone());
			ps.setString(3,supplier.getAddress());
			ps.setString(4,supplier.getContactName());
			ps.setString(5,supplier.getContactPhone() );
			ps.setString(6,supplier.getComment());
			ps.setInt(7, supplier.getSupplierId());
			return ps.executeUpdate();
		}
		
		
		public ResultSet query(Connection conn,Supplier supplier)throws Exception{
			StringBuffer sb = new StringBuffer("select * from t_supplier");
			if (supplier.getSupplierName()!=null) {
				sb.append(" and supplierName like '%"+supplier.getSupplierName()+"%'");
			}
			if (supplier.getContactName()!=null) {
				sb.append(" and contactName like '%"+supplier.getContactName()+"%'");
			}
			if (supplier.getAddress()!=null) {
				sb.append(" and address like '%"+supplier.getAddress()+"%'");
			}
			
			PreparedStatement ps = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
			return ps.executeQuery();
			
		}
	
}