// Java Resources/src/entities/Tasks.java
// ���ݿ� ToDoList �б� tasks ��Ӧ��ʵ����

package beans;

public class Tasks {

	private int id;
	//private String username;
	private String taskname;
	private String email;
	private int accomplished;
	private String type;
	
	public Tasks() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAccomplished() {
		return accomplished;
	}

	public void setAccomplished(int accomplished) {
		this.accomplished = accomplished;
	}
	
	
}
