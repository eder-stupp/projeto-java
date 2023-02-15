package br.com.devti.livraria.core.service;

import java.util.List;

import br.com.devti.livraria.core.bo.FornecedorBo;
import br.com.devti.livraria.core.entity.FornecedorEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class FornecedorService {
	
	public String salvarFornecedor(FornecedorEntity fornecedor) throws BusinessException {
		FornecedorBo fobo = new FornecedorBo();
		return fobo.salvarFornecedor(fornecedor);
	}
	
	public List<FornecedorEntity> listarFornecedor() throws BusinessException{
		return new FornecedorBo().listarFornecedor();		
	}
	
	public void excluirFornecedor(Long codigoFornecedor) throws BusinessException {
		new FornecedorBo().excluirFornecedor(codigoFornecedor);
	}
	
	public FornecedorEntity buscarFornecedorPorId(Long codigoFornecedor) throws BusinessException{
		return new FornecedorBo().buscarFornecedorPorId(codigoFornecedor);	
	}
	
	public String editarFornecedor(FornecedorEntity fornecedor) throws BusinessException{
		return new FornecedorBo().editarFornecedor(fornecedor);
	}
	
	public List<FornecedorEntity> buscarFornecedorFiltrado(FornecedorEntity fornecedor) throws BusinessException{
		return new FornecedorBo().buscarFornecedorFiltrado(fornecedor);
	}	
}
