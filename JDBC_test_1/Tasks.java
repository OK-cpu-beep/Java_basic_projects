package spring_test;

import java.util.*;

public class Tasks {
	HashSet<Task> HS;
	
	@SuppressWarnings("unchecked")
	Tasks(HashSet<Task> HS){
		this.HS = (HashSet<Task>)HS.clone();
	}
	
	HashSet<Task> get_HS(){return HS;}
	
	void add(Task task) {
		HS.add(task);
	}
	void erase(Task task) {
		HS.remove(task);
	}
	void add_many(HashSet<Task> HS2) {
		HS.addAll(HS2);
	}
	void erase_many(HashSet<Task> HS2) {
		HS.removeAll(HS2);
	}
}
