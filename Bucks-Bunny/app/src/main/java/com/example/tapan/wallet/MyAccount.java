package com.example.tapan.wallet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyAccount.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyAccount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAccount extends Fragment {

    final ThreadLocal<Personaldb> pdb= new ThreadLocal<Personaldb>(){
        @Override
        protected Personaldb initialValue() {
            return new Personaldb(getContext());
        }
    };

    final ThreadLocal<Personal> per= new ThreadLocal<Personal>(){
        @Override
        protected Personal initialValue() {
            return new Personal();
        }
    };
    public Context activity=getContext();
    
    
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //Button addExpense =(Button)findViewById(R.id.button);

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private MyAccount.OnFragmentInteractionListener mListener;

    public MyAccount() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyAccount.
     */
    // TODO: Rename and change types and number of parameters
    public static MyAccount newInstance(String param1, String param2) {
        MyAccount fragment = new MyAccount();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view =inflater.inflate(R.layout.fragment_my_account,container,false);
        Button AddExpense=(Button)view.findViewById(R.id.addexp);
        Button history=(Button)view.findViewById(R.id.history);
        final Button ExpenseSheet=(Button)view.findViewById(R.id.ExpenseSheet);
        final ListView listdata= (ListView)view.findViewById(R.id.listdata);
        AddExpense.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(),ExpenseInput.class);
                startActivity(i);


            }
        });

        history.setOnClickListener(new OnClickListener( ) {
            @Override
            public void onClick(View v) {
                int i = 0;
                Cursor data = pdb.get( ).getTransaction( );
                ArrayList <Personal> listdataarr = new ArrayList<>( );
                String[] obj= (String[]) listdataarr.toArray();
                while (data.moveToNext( ) &&(i<10)) {
                    Personal per = new Personal( );
                    per.setAmount(data.getFloat(0));
                    per.setCategory(data.getString(1));
                    per.setType(data.getString(2));
                    listdataarr.add(per);
                    i++;
                }
                ListAdapter adapter = new ArrayAdapter<>(activity,R.layout.fragment_my_account,obj);
                listdata.setAdapter(adapter);
            }
        });
        ExpenseSheet.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),tapan.class);
                startActivity(i);
            }

        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /*public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ECLAIR
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            // Take care of calling this method on earlier versions of
            // the platform where it doesn't exist.
            onBackPressed();
        }

        return super.onKeyDown(keyCode,event);
    }


    public void onBackPressed() {
        // This will be called either automatically for you on 2.0
        // or later, or by the code above on earlier versions of the
        // platform.
        Intent in = new Intent(getActivity(), secondpage.class);
        startActivity(in);
    }*/

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
