package db.dao;

import java.util.Comparator;
import java.util.List;

import com.db4o.query.Predicate;

import model.Jogador;
import db.DB;
import db.Dao;

public class JogadorDao extends Dao<Jogador> {

	public JogadorDao() {
		super(Jogador.class);
	}
	
	public List<Jogador> findByUsuario(final Long idUsuario){

		List<Jogador> list = DB.getDb().query(new Predicate<Jogador>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Jogador jogador) {
				return idUsuario.equals(jogador.getIdUsuario());
			}
		
		}, new Comparator<Jogador>() {
			@Override
			public int compare(Jogador o1, Jogador o2) {
				
				final String nome1 = String.valueOf( o1.getNome() ).toLowerCase();
				final String nome2 = String.valueOf( o2.getNome() ).toLowerCase();
				
				return nome1.compareTo(nome2);
			}
		});
	
	return list;
	}

}
