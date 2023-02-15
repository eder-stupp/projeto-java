package br.com.devti.livraria.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.devti.livraria.core.dao.connection.ConexaoMySQL;
import br.com.devti.livraria.core.entity.FornecedorEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class FornecedorDao {
	
	public String salvarFornecedor(FornecedorEntity fornecedor) throws BusinessException{
		
		String sql = "INSERT INTO fornecedor (nome_fornecedor, cnpj_fornecedor, tel_fornecedor, end_fornecedor) VALUES (?,?,?,?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, fornecedor.getNome());
			ps.setString(2, fornecedor.getCnpj());
			ps.setString(3, fornecedor.getTelefone());
			ps.setString(4, fornecedor.getEndereco());
			ps.execute();
						
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao cadastrar fornecedor.");
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
	
	public java.util.List<FornecedorEntity> listarFornecedor() throws BusinessException{
		String sql = "SELECT id_fornecedor, nome_fornecedor, cnpj_fornecedor, tel_fornecedor, end_fornecedor FROM fornecedor";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<FornecedorEntity> fornecedor = new ArrayList<FornecedorEntity>();
		
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FornecedorEntity fol = new FornecedorEntity();
				fol.setCodigo(rs.getLong("id_fornecedor"));
				fol.setNome(rs.getString("nome_fornecedor"));
				fol.setCnpj(rs.getString("cnpj_fornecedor"));
				fol.setTelefone(rs.getString("tel_fornecedor"));
				fol.setEndereco(rs.getString("end_fornecedor"));
				fornecedor.add(fol);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao listar fornecedores.");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		return fornecedor;
	}
	
	public void excluirFornecedor(Long codigoFornecedor) throws BusinessException {
		String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoFornecedor);
			ps.execute();
			
		
		} catch (SQLException e) {
			throw new BusinessException("Não foi possível excluir fornecedor.");
		} finally {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public FornecedorEntity buscarFornecedorPorId(Long codigoFornecedor) throws BusinessException{
		String sql = "SELECT id_fornecedor, nome_fornecedor, cnpj_fornecedor, tel_fornecedor, end_fornecedor from fornecedor WHERE id_fornecedor = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoFornecedor);
			rs = ps.executeQuery();
			
			FornecedorEntity fornecedorEncontrado = null;
			
			if(rs.next()) {
				fornecedorEncontrado = new FornecedorEntity();
				fornecedorEncontrado.setCodigo(rs.getLong("id_fornecedor"));
				fornecedorEncontrado.setNome(rs.getString("nome_fornecedor"));
				fornecedorEncontrado.setCnpj(rs.getString("cnpj_fornecedor"));
				fornecedorEncontrado.setTelefone(rs.getString("tel_fornecedor"));
				fornecedorEncontrado.setEndereco(rs.getString("end_fornecedor"));
			}
			return fornecedorEncontrado;
						
		} catch (SQLException e) {
			throw new BusinessException("Erro ao buscar fornecedor.");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}						
		}
	}
	
	public String editarFornecedor(FornecedorEntity fornecedor) throws BusinessException{
		String sql = "UPDATE fornecedor SET nome_fornecedor = ?, cnpj_fornecedor = ?, tel_fornecedor = ?, end_fornecedor = ? WHERE id_fornecedor = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, fornecedor.getNome());
			ps.setString(2, fornecedor.getCnpj());
			ps.setString(3, fornecedor.getTelefone());
			ps.setString(4, fornecedor.getEndereco());
			ps.setLong(5, fornecedor.getCodigo());
			ps.execute();
			
		} catch (SQLException e) {
			throw new BusinessException("Erro ao atualizar dados do fornecedor.");
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Fornecedor editado com sucesso.";
	}
	
	public List<FornecedorEntity> buscarFornecedorFiltrado(FornecedorEntity fornecedor) throws BusinessException{
		String sql = "SELECT id_fornecedor, nome_fornecedor, cnpj_fornecedor, tel_fornecedor, end_fornecedor FROM fornecedor";
		List<FornecedorEntity> resultado = new ArrayList<FornecedorEntity>();
		boolean adicionaWhere = true;
		
		if(fornecedor != null) {
			if(fornecedor.getCodigo() != null) {
				adicionaWhere = false;
				sql += " WHERE ";
				sql += "id_fornecedor = ? ";
			}
			if(fornecedor.getNome() != null && !fornecedor.getNome().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += "nome_fornecedor LIKE ? ";
			}
			if(fornecedor.getCnpj() != null && !fornecedor.getCnpj().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += "cnpj_fornecedor LIKE ? ";
			}
		}
			
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			int indice = 0;
			
			if (fornecedor != null) {
				if(fornecedor.getCodigo() != null) {
					indice = indice + 1;
					ps.setLong(indice, fornecedor.getCodigo());
				}
				if (fornecedor.getNome() != null && !fornecedor.getNome().equals("")) {
					indice = indice + 1;
					ps.setString(indice, fornecedor.getNome() + "%");
				}
				if (fornecedor.getCnpj() != null && !fornecedor.getCnpj().equals("")) {
					indice = indice + 1;
					ps.setString(indice, fornecedor.getCnpj() + "%");
				}
			}
					
			rs = ps.executeQuery();
			while(rs.next()) {
				FornecedorEntity fornecedorResultado = new FornecedorEntity();
				fornecedorResultado.setCodigo(rs.getLong("id_fornecedor"));
				fornecedorResultado.setNome(rs.getString("nome_fornecedor"));
				fornecedorResultado.setCnpj(rs.getString("cnpj_fornecedor"));
				fornecedorResultado.setTelefone(rs.getString("tel_fornecedor"));
				fornecedorResultado.setEndereco(rs.getString("end_fornecedor"));
				resultado.add(fornecedorResultado);
			}
			
		} catch (SQLException e) {
			throw new BusinessException("Erro em buscar fornecedor.");
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
