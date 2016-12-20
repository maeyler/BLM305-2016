import java.io.File;
import java.net.URL;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class GrayConverter {

    final JFrame frm = new JFrame();
    final ImageIcon icn1 = new ImageIcon();
    final ImageIcon icn2 = new ImageIcon();
    BufferedImage img1, img2;
    public static GrayConverter G = new GrayConverter(); //Singleton
    
    GrayConverter() {
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel pan = new JPanel(new java.awt.FlowLayout(0, 10, 10));
        pan.add(new JLabel(icn1)); pan.add(new JLabel(icn2));
        frm.setContentPane(pan);
    }
    public void setImage(BufferedImage i, String t) {
        img1 = copyOf(i); icn1.setImage(img1); 
        img2 = copyOf(i); convert(img2); icn2.setImage(img2);
        if (t==null || t.length()==0) t = "Some image";
        frm.setTitle(t); frm.pack(); 
        frm.setVisible(true); frm.repaint();
    }
    
    public static BufferedImage fileToImage(String n) throws Exception {
        return fileToImage(new File(n)); 
    }
    public static BufferedImage fileToImage(File f) throws Exception {
        return urlToImage(f.toURI().toURL()); 
    }
    public static BufferedImage urlToImage(String u) throws Exception {
        return urlToImage(new URL(u)); 
    }
    public static BufferedImage urlToImage(URL u) throws Exception {
        //Image i = new ImageIcon(u).getImage();
        return copyOf(javax.imageio.ImageIO.read(u));
    }
    public static BufferedImage copyOf(java.awt.Image i) {
        int w = i.getWidth(null), h = i.getHeight(null);
        int MAX = 512; 
        if (w > MAX) {
            h = (int)(h*(float)MAX/w); w = MAX; 
        }
        //make sure i2 has correct type
        BufferedImage i2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        i2.getGraphics().drawImage(i, 0, 0, w, h, null);
        return i2;
    }
    public static void convert(BufferedImage bi) {
        WritableRaster R = bi.getRaster();
        int[] rgb = { 0, 0, 0 };
        int w = bi.getWidth(), h = bi.getHeight();
        for (int x=0; x<w; x++) 
            for (int y=0; y<h; y++) {
                R.getPixel(x, y, rgb); 
                int m = (rgb[0]+rgb[1]+rgb[2])/3;
                rgb[0] = m; rgb[1] = m; rgb[2] = m;
                R.setPixel(x, y, rgb); 
            }
        bi.setData(R);  //modified copy of the data
    }
    public static void main(String[] args) throws Exception {
        File f = new File("images", "Kedi.png");
        G.setImage(fileToImage(f), f.toString());
    }
}
