package utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ELResolver;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsfUtil {

	public static String getRequestParameter(String key) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(key);
	}

	public static Map<String, Object> getRequestMap() {
		return getFacesContext().getExternalContext().getRequestMap();
	}

	public static Map<String, Object> getSessionMap() {
		return getFacesContext().getExternalContext().getSessionMap();
	}
	
	public static String appPath(){
		return getFacesContext().getExternalContext()
				.getRequestContextPath();
	}

	public static HttpServletRequest getHttpServletRequest(){
		return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}
	
	public static HttpServletResponse getHttpServletResponse(){
		return (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
	}
	
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static Object getBean(String nome) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELResolver resolver = context.getApplication().getELResolver();
		return resolver.getValue(context.getELContext(), null, nome);
	}
	
	public static Object getObjectFromRequestParameter(
			String requestParameterName, Converter converter,
			UIComponent component) {
		String theId = JsfUtil.getRequestParameter(requestParameterName);
		return converter.getAsObject(FacesContext.getCurrentInstance(),
				component, theId);
	}

	public static String getAsConvertedString(Object object, Converter converter) {
		return converter.getAsString(FacesContext.getCurrentInstance(), null,
				object);
	}

	public final static String getAsString(Object object) {
		if (object instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) object;
			if (collection.size() == 0) {
				return "(No Items)";
			}
			StringBuffer sb = new StringBuffer();
			int i = 0;
			for (Object item : collection) {
				if (i > 0) {
					sb.append("<br />");
				}
				sb.append(item);
				i++;
			}
			return sb.toString();
		}
		return String.valueOf(object);
	}
	
	public static boolean hasJSFContext(){
		return FacesContext.getCurrentInstance() != null;
	}
	
	/**
	 * Informa se a requisi????o ?? ajax
	 * @return
	 */
	public static final boolean isAjaxRequest(){
		return getFacesContext().getPartialViewContext().isAjaxRequest();
	}
	
	
	
	/* METODOS DE MENSAGEM */
	public static final boolean hasErrorMessages(){
		Iterator<FacesMessage> iteratorMsg = getFacesContext().getMessages();
		while(iteratorMsg.hasNext()){
			FacesMessage fm = iteratorMsg.next();
			if (!FacesMessage.SEVERITY_INFO.equals( fm.getSeverity() ))
				return true;
		}
		return false;
	}
	public static void ensureAddErrorMessage(Exception ex, String defaultMsg) {
		String msg = ex.getLocalizedMessage();
		if (msg != null && msg.length() > 0) {
			addErrorMessage(msg);
		} else {
			addErrorMessage(defaultMsg);
		}
	}

	public static void addErrorMessages(List<String> messages) {
		for (String message : messages) {
			addErrorMessage(message);
		}
	}

	public static void addErrorMessage(String sumary, String... detail) {
		String det = detail.length > 0 ? detail[0] : "";
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				sumary, det);
		if (getFacesContext() != null)
			getFacesContext().addMessage(null, facesMsg);
	}

	public static void addSuccessMessage(String sumary, String... detail) {
		String det = detail.length > 0 ? detail[0] : "";
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				sumary, det);
		if (getFacesContext() != null)
			getFacesContext().addMessage(null, facesMsg);
	}

	public static void clearMessages() {
		Iterator<FacesMessage> messages = FacesContext.getCurrentInstance()
				.getMessages();
		while (messages.hasNext()) {
			messages.next();
			messages.remove();
		}
	}
	
	public static void addFatalMessage(String sumary, String... detail) {
		String det = detail.length > 0 ? detail[0] : "";
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				sumary, det);
		if (getFacesContext() != null)
			getFacesContext().addMessage(null, facesMsg);
	}
	
	public static void addWarnMessage(String sumary, String... detail) {
		String det = detail.length > 0 ? detail[0] : "";
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				sumary, det);
		if (getFacesContext() != null)
			getFacesContext().addMessage(null, facesMsg);
	}
	
	public static Flash getFlashScope (){
		return (FacesContext.getCurrentInstance().getExternalContext().getFlash());
	   }
}
