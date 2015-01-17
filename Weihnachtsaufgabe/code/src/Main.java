import graph.Digraph;


public class Main {

	public static void main(String[] args) {
		Digraph graph = new Digraph("dlexdb_results_4.txt");
		graph.zusammenhaengend();
		graph.groeßterGrad();
	}

}
