package spring_test;

import java.sql.*;
import java.util.HashSet;

public class Task_sql {
	Connection con;
	String db = "tasks_1";
	Task_sql() throws Exception{
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_1", "root", "1234");
	}
	
	boolean is_unique_task(Task task) throws Exception{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from tasks_1");
		while(rs.next()) {
			if(task.get_answer() == rs.getString("task")) {
				rs.close();
				return false;
			}
		}
		rs.close();
		return true;
	}
	
	HashSet<Task> get_all_tasks() throws Exception{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from " + db);
		HashSet<Task> HS= new HashSet<>();
		while(rs.next()){
			HS.add(ResultSetElement_to_Task(rs));
		}
		rs.close();
		return HS;
	}
	
	void add_task(Task task) throws Exception {
		if(this.is_unique_task(task)) {
			String update = "insert into tasks_1 (task, answer, wrong_answers, points) "+
					"values (\""+ task.get_task() + 
					"\", \""+ task.get_answer() + 
					"\", \""+ task.get_wrong_answers_in_one() + "\", "+ task.get_points()+ ");";
			Statement st = con.createStatement();
			st.executeUpdate(update);
			st.close();
		}
	}
	
	static Task ResultSetElement_to_Task(ResultSet rs) throws Exception{
		//Turn ResultSet element to Task element
		String task = rs.getString("task");
		String answer = rs.getString("answer");
		String[] wrong_answers = Task.wrong_answers_to_many(rs.getString("wrong_answers"));
		int points = rs.getInt("points");
		return new Task(task, answer, wrong_answers, points);
	}
	
	Task get_task_by_id(int id) throws Exception{
		String query = "select * from tasks_1 where id = "+id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		if(rs.next()) {
			Task tmp = ResultSetElement_to_Task(rs);
			rs.close();
			return tmp;
		}
		return null;
	}
	
	void close() throws Exception{
		con.close();
	}
	
	
	
}
