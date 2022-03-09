package gr.codelearn.spring.showcase.demo.component;

import gr.codelearn.spring.showcase.demo.base.BaseComponent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyBean extends BaseComponent {
	public String sayHello() {
		return "Hello, guess I am lazy.";
	}
}
