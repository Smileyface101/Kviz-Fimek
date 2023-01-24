//Import potrebnih biblioteka za pokretanje aplikacije
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.*;



// Dodavanje elemenata na glavni graficki interfejs i kreiranje varijabli radi dalje upotrebe
public class KvizFimek {
	private JFrame frame;
	private JButton nextButton;
	private JButton loadButton;
  private JButton KvizKreator;
	private ArrayList<BrojPitanja> cardList;
	private BrojPitanja currentCard;
	private JTextArea questionDisplay;
	private JLabel[] answer;
	private JRadioButton [] radioAnsw;
	private JProgressBar progBar;
	private int numOfQuestions;
	private int currentCardIndex;
	private ButtonGroup radioGroup;
	private int numOfCorrectAnswers;
	
  //kreiranje glavnog grafickog interfejsa
	public static void main(String[] args) {
		new KvizFimek().goGUI();
	}
	
  //dizajn glavnog grafickog interfejsa aplikacije
	public void goGUI(){
		frame = new JFrame("Kviz Fimek");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		cardList = new ArrayList<BrojPitanja>();
		questionDisplay = new JTextArea(10,20);
		questionDisplay.setEditable(false);
		questionDisplay.setWrapStyleWord(true);
		questionDisplay.setLineWrap(true);
		JScrollPane  scrollP = new JScrollPane(questionDisplay);
		scrollP.setBorder(BorderFactory.createTitledBorder("Pitanje:"));
		scrollP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
    
    
    //dizajn progressbara i elemenata koji ga okruzuju na glavnom grafickom interfejsu aplikacije
		JPanel progressP = new JPanel();
		JLabel progLabel = new JLabel("Trenutno: ");
		progBar = new JProgressBar();
		progBar.setStringPainted(true);
		progBar.setMinimum(0);
		progressP.add(progLabel);
		progressP.add(progBar);
		
    
      //dizajn dela grafickog interfejsa gde se nalaze odgovori pitanja i elemenata koji ga okruzuju
		radioGroup = new ButtonGroup();
		radioAnsw = new JRadioButton[4];
		for (int i = 0; i < 4; i++) {
			radioAnsw[i] = new JRadioButton(1+ i + "");
			radioGroup.add(radioAnsw[i]);
		}
		
		answer = new JLabel[4];
		for (int i = 0; i < 4; i++) {
			answer[i] = new JLabel("");
		}

		JPanel answPanel = new JPanel();
		answPanel.setLayout(new GridBagLayout());
		answPanel.setBackground(new Color(153,204,255));
		answPanel.setBorder(BorderFactory.createTitledBorder("Izaberite odgovor"));
		for (int i = 0; i < 4; i++) {
			answPanel.add(radioAnsw[i], new GridBagConstraints(0, i, 1, 1, 1, 1,
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					new Insets(2, 2, 2, 2), 0, 0));
			radioAnsw[i].setBackground(new Color(153,204,255));
			answPanel.add(answer[i], new GridBagConstraints(1, i, 1, 1, 1, 1,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					new Insets(2, 2, 2, 2), 0, 0));
			
		}
		answPanel.setPreferredSize(new Dimension(300, 100));
		
    //button za ucitavanje kviza
		loadButton = new JButton("Ucitaj kviz");
		loadButton.addActionListener(new LoadActionListener());
    
    //button za prelazak na sledece pitanje        
		nextButton = new JButton("Sledece pitanje");
		nextButton.addActionListener(new NextActionListener());
                
                
    //dizajn elemenata grafickog interfejsa na kom se nalaze button-i ucitaj kviz i sledece pitanje          
		JPanel buttonP = new JPanel();
		buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.X_AXIS));
		buttonP.add(loadButton);
		buttonP.add(Box.createRigidArea(new Dimension(40,0)));
		buttonP.add(nextButton);
		
    //dodavanje frejmova na glavni graficki interfejs
		frame.add(scrollP);
		frame.add(progressP);
		frame.add(answPanel);
		frame.add(buttonP);
		frame.setSize(320,400);
		frame.setVisible(true);
		
	}
	
  //dodavanje actionlistenera da bi pratili sledeci klik korisnika na glavni interfejs
	class NextActionListener implements ActionListener {
    
    //override roditeljsku klasu
		@Override
    
    //logika prikaza sledeceg pitanja
		public void actionPerformed(ActionEvent e) {
			if(currentCardIndex < numOfQuestions){
				isCorrect();
				showNextCard();	}
        //prikaz rezultat-a nakon prikaza poslednjeg pitanja
			else if(currentCardIndex == numOfQuestions){
				isCorrect();
				nextButton.setText("Prikazi rezultat");
				JOptionPane.showMessageDialog(frame, "Broj tacnih odgovora: " + numOfCorrectAnswers, "Rezultat", JOptionPane.INFORMATION_MESSAGE);
                                JOptionPane.showMessageDialog(frame, "Kraj Programa" , "Kraj Programa.", JOptionPane.INFORMATION_MESSAGE);

                        }
			
		}
		
	}
  
  
	//logika racunanja tacno odgovorenih pitanja
	private void isCorrect(){
		for(int i = 0; i < 4; i++){
			if(radioAnsw[i].isSelected()){
				if(answer[i].getText().equals(currentCard.getCorrectAnswer())) {
					numOfCorrectAnswers++;
				}
			}
				
		}
	}
	 //dodavanje actionlistenera da bi pratili da li korisnik pokusava da ucita kviz iz nekog fajla
	class LoadActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(frame);
			loadFile(fileOpen.getSelectedFile());
			showNextCard();
			setNumberOfQuestions(cardList.size());
			progBar.setMaximum(getNumberOfQuestions());
		}
		
	}
	
	// Metoda ucitavanja fajla sa diska u program putem ucitaj kviz buttona
	private void loadFile(File file){
		
		try {
			BufferedReader readerB = new BufferedReader(new FileReader(file));
			cardList.clear();
			currentCardIndex = 0;
			numOfCorrectAnswers = 0;
			String line = null;
			while ((line = readerB.readLine()) != null) {
				makeCard(line);
			}
			readerB.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void makeCard(String lineToParse){
		String [] result = lineToParse.split("/");
		String question = result[0];
		String correctAnswer = result[5];
		String [] answers = new String[4];
		for(int j = 0; j < 4; j++){
			answers[j] = result[j+1];
		}
		BrojPitanja card = new BrojPitanja(question, answers, correctAnswer);
		
		cardList.add(card);
		
	}
	
	private void setNumberOfQuestions(int numOfQuestions){
		this.numOfQuestions = numOfQuestions; 
	}
	
	public int getNumberOfQuestions(){
		return numOfQuestions;
	}
	
	private void showNextCard(){
		currentCard = cardList.get(currentCardIndex);
		currentCardIndex++;
		questionDisplay.setText(currentCard.getQuestion());
		String[] ansVariants = currentCard.getAnswers();
		for(int i = 0; i < 4; i++){
			answer[i].setText(ansVariants[i]);
		}
		progBar.setValue(currentCardIndex);
	}
}
