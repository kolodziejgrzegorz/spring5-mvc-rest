package guru.spring.mvcrest.services;

import guru.spring.mvcrest.api.v1.mapper.CustomerMapper;
import guru.spring.mvcrest.api.v1.model.CustomerDTO;
import guru.spring.mvcrest.bootstrap.Bootstrap;
import guru.spring.mvcrest.domain.Customer;
import guru.spring.mvcrest.repository.CategoryRepository;
import guru.spring.mvcrest.repository.CustomerRepository;
import guru.spring.mvcrest.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        //setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void patchCustomerUpdateFirsName(){
        String updatedName = "updated Name";
        long id = getCustomerIdValue();

        Customer orginalCustomer = customerRepository.getOne(id);
        assertNotNull(orginalCustomer);

        String originalFirstName = orginalCustomer.getFirstName();
        String originalLastName = orginalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstName());
        assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
        assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));

    }

    @Test
    public void patchCustomerUpdateLastName(){
        String updatedName = "updated Name";
        long id = getCustomerIdValue();

        Customer orginalCustomer = customerRepository.getOne(id);
        assertNotNull(orginalCustomer);

        String originalFirstName = orginalCustomer.getFirstName();
        String originalLastName = orginalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getLastName());
        assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));

    }


    private Long getCustomerIdValue(){
        List<Customer> customers = customerRepository.findAll();

        return customers.get(0).getId();
    }

}
