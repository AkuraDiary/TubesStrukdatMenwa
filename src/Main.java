import repositories.LaporanRepository;

public class Main {
    public static  Di di;
    public static void main(String[] args) {
        di = new Di();
        di.initDatasources();
        Menwa app = new Menwa(di);
        app.run();
    }
}
