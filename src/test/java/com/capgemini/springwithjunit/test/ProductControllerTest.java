package com.capgemini.springwithjunit.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capgemini.springwithjunit.entity.Product;
import com.capgemini.springwithjunit.service.ProductService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

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

}
