import java.io.*;

/**
 * Created by Administrator on 2017/3/27.
 */
public class TestReadProject {
         private static String FILE_PATH="E:\\svnCheckOut\\MSB";
    private static String OUT_FILE_PATH = "E:\\svnCheckOut\\MSB\\new.doc";


    public static void main(String[] args) {
        readProject(FILE_PATH);
    }

     public static void readProject(String filePath){

         File file = new File(filePath);
         File outFile = new File(OUT_FILE_PATH);
         if (file.isDirectory()){
             for (File file1 : file.listFiles()) {
                 if (file1.getName().equals("src") && file1.isDirectory()){
                     readFiles(file1,outFile);
                 }else if (file1.getName().equals("pom.xml")){
                      readFileByLines(file1.getAbsolutePath(),outFile);
                 }
             }
         }
     }

    public static void readFileByLines(String fileName,File outFile) {
        File file = new File(fileName);
        BufferedReader reader = null;
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outFile,true), "UTF-8");
            System.out.println(file.getName());
            writer.append(file.getName()+"\r\n");
            if (!file.isDirectory()){
            // 写入数据
            // 读取非汉字可用
            // reader = new BufferedReader(new FileReader(file));
            // 读汉字可用
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));
            String str;
            int line = 0;
            while (null != (str = reader.readLine())) {
                writer.append(str+"\r\n");
                line++;
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readFiles(File file,File outFile){
        for (File file1 : file.listFiles()) {
            if (!file1.getName().equals(".svn")){
            readFileByLines(file1.getAbsolutePath(),outFile);
            if (file1.isDirectory()){
                  readFiles(file1,outFile);
              }
        }
        }
    }

}
