package main.db.dao;

import java.util.List;

import main.model.Partida;

import com.db4o.query.Predicate;

import main.db.DB;
import main.db.Dao;

public class PartidaDao extends Dao<Partida> {

	public PartidaDao() {
		super(Partida.class);
	}
	
	public List<Partida> findByUsuario(final Long idUsuario){

		List<Partida> list = DB.getDb().query(new Predicate<Partida>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Partida partida) {
				return idUsuario.equals( partida.getIdUsuario());
			}
		});
	
	return list;
	}

}
