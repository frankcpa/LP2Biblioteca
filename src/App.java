import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class App {
	static Scanner scanner = new Scanner(System.in);
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws SQLException {
		String pastaNome = "Notes";
		int op;
		Anime anime = null;
		ArrayList<Anime> ani = new ArrayList<Anime>();
		String diretorioAtual = System.getProperty("user.dir");
		File pastaPai = new File(diretorioAtual).getParentFile();
		System.out.println(pastaPai.toString());
		File pasta = new File(pastaPai, pastaNome);
		File arquivo;
		try {
			Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/LP2_Utilidades", "root",
					"root123");
			String sql = "INSERT INTO Anime (dataLancamento,titulo,genero,estudio,sinopse) ";
			sql+="VALUES ('2024/10/10','Titulo 1','Gen 1', 'Est 1', 'Sinop 1')";
			// Prepara a instrução SQL
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Executa a instrução SQL
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (pastaPai != null) {
			if (!pasta.exists()) {
				pasta.mkdir();
			}
		}
		arquivo = criaArquivo(pasta);

		do {
			menu();
			op = scanner.nextInt();
			scanner.nextLine();
			switch (op) {
				case 1:
					anime = coletaDados();
					salvaArquivo(anime, arquivo);
					break;
				case 2:
					ani.clear();
					ani = leArquivo(arquivo, ani);
					if (ani.isEmpty()) {
						System.out.println("Sem animes cadastrados");
					} else {
						for (Anime anime2 : ani) {
							imprimeDados(anime2);

						}
					}
					break;
				case 0:
					break;
				default:
					System.out.println("numero invalido");
			}
		} while (op != 0);

	}

	private static void imprimeDados(Anime anime2) {
		System.out.println("----------------------------");
		System.out.println("Titulo do anime: " + anime2.getTitulo());
		System.out.println("Data de lançamento do anime: " + sdf.format(anime2.getDataLancamento()));
		System.out.println("Studio que produziu: " + anime2.getEstudio());
		System.out.println("Generos do anime: " + anime2.getGenero());
		System.out.println("Sinopse do anime: " + anime2.getSinopse());
	}

	private static ArrayList<Anime> leArquivo(File arquivo, ArrayList<Anime> ani) {
		String dataLancamento;
		Date data = null;
		String titulo;
		String genero;
		String estudio;
		String sinopse;

		try {
			Scanner leitorArquivo = new Scanner(arquivo);

			while (leitorArquivo.hasNextLine()) {
				titulo = leitorArquivo.nextLine();
				dataLancamento = leitorArquivo.nextLine();
				estudio = leitorArquivo.nextLine();
				genero = leitorArquivo.nextLine();
				sinopse = leitorArquivo.nextLine();

				data = sdf.parse(dataLancamento);

				Anime animi = new Anime(data, titulo, genero, estudio, sinopse);
				ani.add(animi);
			}
			leitorArquivo.close();
		} catch (Exception e) {
			System.out.println("Erro ao abrir arquivo" + e.getMessage());
		}
		return ani;

	}

	private static void salvaArquivo(Anime anime, File arquivo) {
		try {
			FileWriter fileWriter = new FileWriter(arquivo, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);

			printWriter.println(anime.getTitulo());
			printWriter.println(sdf.format(anime.getDataLancamento()));
			printWriter.println(anime.getEstudio());
			printWriter.println(anime.getGenero());
			printWriter.println(anime.getSinopse());
			printWriter.flush();
			printWriter.close();

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	private static File criaArquivo(File pasta) {
		File arquivo = new File(pasta, "arquivo.txt");

		if (!arquivo.exists()) {
			try {
				arquivo.createNewFile();
				return arquivo;
			} catch (Exception e) {
				System.out.println("Erro ao execultar arquivo " + e.getMessage());
			}
		}
		return arquivo;
	}

	private static Anime coletaDados() {
		String dataLancamento;
		Date data = null;
		String titulo;
		String genero;
		String estudio;
		String sinopse;

		do {
			System.out.println("Digite a data de lançamento do anime no formato dd/MM/yyyy");
			dataLancamento = scanner.nextLine();
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

		Anime ani = new Anime(data, titulo, genero, estudio, sinopse);

		return ani;

	}

	static void menu() {
		System.out.println("----------------------------");
		System.out.println("Digite o numero correspondente: ");
		System.out.println("1 - para cadastrar um anime e salvar em um arquivo");
		System.out.println("2 - para instanciar e exibir os dados no arquivo.txt");
		System.out.println("0 - para fechar o programa");
	}

}
