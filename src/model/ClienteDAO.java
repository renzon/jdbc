package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import db.GerenteDeConexao;

public class ClienteDAO {
	public void salvar(Cliente cliente) {

		String sql = null;
		if (cliente.getId() == 0) {
			sql = "INSERT INTO cliente (nome,idade) VALUES ";
			sql += "('" + cliente.getNome() + "',";
			sql += cliente.getIdade() + ")";

		} else {
			sql = "UPDATE cliente SET ";
			sql += "nome='" + cliente.getNome() + "',";
			sql += "idade=" + cliente.getIdade();
			sql += " WHERE id='+cliente.getId()";

		}

		Connection connection = GerenteDeConexao.getConexao();
		Statement stm = null;
		try {
			stm = connection.createStatement();
			System.out.println("SQL: " + sql);
			stm.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			throw new RuntimeException("Salvar falhou");
		} finally {
			GerenteDeConexao.fechar(connection, stm);
		}

	}
	
	private Cliente toCliente(ResultSet rs) throws SQLException{
		return new Cliente(rs.getInt("id"),rs.getString("nome"), rs.getInt("idade"));
		
	}

	public List<Cliente> listarTodos() {

		String sql = "SELECT * FROM cliente ORDER BY nome";
		
		Connection connection = GerenteDeConexao.getConexao();
		Statement stm = null;
		ResultSet rs=null;
		try {
			stm = connection.createStatement();
			System.out.println("SQL: " + sql);
			rs=stm.executeQuery(sql);
			List<Cliente> clientes =new ArrayList<Cliente>();
			while(rs.next()){
				clientes.add(toCliente(rs));
			}
			return clientes;

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			
		} finally {
			GerenteDeConexao.fechar(connection, stm,rs);
		}
		throw new RuntimeException("Listar falhou");

	}

	public static void main(String[] args) {
		Cliente cliente = new Cliente("André", 31);
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);
		System.out.println(clienteDAO.listarTodos());
	}

}
