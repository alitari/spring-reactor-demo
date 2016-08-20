package de.alexkrieg.springreact;

public class ZippedInfo {
	
	
	
	
	private long id1;
	private long userId1;
    private String title1;
	
	private long id2;
	private long userId2;
	private String title2;
	
	public ZippedInfo(MainInfo m1, MainInfo m2) {
		super();
        setId1(m1.getId());
        setId2(m2.getId());
        
        setTitle1(m1.getTitle());
        setTitle2(m2.getTitle());
        
        setUserId1(m1.getUserId());
        setUserId2(m2.getUserId());
	}
	
    public long getId1() {
		return id1;
	}
	public void setId1(long id1) {
		this.id1 = id1;
	}
	public long getUserId1() {
		return userId1;
	}
	public void setUserId1(long userId1) {
		this.userId1 = userId1;
	}
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public long getId2() {
		return id2;
	}
	public void setId2(long id2) {
		this.id2 = id2;
	}
	public long getUserId2() {
		return userId2;
	}
	public void setUserId2(long userId2) {
		this.userId2 = userId2;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	

	
	
}
