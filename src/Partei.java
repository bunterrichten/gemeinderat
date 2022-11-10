import java.util.ArrayList;

public class Partei {
    public String name;
    public String parteiFarbe;

    public Partei(String nameI, String parteiFarbeI) {
        name = nameI;
        parteiFarbe = parteiFarbeI;
    }

    public static Partei getParteiByName(ArrayList<Partei> parteien, String name) {
        for (int i = 0; i < parteien.size(); i++) {
            if (parteien.get(i).name.equalsIgnoreCase(name))
                return parteien.get(i);
        }
        return null;
    }
}
