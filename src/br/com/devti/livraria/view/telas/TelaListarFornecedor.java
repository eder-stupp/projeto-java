package br.com.devti.livraria.view.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import br.com.devti.livraria.core.entity.FornecedorEntity;
import br.com.devti.livraria.core.service.FornecedorService;
import br.com.devti.livraria.core.util.exception.BusinessException;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaListarFornecedor extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField fieldCodigoFiltro;
	private JTextField fieldNomeFiltro;
	private JTextField fieldCnpjFiltro;
	private List<FornecedorEntity> fornecedor;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaListarFornecedor frame = new TelaListarFornecedor();
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
	public TelaListarFornecedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaListarFornecedor.class.getResource("/resource/open-book.png")));
		setTitle("Lista de Fornecedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(TelaListarFornecedor.class.getResource("/resource/edit.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorEntity fornecedorSelecionado = fornecedor.get(table.getSelectedRow());
				TelaCadastroFornecedor tcfo = new TelaCadastroFornecedor();
				tcfo.carregarFornecedorPorId(fornecedorSelecionado.getCodigo());
				tcfo.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(TelaListarFornecedor.class.getResource("/resource/add.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroFornecedor tcfo = new TelaCadastroFornecedor();
				tcfo.setVisible(true);
				dispose();
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(TelaListarFornecedor.class.getResource("/resource/delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorEntity fornecedorSelecionado = fornecedor.get(table.getSelectedRow());
				if(JOptionPane.showConfirmDialog(null, "Deseja excluir fornecedor?") == JOptionPane.OK_OPTION) {
					try {
						new FornecedorService().excluirFornecedor(fornecedorSelecionado.getCodigo());
						popularTabela();
					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}
				btnExcluir.setEnabled(false);
				btnEditar.setEnabled(false);
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(TelaListarFornecedor.class.getResource("/resource/documento.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorEntity fornecedorFiltro = new FornecedorEntity();
				fornecedorFiltro.setNome(fieldNomeFiltro.getText());
				fornecedorFiltro.setCnpj(fieldCnpjFiltro.getText());
				
				try {
					if (!fieldCodigoFiltro.getText().equals("")) {
						Long codigo = Long.parseLong(fieldCodigoFiltro.getText());
						fornecedorFiltro.setCodigo(codigo);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "O campo código precisa ser numérico.");
				}
				popularTabelaFiltrada(fornecedorFiltro);
				limparCampos();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("CNPJ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldCodigoFiltro = new JTextField();
		fieldCodigoFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldCodigoFiltro.setColumns(10);
		
		fieldNomeFiltro = new JTextField();
		fieldNomeFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldNomeFiltro.setColumns(10);
		
		fieldCnpjFiltro = new JTextField();
		fieldCnpjFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldCnpjFiltro.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 654, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnAdicionar)
								.addPreferredGap(ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
								.addComponent(btnEditar)
								.addGap(18)
								.addComponent(btnExcluir)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(fieldCnpjFiltro, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(fieldNomeFiltro, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(fieldCodigoFiltro, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
								.addGap(139)
								.addComponent(btnPesquisar)
								.addGap(208)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(fieldCodigoFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(fieldNomeFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(fieldCnpjFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionar)
						.addComponent(btnExcluir)
						.addComponent(btnEditar))
					.addGap(20))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnExcluir.setEnabled(true);
				btnEditar.setEnabled(true);
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Nome", "CNPJ", "Telefone", "Endereço"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		popularTabela();
	}
	
	private void popularTabela() {
		try {
			fornecedor = new FornecedorService().listarFornecedor();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.getDataVector().removeAllElements();
			for (FornecedorEntity fornecedorEntity : fornecedor) {
				model.addRow(new Object[] {fornecedorEntity.getCodigo(),
										   fornecedorEntity.getNome(),
										   fornecedorEntity.getCnpj(),
										   fornecedorEntity.getTelefone(),
										   fornecedorEntity.getEndereco()});
			}
		} catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar fornecedor.");
		}
	}
	
		private void popularTabelaFiltrada(FornecedorEntity fornecedorFiltro) {
			try {
				fornecedor = new FornecedorService().buscarFornecedorFiltrado(fornecedorFiltro);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector().removeAllElements();
			
				for (FornecedorEntity fornecedorEntity : fornecedor) {
					model.addRow(new Object[] {fornecedorEntity.getCodigo(),
											   fornecedorEntity.getNome(),
											   fornecedorEntity.getCnpj(),
											   fornecedorEntity.getTelefone(),
											   fornecedorEntity.getEndereco()});
				}
			
			} catch (BusinessException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar fornecedor.");
			}
		}
			
	private void limparCampos() {
		fieldCodigoFiltro.setText("");
		fieldNomeFiltro.setText("");
		fieldCnpjFiltro.setText("");
	}
}
