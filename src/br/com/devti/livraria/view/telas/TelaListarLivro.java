package br.com.devti.livraria.view.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import br.com.devti.livraria.core.entity.LivroEntity;
import br.com.devti.livraria.core.service.LivroService;
import br.com.devti.livraria.core.util.exception.BusinessException;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;

public class TelaListarLivro extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField fieldCodigoFiltro;
	private JTextField fieldNomeFiltro;
	private JTextField fieldAutorFiltro;
	private List<LivroEntity> livro;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaListarLivro frame = new TelaListarLivro();
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
	public TelaListarLivro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaListarLivro.class.getResource("/resource/open-book.png")));
		setTitle("Lista de Livros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(TelaListarLivro.class.getResource("/resource/add.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroLivro tcl = new TelaCadastroLivro();
				tcl.setVisible(true);
				dispose();
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(TelaListarLivro.class.getResource("/resource/edit.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LivroEntity livroSelecionado = livro.get(table.getSelectedRow());
				TelaCadastroLivro tcl = new TelaCadastroLivro();
				tcl.carregarLivroPorId(livroSelecionado.getCodigo());
				tcl.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(TelaListarLivro.class.getResource("/resource/delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LivroEntity livroSelecionado = livro.get(table.getSelectedRow());
				if(JOptionPane.showConfirmDialog(null, "Deseja excluir livro?") == JOptionPane.OK_OPTION) {
					try {
						new LivroService().excluirLivro(livroSelecionado.getCodigo());
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
		btnPesquisar.setIcon(new ImageIcon(TelaListarLivro.class.getResource("/resource/documento.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LivroEntity livroFiltro = new LivroEntity();
				livroFiltro.setNome(fieldNomeFiltro.getText());
				livroFiltro.setAutor(fieldAutorFiltro.getText());
				
				try {
					if (!fieldCodigoFiltro.getText().equals("")) {
						Long codigo = Long.parseLong(fieldCodigoFiltro.getText());
						livroFiltro.setCodigo(codigo);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "O campo código precisa ser numérico.");
				}
				popularTabelaFiltrada(livroFiltro);
				limparCampos();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Autor");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		fieldCodigoFiltro = new JTextField();
		fieldCodigoFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldCodigoFiltro.setColumns(10);
		
		fieldNomeFiltro = new JTextField();
		fieldNomeFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldNomeFiltro.setColumns(10);
		
		fieldAutorFiltro = new JTextField();
		fieldAutorFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fieldAutorFiltro.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 656, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(fieldCodigoFiltro, 0, 0, Short.MAX_VALUE)
								.addGap(451))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(fieldAutorFiltro, 160, 160, 160))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(fieldNomeFiltro, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
								.addGap(145)
								.addComponent(btnPesquisar)
								.addContainerGap(203, Short.MAX_VALUE)))))
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
						.addComponent(fieldAutorFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir)
						.addComponent(btnEditar)
						.addComponent(btnAdicionar))
					.addGap(20))
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
				"Código", "Nome", "Autor", "Editora", "ISBN", "Valor"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
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
			livro = new LivroService().listarLivro();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.getDataVector().removeAllElements();
			for (LivroEntity livroEntity : livro) {
				model.addRow(new Object[] {livroEntity.getCodigo(),
										   livroEntity.getNome(),
										   livroEntity.getAutor(),
										   livroEntity.getEditora(),
										   livroEntity.getIsbn(),
										   livroEntity.getValor()});
			}
		} catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar livro.");
		}
	}
	
		private void popularTabelaFiltrada(LivroEntity livroFiltro) {
			try {
				livro = new LivroService().buscarLivroFiltrado(livroFiltro);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector().removeAllElements();
			
				for (LivroEntity livroEntity : livro) {
					model.addRow(new Object[] {livroEntity.getCodigo(),
											   livroEntity.getNome(),
											   livroEntity.getAutor(),
											   livroEntity.getEditora(),
											   livroEntity.getIsbn(),
											   livroEntity.getValor()});
				}
			
			} catch (BusinessException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar livro.");
			}
		}
			
	private void limparCampos() {
		fieldCodigoFiltro.setText("");
		fieldNomeFiltro.setText("");
		fieldAutorFiltro.setText("");
	}
}
