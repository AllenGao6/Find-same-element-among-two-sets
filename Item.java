public class Item {
    private String name;
    private int price;
    
    public Item(String name, int price){
        this.name = name;
        this.price = price;
    }
    
    public String getName(){
        return name;
    }
    
    public int getPrice(){
        return price;
    }   
    public boolean equals(Object o){
        Item t = (Item) o;
        if(name.equals(t.getName()) && price == t.getPrice()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public int hashCode(){
        int hashCode = name.hashCode();
        hashCode = 31 * hashCode + price;
        return hashCode;
    }
    
    public String toString(){
        return name + " - $" + price ;
    }
}