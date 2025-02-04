package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DeckCreator extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeckCreator frame = new DeckCreator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeckCreator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDecksLocation = new JLabel("Decks Location:");
		lblDecksLocation.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDecksLocation.setBounds(10, 27, 117, 24);
		contentPane.add(lblDecksLocation);
		
		JButton btnSetLocation = new JButton("Set Location");
		btnSetLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Explorer explorer = new Explorer();
				explorer.setVisible(true);
				//explorer.getSelectedFile().getAbsolutePath();
				System.out.println(explorer.getPath());
			
			}
		});
		btnSetLocation.setBounds(123, 29, 106, 23);
		contentPane.add(btnSetLocation);
		
		
	}
}
