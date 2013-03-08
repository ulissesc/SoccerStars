package db;

import com.db4o.ObjectSet;
import com.db4o.query.Query;

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
	
	public ObjectSet<T> findAll(int fromIndex, int toIndex){
		ObjectSet<T> queryByExample = DB.getDb().queryByExample(clazz);
		return (ObjectSet<T>) queryByExample.subList(fromIndex, toIndex);
	}
	
	public void delete(Long id){
		T loaded = load(id);
		if (loaded != null)
			DB.getDb().delete(loaded);
	}
	
	public ObjectSet<T> findAll(String orderBy, boolean asc){
		Query query = DB.getDb().query();
		query.constrain(clazz);
		
		if (asc)
			query.descend(orderBy).orderAscending();
		else
			query.descend(orderBy).orderDescending();
		
		return query.execute();
	}
	
}
