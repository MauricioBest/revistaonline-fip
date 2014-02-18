package br.com.fip.gati.revistaonline.resources.web;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.fip.gati.revistaonline.domain.model.Usuario;

@Component
@SessionScoped
public class UsuarioLogado {
	private Usuario user;

	public Usuario getUsuario() {
		return user;
	}
	
	public void setUsuario(Usuario usuario) {
		this.user = usuario;
	}

	public boolean isLogado() {
		return user != null;
	}
	
	public void logout() {
		user = null;
	}
	
}
