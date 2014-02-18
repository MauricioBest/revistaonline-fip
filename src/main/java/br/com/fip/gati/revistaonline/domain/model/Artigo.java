package br.com.fip.gati.revistaonline.domain.model;

import javax.validation.constraints.NotNull;

@javax.persistence.Entity(name="Artigo")
public class Artigo {
	@NotNull
	private String titulo;
	private String subTitulo;
	private String resumo;
	private String keyWord;
	private String autor;
	
	private String Secao;
	private String idioma;
	private String condicoesParaSubmissão;
	
}
