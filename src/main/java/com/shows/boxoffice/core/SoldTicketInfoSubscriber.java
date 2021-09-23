package com.shows.boxoffice.core;


import java.util.function.Consumer;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.shows.boxoffice.data.SoldTicketsInfo;

public class SoldTicketInfoSubscriber implements Subscriber<SoldTicketsInfo> {

	private Subscription subscription;
	private Consumer<SoldTicketsInfo> consumer;
	
	public SoldTicketInfoSubscriber(
			Consumer<SoldTicketsInfo> consumer) {
		super();
		this.consumer = consumer;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1); 
		System.out.println(consumer + "Subscribed to SoldTicketInfoSubscriber");
	}

	@Override
	public void onNext(SoldTicketsInfo item) {
		consumer.accept(item);
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println(throwable);
	}

	@Override
	public void onComplete() {
		System.out.println("Subscriber completed processing all sold tickets info.");
	}
}
