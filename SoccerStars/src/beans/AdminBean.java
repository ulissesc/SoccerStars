package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Usuario;

@ManagedBean
@ViewScoped
public class AdminBean {

	public List<Usuario> getTodosOsUsuarios(){
		return Usuario.dao().findAll();
	}
	
}
