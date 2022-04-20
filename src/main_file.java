import java.io.*;
public class main_file {
    public static void store_in_file(File a,String input){
        OutputStream output=null;
        try {
            output=new FileOutputStream(a);
            byte []buffer=input.getBytes();
            output.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String read_From_File(File a) throws IOException {
        String Symbols="",i;
        BufferedReader br = new BufferedReader(new FileReader(a));
        while ((i = br.readLine()) != null) { Symbols += i; }
        return Symbols;
    }
    public static void main(String []args) throws IOException {
        String path="E:\\الكليه\\Third Level\\Information Theory and Data Compression\\Assignments\\Hoffman\\Hoffman";
        Huffman a=new Huffman();
        File s=new File(path+"\\Symbols.txt");
        a.Set_All_Probabilities(read_From_File(s));
        File f=new File(path+"\\compressedfile.txt");
        File f2=new File(path+"\\Dictionary.txt");
        File f3=new File(path+"\\decompression.txt");
        store_in_file(f,a.Compress());
        store_in_file(f2,a.print_Symbols_Details());
        a.Print_In_Console();
        store_in_file(f3,a.decompress(read_From_File(f)));
    }
}