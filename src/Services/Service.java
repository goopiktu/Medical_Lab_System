package Services;

public class Service {
    private String serviceCode;
    private String description;
    private String Price;

    public Service() {

        serviceCode = "";
        description = "";
        Price = "";
    }

    public Service(String serviceCode,
            String description,
            String Price) {

        this.serviceCode = serviceCode;
        this.description = description;
        this.Price = Price;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
