package com.shows.boxoffice.config;

import com.shows.boxoffice.data.SeatType;
import com.shows.boxoffice.data.ShowTime;

public interface AppConstants {

	public int INTEGER_CONSTANT_1 = 1;
	public SeatType[] types = SeatType.values();
	public ShowTime[] times = ShowTime.values();
	public String[] dates = {"21-09-2021", "22-09-2021", "23-09-2021"};
	public String[] names = {"Avengers", "Batman", "Cinderella"};
	public int MAX_TICKETS = 5;
	public int MOVIE_TOTAL_TICKETS_ALERT_THRESHOLD = 10;
}
