import java.io.*;

class Copy  {   //any file
    public static void main(String args[]) throws IOException {
	String fName = "README.md";
	if (args.length>0) fName = args[0];

	InputStream in;
	OutputStream out;
	byte[] buf;

 	in  = new FileInputStream(fName);
	out = new FileOutputStream(fName+".copy");
	int n = (int) new File(fName).length();
	System.out.println(fName+" length:"+n);
	buf = new byte[n];
	n = in.read(buf);
	out.write(buf, 0, n);
	in.close(); 
        out.close();
    }
}
