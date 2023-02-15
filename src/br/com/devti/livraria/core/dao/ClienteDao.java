package br.com.devti.livraria.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.devti.livraria.core.dao.connection.ConexaoMySQL;
import br.com.devti.livraria.core.entity.ClienteEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class ClienteDao {

	public String salvarCliente(ClienteEntity cliente) throws BusinessException{
		
		String sql = "INSERT INTO cliente (nome_cliente, cpf_cliente, tel_cliente, end_cliente, nasc_cliente, email_cliente) VALUES (?,?,?,?,?,?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getTelefone());
			ps.setString(4, cliente.getEndereco());
			ps.setString(5, cliente.getDataNascimento());
			ps.setString(6, cliente.getEmail());
			ps.execute();
						
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao cadastrar cliente.");
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "Cadastro realizado com sucesso!";		
	}
	
	public java.util.List<ClienteEntity> listarCliente() throws BusinessException{
		String sql = "SELECT id_cliente, nome_cliente, cpf_cliente, tel_cliente, end_cliente, nasc_cliente, email_cliente FROM cliente";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ClienteEntity> cliente = new ArrayList<ClienteEntity>();
		
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ClienteEntity cl = new ClienteEntity();
				cl.setCodigo(rs.getLong("id_cliente"));
				cl.setNome(rs.getString("nome_cliente"));
				cl.setCpf(rs.getString("cpf_cliente"));
				cl.setTelefone(rs.getString("tel_cliente"));
				cl.setEndereco(rs.getString("end_cliente"));
				cl.setDataNascimento(rs.getString("nasc_cliente"));
				cl.setEmail(rs.getString("email_cliente"));
				cliente.add(cl);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao listar clientes.");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return cliente;
	}
	
	public void excluirCliente(Long codigoCliente) throws BusinessException {
		String sql = "DELETE FROM cliente WHERE id_cliente = ?";
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoCliente);
			ps.execute();
			
		
		} catch (SQLException e) {
			throw new BusinessException("Não foi possível excluir cliente.");
		} finally {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ClienteEntity buscarClientePorId(Long codigoCliente) throws BusinessException{
		String sql = "SELECT id_cliente, nome_cliente, cpf_cliente, tel_cliente, end_cliente, nasc_cliente, email_cliente from cliente WHERE id_cliente = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoCliente);
			rs = ps.executeQuery();
			
			ClienteEntity clienteEncontrado = null;
			
			if(rs.next()) {
				clienteEncontrado = new ClienteEntity();
				clienteEncontrado.setCodigo(rs.getLong("id_cliente"));
				clienteEncontrado.setNome(rs.getString("nome_cliente"));
				clienteEncontrado.setCpf(rs.getString("cpf_cliente"));
				clienteEncontrado.setTelefone(rs.getString("tel_cliente"));
				clienteEncontrado.setEndereco(rs.getString("end_cliente"));
				clienteEncontrado.setDataNascimento(rs.getString("nasc_cliente"));
				clienteEncontrado.setEmail(rs.getString("email_cliente"));
			}
			return clienteEncontrado;
						
		} catch (SQLException e) {
			throw new BusinessException("Erro ao buscar cliente.");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}						
		}
	}
	
	public String editarCliente(ClienteEntity cliente) throws BusinessException{
		String sql = "UPDATE cliente SET nome_cliente = ?, cpf_cliente = ?, tel_cliente = ?, end_cliente = ?, nasc_cliente = ?, "
					 + "email_cliente = ? WHERE id_cliente = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getTelefone());
			ps.setString(4, cliente.getEndereco());
			ps.setString(5, cliente.getDataNascimento());
			ps.setString(6, cliente.getEmail());
			ps.setLong(7, cliente.getCodigo());
			ps.execute();
			
		} catch (SQLException e) {
			throw new BusinessException("Erro ao atualizar dados do cliente.");
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Cliente editado com sucesso.";
	}
	
	public List<ClienteEntity> buscarClienteFiltrado(ClienteEntity cliente) throws BusinessException{
		String sql = "SELECT id_cliente, nome_cliente, cpf_cliente, tel_cliente, end_cliente, nasc_cliente, email_cliente FROM cliente";
		List<ClienteEntity> resultado = new ArrayList<ClienteEntity>();
		boolean adicionaWhere = true;
		
		if(cliente != null) {
			if(cliente.getCodigo() != null) {
				adicionaWhere = false;
				sql += " WHERE ";
				sql += "id_cliente = ? ";
			}
			if(cliente.getNome() != null && !cliente.getNome().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += "nome_cliente LIKE ? ";
			}
			if(cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += "cpf_cliente LIKE ? ";
			}
		}
			
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			int indice = 0;
			
			if (cliente != null) {
				if(cliente.getCodigo() != null) {
					indice = indice + 1;
					ps.setLong(indice, cliente.getCodigo());
				}
				if (cliente.getNome() != null && !cliente.getNome().equals("")) {
					indice = indice + 1;
					ps.setString(indice, cliente.getNome() + "%");
				}
				if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
					indice = indice + 1;
					ps.setString(indice, cliente.getCpf() + "%");
				}
			}
					
			rs = ps.executeQuery();
			while(rs.next()) {
				ClienteEntity clienteResultado = new ClienteEntity();
				clienteResultado.setCodigo(rs.getLong("id_cliente"));
				clienteResultado.setNome(rs.getString("nome_cliente"));
				clienteResultado.setCpf(rs.getString("cpf_cliente"));
				clienteResultado.setTelefone(rs.getString("tel_cliente"));
				clienteResultado.setEndereco(rs.getString("end_cliente"));
				clienteResultado.setDataNascimento(rs.getString("nasc_cliente"));
				clienteResultado.setEmail(rs.getString("email_cliente"));
				resultado.add(clienteResultado);
			}
			
		} catch (SQLException e) {
			throw new BusinessException("Erro em buscar cliente.");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}
}
