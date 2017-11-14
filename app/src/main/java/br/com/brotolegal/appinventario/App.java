package br.com.brotolegal.appinventario;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.brotolegal.appinventario.Models.Usuario;


/**
 *
 * Created by Falconi on 20/10/2015.
 *
 */
public class App extends Application {
    private static String TAG = "App";
    private static Context context;
    public static String BasePath = Environment.getExternalStorageDirectory().getPath(); //"/mnt/sdcard/";
    public static String AppPath  = "SAI700";
    public static final String version;
    public static Usuario user;
    public static Boolean ItsOK = false;
    public static String PathDB = BasePath + "/" + AppPath;
    public static String base   = HelpInformation.BaseProducao;

    static {

        String retorno = "";

        try {


            int versao = Build.VERSION.SDK_INT;

            switch (versao) {
                case 8:

                    retorno = "Android 2.2 Froyo";

                    break;

                case 9:

                    retorno = "Android 2.3 Gingerbread";

                    break;

                case 10:

                    retorno = "Android 2.3.3 Gingerbread";

                    break;

                case 11:

                    retorno = "Android 3.0 Honeycomb";

                    break;

                case 12:

                    retorno = "Android 3.1 Honeycomb";

                    break;

                case 13:

                    retorno = "Android 3.2 Honeycomb";

                    break;

                case 14:

                    retorno = "Android 4.0 Ice Cream Sandwich";

                    break;

                case 15:

                    retorno = "Android 4.0.3 Ice Cream Sandwich";

                    break;

                case 16:

                    retorno = "Android 4.1 Jellybean";

                    break;

                case 17:

                    retorno = "Android 4.2 Jellybean";

                    break;
                case 18:

                    retorno = "Android 4.3 Jellybean";

                    break;
                case 19:

                    retorno = "Android 4.4 KitKat";

                    break;
                case 20:

                    retorno = "Android 4.4 KitKat Watch";

                    break;
                case 21:

                    retorno = "Android 5.0 Lollipop";

                    break;
                case 22:

                    retorno = "Android 5.1 Lollipop";

                    break;
                default:
                    break;
            }

        } catch (Exception e) {

            retorno = "Erro Na Veirificação !!!!";

        }


        version = retorno;
    }

    public void onCreate(){

        super.onCreate();

        context=getApplicationContext();

        Log.i(TAG, "Definido Contexto....");

        user = new Usuario();

        Estrutura stru = new Estrutura();


        /* Verifica se existe a estrutura de pasta para aplicação */

        try {

            if (stru.isFirst()) {

                /* Cria A Estrutura de diretorios */
                try {

                    stru.CreateBaseStructure();

                    Log.i(TAG, "Estrutura De Pastas Do Sistema Criada Com Socesso.");

                } catch (Exception e) {

                    Log.i(TAG,"Não Foi Possível Criar As Pastas Do Sistema.");

                }

            }

        } catch (Exception e) {

            Log.i(TAG,e.getMessage());

        }

        try {

            ItsOK = true;

        } catch (Exception e){

            Log.i(TAG,e.getMessage());

        }
    }

    public static Context getCustomAppContext(){
        return context;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {

        this.user = user;
    }


    public static Boolean getItsOK() {
        return ItsOK;
    }

    public static String TotvsSN(String flag){

        String retorno = "N";

        if (flag.trim().equals("2") || flag.isEmpty()){

            retorno = "N";

        } else {

            retorno = "S";

        }

        return  retorno;


    }

    public static String TotvsSIMNAO(String flag){

        String retorno = "N";

        if (flag == null){

            return retorno;


        }


        if (flag.trim().equals("2") || flag.trim().isEmpty()){

            retorno = "NÃO";

        } else {

            retorno = "SIM";

        }

        return  retorno;


    }

    public static String TabletSIMNAO(String flag){

        String retorno = "NÃO";

        if (flag == null){

            return retorno;


        }


        if (flag.trim().equals("N") || flag.trim().isEmpty()){

            retorno = "NÃO";

        } else {

            retorno = "SIM";

        }

        return  retorno;


    }


    public static String aaaammddToddmmaaaa(String value) {

        String retorno = "";

        if (value == null) return retorno;

        if (value.length() < 8) return retorno;


        if (!value.isEmpty()) {

            retorno = value.substring(6, 8) + "/" +
                    value.substring(4, 6)   + "/" +
                    value.substring(0, 4);


        }

        return retorno;
    }

    public static String aaaammddToddmmaa(String value) {

        String retorno = "";

        if (!value.isEmpty()) {

            retorno = value.substring(6, 8) + "/" +
                    value.substring(4, 6)   + "/" +
                    value.substring(2, 4);


        }

        return retorno;
    }

    public static String ddmmaaaaatoaaaammdd(String value) {

        String retorno = "";

        if (!value.isEmpty()) {

            retorno = value.substring(6, 10)   +
                    value.substring(3,  5)   +
                    value.substring(0,  2);


        }

        return retorno;
    }

    public static String getHoraHHMM() {

        String retorno = "";

        Calendar c = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        retorno = format.format(c.getTime());

        return retorno;
    }

    public static String cep(String value) {

        String retorno = "";

        if (!value.isEmpty()) {

            retorno = value.substring(0, 5) + "-" +  value.substring(5, 8);

        }

        return retorno;
    }

    public static String cnpj_cpf(String value){

        String retorno = "";

        int len = 0;

        len = value.trim().length();

        switch (len) {

            case 11:{

                retorno = value.substring(0,3)+"."+value.substring(3,6)+"."+value.substring(6,9)+"-"+value.substring(9,11);

                break;

            }

            case 14:{

                retorno = value.substring(0,2)+"."+value.substring(2,5)+"."+value.substring(5,8)+"/"+value.substring(8,12)+"-"+value.substring(12,14);

                break;
            }

            default:

                retorno = value;

                break;
        }


        return  retorno;


    }

    public  static String getNewID()

    {

        String retorno = "";

        Calendar c = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        retorno = App.user.getCOD() + format.format(c.getTime());

        return retorno;

    }

    public  static String getHoje()

    {

        String retorno = "";

        Calendar c = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        retorno = format.format(c.getTime());

        return retorno;

    }

    public  static String getHojeaaaamm()

    {

        String retorno = "";

        Calendar c = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");

        retorno = format.format(c.getTime());

        return retorno;

    }

    public  static String getInicialMesddmmyyyy()

    {

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            setTimeToBeginningOfDay(calendar);
            Date begining = calendar.getTime();
            return format.format(begining.getTime());
        }


    }

