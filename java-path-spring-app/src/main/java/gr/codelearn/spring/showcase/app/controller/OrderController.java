package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.OrderService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import gr.codelearn.spring.showcase.app.transfer.PurchasesPerCustomerCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController extends AbstractController<Order> {
	private final OrderService orderService;

	@Override
	protected BaseService<Order, Long> getBaseService() {
		return orderService;
	}

	@GetMapping
	@Override
	public ResponseEntity<ApiResponse<List<Order>>> findAll() {
		return new ResponseEntity<>(ApiResponse.<List<Order>>builder().data(orderService.findAllLazy()).build(),
									HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(headers = "reporting=totalNumberAndCostOfPurchasesPerCustomerCategory")
	public ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> totalNumberAndCostOfPurchasesPerCustomerCategory() {
		return new ResponseEntity<>(ApiResponse.<List<PurchasesPerCustomerCategoryDto>>builder().data(orderService.findTotalNumberAndCostOfPurchasesPerCustomerCategory())
											   .build(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(headers = "reporting=averageOrderCostPerCustomer")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> averageOrderCostPerCustomer() {
		return new ResponseEntity<>(ApiResponse.<List<KeyValue<String, BigDecimal>>>builder().data(orderService.findAverageOrderCostPerCustomer()).build(),
									HttpStatus.OK);
	}

	@GetMapping("v1")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> averageOrderCostPerCustomerV1() {
		return getV1Content();
	}

	@GetMapping("v2")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> averageOrderCostPerCustomerV2() {
		return new ResponseEntity<>(ApiResponse.<List<KeyValue<String, BigDecimal>>>builder().data(null).build(),
									HttpStatus.OK);
	}

	@GetMapping(headers = "X-API-VERSION=1")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> averageOrderCostPerCustomerHV1() {
		return getV1Content();
	}

	@GetMapping(headers = "X-API-VERSION=2")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> averageOrderCostPerCustomerHV2() {
		return getV2Content();
	}

	@GetMapping(params = "version=1")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> averageOrderCostPerCustomerPV1() {
		return getV1Content();
	}

	@GetMapping(params = "version=2")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> averageOrderCostPerCustomerPV2() {
		return getV2Content();
	}

	@GetMapping(produces = "application/vnd.api-v1+json")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> averageOrderCostPerCustomerPPV1() {
		return getV1Content();
	}

	@GetMapping(produces = "application/vnd.api-v2+json")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> averageOrderCostPerCustomerPPV2() {
		return getV2Content();
	}

	private ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> getV1Content() {
		return getContent(orderService.findAverageOrderCostPerCustomer());
	}

	private ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> getV2Content() {
		return getContent(null);
	}

	private ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> getContent(
			List<KeyValue<String, BigDecimal>> input) {
		return new ResponseEntity<>(ApiResponse.<List<KeyValue<String, BigDecimal>>>builder().data(input).build(),
									HttpStatus.OK);
	}
}
