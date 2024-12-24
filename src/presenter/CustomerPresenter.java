package presenter;

import data.schemas.adt.DllCustomer;
import data.schemas.models.Customer;
import repositories.CustomerRepository;

public class CustomerPresenter {
    CustomerRepository customerRepository;

    public CustomerPresenter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer selectedCust = null;

    public void saveCustomer(int id, String name, String email, String identity_number, String phone, String address) {
        Customer newData = new Customer(id, name, email, identity_number, phone, address);
        customerRepository.addCustomer(newData);
    }

    public void removeCustomer(int id) {
        customerRepository.removeCustomer(id);
    }

    public void selectCustomer(int id) {
        customerRepository.selectCustomer(id);
        selectedCust = customerRepository.selectedCustomer;
    }

    public void updateCustomer(int id, String name, String email, String identity_number, String phone, String address) {
        Customer newData = new Customer(id, name, email, identity_number, phone, address);
        customerRepository.updateCustomer(newData);
    }

    public DllCustomer getAllCustomers() {
         return  customerRepository.getAllCustomers();
    }

    public void selectCustomerByName(String name) {
        customerRepository.selectCustomerByName(name);
        selectedCust = customerRepository.selectedCustomer;
    }
}
