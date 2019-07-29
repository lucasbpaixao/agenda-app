package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import business.ContatoBO;
import dao.ContatoDAO;
import vo.ContatoVO;

public class AgendaDeContatosController {

	public String excluir(ContatoVO contatoVO) throws SQLException, IOException {

		ContatoDAO contatoDAO = new ContatoDAO();

		return contatoDAO.excluir(contatoVO);
	}

	public boolean salvarContato(ContatoVO contatoVO) throws SQLException, IOException {
		boolean retorno = true;

		ContatoBO contatoBO = new ContatoBO();
		retorno = contatoBO.manterContato(contatoVO);

		return retorno;
	}

	public ContatoVO pesquisarContatoPorId(int id)
			throws SQLException, IOException {

		ContatoVO contatoVO = new ContatoVO();

		ContatoBO contatoBO = new ContatoBO();
		contatoVO = contatoBO.pesquisarContatoPorId(id);

		return contatoVO;
	}

	public List<ContatoVO> pesquisarContatoTodos(JTable tabela) throws SQLException, IOException {
		List<ContatoVO> retorno = new ArrayList<ContatoVO>();
		ContatoBO contatoBO = new ContatoBO();
		retorno = contatoBO.pesquisarContatoTodos(tabela);
		return retorno;
	}

	public boolean altera(ContatoVO contatoVO) throws SQLException, IOException {

		boolean retorno = true;

		ContatoBO contatoBO = new ContatoBO();
		retorno = contatoBO.alterar(contatoVO);

		return retorno;
	}

	public List<ContatoVO> pesquisarContatoPorNome(String nome) throws SQLException, IOException {

		List<ContatoVO> retorno = new ArrayList<ContatoVO>();
		
		ContatoVO contatoVO = new ContatoVO();

		ContatoBO contatoBO = new ContatoBO();
		retorno = contatoBO.pesquisarContatoPorNome(nome);

		return retorno;

	}

	public ContatoVO pesquisarContatoPorTelefone(String telefone) throws SQLException, IOException {
		ContatoVO contatoVO = new ContatoVO();

		ContatoBO contatoBO = new ContatoBO();
		contatoVO = contatoBO.pesquisarContatoPorTelefone(telefone);

		return contatoVO;

	}

}