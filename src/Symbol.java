public class Symbol{
    private String Character;
    private int count;
    private int length;
    public String BinaryNumber="";
    private double Probabilities;
    public void setBinaryNumber(String s){this.BinaryNumber=s;}
    public void setCharacter(String character) { this.Character = character; }
    public void setLength(int length) { this.length = length; }
    public void setCount(int count) { this.count = count; }
    public String getChar() { return Character; }
    public void CalculateProb(){
        Probabilities=(count+0.0)/(length+0.0);
        Probabilities=((Math.round(Probabilities*100)) / 100.0);
    }
    public double getProb(){return Probabilities;}
    public void changeProb(double s){this.Probabilities=s;}
    public String getBinaryNumber(){return BinaryNumber;}
    public int getCount(){return count;}
}
