package br.com.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import br.com.model.*;

public class AnimeRepository {
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static Connection conexao=null;

	public void criaConexao() throws SQLException{
		try {
			if (conexao == null || conexao.isClosed()) {
				conexao = DriverManager.getConnection("jdbc:mysql://localhost/LP2_Utilidades", "root", "root123");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buscarTodos() {
		try {
			String retorno="";
			// Prepara a instrução SQL
			PreparedStatement ps = conexao.prepareStatement("select * from Anime");
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				retorno += "ID: " + resultado.getInt("idAnime") +
				"Data: " + resultado.getDate("dataLancamento") +
				 " - Titulo: " + resultado.getString("titulo") + "\n";
			}

			JOptionPane.showMessageDialog(null, retorno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buscarPorTitulo(String titulo) {
		try {
			String retorno="";
			// Prepara a instrução SQL
			PreparedStatement ps = conexao.prepareStatement("select * from Anime where titulo like '%"+titulo+"%'");
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				retorno += "Data: " + resultado.getDate("dataLancamento") +
				 " - Titulo: " + resultado.getString("titulo") + "\n";
			}

			JOptionPane.showMessageDialog(null, retorno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void remover(String id){
		try {
			String sql = "Delete from Anime where idAnime = ?";
			// Prepara a instrução SQL
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, id);
			// Executa a instrução SQL
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			//conexao.rollback();
		}
	}

	public void salvar(AnimeModel anime) throws SQLException {
		try {
			String sql = "INSERT INTO Anime (dataLancamento,titulo,genero,estudio,sinopse) VALUES (?,?,?,?,?)";
			// Prepara a instrução SQL
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(anime.getDataLancamento().getTime()));
			ps.setString(2, anime.getTitulo());
			ps.setString(3, anime.getGenero());
			ps.setString(4, anime.getEstudio());
			ps.setString(5, anime.getSinopse());
			// Executa a instrução SQL
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			conexao.rollback();
		}
	}
}
