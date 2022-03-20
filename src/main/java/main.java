import home.PaginaPrincipal;
import org.json.simple.*;
import schemas.SchemaBranch;
import schemas.SchemaDate;
import utils.JsonWrapper ;
import InterfacesMédico.registrarDatosEnHistoria;
import schemas.SchemaHistorial;

public class main {
    public static void main(String[] args) {
        JsonWrapper.loadJson();
        PaginaPrincipal instance = PaginaPrincipal.getInstance();
        instance.setVisible(true);
    }
}
