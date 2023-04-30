package com.lxd.myacc.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.lxd.myacc.DiariesViewModel;
import com.lxd.myacc.ToastInfo;
import com.lxd.myacc.databinding.FragmentDiariesBinding;
import com.lxd.myacc.list.DiariesAdapter;

/**
 * @Author lixd
 */
public class DiariesFragment extends Fragment {

    private DiariesViewModel mViewModel;
    private FragmentDiariesBinding fragmentDiariesBin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       fragmentDiariesBin =  fragmentDiariesBin.inflate(inflater,container,false);
        fragmentDiariesBin.setViewModel(mViewModel);
        fragmentDiariesBin.setLayoutManager(new LinearLayoutManager(getContext()));
        initDiariesList();
        return fragmentDiariesBin.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToast();
        
        initRecyclerView();
        
    }

    private void initToast() {
        mViewModel.getToastInfo().observe(this, new ToastInfo.ToastObserver(){

            @Override
            public void onNewMessage(String toastInfo){
                showMessage(toastInfo);
            }

        });
    }

    private void showMessage(String toastInfo) {
        Toast.makeText(getContext(),toastInfo,Toast.LENGTH_SHORT).show();
    }

    private void initRecyclerView() {
        mViewModel.listAdapter.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                setListAdapter(mViewModel.listAdapter.get());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.start();
    }

    public void setViewModel(DiariesViewModel viewModel){
        mViewModel = viewModel;
    }

    private void setListAdapter(DiariesAdapter diariesAdapter) {
        fragmentDiariesBin.diariesList.setAdapter(diariesAdapter);
    }

    private void initDiariesList() {

        fragmentDiariesBin.diariesList.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        fragmentDiariesBin.diariesList.setItemAnimator(new DefaultItemAnimator());
    }
}
