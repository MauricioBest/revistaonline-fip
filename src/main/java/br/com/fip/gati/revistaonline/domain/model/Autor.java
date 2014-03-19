package br.com.fip.gati.revistaonline.domain.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//@javax.persistence.Entity(name="autor")
public class Autor extends Entity {
	
	@NotNull
	private String preNome;
	
	private String nomeDoMeio;
	
	@NotNull
	private String sobreNome;
	@NotNull
	private String email;
	@Size(min=1, max=90)
	private String uRL;
	
	@NotNull
	private String instituicao;
	private String pais;
	
	private String resumoDaBiografia;
	private String numeroDeContaGoogleAnalytics;

}
