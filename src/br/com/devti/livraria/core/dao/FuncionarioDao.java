package br.com.devti.livraria.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.devti.livraria.core.dao.connection.ConexaoMySQL;
import br.com.devti.livraria.core.entity.FuncionarioEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class FuncionarioDao {

	public String salvarFuncionario(FuncionarioEntity funcionario) throws BusinessException{
		
		String sql = "INSERT INTO funcionario (nome_funcionario, cpf_funcionario, tel_funcionario, end_funcionario, "
					 + "email_funcionario, senha_funcionario) VALUES (?,?,?,?,?,?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getCpf());
			ps.setString(3, funcionario.getTelefone());
			ps.setString(4, funcionario.getEndereco());
			ps.setString(5, funcionario.getEmail());
			ps.setString(6, funcionario.getSenha());
			ps.execute();
						
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao cadastrar funcionário.");
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
	
	public java.util.List<FuncionarioEntity> listarFuncionario() throws BusinessException{
		String sql = "SELECT id_funcionario, nome_funcionario, cpf_funcionario, tel_funcionario, end_funcionario, "
					 + "email_funcionario, senha_funcionario from funcionario";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<FuncionarioEntity> funcionario = new ArrayList<FuncionarioEntity>();
		
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FuncionarioEntity func = new FuncionarioEntity();
				func.setCodigo(rs.getLong("id_funcionario"));
				func.setNome(rs.getString("nome_funcionario"));
				func.setCpf(rs.getString("cpf_funcionario"));
				func.setTelefone(rs.getString("tel_funcionario"));
				func.setEndereco(rs.getString("end_funcionario"));
				func.setEmail(rs.getString("email_funcionario"));
				func.setSenha(rs.getString("senha_funcionario"));
				funcionario.add(func);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao listar funcionários.");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return funcionario;
	}
	
	public void excluirFuncionario(Long codigoFuncionario) throws BusinessException {
		String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoFuncionario);
			ps.execute();
			
		
		} catch (SQLException e) {
			throw new BusinessException("Não foi possível excluir funcionario.");
		} finally {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public FuncionarioEntity buscarFuncionarioPorId(Long codigoFuncionario) throws BusinessException{
		String sql = "SELECT id_funcionario, nome_funcionario, cpf_funcionario, tel_funcionario, end_funcionario, "
					 + "email_funcionario, senha_funcionario FROM funcionario WHERE id_funcionario = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoFuncionario);
			rs = ps.executeQuery();
			
			FuncionarioEntity funcionarioEncontrado = null;
			
			if(rs.next()) {
				funcionarioEncontrado = new FuncionarioEntity();
				funcionarioEncontrado.setCodigo(rs.getLong("id_funcionario"));
				funcionarioEncontrado.setNome(rs.getString("nome_funcionario"));
				funcionarioEncontrado.setCpf(rs.getString("cpf_funcionario"));
				funcionarioEncontrado.setTelefone(rs.getString("tel_funcionario"));
				funcionarioEncontrado.setEndereco(rs.getString("end_funcionario"));
				funcionarioEncontrado.setEmail(rs.getString("email_funcionario"));
				funcionarioEncontrado.setSenha(rs.getString("senha_funcionario"));
			}
			return funcionarioEncontrado;
						
		} catch (SQLException e) {
			throw new BusinessException("Erro ao buscar funcionário.");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}						
		}
	}
	
	public String editarFuncionario(FuncionarioEntity funcionario) throws BusinessException{
		String sql = "UPDATE funcionario SET nome_funcionario = ?, cpf_funcionario = ?, tel_funcionario = ?, end_funcionario = ?, "
					 + "email_funcionario = ?, senha_funcionario = ? WHERE id_funcionario = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getCpf());
			ps.setString(3, funcionario.getTelefone());
			ps.setString(4, funcionario.getEndereco());
			ps.setString(5, funcionario.getEmail());
			ps.setString(6, funcionario.getSenha());
			ps.setLong(7, funcionario.getCodigo());
			ps.execute();
			
		} catch (SQLException e) {
			throw new BusinessException("Erro ao atualizar dados do funcionário");
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Funcionário editado com sucesso.";
	}
	
	public List<FuncionarioEntity> buscarFuncionarioFiltrado(FuncionarioEntity funcionario) throws BusinessException{
		String sql = "SELECT id_funcionario, nome_funcionario, cpf_funcionario, tel_funcionario, end_funcionario,"
					 + "email_funcionario, senha_funcionario FROM funcionario";
		List<FuncionarioEntity> resultado = new ArrayList<FuncionarioEntity>();
		boolean adicionaWhere = true;
		
		if(funcionario != null) {
			if(funcionario.getCodigo() != null) {
				adicionaWhere = false;
				sql += " WHERE ";
				sql += "id_funcionario = ? ";
			}
			if(funcionario.getNome() != null && !funcionario.getNome().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += "nome_funcionario LIKE ? ";
			}
			if(funcionario.getCpf() != null && !funcionario.getCpf().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += "cpf_funcionario LIKE ? ";
			}
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			int indice = 0;
			
			if (funcionario != null) {
				if(funcionario.getCodigo() != null) {
					indice = indice + 1;
					ps.setLong(indice, funcionario.getCodigo());
				}
				if (funcionario.getNome() != null && !funcionario.getNome().equals("")) {
					indice = indice + 1;
					ps.setString(indice, funcionario.getNome() + "%");
				}
				if (funcionario.getCpf() != null && !funcionario.getCpf().equals("")) {
					indice = indice + 1;
					ps.setString(indice, funcionario.getCpf() + "%");
				}
			}
					
			rs = ps.executeQuery();
			while(rs.next()) {
				FuncionarioEntity funcionarioResultado = new FuncionarioEntity();
				funcionarioResultado.setCodigo(rs.getLong("id_funcionario"));
				funcionarioResultado.setNome(rs.getString("nome_funcionario"));
				funcionarioResultado.setCpf(rs.getString("cpf_funcionario"));
				funcionarioResultado.setTelefone(rs.getString("tel_funcionario"));
				funcionarioResultado.setEndereco(rs.getString("end_funcionario"));
				funcionarioResultado.setEmail(rs.getString("email_funcionario"));
				funcionarioResultado.setSenha(rs.getString("senha_funcionario"));
				resultado.add(funcionarioResultado);
			}
			
		} catch (SQLException e) {
			throw new BusinessException("Erro em buscar funcionário.");
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
	
	public FuncionarioEntity autenticar(String email, String senha) throws BusinessException{
		String sql = "SELECT id_funcionario, nome_funcionario, cpf_funcionario, tel_funcionario, end_funcionario,"
				 	 + "email_funcionario, senha_funcionario FROM funcionario WHERE email_funcionario = ? AND  senha_funcionario = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, senha);
			
			rs = ps.executeQuery();
			
			FuncionarioEntity funcionarioAutenticado = null;
			
			if(rs.next()) {
				funcionarioAutenticado = new FuncionarioEntity();
				funcionarioAutenticado.setCodigo(rs.getLong("id_funcionario"));
				funcionarioAutenticado.setNome(rs.getString("nome_funcionario"));
				funcionarioAutenticado.setCpf(rs.getString("cpf_funcionario"));
				funcionarioAutenticado.setTelefone(rs.getString("tel_funcionario"));
				funcionarioAutenticado.setEndereco(rs.getString("end_funcionario"));
				funcionarioAutenticado.setEmail(rs.getString("email_funcionario"));
				funcionarioAutenticado.setSenha(rs.getString("senha_funcionario"));
			}
			
			return funcionarioAutenticado;
			
		} catch (SQLException e) {
			throw new BusinessException("Erro ao tentar autenticar.");
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}