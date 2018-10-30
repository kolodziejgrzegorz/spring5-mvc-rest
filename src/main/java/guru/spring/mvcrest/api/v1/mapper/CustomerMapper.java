package guru.spring.mvcrest.api.v1.mapper;

import guru.spring.mvcrest.api.v1.model.CustomerDTO;
import guru.spring.mvcrest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
