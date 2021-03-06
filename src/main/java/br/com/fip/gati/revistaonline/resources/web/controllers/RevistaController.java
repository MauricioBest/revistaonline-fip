package br.com.fip.gati.revistaonline.resources.web.controllers;

import static br.com.fip.gati.revistaonline.resources.web.ControllerUtil.includeError;

import java.util.List;

import org.hibernate.Hibernate;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.fip.gati.revistaonline.domain.model.Autor;
import br.com.fip.gati.revistaonline.domain.model.Avaliador;
import br.com.fip.gati.revistaonline.domain.model.Newsletter;
import br.com.fip.gati.revistaonline.domain.model.Revista;
import br.com.fip.gati.revistaonline.domain.repositorio.AutorRepositorio;
import br.com.fip.gati.revistaonline.domain.repositorio.AvaliadorRepositorio;
import br.com.fip.gati.revistaonline.domain.repositorio.NewsLetterRepositorio;
import br.com.fip.gati.revistaonline.domain.repositorio.RevistaRepositorio;
import br.com.fip.gati.revistaonline.resources.web.UsuarioLogado;

@Resource
public class RevistaController {

	private final Result result;
	private final RevistaRepositorio revistas;
	private final AutorRepositorio autores;
	private AvaliadorRepositorio avaliadores;
	private NewsLetterRepositorio repositorioNewsletter;
	private final Validator validator;
	private UsuarioLogado usuarioLogado;
	
	public RevistaController(RevistaRepositorio repositorio, AutorRepositorio autorRepo, AvaliadorRepositorio avaliadores, NewsLetterRepositorio repositorioNewsletter, 
			UsuarioLogado usuarioLogado, Validator validator, Result result) {
		this.revistas = repositorio;
		this.autores = autorRepo;
		this.validator = validator;
		this.avaliadores = avaliadores;
		this.result = result;
		this.repositorioNewsletter = repositorioNewsletter;
		this.usuarioLogado = usuarioLogado;
	}

	@Get("/revistas")
	public void index() {
		result.include("revistaList", revistas.listAll());
	}
	
	@Get("/office/revista/new")
	public Revista newRevista() {
		result.include("action", "new");
		return new Revista();
	}
	
	@Get("/deletar")
	public void deletarAssinatura() {

	}

	@Get("/assinantedestarevista")
	public void errorAssinatura() {

	}
	
	@Post("/office/revista")
	public void create(Revista revista) {
		result.include("action", "new");

		validator.validate(revista);
		validator.onErrorUsePageOf(this).newRevista();
		
		revistas.save(revista);
		result.redirectTo(this).index();
	}

	
	@Put("/office/revista")
	public void update(Revista revista) {
		Revista dbRevista = this.revistas.load(revista.getId());
		dbRevista.setIssn(revista.getIssn());

		validator.validate(dbRevista);
		validator.onErrorUsePageOf(this).edit(revista);
		
		revistas.update(dbRevista);
		result.redirectTo(this).index();
	}
	
	@Get("/office/revista/{revista.id}/edit")
	public Revista edit(Revista revista) {
		result.include("action", "edit");
		return revistas.load(revista.getId());
	}

	@Get("/revista/{revista.id}")
	public Revista show(Revista revista) {
		return revistas.load(revista.getId());
	}

	@Delete("/office/revista/{revista.id}")
	public void destroy(Revista revista) {
		revistas.delete(revistas.load(revista.getId()));
		result.redirectTo(this).index();
	}

	@Get("/revista/assinar/{revista.id}")
	public Revista assinar(Revista revista) {
		Revista rev = this.revistas.load(revista.getId());
		return rev;
	}

	@Post("/revista/assinarlogado/{revista.id}")
	public void assinarLogado(Revista revista) {
		Revista idRevista = this.revistas.load(revista.getId());
		Newsletter nw = new Newsletter();
		Hibernate.initialize(idRevista.getNewsletters());
		
		String mailLogado = usuarioLogado.getUsuarioInfo().getEmail();
		String nomeLogado = usuarioLogado.getUsuarioInfo().getNome();
		
		for (int i = 0; i < idRevista.getNewsletters().size(); i++) {
			Newsletter n = idRevista.getNewsletters().get(i);
			if (n.getEmail().equals(mailLogado)) {
				result.redirectTo(this).errorAssinatura();
				return;
			}
		}
		
		List<Newsletter> listaAssinantes = repositorioNewsletter.listAll();
		for (int i = 0; i < listaAssinantes.size(); i++) {
			Newsletter n = listaAssinantes.get(i);
			if (n.getEmail().equals(mailLogado)) {
				nw = n;
				idRevista.addNewsletter(nw);
				this.revistas.update(idRevista);
				result.redirectTo(this).index();
				return;
			}
		}
		
		nw.setNome(nomeLogado);
		nw.setEmail(mailLogado);
		nw.setAssinante(true);
		idRevista.addNewsletter(nw);
		repositorioNewsletter.save(nw);
		this.revistas.update(idRevista);
		result.redirectTo(this).index();
	}
	
