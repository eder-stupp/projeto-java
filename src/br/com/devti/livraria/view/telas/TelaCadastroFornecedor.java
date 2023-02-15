package br.com.devti.livraria.view.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import br.com.devti.livraria.core.entity.FornecedorEntity;
import br.com.devti.livraria.core.service.FornecedorService;
import br.com.devti.livraria.core.util.exception.BusinessException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaCadastroFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField fieldCodigo;
	private JTextField fieldNome;
	private JTextField fieldCnpj;
	private JTextField fieldTelefone;
	private JTextField fieldEndereco;
	private JLabel labelTitulo;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroFornecedor frame = new TelaCadastroFornecedor();
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
	public TelaCadastroFornecedor() {
		setTitle("Formulário de Fornecedor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroFornecedor.class.getResource("/resource/open-book.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 703, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		labelTitulo = new JLabel("Cadastro de Fornecedores");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel labelCodigo = new JLabel("Código");
		labelCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldCodigo = new JTextField();
		fieldCodigo.setEditable(false);
		fieldCodigo.setEnabled(false);
		fieldCodigo.setColumns(10);
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldNome = new JTextField();
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldNome.setColumns(10);
		
		JLabel labelCnpj = new JLabel("CNPJ");
		labelCnpj.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldCnpj = new JTextField();
		fieldCnpj.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldCnpj.setColumns(10);
		
		JLabel labelTelefone = new JLabel("Telefone");
		labelTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldTelefone = new JTextField();
		fieldTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldTelefone.setColumns(10);
		
		JLabel labelEndereco = new JLabel("Endereço");
		labelEndereco.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldEndereco = new JTextField();
		fieldEndereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldEndereco.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(TelaCadastroFornecedor.class.getResource("/resource/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorEntity fornecedor = new FornecedorEntity();
				fornecedor.setNome(fieldNome.getText());
				fornecedor.setCnpj(fieldCnpj.getText());
				fornecedor.setTelefone(fieldTelefone.getText());
				fornecedor.setEndereco(fieldEndereco.getText());
								
				String msg = null;
				try {
					if(fieldCodigo.getText().equals("")) {
						msg = new FornecedorService().salvarFornecedor(fornecedor);
					}else {
						fornecedor.setCodigo(Long.parseLong(fieldCodigo.getText()));
						msg = new FornecedorService().editarFornecedor(fornecedor);
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
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(TelaCadastroFornecedor.class.getResource("/resource/anterior.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarFornecedor tlfo = new TelaListarFornecedor();
				tlfo.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelCodigo, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelNome, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelCnpj, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(fieldCnpj, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelTelefone, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(fieldTelefone, 148, 148, 148))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelEndereco, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(173)
							.addComponent(btnVoltar)
							.addGap(149)
							.addComponent(btnSalvar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(211)
							.addComponent(labelTitulo, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(fieldEndereco, Alignment.LEADING)
								.addComponent(fieldNome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))))
					.addGap(35))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
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
					.addComponent(labelCnpj)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fieldCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(labelTelefone)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(labelEndereco)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fieldEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnSalvar))
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void limparCampos() {
		fieldCodigo.setText("");
		fieldNome.setText("");
		fieldEndereco.setText("");
		fieldCnpj.setText("");
		fieldTelefone.setText("");
	}
	
	public void carregarFornecedorPorId(Long codigoFornecedor) {
		try {
			FornecedorEntity fornecedorEncontrado = new FornecedorService().buscarFornecedorPorId(codigoFornecedor);
			if(fornecedorEncontrado == null) {
				JOptionPane.showMessageDialog(null, "Fornecedor não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
			}else {
				fieldCodigo.setText(""+fornecedorEncontrado.getCodigo());
				fieldNome.setText(fornecedorEncontrado.getNome());
				fieldCnpj.setText(fornecedorEncontrado.getCnpj());
				fieldTelefone.setText(fornecedorEncontrado.getTelefone());
				fieldEndereco.setText(fornecedorEncontrado.getEndereco());
			}
			
			labelTitulo.setText("Alteração de dados de Fornecedor");
			
		} catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
