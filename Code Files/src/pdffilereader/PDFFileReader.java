/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pdffilereader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author User
 */
public class PDFFileReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PDDocument pd;
        try {
            // TODO code application logic here
            File inputFile=new File("C:\\Users\\User\\Downloads\\MADC Test\\Course Outline.pdf");
            File outputFile =new File("output.txt");
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
            
            
            pd=PDDocument.load(inputFile);
            PDFTextStripper stripper=new PDFTextStripper();
            stripper.writeText(pd, bw);
            String st=stripper.getText(pd);
            String[] split = st.split("\\n");
            
            boolean found=false;
            boolean stf=false;
            
            for(int i=0;i<split.length;i++){
                
                if(found){
                    if(stf){
                        System.out.print(split[i]);
                    }
                    
                    if(Character.isDigit(split[i+1].charAt(0))){
                        stf=true;
                    }else{
                        stf=false;
                    }
                    
                    //System.out.println(Character.isDigit(split[i+2].charAt(0)));
                    if(!stf && !(Character.isDigit(split[i+2].charAt(0))) && !(Character.isDigit(split[i+3].charAt(0))) ){
                        //System.out.println("yeah!! out");
                        found=false;
                    }
                }
                if (split[i].contains("areas:")){
                    System.out.println("fOUND");
                    found=true;
                    stf=true;
                }
            }
            
            if(pd!=null){
                pd.close();
            }
            
            bw.close();
            
        } catch (Exception ex) {
            System.out.println("error");
        }
    }
}
