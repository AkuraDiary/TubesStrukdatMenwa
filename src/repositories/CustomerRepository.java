package repositories;

import data.schemas.adt.DllCustomer;
import data.schemas.models.Customer;
import data.sources.CustomerDataSource;

public class CustomerRepository {

    CustomerDataSource customerDataSource;

    Customer selectedCustomer;

    public CustomerRepository(CustomerDataSource customerDataSource) {
        this.customerDataSource = customerDataSource;
    }

    public void addCustomer(Customer data) {
        customerDataSource.customerList.insertSorted(data);
    }

    public void removeCustomer(int id) {
        customerDataSource.customerList.deleteById(id);
    }

    public void selectCustomer(int id) {
        selectedCustomer = customerDataSource.customerList.searchById(id);
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void updateCustomer(Customer data) {
        customerDataSource.customerList.deleteById(data.getId());
        customerDataSource.customerList.insertSorted(data);
    }

    public DllCustomer getAllCustomers() {
        return customerDataSource.customerList;
    }

}
