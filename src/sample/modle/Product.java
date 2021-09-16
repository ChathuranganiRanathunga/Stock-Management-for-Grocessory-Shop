package sample.modle;

public class Product {
    private String Pcategoryname;               //initialize Variables
    private String productname;
    private String productid;


    public  String getPcategoryname(){return Pcategoryname;}        //get Value

    public void setPcategoryname(String Pcategoryname) {            //set Value
        this.Pcategoryname = Pcategoryname;
    }
    public  String getProductname(){return productname;}            //getValue

    public void setProductname(String productname) {                //Set Value in here
        this.productname = productname;

    }
    public String getProductid(){ return productid;}                                //get Value
    public void setProductid(String productid ){
        this.productid=productid;
    }       //Set Value
}
