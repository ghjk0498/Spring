package spring.rest;

public class Test {

	private String id;
	private String title;
	private String text;
	
	public Test() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", title=" + title + ", text=" + text + "]";
	}
	
}
