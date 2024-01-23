package com.example.sqch08ex2;

import com.example.sqch08ex2.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MainTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Test that /products page can be successfully called.")
	void testPageRequestAndContent() throws Exception {
		mockMvc.perform(get("/products")).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test POST method")
	void testPOST() throws Exception {
		mockMvc.perform(post("/products")
						.param("name", "beer")
						.param("price", "12.5"))
				.andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("<td>beer</td>")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("<td>12.5</td>")));
	}

	@Test
	@DisplayName("Test GET method")
	void testGET() throws Exception {
		mockMvc.perform(post("/products")
				.param("name", "beer")
				.param("price", "12.5"));
		mockMvc.perform(post("/products")
				.param("name", "cake")
				.param("price", "13.0"));

		mockMvc.perform(get("/products"))
				.andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("<td>beer</td>\n" +
						"        <td>12.5</td>")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("<td>cake</td>\n" +
						"        <td>13.0</td>")))
				.andDo(print());
	}

}
