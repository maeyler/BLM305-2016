//Jun 2004 DropOrPaste 
//Dec 2012 modified for CSE 495
//Dec 2016 scaled fonts
//Jan 2017 FlavorListener 

import java.util.List;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ClipWatch {

   Transferable data; //clipboard contents -- not used
   String[] flavors; List lst; String htm, txt; Image img;
   int current;   //index of last button pressed
   final JButton paste = new JButton("Paste");
   final List<JButton> buttons; //all buttons except Paste
   final JPanel top = new JPanel();
   final JList<Object> list = new JList<>();
   final JTextArea text = new JTextArea();
   final JEditorPane html = new JEditorPane();
   final JLabel pict = new JLabel(null, null, JLabel.CENTER);
   final JViewport port; //bottom panel
   final Ear ear = new Ear();
   
   public final static Clipboard 
       clip = Toolkit.getDefaultToolkit().getSystemClipboard();
   public static final int 
       RESOLUTION = Toolkit.getDefaultToolkit().getScreenResolution();
   public static int scaled(int k) { return Math.round(k*RESOLUTION/96f); }
   static final int GAP = scaled(5);
   static final Color 
       NORMAL = Color.cyan, DROP = Color.blue;
   static final Font
       SANS = new Font("SansSerif", 0, scaled(12)),
       MONO  = new Font("Monospaced", 0, scaled(12));
   static final BasicStroke THICK = new BasicStroke(2);
   static final DataFlavor PICT = DataFlavor.imageFlavor;
   static final DataFlavor LIST = DataFlavor.javaFileListFlavor;
   static final DataFlavor TEXT = DataFlavor.stringFlavor;
   //static DataFlavor HTML; //will be assigned below
    
    public ClipWatch() {
      /*try { //no HTML flavor works in all cases -- moved to getDataFlavors()
          String m = "text/html; document=selection; class=java.lang.String";
          HTML = new DataFlavor(m);   
      } catch (ClassNotFoundException x) {
      }*/
      DropTarget dt = new DropTarget(top, ear);
      top.setToolTipText("Drop or paste here");
      top.add(paste); //not in buttons
      paste.addActionListener(ear);
      top.add(javax.swing.Box.createHorizontalStrut(scaled(10)));
      top.add(new JLabel("Data:"));
      JButton[] ALL = { new JButton("Flavors"), new JButton("Files"), 
          new JButton("Rich Text"), new JButton("HTML"), 
          new JButton("Text"), new JButton("Image") };
      buttons = Arrays.asList(ALL); current = -1; 
      for (JButton b : ALL) {
          top.add(b); b.addActionListener(ear);
      }

      JScrollPane bot = new JScrollPane(); 
      port = bot.getViewport();
      list.setFont(SANS);
      list.setDragEnabled(true);
      html.setContentType("text/html");
      html.setEditable(false);
      html.setDragEnabled(true);
      text.setFont(MONO);
      text.setLineWrap(true);
      text.setWrapStyleWord(true);
      text.setEditable(false);
      text.setDragEnabled(true);
      
      JPanel pan = new JPanel(new BorderLayout(GAP, GAP));
      pan.setBorder(new EmptyBorder(GAP, GAP, GAP, GAP));
      pan.setBackground(NORMAL);
      pan.add(top, "North");
      pan.add(bot, "Center");
      paste.doClick(); //initial paste action
      
      JFrame frm = new JFrame("Drag&Drop -- Copy&Paste");
      frm.setContentPane(pan); 
      frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frm.setBounds(scaled(100), scaled(50), scaled(700), scaled(480));
      frm.setVisible(true);  
      frm.addWindowListener(ear);
      clip.addFlavorListener(ear);
      //frm.pack(); not needed
   }
   void setToolTip(int i, String s)  {
      buttons.get(i).setToolTipText(s);
   }
   String bytesToString(int n)  {
      if (n == 0) return "";
      int k = n/1024;
      if (k > 9999) return (k/1024)+" Mb";
      if (n > 9999) return k+" Kb";
      return n+" bytes";
   }
   void setToolTipAsLength(int i, String s)  {
      if (s == null) setToolTip(i, ""); 
      else setToolTip(i, bytesToString(s.length())); 
   }
   public boolean pasteData(Transferable t)  {
      DataFlavor[] a = t.getTransferDataFlavors();
      String s = a.length+" flavors";  //\n";
      //System.out.println("pasteData "+s);
      buttons.get(0).setText(s); 
      data = t; //save for inspection -- not used
      flavors = getDataFlavors(t); //get html data
      lst = (List)getData(t, LIST);
      if (lst == null) setToolTip(1, ""); 
      else setToolTip(1, lst.size()+" items"); 
      txt = (String)getData(t, TEXT);
      setToolTipAsLength(3, htm); setToolTipAsLength(4, txt); 
      if (htm != null) { //discard all chars before HTML tag
          int k = htm.toLowerCase().indexOf("<html"); 
          if (k >= 0) htm = htm.substring(k); 
      }
      img = (Image)getData(t, PICT);
      if (img == null) setToolTip(5, ""); 
      else setToolTip(5, img.getWidth(null)+"x"+img.getHeight(null)); 
      if (displayList(lst)) return true;
      if (displayHTML(htm)) return true;
      if (displayText(txt)) return true;
      if (displayPict(img)) return true;
      displayString("Unknown data"); 
      return false;
   }
   public void displayData(Object[] a) { 
      if (a == null || a.length == 0) return;
      port.setView(list);
      list.setListData(a);
      selectButton(0);
   }
   public boolean displayList(List a) { 
      if (a == null || a.size() == 0) return false;
      port.setView(list);
      list.setListData(a.toArray());
      selectButton(1);
      return true;
   }
   public boolean displayHTML(String s) {
      if (s == null || s.length() == 0) return false;
      port.setView(html); 
      html.setText(s);
      selectButton(2);
      return true;
   }
   public boolean displayText(String s) { 
      if (s == null || s.length() == 0) return false;
      port.setView(text); 
      text.setText(s); text.select(0,0);
      selectButton(4);
      return true;
   }
   public boolean displayPict(Image im) {
      if (im == null) return false;
      port.setView(pict); 
      pict.setText(null); 
      pict.setIcon(new ImageIcon(im));
      selectButton(5);
      return true;
   }
   void displayString(String s) { 
      port.setView(pict);
      pict.setIcon(null);
      pict.setText(s);
      selectButton(-1); 
   }
   void selectButton(int i) { 
      current = i; checkButtons();
   }
   void checkButtons() {
      Object[] a = { this, lst, htm, htm, txt, img };
      for (int i=0; i<a.length; i++)  {
          JButton b = buttons.get(i);
          b.setBackground(i == current? NORMAL : null); 
          b.setEnabled(a[i] != null); 
      }
   }
   public String[] getDataFlavors(Transferable t) { 
      //reads html data as side effect
      DataFlavor[] d = t.getTransferDataFlavors();
      String[] a = new String[d.length]; 
      htm = null;
      for (int i=0; i<a.length; i++) {
         a[i] = d[i].getMimeType();
         if (htm == null && a[i].startsWith("text/html")
             && d[i].getRepresentationClass() == String.class) 
                 htm = (String)getData(t, d[i]);
      }
      return a;
   }

   class Ear extends WindowAdapter implements ActionListener, 
         DropTargetListener, FlavorListener, ClipboardOwner {
     void drawBox(Color c) { //draw a box aroud top (drop feedback)
        Component pan = top.getParent();
        Graphics2D g = (Graphics2D)pan.getGraphics();
        g.setColor(c); g.setStroke(THICK);
        Rectangle r = top.getBounds();
        g.drawRect(r.x-1, r.y-1, r.width+2, r.height+2);
     }
     public void dragEnter(DropTargetDragEvent e) {
        drawBox(DROP);
     }
     public void dragExit(DropTargetEvent e)  {
        drawBox(NORMAL);
     }
     public void dragOver(DropTargetDragEvent e)  {
     }
     public void dropActionChanged(DropTargetDragEvent e)  {
     }
     public void drop(DropTargetDropEvent e)  {
        e.acceptDrop(e.getDropAction());
        pasteData(e.getTransferable());
        e.dropComplete(true);
        drawBox(NORMAL);
     }
     public void actionPerformed(ActionEvent e) {
         int b = buttons.indexOf(e.getSource());
         if (b < 0) { //paste
             Transferable t = clip.getContents(null);
             if (t != null) {
                 //System.out.print("Change Owner ");
                 clip.setContents(t, ear); pasteData(t);
             }
         } 
         else if (b == 0) displayData(flavors);
         else if (b == 1) displayList(lst);
         else if (b == 2) displayHTML(htm);
         else if (b == 3) displayText(htm);
         else if (b == 4) displayText(txt);
         else if (b == 5) displayPict(img);
     }
     public void windowClosed(WindowEvent e) {
         System.out.println("windowClosed");
         clip.removeFlavorListener(ear); 
     }
     public void flavorsChanged(FlavorEvent e) {
         //System.out.println("flavorsChanged");
         paste.doClick(10); //a small delay is needed for MS products
     }
     public void lostOwnership(Clipboard cb, Transferable t) {
         //System.out.println("lostOwnership ");
     }
   }
   
   public static Object getData(Transferable t, DataFlavor f) { 
      try {
          //if (t.isDataFlavorSupported(f)) 
          return t.getTransferData(f);
      } catch (Exception x) { //UnsupportedFlavorException
          return null;
      }
   }
   public static void main(String[] args) {
      new ClipWatch();
   }
}
