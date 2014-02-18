package br.com.fip.gati.revistaonline.resources.web.controllers;

import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.fip.gati.revistaonline.domain.model.Usuario;
import br.com.fip.gati.revistaonline.repositorio.UsuarioRepositorio;

@Resource
public class UsuarioController {
	
	private UsuarioRepositorio usuarioRepositorio;
	private final Result result;

	public UsuarioController(UsuarioRepositorio usuarioRep, Result result) {
		this.usuarioRepositorio = usuarioRep;
		this.result = result;
	}
	
	@Path("/cadastro")
	public void formulario() {
		
	}
	
	public void salvar(Usuario usuario) {
		//EFETUAR VALIDAÇÕES
		usuario.setAlterarSenhaProximoAcesso(true);
		usuario.setDtaCadastro(new Date());
		usuario.setDtaUltimoAcesso(new Date());
		usuario.setStatus("BLOQUEADO");
		usuario.setTentativasLogon(0);
		this.usuarioRepositorio.save(usuario);
		result.redirectTo(this).formulario();
	}
	
	public void atualizar(Usuario usuario) {
//		this.usuarioRepositorio.update(usuario);
	}
	
	public Usuario editar() {
		return null;
	}
	
	public void excluir(Usuario usuario) {
//		usuarioRepositorio.delete(usuario);
	}
	
	public List<Usuario> listar() {
		return usuarioRepositorio.listAll();
	}
}
