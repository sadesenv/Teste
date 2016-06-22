package cotemig.com.br.expressomovel;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cotemig.com.br.expressomovel.Entidades.Item;
import cotemig.com.br.expressomovel.dao.ItemDAO;

public class ListarEntregasAdapter extends RecyclerView.Adapter<ListarEntregasAdapter.ViewHolder> {
    private static ArrayList<Item> listaItens;
    public static Context aContext;
    public static Long idUsuario;

    public ListarEntregasAdapter(Context context, ArrayList<Item> i, long id) {
        aContext = context;
        listaItens = i;
        idUsuario = id;
    }

    @Override
    public ListarEntregasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista_entregas, parent, false);

        // create ViewHolder

        ViewHolder aHolder = new ViewHolder(rowView);
        return aHolder;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.Id.setText(String.valueOf(listaItens.get(position).getIdItem()));
        viewHolder.Descricao.setText(listaItens.get(position).getDescricao());
        viewHolder.DtEntrega.setText(listaItens.get(position).getDataEntrega());
        viewHolder.DtRetirada.setText(listaItens.get(position).getDataRetirada());
        viewHolder.LocalEntrega.setText(listaItens.get(position).getLocalEntrega());
        viewHolder.LocalRetirada.setText(listaItens.get(position).getLocalRetirada());

        long idEntregador = listaItens.get(position).getIdEntregador();
        if (idEntregador == 0) {
            viewHolder.btnCancelar.setVisibility(View.GONE);
            viewHolder.btnAceitar.setVisibility(View.VISIBLE);
        } else {
            viewHolder.btnCancelar.setVisibility(View.VISIBLE);
            viewHolder.btnAceitar.setVisibility(View.GONE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listaItens.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Id;
        public TextView Descricao;
        public TextView DtEntrega;
        public TextView LocalRetirada;
        public TextView DtRetirada;
        public TextView LocalEntrega;
        public ImageButton btnRoute;
        public ImageButton btnAceitar;
        public ImageButton btnCancelar;

        public ViewHolder(View rowView) {
            super(rowView);

            Id = (TextView) rowView.findViewById(R.id.entrega_Id);
            Descricao = (TextView) rowView.findViewById(R.id.entrega_Descricao);
            DtEntrega = (TextView) rowView.findViewById(R.id.entrega_DtEntrega);
            LocalRetirada = (TextView) rowView.findViewById(R.id.entrega_LocalRetirada);
            DtRetirada = (TextView) rowView.findViewById(R.id.entrega_DtRetirada);
            LocalEntrega = (TextView) rowView.findViewById(R.id.entrega_LocalEntrega);
            btnRoute = (ImageButton) rowView.findViewById(R.id.entrega_btnRoute);
            btnAceitar = (ImageButton) rowView.findViewById(R.id.entrega_btnAceitar);
            btnCancelar = (ImageButton) rowView.findViewById(R.id.entrega_btnCancelar);

            btnAceitar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle(R.string.aceitar_tittle)
                            .setMessage(R.string.aceitar_msg)
                            .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    listaItens.get(getAdapterPosition()).setIdEntregador(idUsuario);
                                    ItemDAO itemDAO = new ItemDAO(aContext);
                                    itemDAO.aceitarEntrega(listaItens.get(getAdapterPosition()));
                                    refreshAt(getAdapterPosition());
                                }
                            })
                            .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .show();
                }
            });

            btnCancelar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(v.getContext())
                            .setTitle(R.string.cancelar_title)
                            .setMessage(R.string.cancelar_msg)
                            .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String itemDescricao = listaItens.get(getAdapterPosition()).getDescricao();
                                    if (listaItens.get(getAdapterPosition()).getIdEntregador() == idUsuario) {
                                        long id = 0;
                                        listaItens.get(getAdapterPosition()).setIdEntregador(id);
                                        ItemDAO itemDAO = new ItemDAO(aContext);
                                        itemDAO.cancelarEntrega(listaItens.get(getAdapterPosition()));
                                        refreshAt(getAdapterPosition());
                                    } else {
                                        Toast.makeText(aContext, "Entrega " + itemDescricao + "não pode ser cancelada!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .show();

                }
            });

            btnRoute.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putString("Origem", LocalRetirada.getText().toString());
                    b.putString("Destino", LocalEntrega.getText().toString());
                    Intent i = new Intent(aContext, MapaActivity.class);
                    i.putExtras(b);
                    aContext.startActivity(i);
                }
            });
        }
    }

    public void refreshAt(int position) {
        notifyItemChanged(position);
    }

}
