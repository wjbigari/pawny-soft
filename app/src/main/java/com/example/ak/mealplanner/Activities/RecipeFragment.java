package com.example.ak.mealplanner.Activities;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ak.mealplanner.Adapters.RVAdapter;
import com.example.ak.mealplanner.Models.UserRecipe;
import com.example.ak.mealplanner.R;

import java.util.ArrayList;

import com.example.ak.mealplanner.Controllers.GetUserRecipesController;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MyApplication app;
    private OnFragmentInteractionListener mListener;

    private RVAdapter adapter;

    private RecyclerView rv;

    public RecipeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onResume(){
        super.onResume();
        try{
            GetUserRecipesController gurc = new GetUserRecipesController(adapter,app.getUser().getUsername());
            gurc.execute();
        }catch(Exception e){

        }

    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeFragment newInstance(String param1, String param2) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab_fragment2, container, false);

        ArrayList<UserRecipe> recipes = new ArrayList<UserRecipe>();

        rv=(RecyclerView) rootView.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        app = (MyApplication) getActivity().getApplication();

        adapter = new RVAdapter(recipes, (MainActivity) getActivity());
        try{
            GetUserRecipesController gurc = new GetUserRecipesController(adapter, app.getUser().getUsername() );
            gurc.execute();
        }catch(Exception e){

        }


        adapter.sort();
        adapter.notifyDataSetChanged();

        rv.setAdapter(adapter);



        /**
        final ListView mainListView =  rootView.findViewById(R.id.recipeList);
        ArrayList<UserRecipe> foodList = new ArrayList<UserRecipe>();
        app = (MyApplication) getActivity().getApplication();
        // Create ArrayAdapter
        listAdapter  = new ArrayAdapter<UserRecipe>(getContext(), R.layout.listrow, foodList);
        GetUserRecipesController gurc = new GetUserRecipesController(listAdapter, app.getUser().getUsername() );
        gurc.execute();


        listAdapter.sort(new Comparator<UserRecipe>() {
            @Override
            public int compare(UserRecipe o1, UserRecipe o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        listAdapter.notifyDataSetChanged();

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserRecipe x = (UserRecipe) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), UserRecipeActivity.class);
                intent.putExtra("userRecipe", x);
                app.setUserRecipe(x);
                startActivity(intent);
            }
        });

         */
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
