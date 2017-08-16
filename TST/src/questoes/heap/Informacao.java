package questoes.heap;

public class Informacao implements Comparable<Informacao> {

	private String data;
	int key;

	public Informacao(String newData) {
		data = newData;
		key = (int) System.currentTimeMillis();
	}

	@Override
	public int compareTo(Informacao o) {
		return this.key - o.key;
	}
	

	@Override
	public String toString() {
		return "( "+ data + " , " + key +" )";
	}
	
	
}
