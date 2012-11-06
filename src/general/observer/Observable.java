package general.observer;

import java.util.HashSet;
import java.util.Collection;

public class Observable {
	
	private Collection<Observer> observers = new HashSet<Observer>();
	
	public Observable() {
		
	}
	
	public Observable(Observable observable) {
		setObservers(observable.observers);
	}
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void removeObservers() {
		observers.clear();
	}
	
	public Collection<Observer> getObservers() {
		return new HashSet<Observer>(observers);
	}
	
	public void setObservers(Collection<Observer> observers) {
		this.observers = new HashSet<Observer>(observers);
	}
	
	public void notifyObservers(Object args) {
		for (Observer observer: observers) 
			observer.observe(this, args);
	}
	
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	
	

}
