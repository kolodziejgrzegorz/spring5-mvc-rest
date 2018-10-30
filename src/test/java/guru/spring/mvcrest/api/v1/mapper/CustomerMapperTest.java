package guru.spring.mvcrest.api.v1.mapper;

import guru.spring.mvcrest.api.v1.model.CustomerDTO;
import guru.spring.mvcrest.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final String NAME = "Kowalski";
    public static final Long ID = 2L;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {

        Customer customer = new Customer();
        customer.setLastName(NAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(NAME, customerDTO.getLastName());
    }
}