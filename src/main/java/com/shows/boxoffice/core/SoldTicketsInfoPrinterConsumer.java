package com.shows.boxoffice.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Uninterruptibles;
import com.shows.boxoffice.config.AppConstants;
import com.shows.boxoffice.data.SeatType;
import com.shows.boxoffice.data.SoldTicketsInfo;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Component("stipConsumer")
public class SoldTicketsInfoPrinterConsumer implements Consumer<SoldTicketsInfo>, AppConstants {

	@Override
	public void accept(SoldTicketsInfo sti) {
		if(sti.getSeatType().equals(SeatType.Gold)) {
			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
				Uninterruptibles.sleepUninterruptibly(10,
						TimeUnit.MILLISECONDS);
		    });
			Maybe.fromFuture(future)
			.subscribeOn(Schedulers.io())
			.subscribe(v -> System.out.println("Got " + v),
                    e -> System.err.println("Exception " + e),
                    () -> System.out.println(String.format(
    						"ASYNC-CONSUMER: Asynchronously sent %s class tickets info for movie: %s ", 
    						sti.getSeatType(), sti.getMovieName())));
		}
	}
}
