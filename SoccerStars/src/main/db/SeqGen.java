package main.db;

import com.db4o.ObjectSet;

public class SeqGen {
	
	public static Long next(@SuppressWarnings("rawtypes") Class clazz){
		return next(clazz.getName());
	}
	public static Long next(String clazz){
		Sequence sequence = new Sequence();
		sequence.setClazz(clazz);
		
		ObjectSet<Object> queryByExample = DB.getDb().queryByExample(sequence);
		if (queryByExample.size() > 0)
			sequence = (Sequence) queryByExample.get(0);
		else
			sequence.setValue(1L);
		
		Long nextValue = sequence.getNextValue();
		
		DB.getDb().store(sequence);
		
		return nextValue;
	}

}
