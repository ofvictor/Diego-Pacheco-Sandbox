package com.blogspot.diegopacheco.sandbox.github.java.akka.typed;

import java.util.HashMap;
import java.util.Map;

import akka.actor.TypedActor;

/**
 * 
 * ChickHotel 
 *
 * @author pacheco
 * @version 1.0
 * @since 13/12/2010
 *
 */
@SuppressWarnings("unchecked")
public class ChickHotel extends TypedActor implements HotelBook{
	
	private Map<String,People> control = new HashMap<String, People>();
	
	public People checkOut(String name) {
		People p = control.get(name);
		if (p!=null) return p;
		throw new RuntimeException("People " + name + " is not here");
	}
	
	public void register(String peopleName) {
		control.put(peopleName, new People(peopleName));
		System.out.println("People " + peopleName + " registered!");    
	}
	
}
