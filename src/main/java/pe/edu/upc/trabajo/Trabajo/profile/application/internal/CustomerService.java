package pe.edu.upc.trabajo.Trabajo.profile.application.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.profile.domain.model.entities.Customer;
import pe.edu.upc.trabajo.Trabajo.profile.infrastructure.repositories.jpa.ICustomerRepository;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.IUserRepository;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IUserRepository userRepository;

    public Customer createOrUpdateCustomer(Long userId, Customer customerData) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Customer customer = customerRepository.findByUserId(userId).orElse(new Customer());
        customer.setGoal(customerData.getGoal());
        customer.setMethod(customerData.getMethod());
        customer.setSexo(customerData.getSexo());
        customer.setEdad(customerData.getEdad());
        customer.setAltura(customerData.getAltura());
        customer.setPeso(customerData.getPeso());
        customer.setActivityLevel(customerData.getActivityLevel());
        customer.setDietType(customerData.getDietType());
        customer.setUser(user);

        return customerRepository.save(customer);
    }

    public Customer getCustomerByUserId(Long userId) {
        return customerRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
