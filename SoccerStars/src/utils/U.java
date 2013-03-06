package utils;



import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Classe com funcoes uteis para qualquer programador Java
 * 
 */
public class U {
	
	/** VALIDACOES **/
	public static boolean empty(String obj) {
		return !(obj != null && !"".equals(obj) && !"null".equals(obj));
	}
	public static boolean empty(Object obj) {
		return obj == null;
	}
    public static boolean empty(List<?> objs) {
		return !(objs != null && objs.size() > 0);
	}
	public static boolean empty(Object[] objs) {
		return !(objs != null && objs.length > 0);
	}
	public static boolean emptyZero(Object obj) {
		return !(obj != null && !"".equals(obj) && !"null".equals(obj) && !new Long(0L).equals(obj) && !new Double(0D).equals(obj) );
	}
	public static boolean varArgsTrue(Boolean... varArgs) {
		return !empty(varArgs) && varArgs[0] == true;
	}
	public static <O extends Object> boolean varArgsEmpty(O...varArgs){
		return varArgs == null || varArgs.length == 0 || varArgs[0] == null;
	}
	
	/**
	 * Retorna o VALOR do primeiro parametro caso o mesmo seja diferente de null.
	 * Caso contrario, retorna o segundo.
	 * @param <T>
	 * @param valor
	 * @param ouValor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T coalesce(Number valor, T ouValor){
		if (valor == null)
			return ouValor;
		
		if (ouValor.getClass() == Integer.class )
			return (T) new Integer(valor.intValue());
		
		else if (ouValor.getClass() == Float.class )
			return (T) new Float( valor.floatValue() );
		
		else if (ouValor.getClass() == Double.class )
			return (T) new Double( valor.doubleValue() );
		
		else if (ouValor.getClass() == Long.class )
			return (T) new Long( valor.longValue() );

		else if (ouValor.getClass() == Byte.class )
			return (T) new Byte( valor.byteValue() );
		
		throw new IllegalArgumentException("O objeto "+ ouValor + " tem tipo inv??lido.");
	}
	
	/**
	 * Retorna o VALOR do primeiro parametro caso o mesmo seja diferente de null.
	 * Caso contrario, retorna o segundo.
	 * @param valor
	 * @param ouValor
	 * @return
	 */
	public static String coalesce(String valor, String ouValor){
		return valor != null ? valor : ouValor;
	}

	public static String coalesce(String valor){
		return valor != null ? valor : "";
	}
	
	/**
	 * Retorna a lista ou uma lista vazia caso a mesma seja igual a NULL
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T extends Object> List<T> list(List<T> list){
		if (list == null)
			return new ArrayList<T>();
		return list;
	}
	
	/**
	 * Retorna true se algum dos VALORES for igual ao VALOR
	 * @param valor
	 * @param valores
	 * @return
	 */
	public static final boolean in(String valor, String... valores){
		
		for (String v : valores)
			if(valor.equals(v))
				return true;
		
		return false;
	}
	
	/**
	 * Retorna TRUE se algum dos valores for igual
	 * @param valor
	 * @param valores
	 * @return
	 */
	public static final boolean inNumber(String valor, String... valores){
		
		for (String v : valores){
			try {
				if (Double.valueOf( valor ).equals( Double.valueOf( v )))
					return true;
			}catch (Exception e) { }
		}
		
		return false;
	}
	
	public static final <T> boolean inList(T valor, T... valores){
		
		for (T v : valores)
			if(valor.equals(v))
				return true;
		
		return false;
	}
	
	/**
	 * Transforma qualquer objeto em uma String
	 * Se o objeto for NULO, retorna uma String vazia ""
	 * @param object
	 * @return
	 */
	public static final String asStr(Object object){
		if (object != null)
			return object.toString();
		return "";
	}
	
	/**
	 * Transforma um objeto qualquer em uma String,
	 * desde que o mesmo n??o seja nulo ou vazio
	 * @param object
	 * @return
	 */
	public static final String asStrOrNull(Object object){
		if ("".equals( asStr(object) ))
			return null;
		return object.toString();
	}
}
