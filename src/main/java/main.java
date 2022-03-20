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
        System.out.print("1 \n");
        SchemaHistorial historial = new SchemaHistorial("H00000001","1234567");
        System.out.print("2 \n");
        historial.pushCita("150","100","45","1.0","1.50");
        System.out.print("3 \n");
        historial.commit();
        System.out.print("4 \n");
        PaginaPrincipal instance = PaginaPrincipal.getInstance();
        instance.setVisible(true);
    }
}
