package com.bicyclebooking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
class BicycleBookingApplicationTests {

	@Test
	void contextLoads() {

	}

	public static void main (String[] args){
		LocalDateTime a = LocalDateTime.now();
		System.out.println(LocalDateTime.now().compareTo(a));

	}

}
