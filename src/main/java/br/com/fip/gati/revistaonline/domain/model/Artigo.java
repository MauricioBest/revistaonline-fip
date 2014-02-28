package br.com.fip.gati.revistaonline.domain.model;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

@javax.persistence.Entity(name="artigo")
public class Artigo {
	
	private String codigo;
	
	@NotNull
	private String titulo;
	
	private String subTitulo;
	
	@NotNull
	private String resumo;
	@NotNull
	private String keyWord;
	@NotNull
	private ArrayList<Autor> autores;
	@NotNull
	private String Secao;
	@NotNull
	private String idioma;
	
	private String areaSubAreaDoConhecimento;
	
	private String geoEspacial;
	
	private String cronologicaOuHistorica;
	
	private String caracteristicasDaAmostragemDaPesquisa;
	
	
	private String condicoesParaSubmissao;
	
	private String agencias;
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
	public String getCondicoesParaSubmissao() {
		return condicoesParaSubmissao;
	}
	public void setCondicoesParaSubmiss�o(String condicoesParaSubmissao) {
		this.condicoesParaSubmissao = condicoesParaSubmissao;
	}
	
	
	
}
