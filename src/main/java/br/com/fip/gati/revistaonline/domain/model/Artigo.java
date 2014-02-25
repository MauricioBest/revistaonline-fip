package br.com.fip.gati.revistaonline.domain.model;

import javax.validation.constraints.NotNull;

@javax.persistence.Entity(name="artigo")
public class Artigo {
	@NotNull
	private String titulo;
	@NotNull
	private String subTitulo;
	@NotNull
	private String resumo;
	@NotNull
	private String keyWord;
	@NotNull
	private String autor;
	@NotNull
	private String Secao;
	@NotNull
	private String idioma;
	
	
	private String condicoesParaSubmissão;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubTitulo() {
		return subTitulo;
	}
	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getSecao() {
		return Secao;
	}
	public void setSecao(String secao) {
		Secao = secao;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getCondicoesParaSubmissão() {
		return condicoesParaSubmissão;
	}
	public void setCondicoesParaSubmissão(String condicoesParaSubmissão) {
		this.condicoesParaSubmissão = condicoesParaSubmissão;
	}
	
	
	
}
