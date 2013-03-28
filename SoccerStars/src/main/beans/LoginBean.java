package main.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.db4o.ObjectSet;

import main.model.Partida;
import main.model.Usuario;
import main.security.SecurityContext;
import main.utils.JsfUtil;
import main.utils.U;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario;
	
	public LoginBean(){
		this.usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String criarUsuario(){
		
		List<Usuario> usuarios = Usuario.dao().find(usuario.getEmail(),usuario.getSenha());
		if (!U.empty(usuarios)){
			JsfUtil.addErrorMessage("Já existe um usuário com o e-mail " + usuario.getEmail());	
		}
		Usuario.dao().save(usuario);
		
		JsfUtil.getSessionMap().put(SecurityContext.ID_USUARIO_LOGADO, usuario.getId());
		
		return null;
	}
	
	public String logar(){
		
		List<Usuario> usuarios = Usuario.dao().find(usuario.getEmail(),usuario.getSenha());
		if (!U.empty(usuarios)){
			final Usuario usuario = usuarios.get(0);
			JsfUtil.getSessionMap().put(SecurityContext.ID_USUARIO_LOGADO, usuario.getId());	
		}else{
			JsfUtil.addErrorMessage("Usuário ou senha inválidos");
		}
		
		return null;
	}
	
	public String deslogar(){
		
		JsfUtil.getHttpServletRequest().getSession().invalidate();
		
		return "index.jsf&faces-redirect=true";
	}
	
	public Usuario getUsuarioLogado(){
		return SecurityContext.getUsuario();
	}

	public List<Partida> getUltimasPartidas(){
		ObjectSet<Partida> objectSet = Partida.dao().findAll("id", false);
		
		if (objectSet == null)
			return new ArrayList<Partida>();
		
		if (objectSet.size() > 10)
			return objectSet.subList(0,10);
		
		return objectSet;
	}
}
