package com.shows.boxoffice.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Uninterruptibles;
import com.shows.boxoffice.config.AppConstants;
import com.shows.boxoffice.data.SeatType;
import com.shows.boxoffice.data.SoldTicketsInfo;

@Component
public class SoldTicketsInfoPrinterConsumer implements Consumer<SoldTicketsInfo>, AppConstants {

	@Override
	public void accept(SoldTicketsInfo sti) {
		if(sti.getSeatType().equals(SeatType.Gold)) {
			CompletableFuture.runAsync(() -> {
				Uninterruptibles.sleepUninterruptibly(1000,
						TimeUnit.MILLISECONDS);
				System.out.println(String.format(
						"Asynchronously sent %s class tickets info for movie: %s ", 
						sti.getSeatType(), sti.getMovieName()));
		    });
		}
	}
}
