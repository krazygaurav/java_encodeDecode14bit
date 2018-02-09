import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
	public static String encoding(int value){
		int intermediateDec = value + 8192;
		int lsb = intermediateDec & 0x007F;
		int msb = intermediateDec & 0x3F80;
		int encoded = lsb + (msb << 1);
		return String.format("%04x", encoded);
	}

	public static int decoding(String encoded){
		int intEncoded = Integer.parseInt(encoded, 16);
		int lsb = (intEncoded&0x00ff);
		int msb = ((intEncoded >> 8)<<7);
		int com = (msb | lsb) - 8192;
		return com;
	}
	public static void main(String[] args) {
		try{
			BufferedReader br = new BufferedReader(new FileReader("InputFile1.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("ConvertedData1.txt"));
			String line = br.readLine();
			while(line != null){
				int value = Integer.parseInt(line);
				String encoded = encoding(value);
				bw.write(encoded);
				bw.newLine();
				line = br.readLine();
			}
			br.close();
			bw.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		try{
			BufferedReader br = new BufferedReader(new FileReader("InputFile2.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("ConvertedData2.txt"));
			String line = br.readLine();
			while(line != null){
				int decoded = decoding(line);
				System.out.println(decoded);
				bw.write(""+decoded);
				bw.newLine();
				line = br.readLine();
			}
			br.close();
			bw.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
