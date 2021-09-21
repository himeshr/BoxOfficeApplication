package com.shows.boxoffice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoldTicketsInfo {
	private int numberOfTickets;
	private SeatType seatType;
    private ShowTime showTime;
    private String showDate;
    private String movieName;
}
