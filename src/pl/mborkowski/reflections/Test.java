package pl.mborkowski.reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
	
	final int TIMES = 10000;
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Vehicle v = new Vehicle("Daewoo", "Nubira", 2000);
		Test t = new Test();
		long readTime = t.readValue(t,v);
		long writeTime = t.writeValue(t,v);
		long invokeMethod = t.invokeMethod(t, v);
		t.processTime(readTime, "odczyt zmiennej");
		t.processTime(writeTime, "zapis zmiennej");
		t.processTime(writeTime, "wywołanie metody");
		
		long readTimeByReflection = t.readValueByReflection(t, v);
		long writeTimeByReflection = t.writeValueByReflection(t, v);
		long invokeMethodByReflection = t.invokeMethodByReflection(t, v);
		t.processTime(readTimeByReflection, "odczyt zmiennej przez refleksje");
		t.processTime(writeTimeByReflection, "zapis zmiennej przez refleksje");
		t.processTime(invokeMethodByReflection, "wywołanie metody przez reflekcje");
		
	}
	
	public long readValue(Test t, Vehicle v) {
	   t.readValueManyTimes(v);	   
	   long startTime = System.nanoTime();
	   t.readValueManyTimes(v);	
	   long estimatedTime = System.nanoTime() - startTime;
	   
	   return estimatedTime;
	}
		
	public long writeValue(Test t, Vehicle v) {
	   t.writeValueManyTimes(v);   
	   long startTime = System.nanoTime();
	   t.writeValueManyTimes(v);   
	   long estimatedTime = System.nanoTime() - startTime;
	   
	   return estimatedTime;
	}
	
	public long invokeMethod(Test t, Vehicle v) {
		t.invokeMethodManyTimes(v);
		long startTime = System.nanoTime();
		t.invokeMethodManyTimes(v);   
		long estimatedTime = System.nanoTime() - startTime;
	   
		return estimatedTime;
	}
	
	public long readValueByReflection(Test t, Vehicle v) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	   t.readValueManyTimesByReflection(v);	   
	   long startTime = System.nanoTime();
	   t.readValueManyTimesByReflection(v);	
	   long estimatedTime = System.nanoTime() - startTime;
	   
	   return estimatedTime;
	}
			
	public long writeValueByReflection(Test t, Vehicle v) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	   t.writeValueManyTimesByReflection(v);   
	   long startTime = System.nanoTime();
	   t.writeValueManyTimesByReflection(v);   
	   long estimatedTime = System.nanoTime() - startTime;
	   
	   return estimatedTime;
	}
	
	public long invokeMethodByReflection(Test t, Vehicle v) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
	   t.invokeMethodManyTimesByReflection(v); 
	   long startTime = System.nanoTime();
	   t.invokeMethodManyTimesByReflection(v);   
	   long estimatedTime = System.nanoTime() - startTime;
	   
	   return estimatedTime;
	}
	
	public void readValueManyTimes(Vehicle v) {
		String make;
		for (int i = 0; i < TIMES ; i++) {
			make = v.make;
		}
	}
	
	public void writeValueManyTimes(Vehicle v) {
		for (int i = 0; i < TIMES ; i++) {
			v.make = "Ferrari";
		}
	}
	
	public void invokeMethodManyTimes(Vehicle v) {
		for (int i = 0; i < TIMES ; i++) {
			v.backMileage(100);
		}
	}
	
	public void readValueManyTimesByReflection(Vehicle v) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = v.getClass().getDeclaredField("make");
		for (int i = 0; i < TIMES ; i++) {
			f.get(v);
		}
	}
	
	public void writeValueManyTimesByReflection(Vehicle v) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = v.getClass().getDeclaredField("make");
		for (int i = 0; i < TIMES ; i++) {
			f.set(v, "Ferrari");
		}
	}
	
	public void invokeMethodManyTimesByReflection(Vehicle v) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Method m = v.getClass().getMethod("backMileage", int.class);
		for (int i = 0; i < TIMES ; i++) {
			m.invoke(v, 1000);
		}
	}
			
	public void processTime(long time, String operation) {
		time = time/TIMES;
		System.out.println("Czas wykonania " + operation+" na jeden rekord wynosi "+time+" nano sekundy na proces");
	}
}