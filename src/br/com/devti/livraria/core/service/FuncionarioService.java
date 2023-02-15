package br.com.devti.livraria.core.service;

import java.util.List;
import br.com.devti.livraria.core.bo.FuncionarioBo;
import br.com.devti.livraria.core.dao.FuncionarioDao;
import br.com.devti.livraria.core.entity.FuncionarioEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class FuncionarioService {

	public String salvarFuncionario(FuncionarioEntity funcionario) throws BusinessException {
		FuncionarioBo fbo = new FuncionarioBo();
		return fbo.salvarFuncionario(funcionario);
	}
	
	public List<FuncionarioEntity> listarFuncionario() throws BusinessException{
		return new FuncionarioBo().listarFuncionario();		
	}
	
	public void excluirFuncionario(Long codigoFuncionario) throws BusinessException{
		new FuncionarioBo().excluirFuncionario(codigoFuncionario);
	}
	
	public FuncionarioEntity buscarFuncionarioPorId(Long codigoFuncionario) throws BusinessException{
		return new FuncionarioBo().buscarFuncionarioPorId(codigoFuncionario);	
	}
	
	public String editarFuncionario(FuncionarioEntity funcionario) throws BusinessException{
		return new FuncionarioBo().editarFuncionario(funcionario);
	}
	
	public List<FuncionarioEntity> buscarFuncionarioFiltrado(FuncionarioEntity funcionario) throws BusinessException{
		return new FuncionarioBo().buscarFuncionarioFiltrado(funcionario);
	}
	
	public FuncionarioEntity autenticar(String email, String senha) throws BusinessException{
		return new FuncionarioBo().autenticar(email, senha);
	}	
}


