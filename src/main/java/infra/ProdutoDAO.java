package infra;

import modelo.basico.Produto;

//classe espec�fica para extends o DAO e informar que vai trabalhar com a classe Produto
public class ProdutoDAO extends DAO<Produto>{
	
	public ProdutoDAO() {
		super(Produto.class);
	}

}
