package guru.spring.mvcrest.bootstrap;

import guru.spring.mvcrest.domain.Category;
import guru.spring.mvcrest.domain.Customer;
import guru.spring.mvcrest.repository.CategoryRepository;
import guru.spring.mvcrest.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        loadCustomers();

    }

    private void loadCustomers() {
        Customer customer = new Customer();
        customer.setFirstName("Jan");
        customer.setLastName("Kowalski");

        Customer customer2 = new Customer();
        customer2.setFirstName("Aleksander");
        customer2.setLastName("Nowak");

        Customer customer3 = new Customer();
        customer3.setFirstName("Ula");
        customer3.setLastName("Kwaśna");

        customerRepository.save(customer);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        System.out.println("Customers loaded :" + customerRepository.count());
    }

    private void loadCategories() {
        Category fruits  = new Category();
        fruits.setName("Fruits");

        Category dried  = new Category();
        dried.setName("Dried");

        Category fresh  = new Category();
        fresh.setName("Fresh");

        Category exotic  = new Category();
        exotic.setName("Exotic");

        Category nuts  = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories loaded :" + categoryRepository.count());
    }
}
