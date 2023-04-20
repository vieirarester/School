package com.example.appschool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appschool.databinding.FragmentCadastrarBinding;


public class CadastrarFragment extends Fragment {

    FragmentCadastrarBinding cadastrarBinding;
    UsuarioDao usuarioDao;

    public CadastrarFragment() {
        super(R.layout.fragment_cadastrar);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cadastrarBinding = FragmentCadastrarBinding.inflate(inflater, container, false);

        usuarioDao = DatabaseSingleton.getInstance(getContext()).appDatabase.usuarioDao();

        if(getArguments() != null){
            int id = getArguments().getInt("id");

            String nome = getArguments().getString("nome");
            String categoria = getArguments().getString("categoria");

            cadastrarBinding.editTextNome.setText(nome);
            cadastrarBinding.editTextCategoria.setText(categoria);
        }

        return cadastrarBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cadastrarBinding.botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = cadastrarBinding.editTextNome.toString();
                String categoria = cadastrarBinding.editTextCategoria.toString();

                Usuario usuario = new Usuario(nome, categoria);

                usuarioDao.inserir(usuario);

                Navigation.findNavController(view).navigate(R.id.loginFragment);
            }
        });

        cadastrarBinding.botaoExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getArguments() != null) {
                    int id = getArguments().getInt("id");

                    for(Usuario usuario: usuarioDao.listarTodos()){
                        if(usuario.id == id){
                            usuarioDao.deletar(usuario);

                        }
                    }
                }
                Navigation.findNavController(view).navigate(R.id.professorFragment);
            }

        });

    }
}