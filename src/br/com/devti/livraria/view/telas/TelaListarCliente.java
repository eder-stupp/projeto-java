package br.com.devti.livraria.view.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import br.com.devti.livraria.core.entity.ClienteEntity;
import br.com.devti.livraria.core.service.ClienteService;
import br.com.devti.livraria.core.util.exception.BusinessException;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaListarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField fieldCodigoFiltro;
	private JTextField fieldNomeFiltro;
	private JTextField fieldCpfFiltro;
	private JScrollPane scrollPane;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JTable table;
	private List<ClienteEntity> cliente;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaListarCliente frame = new TelaListarCliente();
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
	public TelaListarCliente() {
		setTitle("Lista de Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaListarCliente.class.getResource("/resource/open-book.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldCodigoFiltro = new JTextField();
		fieldCodigoFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldCodigoFiltro.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldNomeFiltro = new JTextField();
		fieldNomeFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldNomeFiltro.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldCpfFiltro = new JTextField();
		fieldCpfFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldCpfFiltro.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(TelaListarCliente.class.getResource("/resource/documento.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteEntity clienteFiltro = new ClienteEntity();
				clienteFiltro.setNome(fieldNomeFiltro.getText());
				clienteFiltro.setCpf(fieldCpfFiltro.getText());
				
				try {
					if (!fieldCodigoFiltro.getText().equals("")) {
						Long codigo = Long.parseLong(fieldCodigoFiltro.getText());
						clienteFiltro.setCodigo(codigo);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "O campo código precisa ser numérico.");
				}
				popularTabelaFiltrada(clienteFiltro);
				limparCampos();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		scrollPane = new JScrollPane();
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(TelaListarCliente.class.getResource("/resource/add.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente tcc = new TelaCadastroCliente();
				tcc.setVisible(true);
				dispose();
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(TelaListarCliente.class.getResource("/resource/edit.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteEntity clienteSelecionado = cliente.get(table.getSelectedRow());
				TelaCadastroCliente tcc = new TelaCadastroCliente();
				tcc.carregarClientePorId(clienteSelecionado.getCodigo());
				tcc.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(TelaListarCliente.class.getResource("/resource/delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteEntity clienteSelecionado = cliente.get(table.getSelectedRow());
				if(JOptionPane.showConfirmDialog(null, "Deseja excluir cliente?") == JOptionPane.OK_OPTION) {
					try {
						new ClienteService().excluirCliente(clienteSelecionado.getCodigo());
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 654, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnAdicionar)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnEditar)
								.addGap(18)
								.addComponent(btnExcluir)
								.addGap(40))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(fieldCodigoFiltro, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(fieldNomeFiltro, 147, 147, 147)
										.addGap(145)
										.addComponent(btnPesquisar))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(fieldCpfFiltro, 147, 147, 147)))
								.addGap(222)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
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
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fieldCpfFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEditar)
							.addComponent(btnExcluir))
						.addComponent(btnAdicionar))
					.addContainerGap())
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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Nome", "CPF", "Telefone", "Endereço", "Data de Nascimento", "Email"
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
			cliente = new ClienteService().listarCliente();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.getDataVector().removeAllElements();
			for (ClienteEntity clienteEntity : cliente) {
				model.addRow(new Object[] {clienteEntity.getCodigo(),
										   clienteEntity.getNome(),
										   clienteEntity.getCpf(),
										   clienteEntity.getTelefone(),
										   clienteEntity.getEndereco(),
										   clienteEntity.getDataNascimento(),
										   clienteEntity.getEmail()});
			}
		} catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar cliente.");
		}
	}
	
		private void popularTabelaFiltrada(ClienteEntity clienteFiltro) {
			try {
				cliente = new ClienteService().buscarClienteFiltrado(clienteFiltro);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector().removeAllElements();
			
				for (ClienteEntity clienteEntity : cliente) {
					model.addRow(new Object[] {clienteEntity.getCodigo(),
											   clienteEntity.getNome(),
										       clienteEntity.getCpf(),
										       clienteEntity.getTelefone(),
											   clienteEntity.getEndereco(),
											   clienteEntity.getDataNascimento(),
											   clienteEntity.getEmail()});
				}
			
			} catch (BusinessException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar cliente.");
			}
		}
			
	private void limparCampos() {
		fieldCodigoFiltro.setText("");
		fieldNomeFiltro.setText("");
		fieldCpfFiltro.setText("");
	}
}
