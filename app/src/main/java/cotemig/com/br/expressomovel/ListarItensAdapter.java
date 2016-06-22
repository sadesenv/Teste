package cotemig.com.br.expressomovel;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import cotemig.com.br.expressomovel.Entidades.Item;
import cotemig.com.br.expressomovel.dao.ItemDAO;
import cotemig.com.br.expressomovel.dao.UsuarioDAO;

public class ListarItensAdapter extends RecyclerView.Adapter<ListarItensAdapter.ViewHolder> {
    private static ArrayList<Item> listaItens;
    public static Context aContext;

    public ListarItensAdapter(Context context, ArrayList<Item> i) {
        aContext = context;
        listaItens = i;
    }

    @Override
    public ListarItensAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista_itens, parent, false);

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

        UsuarioDAO dao = new UsuarioDAO(aContext);
        viewHolder.Entregador.setText(dao.getNome(listaItens.get(position).getIdEntregador()));
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
        public TextView Entregador;
        public ImageButton btnEditar;
        public ImageButton btnCancelar;

        public ViewHolder(View rowView) {
            super(rowView);

            Id = (TextView) rowView.findViewById(R.id.pedido_Id);
            Descricao = (TextView) rowView.findViewById(R.id.pedido_Descricao);
            DtEntrega = (TextView) rowView.findViewById(R.id.pedido_DtEntrega);
            LocalRetirada = (TextView) rowView.findViewById(R.id.pedido_LocalRetirada);
            DtRetirada = (TextView) rowView.findViewById(R.id.pedido_DtRetirada);
            LocalEntrega = (TextView) rowView.findViewById(R.id.pedido_LocalEntrega);
            Entregador = (TextView) rowView.findViewById(R.id.pedido_Entregador);

            btnEditar = (ImageButton) rowView.findViewById(R.id.pedido_btnEdit);
            btnCancelar = (ImageButton) rowView.findViewById(R.id.pedido_btnCancelar);

            btnEditar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String itemDescricao = listaItens.get(getAdapterPosition()).getDescricao();
                    Intent vaiProFormulario = new Intent(aContext, CadastrarItemActivity.class);
                    vaiProFormulario.putExtra("item", listaItens.get(getAdapterPosition()));
                    aContext.startActivity(vaiProFormulario);
                    Toast.makeText(aContext, "Editar " + itemDescricao, Toast.LENGTH_SHORT).show();
                }
            });

            btnCancelar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String itemDescricao = listaItens.get(getAdapterPosition()).getDescricao();
                    ItemDAO itemDAO = new ItemDAO(aContext);
                    if (listaItens.get(getAdapterPosition()).getIdEntregador() == 0) {
                        itemDAO.deletar(listaItens.get(getAdapterPosition()));
                        removeAt(getAdapterPosition());
                        Toast.makeText(aContext, "Deletar " + itemDescricao, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(aContext, "Item " + itemDescricao + " não pode ser excluido!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void removeAt(int position) {
        listaItens.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listaItens.size());
    }

}
