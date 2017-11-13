package br.com.brotolegal.appinventario;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import Models.Lancamento;
import Models.NoData;

public class LancamentosActivity extends AppCompatActivity {

    private Adapter adapter;
    private FloatingActionButton fab;
    private List<Object> lsLancamentos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamentos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("LANÇAMENTOS DO INVENTÁRIO");
        toolbar.setSubtitle("20/12/2017");
        toolbar.setLogo(R.mipmap.ic_launcher);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

                    break;

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

                        txt_nroetiqueta_400.setText(obj.getData());

                        txt_armazem_400.setText("Armazém: "+obj.getArmazem());
                        txt_contagem_400.setText("Contagem: "+obj.getContagem());
                        txt_produto_400.setText(obj.getCodProduto());

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

        final TextView preco = (TextView) dialog.findViewById(R.id.txt_preco_118);

        preco.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Float value = 0f;

                try {

                    value = Float.valueOf(preco.getText().toString().replaceAll(",", "."));

                } catch (Exception e) {

                    value = 0f;

                }

                adapter.refresh(pedido.getEdicao());


            }
        });


        campo.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);


        if (pedido.getEdicao().getTypeByName(FieldName).equals(ObjRegister._float)){

            Float value = (Float) pedido.getEdicao().getFieldByName(FieldName);

            campo.setText(format_04.format(value));

        }

        final Button confirmar    = (Button) dialog.findViewById(R.id.bt_confirma_118);
        final Button cancelar     = (Button) dialog.findViewById(R.id.bt_cancela_118);

        cancelar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                try

                {

                    if (pedido.getEdicao().getTypeByName(FieldName).equals(ObjRegister._float)) {

                        if (FieldName.equals("BONIPREC")) {

                            Float precoMaximo = pedido.getEdicao().getPRECOFORMACAO() * (1 + (pedido.getEdicao().getACRESCIMOMAIS() / 100));

                            Float descMaximo = pedido.getEdicao().getDESCONTOMAIS() + pedido.getEdicao().getDESCONTOPOL();

                            BigDecimal precven;

                            BigDecimal desc;

                            precven = new BigDecimal(pedido.getEdicao().getPRECOFORMACAO());

                            desc = new BigDecimal(descMaximo);

                            Double preco = precven.doubleValue() - (precven.doubleValue() * (desc.doubleValue() / 100));

                            precven = new BigDecimal(preco);

                            precven = precven.setScale(2, BigDecimal.ROUND_HALF_UP);

                            precven.doubleValue();

                            Float precoMinimo = precven.floatValue();

                            Float value = 0f;

                            try {
                                value = Float.valueOf(campo.getText().toString().replaceAll(",", "."));

                            } catch (Exception e) {

                                value = 0f;

                            }

                            if (((Float.compare(value, precoMaximo)) > 0) || (Float.compare(value, precoMinimo)) < 0) {

                                if (Float.compare(value, precoMaximo) > 0)

                                    Toast.makeText(getBaseContext(), "Preço Não Pode Ser Maior Que: " + format_02.format(precoMaximo), Toast.LENGTH_LONG).show();


                                if (Float.compare(value, precoMinimo) < 0)

                                    Toast.makeText(getBaseContext(), "Preço Não Pode Ser Menor Que: " + format_02.format(precoMinimo), Toast.LENGTH_LONG).show();

                            } else {

                                pedido.getEdicao().setFieldByName(FieldName, value);


                            }

                        } else {

                            Float precoMaximo = pedido.getEdicao().getPRECOFORMACAO() * (1 + (pedido.getEdicao().getACRESCIMOMAIS() / 100));

                            Float precoMinimo = pedido.getEdicao().getPRECOFORMACAO() - ( pedido.getEdicao().getPRECOFORMACAO() * ( (pedido.getEdicao().getDESCONTOPOL() + pedido.getEdicao().getDESCONTOMAIS())/100));

                            Float value = 0f;

                            try {
                                value = Float.valueOf(campo.getText().toString().replaceAll(",", "."));

                            } catch (Exception e) {

                                value = 0f;

                            }

                            if ((Float.compare(value, precoMaximo)) > 0 || (Float.compare(value, precoMinimo)) < 0 ) {

                                if ((Float.compare(value, precoMaximo)) > 0 ) {

                                    Toast.makeText(getBaseContext(), "Preço Não Pode Ser Maior Que: " + format_02.format(precoMaximo), Toast.LENGTH_LONG).show();

                                }


                                if ( (Float.compare(value, precoMinimo)) < 0  ) {

                                    Toast.makeText(getBaseContext(), "Preço Não Pode Ser Menor Que: " + format_02.format(precoMinimo), Toast.LENGTH_LONG).show();

                                }
                            }

                            else {

                                pedido.getEdicao().setDESCON(0f);

                                pedido.ajustaPrcVenByPreco(pedido.getEdicao(), value);

                                pedido.getEdicao().setFieldByName(FieldName, value);

                            }
                        }
                    }
                    pedido.validarItemEdicao();

                    pedido.getLsDetalhe().set(Integer.valueOf(pedido.getEdicao().getITEM()) - 1,pedido.getEdicao().ToFast() );

                    pedido.recalculo();

                    pedido.Validar();

                    pedido.getEdicao().ImportFast(pedido.getLsDetalhe().get(Integer.valueOf(pedido.getEdicao().getITEM()) - 1));


                } catch  (Exception e){

                    Toast.makeText(getBaseContext(), e.getMessage(),Toast.LENGTH_LONG).show();

                    pedido.getLsDetalhe().set(Integer.valueOf(pedido.getEdicao().getITEM()) - 1,pedido.getEdicao().ToFast() );

                    pedido.recalculo();

                    pedido.Validar();

                    pedido.getEdicao().ImportFast(pedido.getLsDetalhe().get(Integer.valueOf(pedido.getEdicao().getITEM()) - 1));

                }
                if ((dialog != null)){

                    if (dialog.isShowing()){

                        dialog.dismiss();

                    }

                }

                cabec_refresh();

                cabec_onClick();

                detalhe_popula();

                adapter.refresh(pedido.getEdicao());


            }

        });

        dialog.show();

    }

    private  void toast(String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}


}
