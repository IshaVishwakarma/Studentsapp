package student;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;




public class Main {
	JFrame fr = new JFrame("4Achivers");
	JButton bt[] = new JButton[5];



public Main() {
	fr.setSize(500,500);
	fr.setLocationRelativeTo(null);
	fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fr.setResizable(false);
	fr.getContentPane().setBackground(Color.DARK_GRAY);
	fr.setLayout(null);
	
	addbuttons();
	fr.setVisible(true);
}

private void addbuttons() {
	int y = 70;
	String[] str = {"Insert","Delete","Update","Select","Show"};
	Font font = new Font("Verdana",0,19);
	
	StudentListener listner = new StudentListener();
	for(int i =0;i<5;i++) {
		bt[i]=new JButton(str[i]+" Record");
		bt[i].setBounds(130, y, 220, 40);
		bt[i].setFont(font);
		bt[i].setBackground(Color.lightGray);
		bt[i].addActionListener(listner);
		fr.add(bt[i]);
		y+=70;
	}
		
}
class StudentListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent evt) {
		JButton btn =(JButton)evt.getSource();
		if(btn==bt[0]) {
			new InsertRecordForm();
		}
		else if(btn==bt[1]) {
			new DeleteRecordForm();
			
		}else if(btn==bt[2]) {
			new UpdateRecordForm();
		}
		else if(btn==bt[3]) {
			new GettingSingleRecord();
		}
		else if(btn==bt[4]) {
			new RecordList();
		}
		else if(btn==bt[5]) {
			
		}
		
	}
}

public static void main(String[] args) {
	new Main();
}
}

