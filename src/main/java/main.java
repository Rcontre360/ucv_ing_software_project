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
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("ID", "Lokesha");
        employeeDetails2.put("firstName", "Lokesha");
        employeeDetails2.put("lastName", "Gupta");
        employeeDetails2.put("website", "howtodoinjava.com");
        db.setUniversal(employeeDetails2, "historiales_medicos2");
        InterfacesMédico.registrarDatosEnHistoria.setPeso(4,"Brian");
        InterfacesMédico.registrarDatosEnHistoria.setPesoTest(4,"Lokesha");
        //
        System.out.println(date);
    }
}
