package gr.codelearn.spring.showcase.demo.component;

import gr.codelearn.spring.showcase.demo.base.BaseComponent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Prototype extends BaseComponent {
	private int value = 0;

	public void increaseValueByOne() {
		logger.info("Increasing bean's value by one to {}.", ++value);
	}
}
