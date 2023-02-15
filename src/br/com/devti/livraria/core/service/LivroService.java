package br.com.devti.livraria.core.service;

import java.util.List;
import br.com.devti.livraria.core.bo.LivroBo;
import br.com.devti.livraria.core.entity.LivroEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class LivroService {
	
	public String salvarLivro(LivroEntity livro) throws BusinessException {
		LivroBo lbo = new LivroBo();
		return lbo.salvarLivro(livro);
	}
	
	public List<LivroEntity> listarLivro() throws BusinessException{
		return new LivroBo().listarLivro();		
	}
	
	public void excluirLivro(Long codigoLivro) throws BusinessException {
		new LivroBo().excluirLivro(codigoLivro);
	}
	
	public LivroEntity buscarLivroPorId(Long codigoLivro) throws BusinessException{
		return new LivroBo().buscarLivroPorId(codigoLivro);	
	}
	
	public String editarLivro(LivroEntity livro) throws BusinessException{
		return new LivroBo().editarLivro(livro);
	}
	
	public List<LivroEntity> buscarLivroFiltrado(LivroEntity livro) throws BusinessException{
		return new LivroBo().buscarLivroFiltrado(livro);
	}
}
