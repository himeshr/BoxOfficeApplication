package com.shows.boxoffice.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shows.boxoffice.config.AppConstants;
import com.shows.boxoffice.data.SoldTicketsInfo;

@Component
public class MovieTotalTicketsCounterConsumer implements Consumer<SoldTicketsInfo>, AppConstants {

	private ConcurrentHashMap<String, Integer> movieTicketsMap;
	private PropertyChangeSupport support;
	
	@Autowired
	public MovieTotalTicketsCounterConsumer() {
		super();
		this.movieTicketsMap = new ConcurrentHashMap<>();
		this.support = new PropertyChangeSupport(this);;
	}
	
	@Override
	public void accept(SoldTicketsInfo sti) {
		computeTotalMovieTickets(sti);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
	
	private void computeTotalMovieTickets(SoldTicketsInfo sti) {
		int newTotal = movieTicketsMap.merge(sti.getMovieName(),
				sti.getNumberOfTickets(), Integer::sum);
		System.out.println(String.format("Total tickets sold for %s = %d",
				sti.getMovieName(), newTotal));
		support.firePropertyChange(sti.getMovieName(),
				newTotal-sti.getNumberOfTickets(), newTotal);
	}
}
