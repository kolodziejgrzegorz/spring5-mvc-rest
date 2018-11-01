package guru.spring.mvcrest.controllers.v1;

import guru.spring.mvcrest.api.v1.model.CustomerDTO;
import guru.spring.mvcrest.controllers.RestResponseEntityExceptionHandler;
import guru.spring.mvcrest.services.CustomerService;
import guru.spring.mvcrest.services.ResourceNotFoundException;
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

import static guru.spring.mvcrest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getCustomerList() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Jan");
        customer.setLastName("Kowalski");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("Aleksander");
        customer2.setLastName("Nowak");

        List<CustomerDTO> customerDTOS = Arrays.asList(customer, customer2);

        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {

        CustomerDTO customer = new CustomerDTO();
        customer.setLastName(NAME);
        customer.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", equalTo(NAME)));
    }

    @Test
    public void getCustomerByIdNotFound() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/1222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createNewCustomer() throws Exception {

        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstName("Michale");
        returnDto.setLastName("Weston");
        returnDto.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.createNewCustomer(customer1)).thenReturn(returnDto);

        mockMvc.perform(post(CustomerController.BASE_URL )
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Michale")))
                .andExpect(jsonPath("$.customer_url",equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void updateCustomer() throws Exception {

        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstName("Michale");
        returnDto.setLastName("Weston");
        returnDto.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDto);

        mockMvc.perform(put(CustomerController.BASE_URL + "/1" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michale")))
                .andExpect(jsonPath("$.customer_url",equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void patchCustomer() throws Exception {

        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Michale");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstName("Michale");
        returnDto.setLastName("Weston");
        returnDto.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDto);

        mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michale")))
                .andExpect(jsonPath("$.lastName", equalTo("Weston")))
                .andExpect(jsonPath("$.customer_url",equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteById(anyLong());
    }
}