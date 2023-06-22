package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Component
public class BoardDataInitializer implements ApplicationRunner{

	@Autowired
	BoardRepository boardRep;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Random rd = new Random();

		for (int i = 1; i <= 100; i++) {
			 boardRep.save(Board.builder()
					.title("Title" + i)
					.writer("tester" + i)
					.content("Content" + i)
					.createDate(new Date())
					.cnt(rd.nextLong(0,101))
					.build());
			 
		}
	}

}
