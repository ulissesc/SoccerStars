package security;

import utils.JsfUtil;


public class SecurityContext {

	public static final String ID_USUARIO_LOGADO = "_id_usuario_logado";

	public static Long getIdUsuario(){
		Long idUsuario = (Long) JsfUtil.getSessionMap().get(ID_USUARIO_LOGADO);
		return idUsuario;
	}
	
}
