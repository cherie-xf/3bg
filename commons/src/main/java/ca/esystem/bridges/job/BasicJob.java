package ca.esystem.bridges.job;

import org.springframework.stereotype.Service;

@Service
public class BasicJob {

	public void echo() {
		System.out.println("Executing job...");
	}
}
