package guru.spring.mvcrest.bootstrap;

import guru.spring.mvcrest.domain.Category;
import guru.spring.mvcrest.domain.Customer;
import guru.spring.mvcrest.domain.Vendor;
import guru.spring.mvcrest.repository.CategoryRepository;
import guru.spring.mvcrest.repository.CustomerRepository;
import guru.spring.mvcrest.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadVendors(){
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor One");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor Two");
        vendorRepository.save(vendor2);
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
