package com.nikhil.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {

	public static void main(String[] args) {

		int availableProcessors = Runtime.getRuntime().availableProcessors();

		ExecutorService es = Executors.newFixedThreadPool(availableProcessors);

		for (int i = 0; i < 1000; i++) {
			System.out.println(i);
			es.execute(new Task());
		}

	}

}
