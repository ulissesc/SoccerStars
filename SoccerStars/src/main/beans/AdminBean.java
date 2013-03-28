package main.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import main.utils.JsfUtil;

import main.model.Usuario;

@ManagedBean
@ViewScoped
public class AdminBean {

	public List<Usuario> getTodosOsUsuarios(){
		return Usuario.dao().findAll();
	}
	
	public String excluirUsuario(Usuario usuario){
		
		Usuario.dao().delete(usuario);
		JsfUtil.addSuccessMessage("Usuário excluído com sucesso");
		
		return null;
	}
}
