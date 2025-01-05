package spring_test;

public class Task {
	String task;
	String answer;
	String[] wrong_answers;
	int points;
	
	Task(String task, String answer, String[] wrong_answers, int points){
		this.task = task;
		this.answer = answer;
		this.wrong_answers = wrong_answers;
		this.points = points;
	}
	
	String get_task() {return this.task;}
	void set_task(String task) {this.task = task;}
	
	String get_answer() {return this.answer;}
	void set_answer(String answer) {this.answer = answer;}
	
	String[] get_wrong_answers() {return this.wrong_answers;}
	void set_answer(String[] wrong_answers) {this.wrong_answers = wrong_answers;}
	
	int get_points() {return this.points;}
	void set_points(int points) {this.points = points;}
	
	String wrong_answers_to_one(String[] wrong_answers) {
		String S = "";
		for(int i = 0; i<wrong_answers.length; i++) {
			S = S+wrong_answers[i] + "@";
		}
		return S;
	}
	
	String get_wrong_answers_in_one() {return this.wrong_answers_to_one(wrong_answers);}
	
	static String[] wrong_answers_to_many(String wrong_answers) {
		return wrong_answers.split("@");
	}
}
