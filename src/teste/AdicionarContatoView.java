package teste;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AgendaDeContatosController;
import vo.ContatoVO;

import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdicionarContatoView extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtDdd;
	private JTextField txtTel;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
           System.err.println(ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarContatoView frame = new AdicionarContatoView();
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
	public AdicionarContatoView() {
		setTitle("Cadastrar Contato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 361);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtId = new JTextField();
		txtId.setBounds(64, 51, 38, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(64, 82, 154, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtDdd = new JTextField();
		txtDdd.setBounds(273, 51, 38, 20);
		contentPane.add(txtDdd);
		txtDdd.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setBounds(273, 82, 154, 20);
		contentPane.add(txtTel);
		txtTel.setColumns(10);
		
		JButton btnsalvar = new JButton("Salvar");
		btnsalvar.setBounds(522, 134, 89, 23);
		btnsalvar.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				

				
			}
		});
		contentPane.add(btnsalvar);
		
		JLabel ID = new JLabel("ID");
		ID.setBounds(10, 54, 46, 14);
		ID.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(ID);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(10, 85, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DDD");
		lblNewLabel_2.setBounds(235, 54, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Telefone");
		lblNewLabel_3.setBounds(228, 85, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(522, 289, 89, 23);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		contentPane.add(btnSair);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(522, 189, 89, 23);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()!=-1){
					
					table.setValueAt(txtId.getText(), table.getSelectedRow(), 0);
					table.setValueAt(txtNome.getText(), table.getSelectedRow(),1);
					table.setValueAt(txtDdd.getText(), table.getSelectedRow(),3);
					table.setValueAt(txtTel.getText(), table.getSelectedRow(),2);
				}	
				
			}
		});
		contentPane.add(btnAlterar);
		
		JScrollPane jtcontatos = new JScrollPane();
		jtcontatos.setBounds(0, 113, 484, 210);
		jtcontatos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(table.getSelectedRow()!=-1){
						
						txtId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
						txtNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
						txtDdd.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
						txtTel.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					}
			}
		});
		jtcontatos.addMouseListener(new MouseAdapter() {
			
		});
		contentPane.add(jtcontatos);
		
		table = new JTable();
		jtcontatos.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "DDD", "Telefone"
			}
		));
		
		JLabel lblAgenda = new JLabel("Agenda");
		lblAgenda.setBounds(252, 11, 61, 14);
		lblAgenda.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblAgenda);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(512, 0, 0, 323);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(512, 0, 0, 323);
		separator_1.setBackground(SystemColor.textHighlight);
		contentPane.add(separator_1);
		
		JButton btndeletar = new JButton("Deletar");
		btndeletar.setBounds(522, 240, 89, 23);
		btndeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
				
				
				
				
			}
		});
		contentPane.add(btndeletar);
	}
}
