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
	public T load(final Long id){

		if (id == null)
			return null;
		Query query = DB.getDb().query();
		query.constrain(clazz);
		query.descend("id").constrain(id);
		ObjectSet<Object> objectSet = query.execute();
		if (objectSet.size() > 0)
			return (T) objectSet.get(0);
		
//		DB.getDb().query(new Predicate<T>() {
//			private static final long serialVersionUID = 1L;
//			
//			@Override
//			public boolean match(T object) {
//				return id.equals(object.getId());
//			}
//		});
	
		return null;
	}
	
//	@SuppressWarnings("unchecked")
//	public T load(Long id) {
//		T t;
//		try {
//			t = (T) clazz.newInstance();
//		} catch (Throwable e) {
//			throw new RuntimeException(e);
//		}  
//		t.setId(id);
//		ObjectSet<Object> queryByExample = DB.getDb().queryByExample(t);
//		if (queryByExample.size() > 0)
//			return (T) queryByExample.get(0);
//		
//		return null;
//	}
	
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
		delete(loaded);
	}
	
	public void delete(T object){
		if (object != null)
			DB.getDb().delete(object);
	}
	
	public ObjectSet<T> findAll(String orderBy, boolean asc){

		Query query = DB.getDb().query();
		query.constrain(clazz);
		
		if (asc)
			query.descend(orderBy).orderAscending();
		else
			query.descend(orderBy).orderDescending();
		
		try{
			ObjectSet<T> result = query.execute();
			return result;
		}catch (NullPointerException nullPointerException){
			System.out.println("Nenhuma classe encontrada para: " + clazz);
		}
			
		return null;
	}
	
}
