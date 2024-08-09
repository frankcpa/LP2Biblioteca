import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import br.com.model.*;
import br.com.repository.AnimeRepository;

public class App {
	static Scanner scanner = new Scanner(System.in);
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws SQLException {
		int op;
		String id;
		AnimeRepository animeRepositorio = new AnimeRepository();
		AnimeModel anime = new AnimeModel();
		animeRepositorio.criaConexao();

		do {
			op = menu();
			switch (op) {
				case 1:
					anime = coletaDados(anime);
					animeRepositorio.salvar(anime);
					break;
				case 2:
					animeRepositorio.buscarTodos();
					break;
				case 3:
					String titulo = JOptionPane.showInputDialog(null, "Entre com o titulo");
					animeRepositorio.buscarPorTitulo(titulo);
					break;
				case 4:
					id = JOptionPane.showInputDialog(null, "Entre com o ID para remover");
					animeRepositorio.remover(id);
					break;
				case 5:
					id = JOptionPane.showInputDialog(null, "Entre com o ID para Editar");
					anime = animeRepositorio.buscarPorId(id);
					if (anime.getIdAnime()==0) {
						JOptionPane.showMessageDialog(null, "Registro não encontrado");
					}else{
						anime = coletaDados(anime);
						animeRepositorio.editar(anime);
					}
					break;
				case 0:
					break;
				default:
					System.out.println("numero invalido");
			}
		} while (op != 0);

	}

	private static AnimeModel coletaDados(AnimeModel anime) {
		String dataLancamento;
		Date data = null;

		do {
			dataLancamento = JOptionPane.showInputDialog(null,
					"Digite a data de lançamento do anime no formato dd/MM/yyyy", anime.getDataLancamento());

			sdf.setLenient(false);
			try {
				data = sdf.parse(dataLancamento);
				System.out.println("Data digitada no formato correto: " + data);
				break;
			} catch (Exception e) {
				System.out.println("Formato de data inválido.");
			}
		} while (data == null);

		anime.setDataLancamento(data);
		anime.setTitulo(JOptionPane.showInputDialog(null, "Digite o titulo do anime",anime.getTitulo()));
		anime.setGenero(JOptionPane.showInputDialog(null, "Digite os generos do anime", anime.getGenero()));
		anime.setEstudio(JOptionPane.showInputDialog(null, "Digite o studio que fez o anime", anime.getEstudio()));
		anime.setSinopse(JOptionPane.showInputDialog(null, "Digite a sinopse do anime", anime.getSinopse()));

		return anime;
	}

	static int menu() {
		String menu = "\nDigite o número correspondente\n1 - para cadastrar um anime e salvar em um BD\n";
		menu += "2 - exibir todos os animes cadastrados\n3 - para buscar por Titulo\n";
		menu += "4 - remover\n";
		menu += "5 - Editar\n0 - para fechar o programa";

		String opt = JOptionPane.showInputDialog(null, menu);

		return Integer.parseInt(opt);
	}

}
