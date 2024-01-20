package hawkershowtable;

public class userbean {
	

	String hname;
	String address;
	String acard;
	String mobile;
	float sal;
	String doj;
	String selareas;
	public userbean(String hname, String address, String acard, String mobile, float sal, String doj, String selareas) {
		super();
		this.hname = hname;
		this.address = address;
		this.acard = acard;
		this.mobile = mobile;
		this.sal = sal;
		this.doj = doj;
		this.selareas = selareas;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAcard() {
		return acard;
	}
	public void setAcard(String acard) {
		this.acard = acard;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getSelareas() {
		return selareas;
	}
	public void setSelareas(String selareas) {
		this.selareas = selareas;
	}
	
	

}
