package gr.codelearn.spring.showcase.demo.component;

import gr.codelearn.spring.showcase.demo.base.BaseComponent;
import gr.codelearn.spring.showcase.demo.domain.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
public class SampleBean extends BaseComponent {
	private final SecondBean secondBean;
	private String firstname;
	private String lastname;

	@PostConstruct
	public void initSecondVersion() {
		logger.debug("Received this message: {}.", secondBean.sayHello());
		User sampleUser = User.builder().firstname("John").lastname("Pappas").build();
		logger.debug("Generated user {}.", sampleUser);
	}
}
