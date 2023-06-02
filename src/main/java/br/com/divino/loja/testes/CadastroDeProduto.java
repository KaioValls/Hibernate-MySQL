package br.com.divino.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import br.com.divino.loja.dao.CategoriaDao;
import br.com.divino.loja.dao.ProdutoDao;
import br.com.divino.loja.modelo.Categoria;
import br.com.divino.loja.modelo.Produto;
import br.com.divino.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		Long id = 1l;
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(id);
	
		System.out.println(p.getNome());
		
		List<Produto> todos = produtoDao.buscarTodos();
		List<Produto> nomes = produtoDao.buscarPorNome("Xiaomi Redmi Note 10");
		todos.forEach( p2 -> System.out.println(p2.getNome()));
		nomes.forEach( p2 -> System.out.println(p2.getNome()));
	}

	
	
	private static void cadastrarProduto() {
		Categoria INFORMATICA = new Categoria("INFORMATICA");
		Categoria CELULARES = new Categoria("CELULARES");
		Produto jbl = new Produto("JBL BoomBox","Melhor som do mercado",new BigDecimal("5000"),INFORMATICA);
		Produto celular = new Produto("Xiaomi Redmi Note 10","Xing Ling",new BigDecimal("1"),CELULARES);
		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(INFORMATICA);
		categoriaDao.cadastrar(CELULARES);
		
		produtoDao.cadastrar(jbl);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}
}
