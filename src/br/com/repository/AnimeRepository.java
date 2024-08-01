package br.com.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import br.com.model.*;

public class AnimeRepository {
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Connection conexao;

	public static void criaConexao() throws SQLException{
		try {
			if (conexao.isClosed()) {
				conexao = DriverManager.getConnection("jdbc:mysql://localhost/LP2_Utilidades", "root", "root123");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buscarTodos() {

	}

	public void buscarPorGenero() {

	}

	public void salvar(AnimeModel anime) throws SQLException {
		//criaConexao();
		try {
			String sql = "INSERT INTO Anime (dataLancamento,titulo,genero,estudio,sinopse) VALUES ('2024/10/10','Titulo 1','Gen 1', 'Est 1', 'Sinop 1')";
			// Prepara a instrução SQL
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Executa a instrução SQL
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			conexao.rollback();
		}
	}
}
