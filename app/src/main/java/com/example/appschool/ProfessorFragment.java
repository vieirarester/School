package com.example.appschool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.appschool.databinding.FragmentProfessorBinding;

import java.util.ArrayList;

public class ProfessorFragment extends Fragment {

    FragmentProfessorBinding professorBinding;
    UsuarioDao usuarioDao;
    ArrayList<Usuario> usuarios;
    ArrayList<String> usuariosString;
    ArrayAdapter<String> adapter;

    public ProfessorFragment() {
        super(R.layout.fragment_professor);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        professorBinding = FragmentProfessorBinding.inflate(inflater, container, false);

        usuarioDao = DatabaseSingleton.getInstance(getActivity()).appDatabase.usuarioDao();
        usuarios = (ArrayList<Usuario>) usuarioDao.listarTodos();
        usuariosString = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, usuariosString);

        return professorBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(Usuario usuario: usuarios){
            usuariosString.add(usuario.toString());
        }

        professorBinding.listaUsuarios.setAdapter(adapter);

        professorBinding.listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle atributos = new Bundle();
                atributos.putString("nome", usuarios.get(i).nome);
                atributos.putString("categoria", usuarios.get(i).categoria);
                atributos.putInt("id", usuarios.get(i).id);

                Navigation.findNavController(view).navigate(R.id.cadastrarFragment, atributos);
            }
        });
    }
}