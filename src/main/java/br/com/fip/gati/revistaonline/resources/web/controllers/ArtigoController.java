package br.com.fip.gati.revistaonline.resources.web.controllers;

import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fip.gati.revistaonline.domain.model.Artigo;
import br.com.fip.gati.revistaonline.domain.model.Usuario;
import br.com.fip.gati.revistaonline.repositorio.ArtigoRepositorio;
import br.com.fip.gati.revistaonline.repositorio.UsuarioRepositorio;

@Resource
public class ArtigoController {
	
	private ArtigoRepositorio artigoRepositorio;
	private final Result result;

	public ArtigoController(ArtigoRepositorio artigoRep, Result result) {
		this.artigoRepositorio = artigoRep;
		this.result = result;
	}
	
	@Path("/cadastro")
	public void formulario() {
		
	}
	
	public void salvar(Artigo artigo) {
		//EFETUAR VALIDAÇÕES
//		artigo.setAlterarSenhaProximoAcesso(true);
//		usuario.setDtaCadastro(new Date());
//		usuario.setDtaUltimoAcesso(new Date());
//		usuario.setStatus("BLOQUEADO");
//		usuario.setTentativasLogon(0);
//		this.artigoRepositorio.save(artigo);
//		result.redirectTo(this).formulario();
	}
	
	public void atualizar(Artigo artigo) {
//		this.usuarioRepositorio.update(usuario);
	}
	
	public Artigo editar() {
		return null;
	}
	
	public void excluir(Usuario usuario) {
//		usuarioRepositorio.delete(usuario);
	}
	
	public List<Artigo> listar() {
		return artigoRepositorio.listAll();
	}
}
