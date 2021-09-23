package com.shows.boxoffice.config;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shows.boxoffice.core.MovieTotalTicketsCounterConsumer;
import com.shows.boxoffice.core.SoldTicketInfoSubscriber;
import com.shows.boxoffice.core.SoldTicketsInfoPrinterConsumer;
import com.shows.boxoffice.core.SoldTicketsInfoSupplier;
import com.shows.boxoffice.data.SoldTicketsInfo;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;

@Configuration
public class BoxOfficeAppConfiguration implements AppConstants {

	@Autowired
	public void initSoldTicketsInfoSupplierConsumer(
			@Qualifier("mttcSubscriber") SoldTicketInfoSubscriber mttcSubscriber,
			@Qualifier("stipSubscriber") SoldTicketInfoSubscriber stipSubscriber,
			SoldTicketsInfoSupplier supplier) {

		Flowable<SoldTicketsInfo> flowable = Flowable
				.fromStream(Stream.generate(supplier))
				.onBackpressureBuffer(10, false);
		ConnectableFlowable<SoldTicketsInfo> cflowable = flowable.publish();
		cflowable.subscribe(mttcSubscriber);
		cflowable.subscribe(stipSubscriber);
		cflowable.connect();
	}

	@Autowired
	@Bean(name = "mttcSubscriber")
	public SoldTicketInfoSubscriber getMovieTotalTicketsCounterSubscriber(
			@Qualifier("mttcConsumer") MovieTotalTicketsCounterConsumer mttcConsumer) {
		return new SoldTicketInfoSubscriber(mttcConsumer);
	}

	@Autowired
	@Bean(name = "stipSubscriber")
	public SoldTicketInfoSubscriber getSoldTicketsInfoPrinterSubscriber(
			@Qualifier("stipConsumer") SoldTicketsInfoPrinterConsumer stipConsumer) {
		return new SoldTicketInfoSubscriber(stipConsumer);
	}
}
