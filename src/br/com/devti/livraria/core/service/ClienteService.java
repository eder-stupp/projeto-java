package br.com.devti.livraria.core.service;

import java.util.List;
import br.com.devti.livraria.core.bo.ClienteBo;
import br.com.devti.livraria.core.entity.ClienteEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class ClienteService {

	public String salvarCliente(ClienteEntity cliente) throws BusinessException {
		ClienteBo cbo = new ClienteBo();
		return cbo.salvarCliente(cliente);
	}
	
	public List<ClienteEntity> listarCliente() throws BusinessException{
		return new ClienteBo().listarCliente();		
	}
	
	public void excluirCliente(Long codigoCliente) throws BusinessException {
		new ClienteBo().excluirCliente(codigoCliente);
	}
	
	public ClienteEntity buscarClientePorId(Long codigoCliente) throws BusinessException{
		return new ClienteBo().buscarClientePorId(codigoCliente);	
	}
	
	public String editarCliente(ClienteEntity cliente) throws BusinessException{
		return new ClienteBo().editarCliente(cliente);
	}
	
	public List<ClienteEntity> buscarClienteFiltrado(ClienteEntity cliente) throws BusinessException{
		return new ClienteBo().buscarClienteFiltrado(cliente);
	}	
}
