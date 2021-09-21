package com.shows.boxoffice.core;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.shows.boxoffice.config.AppConstants;
import com.shows.boxoffice.data.SoldTicketsInfo;

@Component
public class SoldTicketsInfoPrinterConsumer implements Consumer<SoldTicketsInfo>, AppConstants {

	@Override
	public void accept(SoldTicketsInfo sti) {
		System.out.println("Received tickets info for movie: " + sti.getMovieName());
	}
}
