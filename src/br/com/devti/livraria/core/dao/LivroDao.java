package br.com.devti.livraria.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.devti.livraria.core.dao.connection.ConexaoMySQL;
import br.com.devti.livraria.core.entity.LivroEntity;
import br.com.devti.livraria.core.util.exception.BusinessException;

public class LivroDao {
	
	public String salvarLivro(LivroEntity livro) throws BusinessException{
		
		String sql = "INSERT INTO livro (nome_livro, autor_livro, editora_livro, isbn_livro, valor_livro) VALUES (?,?,?,?,?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, livro.getNome());
			ps.setString(2, livro.getAutor());
			ps.setString(3, livro.getEditora());
			ps.setString(4, livro.getIsbn());
			ps.setString(5, livro.getValor());
			ps.execute();
						
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao cadastrar livro.");
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
	
	public java.util.List<LivroEntity> listarLivro() throws BusinessException{
		String sql = "SELECT id_livro, nome_livro, autor_livro, editora_livro, isbn_livro, valor_livro FROM livro";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LivroEntity> livro = new ArrayList<LivroEntity>();
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				LivroEntity ll = new LivroEntity();
				ll.setCodigo(rs.getLong("id_livro"));
				ll.setNome(rs.getString("nome_livro"));
				ll.setAutor(rs.getString("autor_livro"));
				ll.setEditora(rs.getString("editora_livro"));
				ll.setIsbn(rs.getString("isbn_livro"));
				ll.setValor(rs.getString("valor_livro"));
				livro.add(ll);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao listar livros.");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return livro;
	}
	
	public void excluirLivro(Long codigoLivro) throws BusinessException {
		String sql = "DELETE FROM livro WHERE id_livro = ?";
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoLivro);
			ps.execute();
				
		} catch (SQLException e) {
			throw new BusinessException("Não foi possível excluir livro.");
		} finally {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public LivroEntity buscarLivroPorId(Long codigoLivro) throws BusinessException{
		String sql = "SELECT id_livro, nome_livro, autor_livro, editora_livro, isbn_livro, valor_livro from livro WHERE id_livro = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoLivro);
			rs = ps.executeQuery();
			
			LivroEntity livroEncontrado = null;
			
			if(rs.next()) {
				livroEncontrado = new LivroEntity();
				livroEncontrado.setCodigo(rs.getLong("id_livro"));
				livroEncontrado.setNome(rs.getString("nome_livro"));
				livroEncontrado.setAutor(rs.getString("autor_livro"));
				livroEncontrado.setEditora(rs.getString("editora_livro"));
				livroEncontrado.setIsbn(rs.getString("isbn_livro"));
				livroEncontrado.setValor(rs.getString("valor_livro"));
			}
			return livroEncontrado;
						
		} catch (SQLException e) {
			throw new BusinessException("Erro ao buscar livro.");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}						
		}
	}
	
	public String editarLivro(LivroEntity livro) throws BusinessException{
		String sql = "UPDATE livro SET nome_livro = ?, autor_livro = ?, editora_livro = ?, isbn_livro = ?, valor_livro = ? WHERE id_livro = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, livro.getNome());
			ps.setString(2, livro.getAutor());
			ps.setString(3, livro.getEditora());
			ps.setString(4, livro.getIsbn());
			ps.setString(5, livro.getValor());
			ps.setLong(6, livro.getCodigo());	
			ps.execute();
			
		} catch (SQLException e) {
			throw new BusinessException("Erro ao atualizar dados do livro.");
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Livro editado com sucesso.";
	}
	
	public List<LivroEntity> buscarLivroFiltrado(LivroEntity livro) throws BusinessException{
		String sql = "SELECT id_livro, nome_livro, autor_livro, editora_livro, isbn_livro, valor_livro FROM livro";
		List<LivroEntity> resultado = new ArrayList<LivroEntity>();
		boolean adicionaWhere = true;
		
		if(livro != null) {
			if(livro.getCodigo() != null) {
				adicionaWhere = false;
				sql += " WHERE ";
				sql += "id_livro = ? ";
			}
			if(livro.getNome() != null && !livro.getNome().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += "nome_livro LIKE ? ";
			}
			if(livro.getAutor() != null && !livro.getAutor().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += "autor_livro LIKE ? ";
			}
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			int indice = 0;
			
			if (livro != null) {
				if(livro.getCodigo() != null) {
					indice = indice + 1;
					ps.setLong(indice, livro.getCodigo());
				}
				if (livro.getNome() != null && !livro.getNome().equals("")) {
					indice = indice + 1;
					ps.setString(indice, livro.getNome() + "%");
				}
				if (livro.getAutor() != null && !livro.getAutor().equals("")) {
					indice = indice + 1;
					ps.setString(indice, livro.getAutor() + "%");
				}
			}
					
			rs = ps.executeQuery();
			while(rs.next()) {
				LivroEntity livroResultado = new LivroEntity();
				livroResultado.setCodigo(rs.getLong("id_livro"));
				livroResultado.setNome(rs.getString("nome_livro"));
				livroResultado.setAutor(rs.getString("autor_livro"));
				livroResultado.setEditora(rs.getString("editora_livro"));
				livroResultado.setIsbn(rs.getString("isbn_livro"));
				livroResultado.setValor(rs.getString("valor_livro"));
				resultado.add(livroResultado);	
			}
			
		} catch (SQLException e) {
			throw new BusinessException("Erro em buscar livro.");
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
