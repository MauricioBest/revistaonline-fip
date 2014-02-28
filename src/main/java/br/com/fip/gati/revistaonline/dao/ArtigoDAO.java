package br.com.fip.gati.revistaonline.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.fip.gati.revistaonline.domain.model.Artigo;
import br.com.fip.gati.revistaonline.domain.model.Usuario;
import br.com.fip.gati.revistaonline.repositorio.UsuarioRepositorio;

@Component
public class ArtigoDAO extends GenericDAO<Artigo> implements UsuarioRepositorio {
	public ArtigoDAO(Session session) {
		super(Artigo.class, session);
	}

	public Usuario getUsuario(String login, String senha) {
		if (login == null || senha == null) {
			return null;
		}
		
		return (Artigo) getCurrentSession().createCriteria(Artigo.class)
				.add(Restrictions.eq("login", login))
				.add(Restrictions.eq("senha", senha))
				.uniqueResult();
	}
}
