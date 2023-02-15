package br.com.devti.livraria.core.bo;

import java.util.List;

import br.com.devti.livraria.core.dao.FornecedorDao;
import br.com.devti.livraria.core.entity.FornecedorEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class FornecedorBo {
	
public String salvarFornecedor(FornecedorEntity fornecedor) throws BusinessException {
		validarFornecedor(fornecedor);
		FornecedorDao fodao = new FornecedorDao(); 
		return fodao.salvarFornecedor(fornecedor);		
	}
	
	public java.util.List<FornecedorEntity> listarFornecedor() throws BusinessException{
		return new FornecedorDao().listarFornecedor();
	}
	
	public void excluirFornecedor(Long codigoFornecedor) throws BusinessException {
		new FornecedorDao().excluirFornecedor(codigoFornecedor);
	}
	
	public FornecedorEntity buscarFornecedorPorId(Long codigoFornecedor) throws BusinessException{
		return new FornecedorDao().buscarFornecedorPorId(codigoFornecedor);	
	}
	
	public String editarFornecedor(FornecedorEntity fornecedor) throws BusinessException{
		validarFornecedor(fornecedor);
		return new FornecedorDao().editarFornecedor(fornecedor);
	}
	
	private void validarFornecedor(FornecedorEntity fornecedor) throws BusinessException{
		if(fornecedor.getNome() != null && fornecedor.getNome().equals("")) {
			throw new BusinessException("Preencher nome do fornecedor.");
		}
		if(fornecedor.getTelefone() != null && fornecedor.getTelefone().equals("")) {
			throw new BusinessException("Preencher telefone do fornecedor.");
		}
	}
	
	public List<FornecedorEntity> buscarFornecedorFiltrado(FornecedorEntity fornecedor) throws BusinessException{
		return new FornecedorDao().buscarFornecedorFiltrado(fornecedor);
	}
}
