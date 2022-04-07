package gr.codelearn.spring.showcase.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.CustomerService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController extends AbstractController<Customer> {
	private final CustomerService customerService;

	@GetMapping(params={"email"})
	public ResponseEntity<ApiResponse<Customer>> findByEmail(@RequestParam String email){
		return ResponseEntity.ok(ApiResponse.<Customer>builder().data(customerService.findByEmail(email)).build());
	}

	@Override
	protected BaseService<Customer, Long> getBaseService() {
		return customerService;
	}

	@GetMapping(value = "filtered/{id}")
	public ResponseEntity<ApiResponse<JsonNode>> findByIdFiltered(@PathVariable Long id){
		Customer customer = customerService.get(id);
		JsonNode customerAsJsonNode = filterCustomer(customer);
		return ResponseEntity.ok(ApiResponse.<JsonNode>builder().data(customerAsJsonNode).build());
	}

	public JsonNode filterCustomer(Customer customer){
		ObjectMapper mapper = new ObjectMapper();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("firstname", "lastname");
		FilterProvider filters = new SimpleFilterProvider().addFilter("customerFilter", filter);
		mapper.setFilterProvider(filters);
		ObjectWriter writer = mapper.writer();
		try {
			String customerAsJsonString = writer.writeValueAsString(customer);
			return mapper.readTree(customerAsJsonString);
		} catch (JsonProcessingException e) {
			logger.error("Something went wrong during filtering", e);
		}
		return null;
	}

}
