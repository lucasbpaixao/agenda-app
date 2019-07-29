package validator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ContatoDAO;
import vo.ContatoVO;

public class ContatoValidator {

	public boolean validarContato(ContatoVO contatoVO) throws SQLException, IOException {

		boolean retorno = true;

		List<ContatoVO> lista = new ArrayList<ContatoVO>();

		ContatoDAO contatoDAO = new ContatoDAO();

		lista = contatoDAO.validaDados();

		for (ContatoVO VO : lista) {

			if (VO.getId() == contatoVO.getId()) {
				retorno = false;
				JOptionPane.showMessageDialog(null, "ID Duplicado");
				break;
			}

		}

		lista = contatoDAO.validaDados();

		for (ContatoVO VO : lista) {

			if (VO.getTelefone().equals(contatoVO.getTelefone())) {
				JOptionPane.showMessageDialog(null, "Telefone Duplicado");
				retorno = false;
				break;
			}

		}

		if (contatoVO.getId() <= 0) {
			JOptionPane.showMessageDialog(null, "Favor informar um ID maior que 0.");
			retorno = false;
		}

		if (contatoVO.getNome().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "O nome do contato não pode ser nulo.");
			retorno = false;
		}

		if (contatoVO.getNome().equals("")) {
			JOptionPane.showMessageDialog(null, "Favor informar o nome do contato.");
			retorno = false;
		}

		if (contatoVO.getDdd() == null || contatoVO.getDdd().trim().equals("") || contatoVO.getDdd().length() != 3) {
			JOptionPane.showMessageDialog(null, "Favor informar um DDD válido.");
			retorno = false;
		}

		if (contatoVO.getTelefone() == null || contatoVO.getTelefone().trim().equals("")
				|| (contatoVO.getTelefone().length() != 8 && contatoVO.getTelefone().length() != 9)) {
			JOptionPane.showMessageDialog(null, "Favor informar um telefone válido.");
			retorno = false;

		}

		return retorno;
	}

	public boolean alterarContato(ContatoVO contatoVO) throws SQLException, IOException {

		boolean retorno = true;

		List<ContatoVO> lista = new ArrayList<ContatoVO>();

		ContatoDAO contatoDAO = new ContatoDAO();

		lista = contatoDAO.validaDados();

		for (ContatoVO VO : lista) {

			if (VO.getId() != contatoVO.getId()) {
				retorno = false;
			} else {
				retorno = true;
				break;
			}

		}
		
		if (retorno == false) {

			JOptionPane.showMessageDialog(null, "ID Não Existente");

		}

		lista = contatoDAO.validaDados();

		for (ContatoVO VO : lista) {
			if (VO.getTelefone().equals(contatoVO.getTelefone())) {
				JOptionPane.showMessageDialog(null, "Telefone Duplicado");
				retorno = false;
				break;
			}

		}
		
		lista = contatoDAO.validaDados();

		if (contatoVO.getId() <= 0) {
			JOptionPane.showMessageDialog(null, "Favor informar um ID maior que 0.");
			retorno = false;
		}

		if (contatoVO.getNome().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "O nome do contato não pode ser nulo.");
			retorno = false;
		}

		if (contatoVO.getNome().equals("")) {
			JOptionPane.showMessageDialog(null, "Favor informar o nome do contato.");
			retorno = false;
		}

		if (contatoVO.getDdd() == null || contatoVO.getDdd().trim().equals("") || contatoVO.getDdd().length() != 3) {
			JOptionPane.showMessageDialog(null, "Favor informar um DDD válido.");
			retorno = false;
		}

		if (contatoVO.getTelefone() == null || contatoVO.getTelefone().trim().equals("")
				|| (contatoVO.getTelefone().length() != 8 && contatoVO.getTelefone().length() != 9)) {
			JOptionPane.showMessageDialog(null, "Favor informar um telefone válido.");
			retorno = false;

		}

		return retorno;
	}

}
