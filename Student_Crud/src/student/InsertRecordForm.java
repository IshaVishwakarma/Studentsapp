package student;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class InsertRecordForm {
	JFrame fr = new JFrame("4Achivers - Insert Record");
	JLabel lb[] = new JLabel[5];
	JTextField[] txt = new JTextField[5];
	JButton btn = new JButton("Save Record");
	PreparedStatement ps , ps1;
	
	public InsertRecordForm() {
		fr.setSize(700,600);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.getContentPane().setBackground(Color.DARK_GRAY);
		addcomponents();
		fr.setVisible(true);
	}
	
	private void addcomponents() {
		fr.setLayout(null);
		int y = 70;
		String[] arr = {"ID","Name","Email","Contact No." ,"Course Name"};
		Font fo = new Font("Verdana",0,19);
		for(int i=0;i<5;i++) {
			lb[i]=new JLabel("Enter Student " +arr[i]);
			lb[i].setBounds(30,y,300,50);
			lb[i].setFont(fo);
			lb[i].setForeground(Color.WHITE);
			fr.add(lb[i]);
			txt[i] = new JTextField();
			txt[i].setBounds(320,y,300,40);
			txt[i].setFont(fo);
			fr.add(txt[i]);
			y+=70;
			
			
		}
		
		btn.setBounds(250, 450, 180, 40);
		btn.setFont(fo);
		btn.setBackground(Color.LIGHT_GRAY);
		fr.add(btn);
		btn.addActionListener(new SaveInsertListener());
		}
	
	private void dbConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ishadb", "root", "Ishamysql@0902");
			
			String query="insert into student_info values(?,?,?,?,?)";
			ps=con.prepareStatement(query);
			System.out.println("Database connected");
			ps1 = con.prepareStatement("select * from student_info where pid=?");
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	class SaveInsertListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String sid = txt[0].getText();
			String name = txt[1].getText();
			String email = txt[2].getText();
			String contact = txt[3].getText();
			String course = txt[4].getText();
			
			//VALIDATIONS
			//FOR REQUIRED FIELDS
			if(sid.equals("")||name.equals("")||email.equals("")||contact.equals("")||course.equals("")) {
				JOptionPane.showMessageDialog(fr, "All Fields are Required");
				return;
			}
			//FOR STUDENTID
			if(sid.length()!=5) {
				JOptionPane.showMessageDialog(fr,"Student Id must contain 4 characters only");
				return;
			}
			//REPLACE THE STRING IN IT'S CORRECT FORMAT
			sid=sid.replace('s', 'S');
			
			try {
				dbConnection();
				//VALIDATION 
				//for id if the id exists or not
				ps1.setString(1,sid);
				ResultSet rst = ps1.executeQuery();
				if(rst.next()) {
					JOptionPane.showMessageDialog(fr,"Product with Id " +sid+ " already exists");
				}
				
				ps.setString(1, sid);
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, contact);
				ps.setString(5, course);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(fr, "Product Record saved");
				System.out.println("Product record saved successfully");
				
			}catch(Exception ex) {
				System.out.println(ex);
			}
		
			}
	}
	

	public static void main(String[] args) {
		new InsertRecordForm();

	}

}
