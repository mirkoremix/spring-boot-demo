package com.united.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		System.out.println("Hello MIMI!!!");
	}

// for testing purposes. Better way to test by writing tests! jokesonyou
//	@Bean
//	public CommandLineRunner commandLineRunner(
//			AuthorRepository repository,
//			CourseRepository courseRepository,
//			SectionRepository sectionRepository
//	) {
//		System.out.println();
//		return args -> {
//
//			var course = Course.builder().title("nesto11").description("nesto11").build();
//
//			var author = Author.builder()
//					.firstName("Ali")
//					.lastName("Bouali")
//					.email("contactt@aliboucoding.com")
////					.courses(List.of(course))
//					.build();
//			course.setAuthors(List.of(author));
//
//			var section = Section.builder().name("sekcija 1").order("order 1").course(course).build();
//			sectionRepository.save(section);
//
////			courseRepository.save(course);
//		};

//	}

}
