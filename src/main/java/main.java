import home.PaginaPrincipal;
import org.json.simple.*;

import schemas.SchemaBranch;
import utils.JsonWrapper ;
import InterfacesMédico.registrarDatosEnHistoria;

public class main {
    public static void main(String[] args) {
        //PaginaPrincipal instance = new PaginaPrincipal();
        //instance.setVisible(true);
        JsonWrapper.loadJson();
        SchemaBranch branch = new SchemaBranch("Some shit");
        branch.setDireccion("Some great direction");
        branch.setLimite(10);
        branch.setCapacidad(15);
        System.out.println("COMMIT");
        //branch.commit();
        //System.out.println(date);
    }
}
