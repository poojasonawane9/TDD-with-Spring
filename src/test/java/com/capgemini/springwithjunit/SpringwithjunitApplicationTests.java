package com.capgemini.springwithjunit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capgemini.springwithjunit.entity.Product;
import com.capgemini.springwithjunit.service.ProductService;

import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringwithjunitApplicationTests {

	@MockBean
	ProductService service;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testSaveNewProduct() throws Exception {
		Product product = new Product(1,"HP");
		when(service.saveProduct(product)).thenReturn(product);
		mockMvc.perform(MockMvcRequestBuilders.post("/product/save"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testAllProduct() throws Exception {
		List<Product> product = new ArrayList<Product>();
		product.add(new Product(1, "HP"));
		product.add(new Product(2, "DELL"));
		
		when(service.findAll()).thenReturn(product);
		 mockMvc.perform(MockMvcRequestBuilders.get("/all")
	                .contentType(MediaType.APPLICATION_JSON)
				 ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
	}

}
