package gr.codelearn.spring.showcase.demo.component;

import gr.codelearn.spring.showcase.demo.base.BaseComponent;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SecondBean extends BaseComponent {
	private Long id;
	private String address;

	public String sayHello() {
		return "Hello from SecondBean class";
	}
}
