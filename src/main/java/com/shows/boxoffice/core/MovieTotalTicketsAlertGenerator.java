package com.shows.boxoffice.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.springframework.stereotype.Component;

import com.shows.boxoffice.config.AppConstants;

@Component
public class MovieTotalTicketsAlertGenerator implements PropertyChangeListener, AppConstants{

	public void propertyChange(PropertyChangeEvent evt) {
		if((Integer)evt.getNewValue() > MOVIE_TOTAL_TICKETS_ALERT_THRESHOLD) {
			System.out.println(String.format("ALERT!! %s movie has sold more than %d tickets.",
					evt.getPropertyName(), MOVIE_TOTAL_TICKETS_ALERT_THRESHOLD));
		}
	}
}
