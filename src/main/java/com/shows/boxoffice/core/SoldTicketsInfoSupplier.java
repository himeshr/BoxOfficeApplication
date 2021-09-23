package com.shows.boxoffice.core;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Uninterruptibles;
import com.shows.boxoffice.config.AppConstants;
import com.shows.boxoffice.data.SeatType;
import com.shows.boxoffice.data.ShowTime;
import com.shows.boxoffice.data.SoldTicketsInfo;

@Component
public class SoldTicketsInfoSupplier implements Supplier<SoldTicketsInfo>, AppConstants {

	private static final int MAX_TICKET_SALE_UNIT = 1;
	private static final int TICKET_SALE_INTERVAL = 1000;
	
	private Random random;
	private Supplier<SoldTicketsInfo> soldTicketsInfoSupplier;

	@Autowired
	public SoldTicketsInfoSupplier() {
		super();
		this.random = new Random();
		this.soldTicketsInfoSupplier = () -> {
			sleepForRandomDuration(); 

			SoldTicketsInfo sti = new SoldTicketsInfo();
			sti.setNumberOfTickets(getRandomTickets());
			sti.setSeatType(getRandomSeatType());
			sti.setShowDate(getRandomShowDate());
			sti.setMovieName(getRandomMovieName());
			sti.setShowTime(getRandomShowTime());
			System.out.println("SUPPLIER: Sold tickets detail: " + sti.toString());
			return sti;
		};
	}

	private void sleepForRandomDuration() {
		Uninterruptibles.sleepUninterruptibly(
				(random.nextInt(MAX_TICKET_SALE_UNIT)+INTEGER_CONSTANT_1) * TICKET_SALE_INTERVAL,
				TimeUnit.MILLISECONDS);
	}

	public SoldTicketsInfo get() {
		return soldTicketsInfoSupplier.get();
	}

	private int getRandomTickets() {
		return random.nextInt(MAX_TICKETS)+INTEGER_CONSTANT_1;
	}

	private SeatType getRandomSeatType() {
		return types[random.nextInt(types.length)];
	}

	private ShowTime getRandomShowTime() {
		return times[random.nextInt(times.length)];
	}

	private String getRandomShowDate() {
		return dates[random.nextInt(dates.length)];
	}

	private String getRandomMovieName() {
		return names[random.nextInt(names.length)];
	}
}

