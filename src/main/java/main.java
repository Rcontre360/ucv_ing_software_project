import home.PaginaPrincipal;
import utils.JsonWrapper ;
import utils.db ;
import org.json.simple.*;
import InterfacesMédico.registrarDatosEnHistoria;

public class main {
    public static void main(String[] args) {
        //PaginaPrincipal instance = new PaginaPrincipal();
        //instance.setVisible(true);
        JSONObject date = JsonWrapper.getDate("00000000");
        System.out.println(date);
    }
}