    public  static String getFinalMesddmmyyyy()

    {

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            setTimeToEndofDay(calendar);
            Date end = calendar.getTime();
            return format.format(end.getTime());
        }


    }

    private static Calendar getCalendarForNow() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    private static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    public static Double getJuros(String Juros){


        BigDecimal JUROS;

        Double juros = (Double.parseDouble(Juros));

        JUROS = new BigDecimal(juros);

        JUROS = JUROS.setScale(9, BigDecimal.ROUND_HALF_UP);

        return JUROS.doubleValue();


    }

    public static String getDataHora(){

        String retorno = "";

        Calendar c = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        retorno = format.format(c.getTime());

        return retorno;




    }


    public static String diferencaDatas(String DataInicial, String DataFinal){

        String retorno = " sem registro ";

        try {

            int hours, minutes, seconds;

            Calendar calendarInicio = Calendar.getInstance();

            Calendar calendarFinal  = Calendar.getInstance();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Date Inicial = format.parse(DataInicial);

            Date Final   = format.parse(DataFinal);

            calendarInicio.setTime(Inicial);

            calendarFinal.setTime(Final);

            long difMilli = Final.getTime() - Inicial.getTime() ;

            int timeInSeconds = (int)difMilli / 1000;

            hours = timeInSeconds / 3600;

            timeInSeconds = timeInSeconds - (hours * 3600);

            minutes = timeInSeconds / 60;

            timeInSeconds = timeInSeconds - (minutes * 60);

            seconds = timeInSeconds;

            if (minutes < 0 || seconds < 0){

                retorno = "Recalculando...";

            } else {

                retorno = String.valueOf(minutes) + " Min. e " + String.valueOf(seconds)+" Seg." ;

            }


        } catch (Exception e){

            retorno = " sem registro ";

        }

        return retorno;

    }

    public static String BaseNome(){

        String retorno = "PRODUÇÃO";

        if (base.equals(HelpInformation.BaseProducao))  retorno = "PRODUÇÃO";
        if (base.equals(HelpInformation.BaseHomolacao)) retorno = "HOMOLOGAÇÃO";
        if (base.equals(HelpInformation.BaseProtheus))  retorno = "PROTHEUS 12";


        return retorno;

    }

    //INNER CLASS
    public class Estrutura {


        public Estrutura() {


        }


        public Boolean isFirst() {

            Boolean retorno = false;

            String path = App.BasePath + "/" + App.AppPath;

            /* Verifica a existencia do diretorio */

            File dir = new File(path);

            try {

                retorno = !dir.exists();

            } catch (Exception e) {

                retorno = true;

            }

            return retorno;
        }


        public void CreateBaseStructure() throws Exception {

            String path = App.BasePath + "/" + App.AppPath;

            try {

                if (isFirst()) {

                    File dir = new File(path);

                    if (!dir.mkdirs()) {

                        throw new Exception("Erro Na Criação Do Diretório Da Aplicação.");
                    }

                }

            } catch (Exception e) {

                throw new Exception("Erro Na Criação Do Diretório Da Aplicação.");

            }

        }


        public void CreateUserStructure(String codigo) throws Exception {

            String path = App.BasePath + "/" + App.AppPath + "/" + codigo;

            try {

                if (!isFirst()) {

                    File dir = new File(path);

                    if (!dir.exists()) {

                        if (!dir.mkdirs()) {

                            throw new Exception("Erro Na Criação Da Pasta Do Usuário.");

                        }

                    }

                } else {

                    throw new Exception("Pasta Do Usuário Não Existe.");

                }

            } catch (Exception e) {

                throw new Exception("Erro Na Criação Da Pasta Do Usuário.");

            }

        }

        public String getUserDataPath(){


            return App.BasePath + "/" + App.AppPath + "/" + App.user.getCOD();

        }
    }




}





