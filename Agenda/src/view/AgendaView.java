package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.AgendaDeContatosController;
import dao.ContatoDAO;
import vo.ContatoVO;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class AgendaView extends JFrame {

	public JPanel contentPane;
	public JTextField txtId;
	public JTextField txtNome;
	public JTextField txtDdd;
	public JTextField txtTelefone;
	public JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		JOptionPane.showMessageDialog(null,
				"Seja Bem vindo! (Este Código Também Está Disponivel Neste Link: https://github.com/LucasPaixao1/Agenda-de-contatos-app)");

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			System.err.println(ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendaView frame = new AgendaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgendaView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Agenda De Contatos");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 672, 25);
		panel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(258, 34, 154, 2);
		panel.add(separator);

		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(46, 102, 78, 14);
		panel.add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setBounds(118, 98, 51, 25);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblNome = new JLabel("NOME:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(46, 161, 78, 14);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(118, 157, 211, 25);
		panel.add(txtNome);

		JLabel lblDdd = new JLabel("DDD:");
		lblDdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDdd.setBounds(46, 230, 43, 14);
		panel.add(lblDdd);

		txtDdd = new JTextField();
		txtDdd.setColumns(10);
		txtDdd.setBounds(118, 226, 51, 25);
		panel.add(txtDdd);

		JLabel lblTelefone = new JLabel("TELEFONE:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefone.setBounds(46, 297, 78, 14);
		panel.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(118, 293, 134, 25);
		panel.add(txtTelefone);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "TODOS OS CONTATOS",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(4, 329, 658, 209);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 638, 160);
		panel_1.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "NOME", "DDD", "TELEFONE" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(132);
		table.getColumnModel().getColumn(3).setPreferredWidth(117);
		scrollPane.setViewportView(table);

		JButton btnListarTodosOs = new JButton("Listar Todos Os Contatos");
		btnListarTodosOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					listarTodos();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro interno: " + e1.getMessage(), "Atenção",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnListarTodosOs.setBounds(454, 11, 155, 23);
		panel_1.add(btnListarTodosOs);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "OP\u00C7\u00D5ES",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(509, 83, 108, 182);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JButton btnNewButton = new JButton("SALVAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					salvaDados();
					limpar();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro interno: " + e1.getMessage(), "Atenção",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});
		btnNewButton.setBounds(6, 16, 94, 23);
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("ALTERAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					altera();
					limpar();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro interno: " + e1.getMessage(), "Atenção",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(6, 50, 94, 23);
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("EXCLUIR");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					delete();
					limpar();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro interno: " + e1.getMessage(), "Atenção",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(6, 84, 94, 23);
		panel_2.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("PESQUISAR");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if ((!txtId.getText().equals("") && !txtNome.getText().equals(""))
							|| (!txtTelefone.getText().equals("") && !txtNome.getText().equals(""))
							|| (!txtTelefone.getText().equals("") && !txtId.getText().equals(""))) {

						JOptionPane.showMessageDialog(null, "Por Favor Insira Apenas um Atributo para Pesquisa",
								"Atenção", JOptionPane.ERROR_MESSAGE);

					} else if (!txtId.getText().equals("")) {
						pesquisarId();
					} else if (!txtNome.getText().equals("")) {
						pesquisarNome();
					} else if (!txtTelefone.getText().equals("")) {
						pesquisarTelefone();
					} else {
						JOptionPane.showMessageDialog(null, "Por Favor Insira Algum Dos Atributos Abaixo");
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro interno: " + e1.getMessage(), "Atenção",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnNewButton_3.setBounds(6, 118, 94, 23);
		panel_2.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("LIMPAR");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limpar();
			}

		});
		btnNewButton_4.setBounds(6, 152, 94, 23);
		panel_2.add(btnNewButton_4);
	}

	///////////////////////////// METODOS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void delete() throws HeadlessException, SQLException, IOException {
		ContatoVO contatoVO = new ContatoVO();

		int txt = Integer.parseInt(txtId.getText());
		contatoVO.setId(txt);

		AgendaDeContatosController contatoController = new AgendaDeContatosController();
		JOptionPane.showMessageDialog(null, contatoController.excluir(contatoVO));

	}

	private void salvaDados() throws SQLException, IOException {

		ContatoVO contatoVO = new ContatoVO();
		contatoVO = pegaDados();

		boolean salvou = false;
		AgendaDeContatosController contatoController = new AgendaDeContatosController();
		salvou = contatoController.salvarContato(contatoVO);

		if (salvou) {
			JOptionPane.showMessageDialog(null, "Contato Cadastrado com sucesso");
		}

	}

	private void altera() throws SQLException, IOException {
		ContatoVO contatoVO = new ContatoVO();

		contatoVO = pegaDados();

		AgendaDeContatosController agendaDeContatosController = new AgendaDeContatosController();

		agendaDeContatosController.altera(contatoVO);

	}

	private void listarTodos() throws SQLException, IOException {
		AgendaDeContatosController controller = new AgendaDeContatosController();
		List<ContatoVO> lista = new ArrayList<ContatoVO>();

		lista = controller.pesquisarContatoTodos(this.table);

		cleanTable(table);
		
		for (ContatoVO contatoVO : lista) {

			DefaultTableModel dados = (DefaultTableModel) table.getModel();

			String txt = Integer.toString(contatoVO.getId());

			dados.addRow(new String[] { txt, contatoVO.getNome(), contatoVO.getDdd(), contatoVO.getTelefone() });
			txtId.setText("");
			txtNome.setText("");
			txtDdd.setText("");
			txtTelefone.setText("");
			txtId.requestFocus();
		}
	}

	private void pesquisarId() throws SQLException, IOException {
		AgendaDeContatosController controller = new AgendaDeContatosController();
		ContatoVO contatoVO = new ContatoVO();
		int id = 0;

		Pattern patternNumeros = Pattern.compile("[0-9]");

		if (patternNumeros.matcher(txtId.getText()).find()) {
			id = Integer.parseInt(txtId.getText());
		}

		contatoVO = controller.pesquisarContatoPorId(id);
		cleanTable(table);
		
		addDadosTable(table, contatoVO);

	}

	private void pesquisarNome() throws SQLException, IOException {

		AgendaDeContatosController controller = new AgendaDeContatosController();
		List<ContatoVO> lista = new ArrayList<ContatoVO>();
		String nome = txtNome.getText();

		lista = controller.pesquisarContatoPorNome(nome);
		

		cleanTable(table);
		
		for (ContatoVO contatoVO : lista) {

			DefaultTableModel dados = (DefaultTableModel) table.getModel();

			String txt = Integer.toString(contatoVO.getId());

			dados.addRow(new String[] { txt, contatoVO.getNome(), contatoVO.getDdd(), contatoVO.getTelefone() });
			txtId.setText("");
			txtNome.setText("");
			txtDdd.setText("");
			txtTelefone.setText("");
			txtId.requestFocus();
		}

	}

	private void pesquisarTelefone() throws SQLException, IOException {

		AgendaDeContatosController controller = new AgendaDeContatosController();
		ContatoVO contatoVO = new ContatoVO();

		String telefone = txtTelefone.getText();

		contatoVO = controller.pesquisarContatoPorTelefone(telefone);
		
		cleanTable(table);
		
		addDadosTable(table, contatoVO);

	}

	private void limpar() {

		txtId.setText("");
		txtNome.setText("");
		txtDdd.setText("");
		txtTelefone.setText("");
		txtId.requestFocus();

	}

	private ContatoVO pegaDados() {
		ContatoVO contatoVO = new ContatoVO();

		int txt = 0;
		Pattern patternNumeros = Pattern.compile("[0-9]");

		if (patternNumeros.matcher(txtId.getText()).find()) {
			txt = Integer.parseInt(txtId.getText());
		} else {
			JOptionPane.showMessageDialog(null, "Favor Digite Apenas Números");
		}

		contatoVO.setId(txt);
		contatoVO.setNome(txtNome.getText());
		contatoVO.setDdd(txtDdd.getText());

		if (!patternNumeros.matcher(txtId.getText()).find()) {
			JOptionPane.showMessageDialog(null, "Favor Digite Apenas Números");
		}
		contatoVO.setTelefone(txtTelefone.getText());

		return contatoVO;
	}

	private void addDadosTable(JTable tabela, ContatoVO contatoVO) {

		DefaultTableModel dados = (DefaultTableModel) tabela.getModel();

		String txt = Integer.toString(contatoVO.getId());

		dados.addRow(new String[] { txt, contatoVO.getNome(), contatoVO.getDdd(), contatoVO.getTelefone() });
		txtId.setText("");
		txtNome.setText("");
		txtDdd.setText("");
		txtTelefone.setText("");
		txtId.requestFocus();

	}

	private void cleanTable(JTable tabela) {

		DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();

		dtm.setNumRows(0);

	}
}
