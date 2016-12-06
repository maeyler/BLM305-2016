import java.io.File;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;

class ImageDisplay extends JPanel {
    public static BufferedImage img;
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public static JFrame newFrame(String t, JPanel p, int x, int y, int w, int h) {
        JFrame f = new JFrame(t); f.setContentPane(p); 
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setBounds(x, y, w+50, h+60);  //f.pack(); 
        f.setVisible(true); return f;
    }
    public static void main(String[] args) throws Exception {
        File file = new File("images", "Kedi.png");
        img = ImageIO.read(file); //may throw IOException 
        int W = img.getWidth();
        int H = img.getHeight();
        System.out.printf("W=%s H=%s %n", W, H);
        JPanel p1 = new ImageDisplay();
        newFrame("First solution", p1, 80, 50, W, H);
        JPanel p2 = new JPanel();
        newFrame("Second solution", p2, 330, 50, W, H);
        Thread.sleep(1000); //may throw InterruptedException 
        Graphics g = p2.getGraphics();
        g.drawImage(img, 0, 0, null);
    }
}
