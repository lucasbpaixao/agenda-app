package dao;

import java.io.IOException;
import java.sql.Connection;

//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import jdk.nashorn.internal.scripts.JO;
import view.AgendaView;
import vo.ContatoVO;

public class ContatoDAO {

	public boolean inserir(ContatoVO contatoVO) throws SQLException, IOException {
		boolean retorno = false;
		Connection connection = DAOFactory.connection();

		String comandoSql = buscaComandoInsert();

		PreparedStatement preparedStatement = connection.prepareStatement(comandoSql);

		preparedStatement.setInt(1, contatoVO.getId());
		preparedStatement.setString(2, contatoVO.getNome());
		preparedStatement.setString(3, contatoVO.getDdd());
		preparedStatement.setString(4, contatoVO.getTelefone());
		int cont = preparedStatement.executeUpdate();

		retorno = true;

		return retorno;
	}

	public String excluir(ContatoVO contatoVO) throws SQLException, IOException {

		String retorno = "Não foi possivel excluir o contato";
		Connection connection = DAOFactory.connection();

		String comandoSql = buscaComandoDelete();

		PreparedStatement preparedStatement = connection.prepareStatement(comandoSql);

		preparedStatement.setInt(1, contatoVO.getId());
		int cont = preparedStatement.executeUpdate();
		if (cont == 1) {
			retorno = "Contato Excluido com sucesso";
		}

		return retorno;
	}

	public ContatoVO pesquisarContatoPorId(int id)
			throws SQLException, IOException {

		ContatoVO contatoVO = new ContatoVO();

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

		} else {
			JOptionPane.showMessageDialog(null, "Contato não cadastrado");
		}

		return contatoVO;

	}

	public List<ContatoVO> pesquisarContatoTodos(JTable tabela) throws SQLException, IOException {
		List<ContatoVO> retorno = new ArrayList<ContatoVO>();

		int cont = 1;

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

			cont++;
		}

		if (retorno.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não Há Contatos Cadastrados", "Atenção", JOptionPane.WARNING_MESSAGE);
		}

		return retorno;

	}

	public boolean alter(ContatoVO contatoVO) throws SQLException, IOException {

		excluir(contatoVO);
		boolean alterou = inserir(contatoVO);

		if (alterou) {

			JOptionPane.showMessageDialog(null, "Contato Alterado Com Sucesso");
			return true;
		} else {

			return false;
		}

	}

	private String buscaComandoDelete() {

		return "DELETE FROM contato WHERE Id = ?";

	}

	private String buscaComandoInsert() {

		return "INSERT INTO contato (id, nome, ddd, telefone) VALUES (?,?,?,?)";
	}

	private String buscaComandoPesquisaId() {
		return "select id, nome, ddd, telefone from contato where id = ?";
	}

	private String buscaComandoPesquisaNome() {
		return "select id, nome, ddd, telefone from contato where nome = ?";
	}

	private String buscaComandoPesquisaTel() {
		return "select id, nome, ddd, telefone from contato where telefone = ?";
	}

	public List<ContatoVO> pesquisarContatoPorNome(String nome) throws SQLException, IOException {
		ContatoVO contatoVO = new ContatoVO();

		List<ContatoVO> retorno = new ArrayList<ContatoVO>();

		Connection connection = DAOFactory.connection();
		String comandoSQL = buscaComandoPesquisaNome();

		PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

		preparedStatement.setString(1, nome);

		ResultSet resultSet = preparedStatement.executeQuery();

		int cont = 0;

		while (resultSet.next()) {

			cont++;

			contatoVO = new ContatoVO();
			contatoVO.setId(resultSet.getInt("id"));
			contatoVO.setNome(resultSet.getString("nome"));
			contatoVO.setDdd(resultSet.getString("ddd"));
			contatoVO.setTelefone(resultSet.getString("telefone"));
			retorno.add(contatoVO);
		}

		if (cont == 0) {
			JOptionPane.showMessageDialog(null, "Contato não cadastrado");
		}

		return retorno;
	}

	public ContatoVO pesquisarContatoPorTelefone(String telefone) throws SQLException, IOException {

		ContatoVO contatoVO = new ContatoVO();

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

		} else {
			JOptionPane.showMessageDialog(null, "Contato não cadastrado");
		}

		return contatoVO;
	}

	public List<ContatoVO> validaDados() throws SQLException, IOException {

		List<ContatoVO> retorno = new ArrayList<ContatoVO>();

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

		}
		return retorno;

	}
}
