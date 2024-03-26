package projTC;

public class Transicao {
	String from;
	String to;
	String read;
	
	public Transicao(String from, String to, String read) {
		setFrom(from);
		setTo(to);
		setRead(read);
	}
	
	public Transicao() {
		this("", "", "");
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	
	
}
