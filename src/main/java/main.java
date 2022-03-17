import home.PaginaPrincipal;
import org.json.simple.*;
import schemas.SchemaBranch;
import schemas.SchemaDate;
import utils.JsonWrapper ;
import InterfacesMédico.registrarDatosEnHistoria;

public class main {
    public static void main(String[] args) {
        PaginaPrincipal instance = PaginaPrincipal.getInstance();
        instance.setVisible(true);
        JsonWrapper.loadJson();
    }
}
