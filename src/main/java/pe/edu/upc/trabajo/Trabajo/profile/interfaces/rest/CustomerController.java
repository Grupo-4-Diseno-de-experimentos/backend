package pe.edu.upc.trabajo.Trabajo.profile.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajo.Trabajo.profile.application.internal.CustomerService;
import pe.edu.upc.trabajo.Trabajo.profile.domain.model.entities.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/{id}")
    public ResponseEntity<Customer> createOrUpdateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer savedCustomer = customerService.createOrUpdateCustomer(id, customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerByUserId(@PathVariable Long id) {
        Customer customer = customerService.getCustomerByUserId(id);
        return ResponseEntity.ok(customer);
    }
}
