import java.time.Year;
import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {//Menu
    menu();
    }

    public static void menu(){
        Scanner in = new Scanner(System.in);
        char opcion;
        System.out.println("_____________________Menu______________________");

        do {

            System.out.println("###############################################");
            System.out.println("(a) Mayores de edad");
            System.out.println("(b) Calculadora de ingresos de canal de Youtube");
            System.out.println("(c) Cálculo horario");
            System.out.println("(d) Salir");
            System.out.println("###############################################");
            opcion = in.next().charAt(0);
            borrarPantalla();
            seleccion(opcion);
        }while (opcion != 'd');


    }
    public  static void seleccion(char seleccion){
        Scanner in = new Scanner(System.in);
        switch (seleccion){
            case 'a','A'->{
                System.out.println("->Has seleccionado mayores de edad");
                System.out.println("###############################################");
                System.out.println("Introduce el numero de personas: ");
                int nDePersonas = in.nextInt();
                in.nextLine();
                int mayores = mayorDeEdad(nDePersonas);
                System.out.println("Hay " + mayores+" mayores de edad");
                System.out.println("###############################################");
                aceptar();

            }
            case 'b','B'->{
                double ingresosCanal;
                System.out.println("->Has seleccionado  Calculadora de ingresos de canal de YouTube");
                ingresosCanal = ingresosYouTube();
                System.out.println("Los ingresos del canal son de "+ ingresosCanal+'€');
                aceptar();
                borrarPantalla();
            }
            case 'c','C'->{
                System.out.println("Has seleccionado Calculo horario");
                System.out.println("###############################################");
                int hora = pedirLaHora();
                int minutos = pedirMinutos();
                String am = pedirMniana();
                int huso = pedirhuso();
                calculoHora(hora, minutos, huso, am);
            }
            case 'd','D'->{
                System.out.println("Saliendo del programa...");

            }
            default -> {
                System.out.println("Porfavor introduce una opción valida ");

            }
        }
    }
    public static int mayorDeEdad(int nDePersonas){
        Scanner in = new Scanner(System.in);
        int mayores = 0;
        int edad;
        for (int persona = 1; persona <= nDePersonas; persona++) {
            System.out.println("Introduce el año de nacimiento de la persona numero: " + persona);

            edad = in.nextInt();
            if (edad<=(Year.now().getValue())-18){
                mayores++;
            }
        }
        return mayores;
    }
    public static double ingresosYouTube(){
        double ingresos = 0;
        int salir;
        int categoria;
        double ingresosVideo=0;
        int nVisitas=1;
        Scanner in = new Scanner(System.in);

        do {
            int tipoCpm =6;
            double cpm;
            do {
            System.out.println("###############################################");
            System.out.println("Cual es la categoría del video?");
            System.out.println("1->(ASMR)");
            System.out.println("2->(Deportes)");
            System.out.println("3->(Animales)");
            System.out.println("4->(Tecnología)");
            System.out.println("5->(Bebés)");
            System.out.println("6->(Introducir CPM manualmente)");
            System.out.println("###############################################");
            tipoCpm = in.nextInt();

            }while (tipoCpm <1 || tipoCpm >6);
            if (tipoCpm == 6){
                System.out.println("Introduce el valor del CPM");
                cpm = in.nextDouble();
            }else {
                cpm=cPM(tipoCpm);
            }
            do {
                System.out.println("Ingresa el numero de visitas");
                nVisitas = in.nextInt();
                if (nVisitas<0){
                    System.out.println("Error, numero de visitas negativo");
                }
            }while (nVisitas<0);

            ingresosVideo = calcularIngresoVideo(cpm,nVisitas);
            System.out.println("El video ha tenido un ingreso de "+ingresosVideo+'€');
            aceptar();
            borrarPantalla();
            ingresos += ingresosVideo;
            System.out.println("Añadir video?(1)Si(2)No");
            salir = in.nextInt();
            in.nextLine();
        } while (salir == 1);
        return ingresos;
    }
    public static double cPM(int seleccion){//nos da los valores predefinidos del cpm
        switch (seleccion){
            case 1->{return 0.1;}
            case 2->{return 1;}
            case 3->{return 0.2;}
            case 4,5->{return 0.5;}
        }
        return 0;
    }
    public static double calcularIngresoVideo(double cpm, int visitas){//calcula lo generado con un video

        return (cpm * visitas)/1000;
    }
    public static void aceptar(){//espera antes de borrar la pantalla
        Scanner in = new Scanner(System.in);
        System.out.println("Aceptar");
        in.nextLine();

    }
    public static void borrarPantalla(){//añade limpieza al programa
        for (int i = 0; i < 20; i++) {
            System.out.println("\n\n");

        }
    }
    public static int pedirLaHora(){//pide la hora
        Scanner in = new Scanner(System.in);
        int hora;
        do {
            System.out.println("Introduce la hora 1-12");
            hora = in.nextInt();
            if (hora<1 || hora>12){
                System.out.println("Error, hora incorrecta");
            }
        }while (hora <1 || hora >12);
        return hora;
    }
    public static int pedirMinutos(){//pide los minutos
        Scanner in = new Scanner(System.in);
        int minutos;
        do {
            System.out.println("Introduce la minutos 1-59");
            minutos = in.nextInt();
            if (minutos <1 || minutos >59){
                System.out.println("Error, minutos incorrectos");
            }
        }while (minutos <1 || minutos >59);
        return minutos;
    }
    public static String pedirMniana(){//pide am o pm
        Scanner in = new Scanner(System.in);
        String maniana;
        do {
            System.out.println("Introduce AM o PM");

            maniana = in.next();
            maniana=maniana.toLowerCase(Locale.ROOT);
        }while (!maniana.equals("am") & !maniana.equals("pm"));
        return maniana;
    }
    public static int pedirhuso(){//pide el huso horario
        Scanner in = new Scanner(System.in);
        int hora;
        do {
            System.out.println("Introduce el huso");
            hora = in.nextInt();
            if (hora<-12 || hora>12){
                System.out.println("Error, huso incorrecto");
            }
        }while (hora <-12 || hora >12);
        return hora;
    }
    public static void calculoHora(int hora, int minuto, int huso, String pm){
        //calcula la hora
        if (hora + huso > 12){//reseteo por abajo
            hora = (hora + huso)-12;
            if (pm.equals("am")) {
            pm = "pm";
            }else {
                pm = "am";
            }

        } else if (hora + huso < 1) {//resetreo por arriba
            hora = (hora + huso) + 12;
            if (pm.equals("am")) {
                pm = "pm";
            }else {
                pm = "am";
            }
        }else {
            hora+=huso;
        }
        System.out.println("En tu huso horario son las  " + hora + ':' + minuto + " " + pm);
        System.out.println("###############################################");

        aceptar();
        borrarPantalla();

    }
}