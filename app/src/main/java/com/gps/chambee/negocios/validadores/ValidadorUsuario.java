package com.gps.chambee.negocios.validadores;

import com.gps.chambee.entidades.Usuario;

import java.util.regex.Pattern;

public class ValidadorUsuario extends Validador<Usuario> {

    public ValidadorUsuario(Usuario usuario) {
        super(usuario);
    }

    @Override
    protected void definirValidaciones() {

        // Nombre
        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                return !t.getNombre().isEmpty();
            }

        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "El nombre no puede estar vacío";
            }

            @Override
            public Object propiedadInvalida() {
                return t.getNombre();
            }
        });

        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                String nombre = t.getNombre();
                Pattern regex = Pattern.compile("^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\']+[\\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])+[\\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])?$");
                return regex.matcher(nombre).find();
            }

        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "El nombre solo puede contener letras de la 'A' a la 'Z'";
            }

            @Override
            public Object propiedadInvalida() {
                return t.getNombre();
            }
        });

        // Apellidos
        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                return !t.getApellidos().isEmpty();
            }

        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "Los apellidos no pueden estar vacíos";
            }

            @Override
            public Object propiedadInvalida() {
                return t.getApellidos();
            }
        });
        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                String apellidos = t.getApellidos();
                Pattern regex = Pattern.compile("^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\']+[\\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])+[\\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])?$");
                return regex.matcher(apellidos).find();
            }

        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "Los apellidos solo pueden contener letras de la 'A' a la 'Z'";
            }

            @Override
            public Object propiedadInvalida() {
                return t.getApellidos();
            }
        });

        //Correo Electronico
        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                return !t.getCorreoElectronico().isEmpty();
            }

        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "El correo no puede estar vacío";
            }

            @Override
            public Object propiedadInvalida() {
                return t.getCorreoElectronico();
            }
        });
        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                String correo = t.getCorreoElectronico();
                Pattern regex = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                return regex.matcher(correo).find();
            }
        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "Formato de correo incorrecto, el formato debe ser 'usuario@.dominio'";
            }

            @Override
            public Object propiedadInvalida() {
                return t.getCorreoElectronico();
            }
        });

        //Contraseña
        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                return !t.getContrasenia().isEmpty();
            }
        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "La contraseña no puede estar vacía";
            }

            @Override
            public Object propiedadInvalida() {
                return t.getContrasenia();
            }
        });
        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                if(t.getContrasenia().length()<6)
                    return false;
                return true;
            }
        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "La contraseña debe contener al menos 6 caracteres ";
            }

            @Override
            public Object propiedadInvalida() {
                return t.getContrasenia();
            }
        });

        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                return t.getContrasenia().length() >= 6;
            }
        });

        //Telefono
        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                return !t.getTelefono().isEmpty();
            }
        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "El télefono no puede estar vacía";
            }

            @Override
            public Object propiedadInvalida() {
                return t.getTelefono();
            }
        });
        agregarValidacion(new ValidadorPropiedad() {
            @Override
            public boolean validar() {
                String telefono = t.getTelefono();
                Pattern regex = Pattern.compile("^/d(?:-/d{3}){3}/d$");
                return !regex.matcher(telefono).find();
            }
        }, new ErrorValidacion() {
            @Override
            public String mensajeError() {
                return "Formato de télefono incorrecto, debe contener 10 números";
            }

            @Override
            public Object propiedadInvalida() {
                return t.getTelefono();
            }
        });
    }
}
