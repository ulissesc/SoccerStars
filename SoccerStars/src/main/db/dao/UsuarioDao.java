package main.db.dao;

import java.util.List;

import main.model.Usuario;

import com.db4o.query.Predicate;

import main.db.DB;
import main.db.Dao;

public class UsuarioDao extends Dao<Usuario> {

	public UsuarioDao() {
		super(Usuario.class);
	}
	
	public List<Usuario> find(final String email, final String senha){

		List<Usuario> list = DB.getDb().query(new Predicate<Usuario>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Usuario usuario) {
				return usuario.getEmail().equals(email) && usuario.getSenha().equals(senha);
			}
		});
	
	return list;
	}

}
