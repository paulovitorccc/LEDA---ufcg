package produto;

import java.util.ArrayList;

/**
 * Classe que representa um repositório de produtos usando ArrayList como
 * estrutura sobrejacente. Alguns métodos (atualizar, remover e procurar) ou
 * executam com sucesso ou retornam um erro. Para o caso desde exercício, o erro
 * será representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 *
 * @author Adalberto
 */
public class RepositorioProdutoArrayList {

	/**
	 * A estrutura onde os produtos sao mantidos. Voce nao precisa se preocupar
	 * por enquanto com o uso de generics em ArrayList.
	 */
	private ArrayList produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoArrayList(int size) {
		super();
		this.produtos = new ArrayList();
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		int indice = -1;
		for (int k = 0; k <= index; k++) {
			Object objetoParaAnalisar = produtos.get(k);

			if (objetoParaAnalisar instanceof Produto) {
				Produto produtoParaAnalisar = (Produto) objetoParaAnalisar;
				if (produtoParaAnalisar.getCodigo() == codigo) {
					indice = k;
				}
			}

		}
		return indice;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public Object existe(int codigo) {
		int i = this.procurarIndice(codigo);
		if (i == -1) {
			return null;
		} else {
			return true;
		}
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(Produto produto) {
		produtos.add(produto);
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(Produto produto) {
		int indiceProduto = this.procurarIndice(produto.getCodigo());
		if (indiceProduto != -1) {
			produtos.remove(indiceProduto);
			produtos.add(indiceProduto, produto);
		} else {
			throw new RuntimeException();
		}
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {
		int indiceAtual = this.procurarIndice(codigo);
		if (indiceAtual == -1) {
			throw new RuntimeException();
		} else {
			produtos.remove(indiceAtual);
		}
		index -= 1;
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public Produto procurar(int codigo) {
		int indiceAtual = this.procurarIndice(codigo);
		Produto resp = null;
		if (indiceAtual == -1) {
			throw new RuntimeException();
		} else {
			resp = (Produto) produtos.get(indiceAtual);
		}
		return resp;
	}
}
