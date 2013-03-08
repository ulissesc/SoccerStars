package db;

import com.db4o.ObjectSet;

public class Dao<T extends Entity> {

	private Class<T> clazz;
	public Dao(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public void save(T entity){
		if (entity.getId() == null || entity.getId() == 0l)
			entity.setId(SeqGen.next(clazz));
		
		DB.getDb().store(entity);
	}
	
	
	@SuppressWarnings("unchecked")
	public T load(Long id) {
		T t;
		try {
			t = (T) clazz.newInstance();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}  
		t.setId(id);
		ObjectSet<Object> queryByExample = DB.getDb().queryByExample(t);
		if (queryByExample.size() > 0)
			return (T) queryByExample.get(0);
		
		return null;
	}
	
	public ObjectSet<T> findAll(){
		ObjectSet<T> queryByExample = DB.getDb().queryByExample(clazz);
		return queryByExample;
	}
	
	public void delete(Long id){
		T loaded = load(id);
		if (loaded != null)
			DB.getDb().delete(loaded);
	}
}
