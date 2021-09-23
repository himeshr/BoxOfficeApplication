package com.shows.boxoffice.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shows.boxoffice.config.AppConstants;
import com.shows.boxoffice.data.SoldTicketsInfo;

@Component("mttcConsumer")
public class MovieTotalTicketsCounterConsumer implements Consumer<SoldTicketsInfo>, AppConstants {

	private ConcurrentHashMap<String, Integer> movieTicketsMap;
	
	@Autowired
	public MovieTotalTicketsCounterConsumer() {
		super();
		this.movieTicketsMap = new ConcurrentHashMap<>();
	}
	
	@Override
	public void accept(SoldTicketsInfo sti) {
		computeTotalMovieTickets(sti);
	}
	
	private void computeTotalMovieTickets(SoldTicketsInfo sti) {
		int newTotal = movieTicketsMap.merge(sti.getMovieName(),
				sti.getNumberOfTickets(), Integer::sum);
		System.out.println(String.format("CONSUMER: Total tickets sold for %s = %d",
				sti.getMovieName(), newTotal));
		if(newTotal > MOVIE_TOTAL_TICKETS_ALERT_THRESHOLD) {
			System.err.println(String.format("ALERT!! %s movie has sold more than %d tickets.",
					sti.getMovieName(), MOVIE_TOTAL_TICKETS_ALERT_THRESHOLD));
		}
	}
}
