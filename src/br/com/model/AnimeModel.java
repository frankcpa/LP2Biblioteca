package br.com.model;
import java.util.Date;

public class AnimeModel {
	private int idAnime;
	private Date dataLancamento;
	private String titulo;
	private String genero;
	private String estudio;
	private String sinopse;
	
	public AnimeModel(Date dataLancamento, String titulo, String genero, String estudio, String sinopse) {
		this.dataLancamento = dataLancamento;
		this.titulo = titulo;
		this.genero = genero;
		this.estudio = estudio;
		this.sinopse = sinopse;
	}

	public AnimeModel(){}
	
	public int getIdAnime() {
		return idAnime;
	}

	public void setIdAnime(int idAnime) {
		this.idAnime = idAnime;
	}
	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
}
