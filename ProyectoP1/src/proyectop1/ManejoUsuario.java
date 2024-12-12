package proyectop1;

public class ManejoUsuario {

    usuario usuarios[];
    int usuariosActuales;

    public ManejoUsuario() {
        usuarios = new usuario[100];
        usuariosActuales = 0;
    }

    usuario buscar(String nombre) {

        for (int i = 0; i < usuariosActuales; i++) {
            if (usuarios[i].nombre.equals(nombre)) {
                System.out.println("Usuario Encontrado!");
                return usuarios[i];
            }
        }

        return null;
    }

    void Registrar(usuario NuevoUsuario) {
        if (buscar(NuevoUsuario.nombre) == null) {
            usuarios[usuariosActuales] = NuevoUsuario;
            usuariosActuales++;
            System.out.println("Se registro");
        }else{
            System.out.println("No se registro");
        }
    }

}
