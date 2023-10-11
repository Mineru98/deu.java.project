package ac.kr.deu.FindEmptyClassroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@EnableAspectJAutoProxy
@EnableJpaRepositories
@SpringBootApplication
public class FindEmptyClassroomApplication {

  public static void main(String[] args) {
    SpringApplication.run(FindEmptyClassroomApplication.class, args);
  }
}
