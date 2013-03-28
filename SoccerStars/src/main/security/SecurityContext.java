package main.security;

import main.model.Usuario;
import main.utils.JsfUtil;


public class SecurityContext {

	public static final String ID_USUARIO_LOGADO = "_id_usuario_logado";

	public static Long getIdUsuario(){
		Long idUsuario = (Long) JsfUtil.getSessionMap().get(ID_USUARIO_LOGADO);
		return idUsuario;
	}
	
	public static Usuario getUsuario(){
		if (getIdUsuario() != null)
			return Usuario.dao().load(getIdUsuario());
		return null;
	}
	
	public static boolean isUsuarioAdmin(){
		Usuario usuarioLogado = getUsuario();
		return isUsuarioAdmin(usuarioLogado);
	}
	
	public static boolean isUsuarioAdmin(Usuario usuarioLogado){
		if (usuarioLogado != null){
			return "torquatro@gmail.com".equals(usuarioLogado.getEmail());
		}
		return false;
	}
}
