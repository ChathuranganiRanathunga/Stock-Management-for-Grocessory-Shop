package sample.modle;

public class Stock {
    private String productnames;                        //initialize variable
    private String productids;
    private  String productquantity;

    public String getProductnames(){return productnames;}
    public void setProductnames(String productnames){
        this.productnames=productnames;
    }
    public String getProductids(){return productids;}                   //get product Id

    public void setProductids(String productids) {
        this.productids = productids;
    }       //set product Ids

    public String getProductquantity() { return productquantity;}       //get Product Quantity

    public void setProductquantity(String productquantity) {
        this.productquantity = productquantity;
    }
}                                                               //set product Quantity

