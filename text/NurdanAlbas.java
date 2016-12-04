package text;
import java.util.StringTokenizer;
public class NurdanAlbas implements Processor {
    public String process(String input) {
        StringTokenizer ayrac = new StringTokenizer(input, " ");
        StringBuffer sb = new StringBuffer();
        while(ayrac.hasMoreElements()){
            sb.append(ayrac.nextElement()).append(" ");
        }
       return sb.toString().trim();
    }
    public String description(String source) {
        return source+" fazla bosluklari atip tek bosluk yapma ";
    }
    public String author() {
        return "Nurdan Albas";
    }
}
