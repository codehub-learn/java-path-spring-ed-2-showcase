package gr.codelearn.spring.showcase.demo.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class BaseComponent {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		logger.trace("Loaded {}.", getClass().getName());
	}

	@PreDestroy
	public void destroy() {
		logger.trace("{} about to be destroyed.", getClass().getName());
	}

}
