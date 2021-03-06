package com.example.ak.mealplanner.Activities;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ak.mealplanner.Controllers.GetUserRecipesController;
import com.example.ak.mealplanner.Models.FoodItem;
import com.example.ak.mealplanner.R;

import java.util.ArrayList;
import java.util.Comparator;

import com.example.ak.mealplanner.Controllers.SearchController;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BuilderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BuilderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuilderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SearchController searchController;
    private EditText inputSearch;

    private ArrayAdapter<FoodItem> listAdapter;

    private OnFragmentInteractionListener mListener;
    MyApplication app;

    public BuilderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuilderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuilderFragment newInstance(String param1, String param2) {
        BuilderFragment fragment = new BuilderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);

        final ListView mainListView =  rootView.findViewById(R.id.list_main);
        app = (MyApplication)getActivity().getApplication();
        // Create and populate a List of food names
        ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();

        // Create ArrayAdapter
        listAdapter  = new ArrayAdapter<FoodItem>(getContext(), R.layout.listrow, foodList);


        // Add more foods
//        listAdapter.add(new FoodItem("Bread", 10));
//        listAdapter.add(new FoodItem("Milk", 10));
//        listAdapter.add(new FoodItem("Eggs", 10));
//        listAdapter.add(new Fooditem("Orange", "10"));
//        listAdapter.add(new Fooditem("Yogurt", "10"));
//        listAdapter.add(new Fooditem("Bacon", "10"));
//        listAdapter.add(new Fooditem("Ham", "10"));
//        listAdapter.add(new Fooditem("Lettuce", "10"));
//        listAdapter.add(new Fooditem("Apple", "10"));
//        listAdapter.add(new Fooditem("Rice", "10"));
//        listAdapter.add(new Fooditem("Steak", "10"));
//        listAdapter.add(new Fooditem("Avocado", "10"));
//        listAdapter.add(new Fooditem("Beans", "10"));
//        listAdapter.add(new Fooditem("Salsa", "10"));


        listAdapter.sort(new Comparator<FoodItem>() {
            @Override
            public int compare(FoodItem o1, FoodItem o2) {
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
                FoodItem x = (FoodItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), FoodActivity.class);
                intent.putExtra("foodItem", x);
                startActivity(intent);

            }
        });

        inputSearch = (EditText) rootView.findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

                if(searchController!= null && searchController.getStatus()== AsyncTask.Status.RUNNING){
                    searchController.cancel(true);
                }
                try{
                    searchController = new SearchController(cs.toString(), listAdapter, app.getUser().getUsername());

                    searchController.execute();
                }catch (Exception e){

                }


                //listAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(searchController!= null && searchController.getStatus()== AsyncTask.Status.RUNNING){
            searchController.cancel(true);
        }
        searchController = new SearchController(inputSearch.getText().toString(), listAdapter, app.getUser().getUsername());

        searchController.execute();

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
