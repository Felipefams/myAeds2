import java.io.File;

public class checkLines {
    public static void main (String[] args){
        File folder = new File("./filmes");
        File[] listOfFiles = folder.listFiles(); 
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    } 
}
