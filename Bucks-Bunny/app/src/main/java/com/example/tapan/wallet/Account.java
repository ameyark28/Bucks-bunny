package com.example.tapan.wallet;

public class Account {

    private float amount;
    private boolean type;
    private String category;

    public void setAmount(float amount){
        this.amount=amount;
    }
    public void setType(String s){
      if(s=="credit")
          type=true;
      else
          type=false;
    }
    public void setCategory(String category){
        this.category=category;
    }

    public float getAmount(){
        return amount;
    }

    public boolean getType(){return type;}

    public String getCategory() {
        return category;
    }


}
