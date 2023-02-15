package br.com.devti.livraria.core.bo;

import java.util.List;
import br.com.devti.livraria.core.dao.ClienteDao;
import br.com.devti.livraria.core.entity.ClienteEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class ClienteBo {

	public String salvarCliente(ClienteEntity cliente) throws BusinessException {
		validarCliente(cliente);
		ClienteDao cdao = new ClienteDao(); 
		return cdao.salvarCliente(cliente);		
	}
	
	public java.util.List<ClienteEntity> listarCliente() throws BusinessException{
		return new ClienteDao().listarCliente();
	}
	
	public void excluirCliente(Long codigoCliente) throws BusinessException {
		new ClienteDao().excluirCliente(codigoCliente);
	}
	
	public ClienteEntity buscarClientePorId(Long codigoCliente) throws BusinessException{
		return new ClienteDao().buscarClientePorId(codigoCliente);	
	}
	
	public String editarCliente(ClienteEntity cliente) throws BusinessException{
		validarCliente(cliente);
		return new ClienteDao().editarCliente(cliente);
	}
	
	private void validarCliente(ClienteEntity cliente) throws BusinessException{
		if(cliente.getNome() != null && cliente.getNome().equals("")) {
			throw new BusinessException("Preencher nome do cliente");
		}
		if(cliente.getTelefone() != null && cliente.getTelefone().equals("")) {
			throw new BusinessException("Preencher telefone do cliente");
		}
	}
	
	public List<ClienteEntity> buscarClienteFiltrado(ClienteEntity cliente) throws BusinessException{
		return new ClienteDao().buscarClienteFiltrado(cliente);
	}
}
