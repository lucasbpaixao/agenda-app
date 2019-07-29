package dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdk.nashorn.internal.scripts.JO;
import view.AgendaView;
import vo.ContatoVO;

public class ContatoDAO {

	public boolean inserir(ContatoVO contatoVO) {
		boolean retorno = false;

		try {
			Connection connection = DAOFactory.connection();

			String comandoSql = buscaComandoInsert();

			PreparedStatement preparedStatement = connection.prepareStatement(comandoSql);

			preparedStatement.setInt(1, contatoVO.getId());
			preparedStatement.setString(2, contatoVO.getNome());
			preparedStatement.setString(3, contatoVO.getDdd());
			preparedStatement.setString(4, contatoVO.getTelefone());
			int cont = preparedStatement.executeUpdate();

			retorno = true;

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "ID ou Telefone duplicado");
		}
		return retorno;
	}

	public String excluir(ContatoVO contatoVO) {

		String retorno = "Contato não apagado";
		try {
			Connection connection = DAOFactory.connection();

			String comandoSql = buscaComandoDelete();

			PreparedStatement preparedStatement = connection.prepareStatement(comandoSql);

			preparedStatement.setInt(1, contatoVO.getId());
			int cont = preparedStatement.executeUpdate();
			if (cont == 1) {
				retorno = "Contato Apagado com sucesso";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public ContatoVO pesquisarContatoPorId(int id, String nome, String telefone, JTable tabela) {

		ContatoVO contatoVO = new ContatoVO();

		
		try {			
			Connection connection = DAOFactory.connection();
			String comandoSQL = buscaComandoPesquisaId();

			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			
			preparedStatement.setInt(1, id);
			
			
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				contatoVO.setId(resultSet.getInt("id"));
				contatoVO.setNome(resultSet.getString("nome"));
				contatoVO.setDdd(resultSet.getString("ddd"));
				contatoVO.setTelefone(resultSet.getString("telefone"));

				cleanTable(tabela);
				addDadosTable(tabela, contatoVO);

			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contatoVO;

	}

	public List<ContatoVO> pesquisarContatoTodos(JTable tabela) {
		List<ContatoVO> retorno = new ArrayList<ContatoVO>();
		try {
			int cont = 1;

			cleanTable(tabela);

			Connection connection = DAOFactory.connection();

			String comandoSQL = "select id, nome, ddd, telefone from contato";

			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ContatoVO contatoVO = new ContatoVO();
				contatoVO.setId(resultSet.getInt("id"));
				contatoVO.setNome(resultSet.getString("nome"));
				contatoVO.setDdd(resultSet.getString("ddd"));
				contatoVO.setTelefone(resultSet.getString("telefone"));
				retorno.add(contatoVO);

				addDadosTable(tabela, contatoVO);

				cont++;
			}

			if (retorno.isEmpty()) {

				JOptionPane.showMessageDialog(null, "Não Há Contatos Cadastrados", "Atenção",
						JOptionPane.WARNING_MESSAGE);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retorno;

	}

	public void addDadosTable(JTable tabela, ContatoVO contatoVO) {

		AgendaView av = new AgendaView();
		DefaultTableModel dados = (DefaultTableModel) tabela.getModel();

		String txt = Integer.toString(contatoVO.getId());

		dados.addRow(new String[] { txt, contatoVO.getNome(), contatoVO.getDdd(), contatoVO.getTelefone() });
		av.txtId.setText("");
		av.txtNome.setText("");
		av.txtDdd.setText("");
		av.txtTelefone.setText("");
		av.txtId.requestFocus();

	}

	public void cleanTable(JTable tabela) {

		DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();

		dtm.setNumRows(0);

	}

	public void alter(ContatoVO contatoVO) {

		excluir(contatoVO);
		boolean alterou = inserir(contatoVO);

		if (alterou) {

			JOptionPane.showMessageDialog(null, "Contato Alterado Com Sucesso");
		}

	}

	public String buscaComandoDelete() {

		return "DELETE FROM contato WHERE Id = ?";

	}

	public String buscaComandoInsert() {

		return "INSERT INTO contato (id, nome, ddd, telefone) VALUES (?,?,?,?)";
	}

	public String buscaComandoPesquisaId() {
		return "select id, nome, ddd, telefone from contato where id = ?";
	}
	
	public String buscaComandoPesquisaNome() {
		return "select id, nome, ddd, telefone from contato where nome = ?";
	}

	public String buscaComandoPesquisaTel() {
		return "select id, nome, ddd, telefone from contato where telefone = ?";
	}
	
	public String pesquisa(int id, String nome, String telefone) {
		String comando = buscaComandoPesquisaId();

		if (id != 0) {
			comando += " Where id = ?";

		} else if (!nome.equals("ç")) {
			
			comando += " WHERE nome = ?";
	
		}else if (!telefone.equals("9")) {
			comando += " WHERE telefone = ?";
		}

		return null;
	}
	
	public ContatoVO pesquisarContatoPorNome(String nome, JTable table) {
		ContatoVO contatoVO = new ContatoVO();

		cleanTable(table);
		
		try {			
			Connection connection = DAOFactory.connection();
			String comandoSQL = buscaComandoPesquisaNome();

			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			
			preparedStatement.setString(1, nome);
			
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				contatoVO = new ContatoVO();
				contatoVO.setId(resultSet.getInt("id"));
				contatoVO.setNome(resultSet.getString("nome"));
				contatoVO.setDdd(resultSet.getString("ddd"));
				contatoVO.setTelefone(resultSet.getString("telefone"));

				addDadosTable(table, contatoVO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contatoVO;
	}

	
	public ContatoVO pesquisarContatoPorTelefone(String telefone, JTable table) {
		
		ContatoVO contatoVO = new ContatoVO();
		
		try {			
			Connection connection = DAOFactory.connection();
			String comandoSQL = buscaComandoPesquisaTel();

			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			
			preparedStatement.setString(1, telefone);
			
			
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				
				contatoVO.setId(resultSet.getInt("id"));
				contatoVO.setNome(resultSet.getString("nome"));
				contatoVO.setDdd(resultSet.getString("ddd"));
				contatoVO.setTelefone(resultSet.getString("telefone"));

				cleanTable(table);
				addDadosTable(table, contatoVO);

			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contatoVO;
	}
}