	@Get("/office/revista/{revista.id}/avaliadores")
	public void avaliadores(Revista revista) {
		Revista revistadb = revistas.load(revista.getId());
		result.include("revista", revistadb);
		result.include("avaliadorList", revistadb.getAvaliadores());
	}
	
	@Get("/office/revista/{revista.id}/avaliador/new")
	public void newAvaliador(Revista revista) {
		Revista revistadb = revistas.load(revista.getId());
		result.include("revista", revistadb);
	}

	@Post("/office/revista/{revista.id}/avaliador")
	public void createAvaliador(Revista revista, Autor autor) {
		Revista revistadb = revistas.load(revista.getId());
		Autor autordb = autores.load(autor.getId());
		
		Avaliador avaliador = null;
		if(autordb.isAvaliador()) {
			avaliador = autordb.getAvaliador();
		} else {
			avaliador = new Avaliador(autordb);
			avaliadores.save(avaliador);
		}
		
		if(revistadb.hasAvaliador(avaliador)) {
			includeError(result, "O usuário já é avaliador da revista");
			result.redirectTo(this).avaliadores(revistadb);
		} else {
			revistadb.addAvaliador(avaliador);
			revistas.update(revistadb);
			result.redirectTo(this).avaliadores(revistadb);
		}
	}
	
	@Delete("/office/revista/{revista.id}/avaliador/{avaliador.id}")
	public void removerAvaliador(Revista revista, Avaliador avaliador) {
		Revista revistadb = revistas.load(revista.getId());
		Avaliador avaliadordb = avaliadores.load(avaliador.getId());
		if(!revistadb.hasAvaliador(avaliadordb)) {
			includeError(result, "A revista não possui o avaliador informado");
			result.redirectTo(this).avaliadores(revistadb);
		} else {
			revistadb.removeAvaliador(avaliadordb);
			result.redirectTo(this).avaliadores(revistadb);
		}
	}
	
	@Get("/office/revista/{revista.id}/avaliador/buscar")
	public void buscarAvaliador(Revista revista, String nome) {
		result.include("autorList", autores.getPorNome(nome)).redirectTo(this).newAvaliador(revista);
	}
	
	@Post("/revista/assinatura/{revista.id}")
	public void assinarRevista(Revista revista, String email, String nome) {
		Revista idRevista = revistas.load(revista.getId());
		Newsletter nw = new Newsletter();
		Hibernate.initialize(idRevista.getNewsletters());
		
		for (int i = 0; i < idRevista.getNewsletters().size(); i++) {
			Newsletter n = idRevista.getNewsletters().get(i);
			if (n.getEmail().equals(email)) {
				result.redirectTo(this).errorAssinatura();
				return;
			}
		}

		List<Newsletter> listaAssinantes = repositorioNewsletter.listAll();
		for (int i = 0; i < listaAssinantes.size(); i++) {
			Newsletter n = listaAssinantes.get(i);
			if (n.getEmail().equals(email)) {
				nw = n;
				idRevista.addNewsletter(nw);
				revistas.update(idRevista);
				result.redirectTo(this).index();
				return;
			}
		}

		nw.setNome(nome);
		nw.setEmail(email);
		nw.setAssinante(true);
		this.validator.validate(nw);
		this.validator.onErrorRedirectTo(this).index();
		idRevista.addNewsletter(nw);
		repositorioNewsletter.save(nw);
		revistas.update(idRevista);
		result.redirectTo(this).index();

	}

	@Get("/revista/deletarAssinatura/{revista.id}")
	public Revista deletarAssinatura(Revista revista) {
		Revista rev = this.revistas.load(revista.getId());
		return rev;
	}

	@Post
	public void deleteNewsletter(Revista revista, String email) {
		Revista idRevista = revistas.load(revista.getId());
		Hibernate.initialize(idRevista.getNewsletters());
		Newsletter nw = new Newsletter();
		for (int i = 0; i < idRevista.getNewsletters().size(); i++) {
			Newsletter n = idRevista.getNewsletters().get(i);
			if (n.getEmail().equals(email)) {
				nw = n;
			}
		}
		idRevista.getNewsletters().remove(nw);
		revistas.update(idRevista);
		result.redirectTo(this).index();
	}
}
