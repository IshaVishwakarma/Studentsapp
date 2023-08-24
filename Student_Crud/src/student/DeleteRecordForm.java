package student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.*;

public class DeleteRecordForm {
	JFrame fr = new JFrame("4Achivers - Delete Record");
	JLabel la = new JLabel("Enter Student Id");
	JButton btn = new JButton("Delete Record");
	JTextField txt = new JTextField();
	PreparedStatement ps;
	
	public DeleteRecordForm() {
		fr.setSize(500,300);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.getContentPane().setBackground(Color.DARK_GRAY);
		addComponents();
		fr.setVisible(true);
	}
	
	public void addComponents(){
		fr.setLayout(null);
		int y =50;
		Font fo = new Font("Verdana",0,19);
		
		la.setBounds(20,y,200,30);
		la.setFont(fo);
		la.setForeground(Color.WHITE);
		fr.add(la);
		
		txt.setBounds(200, y, 250, 30);
		txt.setFont(fo);
		fr.add(txt);
		
		
		btn.setBounds(155, 150, 180, 50);
		btn.setFont(fo);
		btn.setBackground(Color.LIGHT_GRAY);
		btn.addActionListener(new DeleteListener());
		fr.add(btn);
	}
	
	private void dbConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ishadb", "root",
					"Ishamysql@0902");
			System.out.println("Database Connected!");
			String query = "delete from student_info where id=?";
			ps = con.prepareStatement(query);

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			try {
				dbConnection();
				String id = txt.getText();

				ps.setString(1, id);
				int n = ps.executeUpdate();

				if (n >= 1)
					JOptionPane.showMessageDialog(fr, " Product Deleted Succesfully");
				else
					JOptionPane.showMessageDialog(fr, " Product Does Not Exists");
			} catch (Exception ex) {
				System.out.println(ex);
			}

		}

	}
	
	

	public static void main(String[] args) {
		new DeleteRecordForm();

	}

}
