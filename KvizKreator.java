//Import potrebnih biblioteka za pokretanje aplikacije

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;


// Dodavanje elemenata na graficki interfejs i kreiranje varijabli radi dalje upotrebe

public class KvizKreator {

	private JFrame frame;
	private JTextArea questionArea;
	private ArrayList<BrojPitanja> cardList;
	private JRadioButton[] radioButton;
	private JTextField[] answers;

	public void go() {
		frame = new JFrame("Kviz Kreator v1.0");
		JPanel mainPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel answPanel = new JPanel();
		cardList = new ArrayList<BrojPitanja>();
		Font font = new Font("sanserif", Font.BOLD, 15);

		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		questionArea = new JTextArea(6, 25);
		questionArea.setFont(font);
		questionArea.setLineWrap(true);
		questionArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(questionArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		mainPanel.add(scrollPane);

		answers = new JTextField[4];
		for (int i = 0; i < 4; i++) {
			answers[i] = new JTextField(30);
		}

		radioButton = new JRadioButton[4];
		ButtonGroup correctSwitcher = new ButtonGroup();
		for (int i = 0; i < 4; i++) {
			radioButton[i] = new JRadioButton(i + 1 + "");
			correctSwitcher.add(radioButton[i]);
		}

		answPanel.setLayout(new GridBagLayout());
		Border answPanelBorder = BorderFactory.createTitledBorder("Odgovori na pitanja:");
		answPanel.setBorder(answPanelBorder);

		answPanel.add(radioButton[0], new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.CENTER,
				new Insets(2, 2, 2, 2), 0, 0));
		answPanel.add(answers[0], new GridBagConstraints(1, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.CENTER,
				new Insets(2, 2, 2, 2), 0, 0));

		answPanel.add(radioButton[1], new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.CENTER,
				new Insets(2, 2, 2, 2), 0, 0));
		answPanel.add(answers[1], new GridBagConstraints(1, 1, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.CENTER,
				new Insets(2, 2, 2, 2), 0, 0));

		answPanel.add(radioButton[2], new GridBagConstraints(0, 2, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.CENTER,
				new Insets(2, 2, 2, 2), 0, 0));
		answPanel.add(answers[2], new GridBagConstraints(1, 2, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.CENTER,
				new Insets(2, 2, 2, 2), 0, 0));

		answPanel.add(radioButton[3], new GridBagConstraints(0, 3, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.CENTER,
				new Insets(2, 2, 2, 2), 0, 0));
		answPanel.add(answers[3], new GridBagConstraints(1, 3, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.CENTER,
				new Insets(2, 2, 2, 2), 0, 0));
		answPanel.setPreferredSize(new Dimension(400, 140));
    
    
    //kreiranje button-a na interfejsu
		JButton nextButton = new JButton("Sledece pitanje");
		nextButton.addActionListener(new NextButtonListener());
		buttonPanel.add(nextButton);
		JButton saveButton = new JButton("Sacuvaj kviz");
		saveButton.addActionListener(new SaveButtonListener());
		buttonPanel.add(saveButton);
   
    //dodavanje panela i ogranicavanje velicine grafickog interfejsa
		frame.add(mainPanel);
		frame.add(answPanel);
		frame.add(buttonPanel);
		frame.setSize(500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);

	}
  //Kreiranje novog kviza
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				KvizKreator q = new KvizKreator();
				q.go();
			}
		});
		
	}

  
  //dodavanje actionlistenera

	class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			createCard();

		}
	}

  	// Metoda organizovanja podataka radi sacuvanja u nekom fajlu na disku

	private void createCard() {
		String question = questionArea.getText();
		String[] answer = new String[4];
		String correctAnswer =null;

		for (int i = 0; i < 4; i++) {
			if (radioButton[i].isSelected()) {
				correctAnswer = answers[i].getText();
			}
			answer[i] = answers[i].getText();
			radioButton[i].setSelected(false);
			answers[i].setText("");
		}
		questionArea.setText("");
		cardList.add(new BrojPitanja(question, answer, correctAnswer));
	}

    //dodavanje actionlistenera
	class SaveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			createCard();
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());

		}

	}
   	// Metoda kreiranja fajla na disk
	private void saveFile(File file) {
		try {
			BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
			for (BrojPitanja card : cardList) {
				buffer.write(card.getQuestion() + "/");
				String[] ans = card.getAnswers();
				String isCor = card.getCorrectAnswer();
				for (int i = 0; i < 4; i++) {
					buffer.write(ans[i] + "/");
				}
				buffer.write(isCor);
				System.out.println();
			}
			buffer.close();

		} catch (Exception e) {

		}
	}
}
