package spring.rest.service;

public class RestVO {

	private Integer id;
	private String title;
	private String text;
	private String imageUrl;
	
	public RestVO() {
		
	}
	
	public RestVO(Integer id, String title, String text, String imageUrl) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.imageUrl = imageUrl;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "RestVO [id=" + id + ", title=" + title + ", text=" + text + ", imageUrl=" + imageUrl + "]";
	}
	
	
}
