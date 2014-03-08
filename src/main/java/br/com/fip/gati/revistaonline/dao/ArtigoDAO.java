package br.com.fip.gati.revistaonline.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.fip.gati.revistaonline.domain.model.Artigo;
import br.com.fip.gati.revistaonline.domain.model.Usuario;
import br.com.fip.gati.revistaonline.repositorio.ArtigoRepositorio;
import br.com.fip.gati.revistaonline.repositorio.UsuarioRepositorio;

@Component
public class ArtigoDAO extends GenericDAO<Artigo> implements ArtigoRepositorio {
	public ArtigoDAO(Session session) {
		super(Artigo.class, session);
	}

//	public Artigo getArtigo(String titulo, String resumo) {
//		if (titulo == null || resumo == null) {
//			return null;
//		}
//		
//		return (Artigo) getCurrentSession().createCriteria(Artigo.class)
//				.add(Restrictions.eq("titulo", titulo))
//				.add(Restrictions.eq("resumo", resumo))
//				.uniqueResult();
//	}
}
