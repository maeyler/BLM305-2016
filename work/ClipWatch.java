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

   final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
   Transferable data; //clipboard contents
   String[] flavors; List lst; String htm, txt; Image img;
   JButton current;   //last pressed
   final Action paste = new Paste();
   final List<JButton> button; // = new ArrayList<JButton>(5); 
   final JPanel pan = new JPanel(new BorderLayout(GAP, GAP));
   final JPanel top = new JPanel();
   final JList<Object> list = new JList<>();
   final JTextArea text = new JTextArea();
   final JEditorPane html = new JEditorPane();
   final JLabel pict = new JLabel(null, null, JLabel.CENTER);
   final JViewport port; //bottom panel
   final Ear ear = new Ear();
   
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
   static DataFlavor HTML; //will be assigned below
    
    public ClipWatch() {
      try { //must be within try-catch
          String m = "text/html; document=selection; class=java.lang.String";
          HTML = new DataFlavor(m);
      } catch (ClassNotFoundException x) {
      }
      DropTarget dt = new DropTarget(top, ear);
      top.setToolTipText("Drop or paste here");
      top.add(new JButton(paste)); //first button: Paste is ActionListener
      top.add(javax.swing.Box.createHorizontalStrut(scaled(10)));
      top.add(new JLabel("Data:"));
      JButton clipB = new JButton(); //shows clipboard contents
      top.add(clipB);
      top.add(javax.swing.Box.createHorizontalStrut(scaled(30)));
      JButton[] ALL = { clipB, new JButton("Files"), 
          new JButton("Rich Text"), new JButton("HTML"), 
          new JButton("Text"), new JButton("Image") };
      button = Arrays.asList(ALL); current = clipB; 
      for (JButton b : ALL) {
          if (b != clipB) top.add(b); //clipB is already added
          b.addActionListener(ear);
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
      
      pan.setBorder(new EmptyBorder(GAP, GAP, GAP, GAP));
      pan.setBackground(NORMAL);
      pan.add(top, "North");
      pan.add(bot, "Center");
      paste.actionPerformed(null); //initial paste action
      
      JFrame frm = new JFrame("Drag&Drop -- Copy&Paste");
      frm.setContentPane(pan); 
      frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frm.setBounds(scaled(100), scaled(100), scaled(600), scaled(480));
      frm.setVisible(true);  
      frm.addWindowListener(ear);
      clip.addFlavorListener(ear);
      //frm.pack(); not needed
   }
   void setToolTips(int i, int n)  {
      String s = "";
      if (n > 9999) s = (n/1024)+" Kb";
      else if (n > 0) s = n+" bytes";
      button.get(i).setToolTipText(s);
   }
   public boolean pasteData(Transferable t)  {
      DataFlavor[] a = t.getTransferDataFlavors();
      String s = a.length+" flavors";  //\n";
      //System.out.println("pasteData "+s);
      button.get(0).setText(s); 
      data = t; //save for inspection -- not used
      flavors = getDataFlavors(t);
      lst = (List)getData(t, LIST);
      htm = (String)getData(t, HTML);
      txt = (String)getData(t, TEXT);
      img = (Image)getData(t, PICT);
      if (htm == null) {
          setToolTips(3, 0); setToolTips(4, 0); 
      } else {
          if (txt == null) txt = htm;
          int k = htm.toLowerCase().indexOf("<html"); 
          if (k >= 0) htm = htm.substring(k); 
          setToolTips(3, htm.length());
          setToolTips(4, txt.length());
      }
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
      current = (i<0? null : button.get(i)); 
      checkButtons();
   }
   void checkButtons() {
      Object[] a = { this, lst, htm, htm, txt, img };
      for (int i=0; i<a.length; i++)  {
          JButton b = button.get(i);
          b.setBackground(b == current? NORMAL : null); 
          b.setEnabled(a[i] != null); 
      }
   }

   class Ear extends WindowAdapter implements ActionListener, 
         DropTargetListener, FlavorListener, ClipboardOwner {
     void drawBox(Color c) {
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
        e.dropComplete(pasteData(e.getTransferable()));
        drawBox(NORMAL);
     }
     public void actionPerformed(ActionEvent e) {
        int b = button.indexOf(e.getSource());
        if (b == 0) displayData(flavors);
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
        paste.actionPerformed(null);
     }
     public void lostOwnership(Clipboard cb, Transferable t) {
        //System.out.println("lostOwnership ");
     }
   }
   
   class Paste extends AbstractAction {
      public Paste() { super("Paste"); }
      public void actionPerformed(ActionEvent e) {
         Transferable t = clip.getContents(null);
         if (data != null && data != t) {
             clip.setContents(t, ear);
             //System.out.print("Change Owner ");
         }
         pasteData(t);
      }
   }

   public static Object getData(Transferable t, DataFlavor f) { 
      try {
          //if (t.isDataFlavorSupported(f)) 
          return t.getTransferData(f);
      } catch (UnsupportedFlavorException x) {
      } catch (Exception x) {
          x.printStackTrace();
      }
      return null;
   }
   public static String[] getDataFlavors(Transferable t) { 
      DataFlavor[] d = t.getTransferDataFlavors();
      String[] a = new String[d.length]; //String s = "";
      for (int i=0; i<a.length; i++) 
         a[i] = d[i].getMimeType();
         //s += d[i].getMimeType() + "\n";
      return a; //s;
   }
   public static void main(String[] args) {
      new ClipWatch();
   }
}
