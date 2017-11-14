package br.com.brotolegal.appinventario;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.brotolegal.appinventario.Models.Etiqueta;
import br.com.brotolegal.appinventario.Models.Lancamento;
import br.com.brotolegal.appinventario.Models.NoData;
import br.com.brotolegal.appinventario.Models.Usuario;

public class LancamentosActivity extends AppCompatActivity {

    private Adapter adapter;
    private FloatingActionButton fab;
    private List<Object> lsLancamentos = new ArrayList<>();
    private ListView lv;
    private DecimalFormat format_02 = new DecimalFormat(",##0.00");
    private DecimalFormat format_04 = new DecimalFormat(",##0.0000");
    private DecimalFormat format_05 = new DecimalFormat(",##0.00000");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamentos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("LANÇAMENTOS DO INVENTÁRIO");
        toolbar.setSubtitle("20/12/2017");
        toolbar.setLogo(R.mipmap.ic_launcher);

        final Activity activity = this;

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lv = (ListView) findViewById(R.id.lv_lancamentos_400);

        fab = (FloatingActionButton) findViewById(R.id.plus_inventario);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator =  new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Aponte Para Etiqueta");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();


            }
        });

        if (lsLancamentos.size() == 0){

            loadLancamentos();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lancamento_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                finish();

                break;

            case R.id.action_menu_sincronizar:

                finish();

                break;

            default:

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {

        try {


        } catch (Exception e) {

            Toast.makeText(LancamentosActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        super.onResume();
    }

    @Override
    public void finish() {

        lsLancamentos    = new ArrayList<Object>();

        super.finish();

    }

    private void toast(String mensagem){

        Toast.makeText(LancamentosActivity.this,mensagem, Toast.LENGTH_LONG).show();


    }

    private void loadLancamentos(){

        try {

            lsLancamentos.clear();

            lsLancamentos.add("CABEC");

            lsLancamentos.add(new Lancamento("000100","20171113","027260201","01","0","01","#FFC0CB",new Usuario(),"20171113 08:30","",0f,"FEIJÃO BROTO LEGAL 30X1"));

            lsLancamentos.add(new Lancamento("000101","20171113","027270201","01","0","01","#14649F",new Usuario(),"20171113 09:30","",0f,"FEIJÃO BROTO LEGAL 10X1"));

            lsLancamentos.add(new Lancamento("000102","20171113","027280201","01","0","01","#FF0033",new Usuario(),"20171113 09:30","",0f,"FEIJÃO BROTO LEGAL 60X1"));

            lsLancamentos.add(new Lancamento("000103","20171113","027280201","01","0","01","#FF0033",new Usuario(),"20171113 09:30","",0f,"FEIJÃO BROTO LEGAL 60X1"));

            lsLancamentos.add(new Lancamento("000104","20171113","027280201","01","0","01","#FF0033",new Usuario(),"20171113 09:30","",0f,"FEIJÃO BROTO LEGAL 60X1"));

            lsLancamentos.add(new Lancamento("000105","20171113","027280201","01","0","01","#FF0033",new Usuario(),"20171113 09:30","",0f,"FEIJÃO BROTO LEGAL 60X1"));

            lsLancamentos.add(new Lancamento("000106","20171113","027280201","01","0","01","#FF0033",new Usuario(),"20171113 09:30","",0f,"FEIJÃO BROTO LEGAL 60X1"));

            lsLancamentos.add(new Lancamento("000107","20171113","027280201","01","0","01","#FF0033",new Usuario(),"20171113 09:30","",0f,"FEIJÃO BROTO LEGAL 60X1"));

            lsLancamentos.add(new Lancamento("000108","20171113","027280201","01","0","01","#FF0033",new Usuario(),"20171113 09:30","",0f,"FEIJÃO BROTO LEGAL 60X1"));


            adapter = new Adapter(LancamentosActivity.this,lsLancamentos);

            lv.setAdapter(adapter);

            adapter.notifyDataSetChanged();

        } catch (Exception e){

            toast(e.getMessage());

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result != null){

            if (result.getContents() == null){

                toast("Leitura Cancelada !!!");

            } else {

                try {

                    toast(result.getContents());

                    Etiqueta et = new Gson().fromJson(result.getContents(), Etiqueta.class);

                    adapter.baixaInventario(et);

                } catch (Exception e){

                    toast(e.getMessage());

                }
            }

        } else {

            super.onActivityResult(requestCode,resultCode,data);

        }

    }

    private  class Adapter extends BaseAdapter
    {

        DecimalFormat format_02 = new DecimalFormat(",##0.00");

        private List<Object> lsObjetos;

        Context context;
        final int ITEM_VIEW_CABEC            = 0;
        final int ITEM_VIEW_LANCAMENTO       = 1;
        final int ITEM_VIEW_NODATA           = 2;
        final int ITEM_VIEW_COUNT            = 3;


        private LayoutInflater inflater;

        public Adapter(Context context, List<Object> pObjects) {

            this.lsObjetos    = pObjects;
            this.context      = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }




        private String Cabec(){

            String retorno = "";

            int qtd = 0;

            for (Object obj : lsObjetos) {


                if (obj instanceof Lancamento) {

                    qtd++;

                }


            }


            retorno = "Qtd De Etiquetas: "+ String.valueOf(qtd);


            return retorno;
        }


        @Override
        public int getCount() {
            return lsObjetos.size();
        }

        @Override
        public Object getItem(int position) {
            return lsObjetos.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public int getViewTypeCount() {
            return ITEM_VIEW_COUNT;
        }


        @Override
        public int getItemViewType(int position) {

            int retorno = -1;

            if (lsObjetos.get(position) instanceof String){

                retorno = ITEM_VIEW_CABEC;
            }


            if (lsObjetos.get(position) instanceof Lancamento){

                retorno = ITEM_VIEW_LANCAMENTO;
            }

            if (lsObjetos.get(position) instanceof NoData){

                retorno = ITEM_VIEW_NODATA;

            }

            return retorno;


        }


        @Override
        public boolean isEnabled(int position) {

            boolean retorno = false;
            return retorno;

        }

        public void deleteitem(int position) {

            this.lsObjetos.remove(position);

            notifyDataSetChanged();

            return;

        }


        public void setitem(int position, Float qtd) {

            if (this.lsObjetos.get(position) instanceof  Lancamento) {
                ((Lancamento) this.lsObjetos.get(position)).setQtd(qtd);
            }

            notifyDataSetChanged();

            return;

        }

        public void baixaInventario(Etiqueta et){

            int index = 0;

            Boolean lOk = false;

            for(Object obj : lsObjetos){

                if (obj instanceof Lancamento) {

                    if (    ((Lancamento) obj).getEtiqueta().equals(et.getEtiqueta()) &&
                            ((Lancamento) obj).getContagem().equals(et.getContagem()) &&
                            ((Lancamento) obj).getCodProduto().equals(et.getCodpro())   &&
                            ((Lancamento) obj).getArmazem().equals(et.getArmazen())
                            ){


                        lOk = true;

                        deleteitem(index);

                        notifyDataSetChanged();

                        break;
                    }

                }

                index++;

            }

            if (lOk){

                toast("Produto Inventariodo Com Sucesso...");

            } else {

                toast("Produto Não Inventariado...");

            }
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            try {

                final int pos  = position;

                final int type = getItemViewType(position);

                if (convertView == null) {

                    switch (type) {

                        case  ITEM_VIEW_CABEC:

                            convertView = inflater.inflate(R.layout.separador,null);

                            break;


                        case ITEM_VIEW_LANCAMENTO:

                            convertView = inflater.inflate(R.layout.lanca_det_row,null);

                            break;


                        case ITEM_VIEW_NODATA:

                            convertView = inflater.inflate(R.layout.no_data_row,null);

                            break;

                    }

                }

                switch (type) {

                    case ITEM_VIEW_CABEC:{

                        TextView tvMensagem = (TextView) convertView.findViewById(R.id.separador);

                        tvMensagem.setText(Cabec());

                        break;
                    }

                    case ITEM_VIEW_LANCAMENTO:{

                        final Lancamento obj = (Lancamento) lsObjetos.get(pos);

                        TextView txt_nroetiqueta_400  = (TextView) convertView.findViewById(R.id.txt_nroetiqueta_400);
                        TextView txt_cor_400          = (TextView) convertView.findViewById(R.id.txt_cor_400);
                        TextView txt_armazem_400      = (TextView) convertView.findViewById(R.id.txt_armazem_400);
                        TextView txt_contagem_400     = (TextView) convertView.findViewById(R.id.txt_contagem_400);
                        TextView txt_produto_400      = (TextView) convertView.findViewById(R.id.txt_produto_400);
                        TextView txt_qtd_400          = (TextView) convertView.findViewById(R.id.txt_qtd_400);

                        txt_nroetiqueta_400.setText(obj.getEtiqueta());
                        try {

                            txt_cor_400.setBackgroundColor(Color.parseColor(obj.getCor()));

                        } catch (Exception e){

                            txt_cor_400.setBackgroundColor(Color.WHITE);

                            txt_cor_400.setText("Sem Cor Definida");
                        }
                        txt_armazem_400.setText("Armazém: "+obj.getArmazem());
                        txt_contagem_400.setText("Contagem: "+obj.getContagem());
                        txt_produto_400.setText(obj.getDescricaoProduto());

                        txt_qtd_400.setOnClickListener(new ClickAgenda(context,obj,pos));


                        break;
                    }

                    case ITEM_VIEW_NODATA:{

                        final NoData obj = (NoData) lsObjetos.get(pos);

                        break;
                    }

                    default:

                        break;

                }

            }

            catch (Exception e) {

                StringWriter sw          = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();

                Log.i("SAI",exceptionAsString);

                toast("Erro No Adapdador =>" + e.getMessage());

            }


            return convertView;

        }

        public void toast (String msg)    {

            Toast.makeText (context, msg, Toast.LENGTH_SHORT).show ();

        }

    }

    private class ClickAgenda implements  View.OnClickListener {

        private Context context = null;

        private Lancamento obj = null;

        private int pos = 0;

        public ClickAgenda(Context context, Lancamento obj, int pos) {
            this.context = context;
            this.obj = obj;
            this.pos = pos;
        }

        @Override
        public void onClick(View v) {

            final Dialog dialog = new Dialog(LancamentosActivity.this);

            dialog.setContentView(R.layout.getprcven);

            dialog.setTitle("Favor Digitar O Informação");

            final TextView titulo = (TextView) dialog.findViewById(R.id.txt_campo_118);

            titulo.setText("Informe A  Qtd Inventariada");

            final EditText campo = (EditText) dialog.findViewById(R.id.edit_campo_118);


            campo.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

            campo.setText("");

            final Button confirmar    = (Button) dialog.findViewById(R.id.bt_confirma_118);
            final Button cancelar     = (Button) dialog.findViewById(R.id.bt_cancela_118);

            cancelar.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(campo.getWindowToken(), 0);

                    dialog.dismiss();

                }
            });

            confirmar.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    try

                    {

                        Float value = Float.parseFloat(campo.getText().toString().trim());

                        adapter.deleteitem(pos);

                        InputMethodManager imm = (InputMethodManager)getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(campo.getWindowToken(), 0);


                    } catch  (Exception e){

                        Toast.makeText(getBaseContext(), e.getMessage(),Toast.LENGTH_LONG).show();

                    }

                    if ((dialog != null)){

                        if (dialog.isShowing()){

                            dialog.dismiss();

                        }

                    }



                }

            });

            campo.requestFocus();
            InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            dialog.show();

        }

    }


}
