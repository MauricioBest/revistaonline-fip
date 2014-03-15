package br.com.fip.gati.revistaonline.dao;

import org.hibernate.Session;

import br.com.fip.gati.revistaonline.domain.model.Revista;
import br.com.fip.gati.revistaonline.repositorio.RevistaRepositorio;

public class RevistaDAO extends GenericDAO<Revista> implements RevistaRepositorio {

	public RevistaDAO(Session session) {
		super(Revista.class, session);
	}

}