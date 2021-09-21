package com.shows.boxoffice.config;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.shows.boxoffice.core.MovieTotalTicketsAlertGenerator;
import com.shows.boxoffice.core.MovieTotalTicketsCounterConsumer;
import com.shows.boxoffice.core.SoldTicketsInfoPrinterConsumer;
import com.shows.boxoffice.core.SoldTicketsInfoSupplier;

@Configuration
public class BoxOfficeAppConfiguration implements AppConstants {

	@Autowired
	public void initSoldTicketsInfoSupplierConsumer(SoldTicketsInfoSupplier supplier,
			SoldTicketsInfoPrinterConsumer stipconsumer,
			MovieTotalTicketsCounterConsumer mttcConsumer) {
		Stream.generate(supplier).forEach(stipconsumer.andThen(mttcConsumer));
	}

	@Autowired
	public void initMovieTotalTicketsCounterChangeListener(
			MovieTotalTicketsCounterConsumer movieTotalTicketsCounterConsumer,
			MovieTotalTicketsAlertGenerator movieTotalTicketsAlertGenerator) {
		movieTotalTicketsCounterConsumer
		.addPropertyChangeListener(movieTotalTicketsAlertGenerator);
	}
}
