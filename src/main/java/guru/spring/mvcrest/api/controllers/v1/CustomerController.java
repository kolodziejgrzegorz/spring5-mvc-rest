package guru.spring.mvcrest.api.controllers.v1;

import guru.spring.mvcrest.api.v1.model.CustomerDTO;
import guru.spring.mvcrest.api.v1.model.CustomerListDTO;
import guru.spring.mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    private ResponseEntity<CustomerListDTO> getAllCustomers(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK );
    }

    @GetMapping("/{name}")
    private ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String name){
        return new ResponseEntity<CustomerDTO>(customerService.findByName(name), HttpStatus.OK);
    }
}
