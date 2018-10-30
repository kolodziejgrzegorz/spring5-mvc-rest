package guru.spring.mvcrest.controllers.v1;

import guru.spring.mvcrest.api.v1.model.CustomerDTO;
import guru.spring.mvcrest.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String NAME = "Kowalski";
    public static final Long ID = 2L;

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Jan");
        customer.setLastName("Kowalski");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("Aleksander");
        customer2.setLastName("Nowak");

        List<CustomerDTO> customerDTOS = Arrays.asList(customer, customer2);

        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

//    @Test
//    public void getCustomerByName() throws Exception {
//
//        CustomerDTO customer = new CustomerDTO();
//        customer.setLastName(NAME);
//
//        when(customerService.getCustomerByLastName(any())).thenReturn(customer);
//
//        mockMvc.perform(get("/api/v1/customers/" + NAME)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.lastName", equalTo(NAME)));
//    }

    @Test
    public void getCustomerById() throws Exception {

        CustomerDTO customer = new CustomerDTO();
        customer.setLastName(NAME);
        customer.setCustomerUrl("/api/v1/customers/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", equalTo(NAME)));
    }
}