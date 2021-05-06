package dados_db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import base.Pedido;
import factory.ConexaoFactory;

public class dao {
	private static Connection conexao;
	private static String erroMsg;

	public String getErroMsg() {
		return erroMsg;
	}

	public void setErroMsg(String erroMsg) {
		dao.erroMsg = erroMsg;
	}

	public void conectar() {
		try {
			conexao = ConexaoFactory.conectar();
		} catch (SQLException e) {
			erroMsg = "erro Ao Conectar ao banco, dao: " + e.getMessage();
			System.out.println(erroMsg);
			e.printStackTrace();
		} catch (IOException e) {
			erroMsg = "erro Ao Conectar ao banco, dao: " + e.getMessage();
			System.out.println(erroMsg);
			e.printStackTrace();
		}
	}

	public void desconectar() {
		try {
			conexao.close();
		} catch (SQLException e) {
			erroMsg = "erro Ao Desconectar ao banco, dao: " + e.getMessage();
			System.out.println(erroMsg);
			e.printStackTrace();
		}
	}

	public void inserir(Pedido p) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PEDIDOS (usuario, pedido, operacao, empresa, datahora) VALUES (?,?,?,?,?)");
		try {
			conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql.toString());
			stmt.setString(1, p.getUsuario());
			stmt.setString(2, p.getPedido());
			stmt.setString(3, p.getOperacao());
			stmt.setString(4, p.getEmpresa());
			Date hoje = new Date();
			java.sql.Timestamp DataHoraStatus_Sql = new java.sql.Timestamp(hoje.getTime());
			stmt.setTimestamp(5, DataHoraStatus_Sql);
			stmt.executeUpdate();
			desconectar();
		} catch (SQLException e) {
			erroMsg = "erro SQL ao inserir dao" + e.getMessage();
			System.out.println(erroMsg);
			desconectar();
			e.printStackTrace();
		}
	}
	
	
	public void excluir(Pedido p) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM PEDIDOS WHERE pedido='"+p.getPedido()+"' and operacao='"+p.getOperacao()+"' and empresa='"+p.getEmpresa()+"'");
		try {
			conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql.toString());
			stmt.executeUpdate();
			desconectar();
		} catch (SQLException e) {
			erroMsg = "erro SQL ao deletar dao" + e.getMessage();
			System.out.println(erroMsg);
			desconectar();
			e.printStackTrace();
		}
	}
	
	public void inserirUser(String u, String s) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO USERS (usuario, senha) VALUES (?,?)");
		try {
			conectar();
			PreparedStatement stmt = conexao.prepareStatement(sql.toString());
			stmt.setString(1, u);
			stmt.setString(2, s);
			stmt.executeUpdate();
			desconectar();
		} catch (SQLException e) {
			erroMsg = "erro SQL ao inserir user dao" + e.getMessage();
			System.out.println(erroMsg);
			desconectar();
			e.printStackTrace();
		}
	}

	public boolean existePedido(Pedido p) { // retorna a venda do mySQL
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID FROM PEDIDOS WHERE pedido = '" + p.getPedido() + "' and operacao= '"
				+ p.getOperacao() + "'");
		try {
			conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			ResultSet res = comando.executeQuery();
			if (res.next()) {
				desconectar();
				return true;
			} else {
				desconectar();
				return false;
			}
		} catch (SQLException e) {
			erroMsg = "erro ao verificar se existe pedido - dao" + e.getMessage();
			System.out.println(erroMsg);
			e.printStackTrace();
			desconectar();
			return false;
		}
	}

	public boolean existeUsuario(String refUser, String senha) { // retorna a venda do mySQL
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT senha FROM USERS WHERE refUser = '" + refUser + "' and senha= '" + senha + "'");
		try {
			conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			ResultSet res = comando.executeQuery();
			if (res.next()) {
				desconectar();
				return true;
			} else {
				desconectar();
				return false;
			}
		} catch (SQLException e) {
			erroMsg = "erro ao verificar se existe user - dao" + e.getMessage();
			System.out.println(erroMsg);
			e.printStackTrace();
			desconectar();
			return false;
		}
	}
	public String RefUsuario(String user) { 
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT usuario FROM USERS WHERE refUser = '" +user+ "'");
		String ref_aux ="";
		try {
			conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			ResultSet results = comando.executeQuery();
			int numCols = results.getMetaData().getColumnCount();
			while (results.next()) {
				for (int col = 0; col < numCols; col++) {
					ref_aux = results.getString(col + 1);
				}
			}
			results.close();
		} catch (SQLException e) {
			erroMsg = "erro ao verificar se existe user - dao" + e.getMessage();
			System.out.println(erroMsg);
			e.printStackTrace();
			desconectar();
		}
		return ref_aux;
	}
	public static void main(String[] args) {
		dao d = new dao();
		d.inserirUser("user","pass");
	}
}
