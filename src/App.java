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
		AnimeRepository repositorio = new AnimeRepository();
		AnimeModel anime = null;
		repositorio.criaConexao();

		do {
			op = menu();
			switch (op) {
				case 1:
					anime = coletaDados();
					//salvaArquivo(anime);
					break;
				case 2:
					repositorio.buscarTodos();
					break;
				case 0:
					break;
				default:
					System.out.println("numero invalido");
			}
		} while (op != 0);

	}

	private static AnimeModel coletaDados() {
		String dataLancamento;
		Date data = null;
		String titulo;
		String genero;
		String estudio;
		String sinopse;

		do {
			dataLancamento = JOptionPane.showInputDialog(null, "Digite a data de lançamento do anime no formato dd/MM/yyyy");
			
			sdf.setLenient(false);
			try {
				data = sdf.parse(dataLancamento);
				System.out.println("Data digitada no formato correto: " + data);
				break;
			} catch (Exception e) {
				System.out.println("Formato de data inválido.");
			}
		} while (data == null);

		System.out.println("Digite o titulo do anime: ");
		titulo = scanner.nextLine();

		System.out.println("Digite os generos do anime: ");
		genero = scanner.nextLine();

		System.out.println("Digite o studio que fez o anime");
		estudio = scanner.nextLine();

		System.out.println("Digite a sinopse do anime");
		sinopse = scanner.nextLine();

		return new AnimeModel(data, titulo, genero, estudio, sinopse);
	}

	static int menu() {
		String menu = "\nDigite o número correspondente\n1 - para cadastrar um anime e salvar em um BD\n";
		menu += "2 - exibir os dados no banco\n0 - para fechar o programa";

		return Integer.parseInt(JOptionPane.showInputDialog(null, menu));
	}

}
