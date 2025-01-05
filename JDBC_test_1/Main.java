package spring_test;

import java.util.*;
import java.sql.*;

public class Main {
	
    public static void sql_tasks_test() throws Exception {
    	Task_sql Ts = new Task_sql();
    	Tasks T = new Tasks(Ts.get_all_tasks());
    	Scanner s1 = new Scanner(System.in);
    	Scanner s2 = new Scanner(System.in);
    	while(true) {
    		System.out.println("ВИИИИИИКТОРИНААА УУУ");
    		System.out.println("Выберете опцию: 1-Играть, 2-Добавить, 3-Удалить");
    		int cases; cases = s1.nextInt();
    		String task, answer;
    		String[] wrong_answers;
    		int points;
    		switch (cases) {
    			case 1:{
    				Random random = new Random();
    				System.out.println("Загружено " + T.get_HS().size() + " Вопросов");
    				int r = random.nextInt(T.get_HS().size());
    				Task current_task = Ts.get_task_by_id(r+1);
    				System.out.println(current_task.task);
    				r = random.nextInt(1,5); int j = 0;
    				for(int i = 1; i<5; i++) {
    					if(i==r) {
    						System.out.println(i+ ": " + current_task.get_answer()); j++;
    					}
    					else {
    						System.out.println(i+ ": " + current_task.get_wrong_answers()[i-j-1]);
    					}
    				}
    				System.out.println("Введите Число от 1 до 4 ");
    				int ans = s1.nextInt();
    				if (ans==r) {
    					System.out.println("Правильно!");
    				}
    				else {
    					System.out.println("Непраивльно лох");
    				}
    				break;
    				
    			}
    			case 2:{
    				System.out.println("Введите Вопрос");
    				task = s2.nextLine();
    				System.out.println("Введите Ответ ");
    				answer = s2.nextLine();
    				System.out.println("Введите 3 неправильных ответа");
    				wrong_answers = new String[3];
    				for(int i = 0; i<3; i++) {
    					wrong_answers[i] = s2.nextLine();
    				}
    				System.out.println("Введите Кол-во очков за правильный ответ");
    				points = s1.nextInt();
    				Task t = new Task(task, answer, wrong_answers, points);
    				T.add(t);
    				Ts.add_task(t);
    				break;
    			}
    		}
    	}
    }
    public static void main(String[] args) throws Exception{
    	sql_tasks_test();
    }
}
