package student;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RecordList {
		JFrame fr = new JFrame("4Achivers - Records");
		JTable ta;
		DefaultTableModel model=new DefaultTableModel();
		
		
		public RecordList() {
			fr.setSize(700,700);
			fr.setLocationRelativeTo(null);
			fr.setResizable(false);
		    fr.getContentPane().setBackground(Color.DARK_GRAY);
		    //ps=Database.psselect;
		    //addComponents();
		    ta=new JTable(model);
		    JScrollPane pa = new JScrollPane(ta);
		    //pa.setBackground(Color.DARK_GRAY);
		    fr.add(pa);
		    showData();
		    
		    fr.setVisible(true);
		}
		
		private void showData() {
			model.addColumn("Student Id");
			model.addColumn("Student Name");
			model.addColumn("Student Email");
			model.addColumn("Student Contact No");
			model.addColumn("Student Course Name");
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ishadb", "root", "Ishamysql@0902");
				Statement st=con.createStatement();
				ResultSet rst=st.executeQuery("select * from student_info");
				while(rst.next()) {
					Object [] data= {rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5)};
					model.addRow(data);
				}
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
			}
		
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RecordList();
		

	}

}
