package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T, Long> {
	public abstract JpaRepository<T, Long> getRepository();

	@Override
	public T create(final T item) {
		return getRepository().save(item);
	}

	@Override
	public List<T> createAll(final List<T> items) {
		return getRepository().saveAll(items);
	}

	@Override
	public List<T> createAll(final T... items) {
		return createAll(Arrays.asList(items));
	}

	@Override
	public void update(final T item) {
		getRepository().save(item);
	}

	@Override
	public void delete(final T item) {
		getRepository().delete(item);
	}

	@Override
	public void deleteById(final Long id) {
		getRepository().deleteById(id);
	}

	@Override
	public T get(final Long id) {
		return getRepository().getById(id);
	}

	@Override
	public boolean exists(final T item) {
		return getRepository().existsById(item.getId());
	}

	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}
}
