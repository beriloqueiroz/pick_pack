package factory;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USUARIO = "user";
	private static final String SENHA = "password";
	private static final String URL1 = "http://db.mysql.dominio.com";
	private static final String URL = "jdbc:mysql://db.mysql.dominio.com:3306/db";

	public static Connection conectar() throws SQLException, IOException {
		Connection conexao = null;
		if (testaConexao(URL1)) {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		}
		return conexao;
	}

	public static boolean testaConexao(String StrUrl) throws IOException {
		URL url = new URL(StrUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setConnectTimeout(10000);
		con.setReadTimeout(10000);
		int cod = con.getResponseCode();
		con.disconnect();
		if (cod == 301 || cod == 200) {
			return true;
		} else {
			return false;
		}

	}
}
