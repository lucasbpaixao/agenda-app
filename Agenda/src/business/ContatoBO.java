package business;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import dao.ContatoDAO;
import validator.ContatoValidator;
import vo.ContatoVO;

public class ContatoBO {

	public boolean manterContato(ContatoVO contatoVO) throws SQLException, IOException {

		boolean retorno = true;
		ContatoValidator contatoValidator = new ContatoValidator();
		retorno = contatoValidator.validarContato(contatoVO);

		if (retorno) {
			ContatoDAO contatoDAO = new ContatoDAO();
			retorno = contatoDAO.inserir(contatoVO);
		}

		return retorno;
	}

	public ContatoVO pesquisarContatoPorId(int id) throws SQLException, IOException {

		ContatoVO contatoVO = new ContatoVO();

		ContatoDAO contatoDAO = new ContatoDAO();
		contatoVO = contatoDAO.pesquisarContatoPorId(id);
		return contatoVO;
	}

	public List<ContatoVO> pesquisarContatoTodos(JTable tabela) throws SQLException, IOException {
		List<ContatoVO> retorno = new ArrayList<ContatoVO>();
		ContatoDAO contatoDAO = new ContatoDAO();
		retorno = contatoDAO.pesquisarContatoTodos(tabela);
		return retorno;
	}

	public List<ContatoVO> pesquisarContatoPorNome(String nome) throws SQLException, IOException {
		ContatoVO contatoVO = new ContatoVO();
		List<ContatoVO> retorno = new ArrayList<ContatoVO>();
		ContatoDAO contatoDAO = new ContatoDAO();
		retorno = contatoDAO.pesquisarContatoPorNome(nome);
		return retorno;
	}

	public ContatoVO pesquisarContatoPorTelefone(String telefone) throws SQLException, IOException {
		ContatoVO contatoVO = new ContatoVO();

		ContatoDAO contatoDAO = new ContatoDAO();
		contatoVO = contatoDAO.pesquisarContatoPorTelefone(telefone);
		return contatoVO;
	}

	public boolean alterar(ContatoVO contatoVO) throws SQLException, IOException {

		boolean retorno = true;
		ContatoValidator contatoValidator = new ContatoValidator();
		retorno = contatoValidator.alterarContato(contatoVO);
		if (retorno) {
			ContatoDAO contatoDAO = new ContatoDAO();
			retorno = (boolean) contatoDAO.alter(contatoVO);
		}
		return retorno;

	}

}