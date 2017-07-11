package repositorio;

public class RepositorioGenericoParametrizado<T> {

	private T[] array;
	private int indice;

	public RepositorioGenericoParametrizado() {
		indice = 0;
		array = (T[]) new Object[20];
	}

	private int procurarIndice(T objeto) {
		int i = 0;
		int resp = -1;
		boolean achou = false;

		while ((i < indice) && !achou) {
			if ((array[i]).equals(objeto)) {
				resp = i;
				achou = true;
			}
			i = i + 1;
		}
		return resp;
	}

	public boolean existe(T objeto) {
		boolean resp = false;

		int i = this.procurarIndice(objeto);
		if (i != -1) {
			resp = true;
		}

		return resp;
	}

	public void inserir(T novo) {
		array[indice] = novo;
		indice = indice + 1;
	}

	public void atualizar(T objeto) {
		int i = procurarIndice(objeto);
		if (i != -1) {
			array[i] = objeto;
		} else {
			throw new RuntimeException("Conta nao encontrada");
		}
	}

	public T procurar(T objeto) {
		T resp = null;
		int i = this.procurarIndice(objeto);
		if (i != -1) {
			resp = array[i];
		} else {
			throw new RuntimeException("Cliente nao encontrado");
		}

		return resp;
	}

	public void remover(T objeto) {
		int i = this.procurarIndice(objeto);
		if (i != -1) {
			array[i] = array[indice - 1];
			array[indice - 1] = null;
			indice = indice - 1;
		} else {
			throw new RuntimeException("Cliente nao encontrado");
		}
	}

}
