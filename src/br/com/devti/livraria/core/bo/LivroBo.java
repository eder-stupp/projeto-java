package br.com.devti.livraria.core.bo;

import java.util.List;

import br.com.devti.livraria.core.dao.LivroDao;
import br.com.devti.livraria.core.entity.LivroEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class LivroBo {
	
	public String salvarLivro(LivroEntity livro) throws BusinessException {
		validarLivro(livro);
		LivroDao ldao = new LivroDao(); 
		return ldao.salvarLivro(livro);		
	}
	
	public java.util.List<LivroEntity> listarLivro() throws BusinessException{
		return new LivroDao().listarLivro();
	}
	
	public void excluirLivro(Long codigoLivro) throws BusinessException {
		new LivroDao().excluirLivro(codigoLivro);
	}
	
	public LivroEntity buscarLivroPorId(Long codigoLivro) throws BusinessException{
		return new LivroDao().buscarLivroPorId(codigoLivro);	
	}
	
	public String editarLivro(LivroEntity livro) throws BusinessException{
		validarLivro(livro);
		return new LivroDao().editarLivro(livro);
	}
	
	private void validarLivro(LivroEntity livro) throws BusinessException{
		if(livro.getNome() != null && livro.getNome().equals("")) {
			throw new BusinessException("Preencher nome do livro.");
		}
		if(livro.getIsbn() != null && livro.getIsbn().equals("")) {
			throw new BusinessException("Preencher ISBN do livro.");
		}
	}
	
	public List<LivroEntity> buscarLivroFiltrado(LivroEntity livro) throws BusinessException{
		return new LivroDao().buscarLivroFiltrado(livro);
	}
}
