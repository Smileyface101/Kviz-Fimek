     // kreiranje klase BrojPitanja
public class BrojPitanja {

    // kreiranje String radi daljeg koriscenja inputa korisnika, pitanja, liste za odgovore, tacan odgovor i brojac za progressbar kviz-a, kao i kalkulisanje
    // tacnih odgovora radi prikaza rezultata pri zavrsetku rada aplikacije
	private String question;
	private String [] answers;
	private String correctAnswer;
	private static int count = 0;
	public BrojPitanja(String question, String [] answers, String correctAnswer) {
		this.question = question;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
		count++;

	}
  	//return pitanja
	public String getQuestion() {
		return question;
	}
	//return odgovora
	public String[] getAnswers() {
		return answers;
	}
	//return tacnog odgovora
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	
}
