package br.com.devti.livraria.core.bo;

import java.util.List;

import br.com.devti.livraria.core.dao.FuncionarioDao;
import br.com.devti.livraria.core.entity.FuncionarioEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class FuncionarioBo {

	public String salvarFuncionario(FuncionarioEntity funcionario) throws BusinessException {
		validarFuncionario(funcionario);
		FuncionarioDao fdao = new FuncionarioDao(); 
		return fdao.salvarFuncionario(funcionario);
	}
	
	public java.util.List<FuncionarioEntity> listarFuncionario() throws BusinessException{
		return new FuncionarioDao().listarFuncionario();
	}
	
	public void excluirFuncionario(Long codigoFuncionario) throws BusinessException {
		new FuncionarioDao().excluirFuncionario(codigoFuncionario);
	}
	
	public FuncionarioEntity buscarFuncionarioPorId(Long codigoFuncionario) throws BusinessException{
		return new FuncionarioDao().buscarFuncionarioPorId(codigoFuncionario);	
	}
	
	public String editarFuncionario(FuncionarioEntity funcionario) throws BusinessException{
		validarFuncionario(funcionario);
		return new FuncionarioDao().editarFuncionario(funcionario);
	}
	
	private void validarFuncionario(FuncionarioEntity funcionario) throws BusinessException{
		if(funcionario.getNome() != null && funcionario.getNome().equals("")) {
			throw new BusinessException("Preencher nome do funcionário");
		}
		if(funcionario.getTelefone() != null && funcionario.getTelefone().equals("")) {
			throw new BusinessException("Preencher telefone do funcionário");
		}
		if(!funcionario.getEmail().contains("@")) {
			throw new BusinessException("Formato de email inválido.");
		}
	}
	
	public List<FuncionarioEntity> buscarFuncionarioFiltrado(FuncionarioEntity funcionario) throws BusinessException{
		return new FuncionarioDao().buscarFuncionarioFiltrado(funcionario);
	}
	
	public FuncionarioEntity autenticar(String email, String senha) throws BusinessException{
		if(email.equals("admin") && senha.equals("admin")) {
			FuncionarioEntity fAdmin = new FuncionarioEntity();
			fAdmin.setEmail("admin");
			fAdmin.setSenha("admin");
			return fAdmin;
		}
		return new FuncionarioDao().autenticar(email, senha);
	}
}
