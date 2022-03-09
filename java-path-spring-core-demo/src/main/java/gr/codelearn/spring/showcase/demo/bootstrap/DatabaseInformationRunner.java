package gr.codelearn.spring.showcase.demo.bootstrap;

import gr.codelearn.spring.showcase.demo.base.BaseComponent;
import gr.codelearn.spring.showcase.demo.component.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class DatabaseInformationRunner extends BaseComponent implements CommandLineRunner {
	private final DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {
		dataSource.createConnectionPool();
	}
}
