package tn.globebusiness.spring.orders.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponse {
	private String message;
	private HashMap<String, Long> response;
}
