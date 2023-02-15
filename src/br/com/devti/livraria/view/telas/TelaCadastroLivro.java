package br.com.devti.livraria.view.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.devti.livraria.core.entity.ClienteEntity;
import br.com.devti.livraria.core.entity.LivroEntity;
import br.com.devti.livraria.core.service.ClienteService;
import br.com.devti.livraria.core.service.LivroService;
import br.com.devti.livraria.core.util.exception.BusinessException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaCadastroLivro extends JFrame {

	private JPanel contentPane;
	private JTextField fieldCodigo;
	private JTextField fieldNome;
	private JTextField fieldAutor;
	private JTextField fieldEditora;
	private JTextField fieldIsbn;
	private JTextField fieldValor;
	private JLabel labelTitulo;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroLivro frame = new TelaCadastroLivro();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroLivro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroLivro.class.getResource("/resource/open-book.png")));
		setTitle("Formulário de Livro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		labelTitulo = new JLabel("Cadastro de Livros");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel labelCodigo = new JLabel("Código");
		labelCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_3 = new JLabel("Autor");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel labelEditora = new JLabel("Editora");
		labelEditora.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel labelIsbn = new JLabel("ISBN");
		labelIsbn.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel labelValor = new JLabel("Valor");
		labelValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldCodigo = new JTextField();
		fieldCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		fieldCodigo.setEnabled(false);
		fieldCodigo.setEditable(false);
		fieldCodigo.setColumns(10);
		
		fieldNome = new JTextField();
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldNome.setColumns(10);
		
		fieldAutor = new JTextField();
		fieldAutor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldAutor.setColumns(10);
		
		fieldEditora = new JTextField();
		fieldEditora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldEditora.setColumns(10);
		
		fieldIsbn = new JTextField();
		fieldIsbn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldIsbn.setColumns(10);
		
		fieldValor = new JTextField();
		fieldValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldValor.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(TelaCadastroLivro.class.getResource("/resource/anterior.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarLivro tll = new TelaListarLivro();
				tll.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(TelaCadastroLivro.class.getResource("/resource/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LivroEntity livro = new LivroEntity();
				livro.setNome(fieldNome.getText());
				livro.setAutor(fieldAutor.getText());
				livro.setEditora(fieldEditora.getText());
				livro.setIsbn(fieldIsbn.getText());
				livro.setValor(fieldValor.getText());
				
				String msg = null;
				try {
					if(fieldCodigo.getText().equals("")) {
						msg = new LivroService().salvarLivro(livro);
					}else {
						livro.setCodigo(Long.parseLong(fieldCodigo.getText()));
						msg = new LivroService().editarLivro(livro);
					}
					limparCampos();
					JOptionPane.showMessageDialog(null, msg);
					dispose();
					
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(null, e1.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(178)
							.addComponent(btnVoltar)
							.addGap(146)
							.addComponent(btnSalvar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelNome, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelEditora, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(fieldEditora, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelIsbn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(fieldIsbn, 163, 163, 163))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelValor, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(fieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(227)
							.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(fieldCodigo, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(labelCodigo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(fieldAutor, Alignment.LEADING)
								.addComponent(fieldNome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))))
					.addContainerGap(112, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(labelTitulo)
					.addGap(18)
					.addComponent(labelCodigo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(labelNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fieldAutor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(labelEditora)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fieldEditora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(labelIsbn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fieldIsbn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(labelValor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnSalvar))
					.addGap(30))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void limparCampos() {
		fieldCodigo.setText("");
		fieldNome.setText("");
		fieldAutor.setText("");
		fieldEditora.setText("");
		fieldIsbn.setText("");
		fieldValor.setText("");
	}
	
	public void carregarLivroPorId(Long codigoLivro) {
		try {
			LivroEntity livroEncontrado = new LivroService().buscarLivroPorId(codigoLivro);
			if(livroEncontrado == null) {
				JOptionPane.showMessageDialog(null, "Livro não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
			}else {
				fieldCodigo.setText(""+livroEncontrado.getCodigo());
				fieldNome.setText(livroEncontrado.getNome());
				fieldAutor.setText(livroEncontrado.getAutor());
				fieldEditora.setText(livroEncontrado.getEditora());
				fieldIsbn.setText(livroEncontrado.getIsbn());
				fieldValor.setText(livroEncontrado.getValor());
			}
			
			labelTitulo.setText("Alteração de dados de Livro");
			
		} catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
