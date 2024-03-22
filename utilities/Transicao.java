package utilities;

public class Transicao {
	private int from;
    private int to;
    private String read;

    public Transicao() {
    }

    public Transicao(int from, int to, String read) {
        setFrom(from);
        setTo(to);
        setRead(read);
    }

	private int getFrom() {
		return from;
	}

	private void setFrom(int from) {
		this.from = from;
	}

	private int getTo() {
		return to;
	}

	private void setTo(int to) {
		this.to = to;
	}

	private String getRead() {
		return read;
	}

	private void setRead(String read) {
		this.read = read;
	}
    
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("From: %d \n", getFrom()));
		sb.append(String.format("To: %d \n", getTo()));
		sb.append(String.format("Read: %s \n", getRead()));
		return sb.toString();
	}
}