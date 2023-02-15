package br.com.devti.livraria.view.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import br.com.devti.livraria.core.entity.FuncionarioEntity;
import br.com.devti.livraria.core.service.FuncionarioService;
import br.com.devti.livraria.core.util.exception.BusinessException;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class TelaListarFuncionario extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<FuncionarioEntity> funcionario;
	private JTextField fieldCodigoFiltro;
	private JTextField fieldNomeFiltro;
	private JTextField fieldCpfFiltro;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaListarFuncionario frame = new TelaListarFuncionario();
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
	public TelaListarFuncionario() {
		setTitle("Lista de Funcionários");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaListarFuncionario.class.getResource("/resource/open-book.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioEntity funcionarioSelecionado = funcionario.get(table.getSelectedRow());
				TelaCadastroFuncionario tcf = new TelaCadastroFuncionario();
				tcf.carregarFuncionarioPorId(funcionarioSelecionado.getCodigo());
				tcf.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(TelaListarFuncionario.class.getResource("/resource/edit.png")));
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioEntity funcionarioSelecionado = funcionario.get(table.getSelectedRow());
				if(JOptionPane.showConfirmDialog(null, "Deseja excluir funcionario?") == JOptionPane.OK_OPTION) {
					try {
						new FuncionarioService().excluirFuncionario(funcionarioSelecionado.getCodigo());
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
		btnExcluir.setIcon(new ImageIcon(TelaListarFuncionario.class.getResource("/resource/delete.png")));
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroFuncionario tcu = new TelaCadastroFuncionario();
				tcu.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(TelaListarFuncionario.class.getResource("/resource/add.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldCodigoFiltro = new JTextField();
		fieldCodigoFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldCodigoFiltro.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldNomeFiltro = new JTextField();
		fieldNomeFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldNomeFiltro.setColumns(10);
		
		fieldCpfFiltro = new JTextField();
		fieldCpfFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldCpfFiltro.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Pesquisar");
		btnNewButton_1.setIcon(new ImageIcon(TelaListarFuncionario.class.getResource("/resource/documento.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioEntity funcionarioFiltro = new FuncionarioEntity();
				funcionarioFiltro.setNome(fieldNomeFiltro.getText());
				funcionarioFiltro.setCpf(fieldCpfFiltro.getText());
				
				try {
					if (!fieldCodigoFiltro.getText().equals("")) {
						Long codigo = Long.parseLong(fieldCodigoFiltro.getText());
						funcionarioFiltro.setCodigo(codigo);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "O campo código precisa ser numérico");
				}
				popularTabelaFiltrada(funcionarioFiltro);
				limparCampos();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldCodigoFiltro))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldNomeFiltro))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldCpfFiltro, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
					.addGap(103)
					.addComponent(btnNewButton_1)
					.addContainerGap(292, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
							.addComponent(btnEditar)
							.addGap(18)
							.addComponent(btnExcluir)))
					.addContainerGap())
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
						.addComponent(btnNewButton_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(fieldCpfFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir)
						.addComponent(btnEditar)
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnExcluir.setEnabled(true);
				btnEditar.setEnabled(true);
			}
			
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "CPF", "Telefone", "Endere\u00E7o", "Email", "Senha"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
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
				funcionario = new FuncionarioService().listarFuncionario();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector().removeAllElements();
				
				for (FuncionarioEntity funcionarioEntity : funcionario) {
					model.addRow(new Object[] {funcionarioEntity.getCodigo(),
											   funcionarioEntity.getNome(),
											   funcionarioEntity.getCpf(),
											   funcionarioEntity.getTelefone(),
											   funcionarioEntity.getEndereco(),
											   funcionarioEntity.getEmail(),
											   funcionarioEntity.getSenha()});
				}
				
			} catch (BusinessException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar funcionários");
		}
	}
		
		private void popularTabelaFiltrada(FuncionarioEntity funcionarioFiltro) {
			try {
				funcionario = new FuncionarioService().buscarFuncionarioFiltrado(funcionarioFiltro);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector().removeAllElements();
				
				for (FuncionarioEntity funcionarioEntity : funcionario) {
					model.addRow(new Object[] {funcionarioEntity.getCodigo(),
											   funcionarioEntity.getNome(),
											   funcionarioEntity.getCpf(),
											   funcionarioEntity.getTelefone(),
											   funcionarioEntity.getEndereco(),
											   funcionarioEntity.getSenha(),
											   funcionarioEntity.getSenha()});
				}
				
			} catch (BusinessException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar funcionários");
		}
	}
		
		private void limparCampos() {
			fieldCodigoFiltro.setText("");
			fieldNomeFiltro.setText("");
			fieldCpfFiltro.setText("");
		}
}
