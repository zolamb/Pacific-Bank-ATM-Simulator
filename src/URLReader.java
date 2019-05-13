import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

//CREDIT TO:  http://www.rgagnon.com/javadetails/java-0085.html
public class URLReader {
    public static boolean addressChecker(String URLName) {
        try {
            DataInputStream di = null;
            FileOutputStream fo = null;
            byte[] b = new byte[1];

            // PROXY
            System.setProperty("http.proxyHost", "proxy.mydomain.local");
            System.setProperty("http.proxyPort", "80");

            URL u = new URL(URLName);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
            //
            //
            sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
            String encodedUserPwd =
                    encoder.encode("mydomain\\MYUSER:MYPASSWORD".getBytes());
            con.setRequestProperty
                    ("Proxy-Authorization", "Basic " + encodedUserPwd);
            // PROXY ----------
            di = new DataInputStream(con.getInputStream());
            String fileName = "C:\\Users\\ZLamb\\Desktop\\Desktop\\Coding\\Java\\Projects\\Pacific_Bank_Extra_Files\\Google_API_Address_Info_JSON.txt";

            //WRITING TO THE TEXT FILE
            try {
                PrintWriter outputStream = new PrintWriter(fileName);
                while (-1 != di.read(b, 0, 1)) {
                    String letter = new String(b);
                    outputStream.print(letter);
                }
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            //READING THE TEXT FILE
            String wordMatch = "\"partial_match\"";
            String wordMatch2 = "\"ZERO_RESULTS\"";
            for (String line : Files.readAllLines(Paths.get(fileName))) {
                //System.out.println(line);
                if(line.indexOf(wordMatch) != -1 || line.indexOf(wordMatch2) != -1){
                    return false;
                }
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean cityChecker(String city){ //throws IOException{
        String fileName = "C:\\Users\\ZLamb\\Desktop\\Desktop\\Coding\\Java\\Projects\\Pacific_Bank_Extra_Files\\Google_API_Address_Info_JSON.txt";
        city = "               \"long_name\" : \"" + city + "\",";//change the format of city so that it looks like the whole line 16
        int lineCount = 0;
        int lineCount2 = 0;
        int cityLine = 0;
        try{
            for (String line : Files.readAllLines(Paths.get(fileName))) {
                //System.out.println(line);
                lineCount++;
                if(line.toLowerCase().equals("               \"types\" : [ \"locality\", \"political\" ]")){//line 16 is where the city is located
                    cityLine = lineCount - 2;
                }
            }

            for (String line : Files.readAllLines(Paths.get(fileName))) {
                //System.out.println(line);
                lineCount2++;
                if(line.toLowerCase().equals(city.toLowerCase()) && lineCount2 == cityLine){//line 16 is where the city is located
                    return true;
                }
            }
            return false;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean zipCodeChecker(String zipCode){
        String fileName = "C:\\Users\\ZLamb\\Desktop\\Desktop\\Coding\\Java\\Projects\\Pacific_Bank_Extra_Files\\Google_API_Address_Info_JSON.txt";
        zipCode = "               \"long_name\" : \"" + zipCode + "\",";
        int lineCount = 0;
        int lineCount2 = 0;
        int zipCodeLine = 0;
        try{
            for (String line : Files.readAllLines(Paths.get(fileName))) {
                //System.out.println(line);
                lineCount++;
                if(line.toLowerCase().equals("               \"types\" : [ \"postal_code\" ]")){//line 16 is where the city is located
                    zipCodeLine = lineCount - 2;
                }
            }

            for (String line : Files.readAllLines(Paths.get(fileName))) {
                //System.out.println(line);
                lineCount2++;
                if(line.toLowerCase().equals(zipCode.toLowerCase()) && lineCount2 == zipCodeLine){//line 16 is where the city is located
                    return true;
                }
            }
            return false;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}