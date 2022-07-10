package org.he.myunsplash.app;

import org.he.myunsplash.app.dao.repository.PhotoRepository;
import org.he.myunsplash.app.model.Photo;
import org.he.myunsplash.app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyUnsplashApplication implements CommandLineRunner {
	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private JsonParser jsonParser;

	public static void main(String[] args) {
		SpringApplication.run(MyUnsplashApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		jsonParser
//				.readFromJSON()
//				.forEach(photoRepository::save);

		System.out.println("-------------------------------------");
		photoRepository
				.findAll()
				.forEach(System.out::println);
	}
}
