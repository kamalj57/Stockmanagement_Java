package com.example.demo1;


public  class StockItem {
    private String  idproduct;
    private String pname;
    private String pstock;
    private String ptype;
    private String icost;
    private String tcost;

     StockItem(String id,String name, String quantity, String type,String cost,String tcost) {
         this.idproduct = id;
         this.pname = name;
         this.pstock = quantity;
         this.ptype =type;
         this.icost=cost;
         this.tcost=tcost;
     }

     public void setPtype(String ptype) {
         this.ptype = ptype;
     }

     public void setPstock(String pstock) {
         this.pstock = pstock;
     }

     public String getPstock() {
         return pstock;
     }

     public void setPname(String pname) {
         this.pname = pname;
     }

     public String getPtype() {
         return ptype;
     }

     public String getIdproduct() {
         return idproduct;
     }

     public String getPname() {
         return pname;
     }

     public void setIdproduct(String idproduct) {
         this.idproduct = idproduct;
     }

    public String getIcost() {
        return icost;
    }

    public String getTcost() {
        return tcost;
    }

    public void setIcost(String icost) {
        this.icost = icost;
    }

    public void setTcost(String tcost) {
        this.tcost = tcost;
    }
}

