import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Huffman {
    public Huffman(){ Symbols = new ArrayList<>(); }
    private int Symbols_length;
    ArrayList<Symbol> Symbols;
    private String Text; //Symbols that i do compressed
    public int getSymbols_length(){return Symbols_length;}
    public void Set_All_Probabilities(String s){
        Text=s; //Text=Symbols that i want be compress
        int count=0;//عدد مرات تكرار الحرف الواحد
        boolean founded; //if The Symbols is dublicate :founded=true else: founded=false
        for (int i = 0 ; i < s.length() ; i++){
            founded=false;
            for (int g = 0 ; g < getSymbols_length() ; g++){ //بيقارن لو الحرف الموجود في s.charAt(i) بيساوي اي حرف موجود في Symbols
                if (s.charAt(i)==Symbols.get(g).getChar().charAt(0)) {
                    founded=true;
                    break;
                }
            }
            if (founded)continue;
            for (int g = i ; g < s.length() ; g++){//طالما الحرف مش موجود في الاراي Symbols روح شوفلي موجود كام مره في s
                if (s.charAt(i)==s.charAt(g)){ count++; }
            }
            Symbol newSymbol=new Symbol();
            newSymbol.setCount(count);
            newSymbol.setLength(s.length());
            newSymbol.setCharacter(s.charAt(i)+"");
            newSymbol.CalculateProb();
            Symbols.add(Symbols_length++,newSymbol);
            count=0;
        }
        Sort(Symbols);
    }
    public String print_Symbols_Details(){
        String result="";
        for (Symbol symbol : Symbols) {
            if (symbol==Symbols.get(Symbols.size()-1))result+=symbol.getChar() + " --> " + symbol.getBinaryNumber();
            else result+=symbol.getChar() + " --> " + symbol.getBinaryNumber()+" , ";
        }
        return result;
    }
    public void Sort(ArrayList<Symbol> Symbols){
        Symbol swap;
        for (int i=0;i<Symbols.size();i++){
            for (int g=i+1;g<Symbols.size();g++){
                if (Symbols.get(i).getProb()<Symbols.get(g).getProb()){
                    swap=Symbols.get(i);
                    Symbols.set(i,Symbols.get(g));
                    Symbols.set(g,swap);
                    g=i+1;
                }
            }
        }
    }
    public void search(String sym,ArrayList<Symbol> Symbols,String num){
        for (Symbol symbol : Symbols) {
            if (sym.equals(symbol.getChar()))symbol.setBinaryNumber(num+symbol.BinaryNumber);
        }
    }
    public String return_Binary(String s){//To Search the Symbol and return Your Binary code
        String Binary="";
        for (Symbol i:Symbols){
            if (s.equals(i.getChar()))Binary=i.getBinaryNumber();
        }
        return Binary;
    }
    public void addBinary(String All_Symbols,String num){
        String[] arr=All_Symbols.split(",");
        for (String i:arr){
            search(i,Symbols,num);
        }
    }
    public String Compress(){
        DecimalFormat convert=new DecimalFormat("#.##");
        double result;
        ArrayList<Symbol> S_copy = new ArrayList<>(Symbols); //transfer All Symbols in S_copy
        int i=S_copy.size()-1;
        while (S_copy.size()!=1){
            Symbol copy=new Symbol();
            result=Double.parseDouble(convert.format(S_copy.get(i).getProb()+S_copy.get(i-1).getProb()));
            copy.changeProb(result);
            copy.setCharacter(S_copy.get(i).getChar()+","+S_copy.get(i-1).getChar());

            addBinary(S_copy.get(i).getChar(),"1");
            addBinary(S_copy.get(i-1).getChar(),"0");

            S_copy.remove(i);
            S_copy.set(i-1,copy);
            Sort(S_copy);
            i=S_copy.size()-1;
        }
        String compress="";
        for (i=0;i<Text.length();i++){
            compress+=return_Binary(Text.charAt(i)+"");
        }
        return compress;
    }
    public void Print_In_Console(){
        int Compressed_Size=0,UnCompressed_Size=0;
        StringBuilder print_char= new StringBuilder();
        StringBuilder Print_count=new StringBuilder();
        for (Symbol s:Symbols){
            if(s!=Symbols.get(Symbols.size()-1)) {
                print_char.append(s.getChar()).append(", ");
                Print_count.append(s.getCount()).append(", ");
            }
            else {
                print_char.append(s.getChar());
                Print_count.append(s.getCount());
            }
            Compressed_Size+=s.getCount()*s.getBinaryNumber().length();
        }
        System.out.println("["+print_char+"]\n"+"["+Print_count+"]");
        UnCompressed_Size=Text.length()*3;
        System.out.println("Compressed ratio ="+(UnCompressed_Size+0.0)/(Compressed_Size+0.0));
    }

    public String decompress(String data)
    {
        String s="";
        String arr[]=new String[data.length()];
        arr=data.split("(?<=.)");
        String buffer="";
        String temp="";
        String temp2="";
        for (int i=0;i<data.length();i++){
            buffer+=arr[i];
            for (int j=0;j<Symbols.size();j++){
                temp =Symbols.get(j).getBinaryNumber();
                temp2=buffer;
                if (temp.equals(temp2))
                {
                    s+=Symbols.get(j).getChar();
                    buffer="";
                    break;
                }
            }
        }
        return s;
    }
}

