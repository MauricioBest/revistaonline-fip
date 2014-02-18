package br.com.fip.gati.revistaonline.resources.web.interceptors;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.fip.gati.revistaonline.resources.web.UsuarioLogado;
import br.com.fip.gati.revistaonline.resources.web.controllers.LoginController;

@Intercepts
@RequestScoped
public class UsuarioLogadoInterceptor implements Interceptor {
	private UsuarioLogado usuarioLogado;
	private Result result;
	
	public UsuarioLogadoInterceptor(UsuarioLogado user, Result result) {
		this.usuarioLogado = user;
		this.result = result;
	}

	public boolean accepts(ResourceMethod arg0) {
		return (!usuarioLogado.isLogado() && !arg0.getMethod().getDeclaringClass().equals(LoginController.class));
	}

	public void intercept(InterceptorStack arg0, ResourceMethod arg1, Object arg2) throws InterceptionException {
		result.redirectTo(LoginController.class).login();
	}
	
}
