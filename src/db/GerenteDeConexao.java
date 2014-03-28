package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GerenteDeConexao {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String BANCO = "meu";
	private static final String CONEXAO = "jdbc:postgresql://localhost:5432/"+BANCO;
	private static final String USUARIO = "postgres";
	private static final String SENHA = "abc123";

	public static Connection getConexao() {

		try {
			Class.forName(DRIVER);

			Connection conn = DriverManager.getConnection(CONEXAO, USUARIO,
					SENHA);
			System.out.println("Conectado");
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new RuntimeException("Conexão com banco falhou");

	}

	public static void fechar(Connection conexao) {
		try {
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void fechar(Connection conexao, Statement declaracao) {
		try {
			if (declaracao != null)
				declaracao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fechar(conexao);
	}
	
	public static void fechar(Connection conexao, Statement declaracao, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fechar(conexao,declaracao);
	}

	public static void main(String[] args) {
		getConexao();
	}

}
