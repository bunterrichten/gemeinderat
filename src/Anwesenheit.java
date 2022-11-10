import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Anwesenheit {
    ArrayList<String[]> politiker;
    ArrayList<String[]> anwesend;
    ArrayList<String[]> entschuldigt;

    public Anwesenheit() {
        politiker = new ArrayList<String[]>();
        anwesend = new ArrayList<String[]>();
        entschuldigt = new ArrayList<String[]>();
        // input names
        politiker.add(new String[]{"Christoph", "Barth"});
        politiker.add(new String[]{"Peter", "Franzmayr"});
        politiker.add(new String[]{"Ralf", "Drack"});
        politiker.add(new String[]{"Johann", "Reindl-Schwaighofer"});
        politiker.add(new String[]{"Andreas", "Rabl"});
        politiker.add(new String[]{"Gerhard", "Kroiß"});
        politiker.add(new String[]{"Christa", "Raggl-Mühlberger"});
        politiker.add(new String[]{"Ralph", "Schäfer"});
        politiker.add(new String[]{"Stefan", "Ganzert"});
        politiker.add(new String[]{"Klaus", "Schinninger"});
        politiker.add(new String[]{"Martin", "Oberndorfer"});
        politiker.add(new String[]{"Thomas", "Rammerstorfer"});
        politiker.add(new String[]{"Miriam", "Faber"});
        politiker.add(new String[]{"Alessandro", "Schatzmann"});
        politiker.add(new String[]{"Walter", "Teubl"});
        politiker.add(new String[]{"Gerhard", "Bruckner"});
        politiker.add(new String[]{"Olivera", "Stojanovic"});
        politiker.add(new String[]{"Ingo", "Spindler"});
        politiker.add(new String[]{"Christoph", "Angelo", "Rigotti"});
        politiker.add(new String[]{"Carmen", "Pühringer"});
        politiker.add(new String[]{"Christiane", "Kroiß"});
        politiker.add(new String[]{"Gunter", "Haydinger"});
        politiker.add(new String[]{"Fabian", "Bauer"});
        politiker.add(new String[]{"Ronald", "Schiefermayr"});
        politiker.add(new String[]{"Sandra", "Wohlschlager"});
        politiker.add(new String[]{"Thorsten", "Aspetzberger"});
        politiker.add(new String[]{"Silke", "Lackner"});
        politiker.add(new String[]{"Anna", "Maria", "Wippl"});
        politiker.add(new String[]{"Paul", "Hammerl"});
        politiker.add(new String[]{"Christian", "Kittenbaumer"});
        politiker.add(new String[]{"Bernhard", "Humer"});
        politiker.add(new String[]{"Hannah", "Stögermüller"});
        politiker.add(new String[]{"Gloria-Maria", "Umlauf"});
        politiker.add(new String[]{"Karl", "Schönberger"});
        politiker.add(new String[]{"Laurien", "Scheinecker"});
        politiker.add(new String[]{"Silvia", "Huber"});
        politiker.add(new String[]{"Andreas", "Weidinger"});
        politiker.add(new String[]{"Markus", "Wiesinger"});
        politiker.add(new String[]{"Ludwig", "Vogl"});
        politiker.add(new String[]{"Birgit", "Ebetshuber"});
        politiker.add(new String[]{"Markus", "Hufnagl"});
        politiker.add(new String[]{"Jörg", "Wehofsich"});
    }

    public ArrayList<String[]>[] getAnwesenheit(String html) {

        Document doc = Jsoup.parse(html);
        Elements all = doc.select("p");

        for (Element one : all) {
            String txt = one.text();
            int entschuldigtPos = txt.indexOf("Entschuldigt:");

            for (int counter = 0; counter < politiker.size(); counter++) {
                String[] oneName = politiker.get(counter);
                String n = getPolName(oneName);
                int polPos = txt.indexOf(n);
                if (polPos != -1) {
                    // politician found
                    if (entschuldigtPos != -1 && entschuldigtPos < polPos) {
                        // entschuldigt
                        //System.out.println("Politiker entschuldigt: " + getPolName(politiker.get(counter)));
                        entschuldigt.add(politiker.get(counter));
                    }
                    else {
                        // is anwesend
                        //System.out.println("Politiker anwesend: " + getPolName(politiker.get(counter)));
                        anwesend.add(politiker.get(counter));
                    }
                    politiker.remove(counter);
                    counter--;
                }
            }
        }

        return new ArrayList[]{anwesend, entschuldigt, politiker};
    }

    public static String getPolName(String[] parts) {
        String ret = "";
        for(String part : parts) {
            ret += " " + part;
        }
        return ret.trim();
    }
}
