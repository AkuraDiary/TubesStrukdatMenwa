// redirect to '../.adt/DllCustomer.java'
// redirect to '../.nodes/NodeCustomer.java'
// redirect to '../.nodes/NodeTransaksi.java'
// redirect to '../.sources/CustomerDataSources.java'


package data.schemas.models;

public class Customer {
    int id ;
    String name, email, identity_number, phone, address;

    public Customer(int id, String name, String email, String identity_number, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.identity_number = identity_number;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer:\n" +
                "id: " + id +
                "\nname: " + name +
                "\nemail: '" + email +
                "\nidentity_number: " + identity_number +
                "\nphone: " + phone +
                "\naddress: " + address;
    }
}
