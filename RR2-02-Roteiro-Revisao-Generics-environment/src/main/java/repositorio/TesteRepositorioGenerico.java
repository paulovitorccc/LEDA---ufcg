package repositorio;

import cliente.Cliente;
import conta.Conta;
<<<<<<< HEAD
=======
import conta.ContaAbstrata;
>>>>>>> 27f36918b5df7e23f6a28ea813d73eade400dffa
import conta.ContaBonificada;
import conta.Poupanca;

public class TesteRepositorioGenerico {
	public static void main(String[] args) {
		RepositorioCliente repClientes = new RepositorioCliente();
		RepositorioConta repContas = new RepositorioConta();
		RepositorioGenerico rep = new RepositorioGenerico();
<<<<<<< HEAD
		
		//quero um repositorio que só guarda clientes
		Cliente cli1 = new Cliente("1","Jose");
		Cliente cli2 = new Cliente("2","Joao");
		repClientes.inserir(cli1);
		repClientes.inserir(cli2);
		//repClientes.inserir(new Conta("3",100.0,cli1));
		
		//quero um repositorio que so guarda contas
		Conta c1 = new Conta("4",50.0, cli1);
		Conta c2 = new Poupanca("5",60.0, cli1);
		Conta c3 = new ContaBonificada("5",70.0, cli1);
		Conta c4 = new Conta("6",80.0, cli1);
=======

		// quero um repositorio que só guarda clientes
		Cliente cli1 = new Cliente("1", "Jose");
		Cliente cli2 = new Cliente("2", "Joao");
		repClientes.inserir(cli1);
		repClientes.inserir(cli2);
		// repClientes.inserir(new Conta("3",100.0,cli1));

		// quero um repositorio que so guarda contas
		Conta c1 = new Conta("4", 50.0, cli1);
		Conta c2 = new Poupanca("5", 60.0, cli1);
		Conta c3 = new ContaBonificada("5", 70.0, cli1);
		Conta c4 = new Conta("6", 80.0, cli1);
>>>>>>> 27f36918b5df7e23f6a28ea813d73eade400dffa

		repContas.inserir(c1);
		repContas.inserir(c2);
		repContas.inserir(c3);
		repContas.inserir(c4);
<<<<<<< HEAD
		//repContas.inserir(cli1);
		
		//testando um reporitorio generico
=======
		// repContas.inserir(cli1);

		// testando um reporitorio generico
>>>>>>> 27f36918b5df7e23f6a28ea813d73eade400dffa
		rep.inserir(c1);
		rep.inserir(cli1);
		rep.inserir(c2);

<<<<<<< HEAD
		//Conta procurado = rep.procurar(c2);
		
		//testando repositorio herdado
=======
		// Conta procurado = rep.procurar(c2);

		// testando repositorio herdado
>>>>>>> 27f36918b5df7e23f6a28ea813d73eade400dffa
		RepositorioContaHerdado repHerdado = new RepositorioContaHerdado();
		repHerdado.inserir(c1);
		repHerdado.inserir(cli1);
		repHerdado.inserir(c2);
<<<<<<< HEAD
		
		//procurado = repHerdado.procurar(c3);
		
=======

		// procurado = repHerdado.procurar(c3);

		RepositorioGenericoParametrizado<Cliente> repClientePar = new RepositorioGenericoParametrizado<Cliente>();

		repClientePar.inserir(cli1);
		// repClientePar.inserir(c2);

		RepositorioGenericoParametrizado<ContaAbstrata> repContaAbstrataPar = new RepositorioGenericoParametrizado<ContaAbstrata>();

		repContaAbstrataPar.inserir(c1);
		// repContaAbstrataPar.inserir(cli2);

>>>>>>> 27f36918b5df7e23f6a28ea813d73eade400dffa
	}
}
