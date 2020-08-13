package com.drz.home.nominate;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.drz.base.fragment.MvvmLazyFragment;
import com.drz.common.contract.BaseCustomViewModel;
import com.drz.home.R;
import com.drz.home.databinding.HomeFragmentNominateBinding;
import com.drz.home.nominate.adapter.ProviderNominateAdapter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 应用模块: 首页
 * <p>
 * 类描述: 首页-推荐
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-09
 */
public class NominateFragment
    extends MvvmLazyFragment<HomeFragmentNominateBinding, NominateViewModel>
    implements INominateView
{
    private ProviderNominateAdapter adapter;
    
    public static NominateFragment newInstance()
    {
        return new NominateFragment();
    }
    
    @Override
    protected void onFragmentFirstVisible()
    {
        super.onFragmentFirstVisible();
        initView();
        
    }
    
    private void initView()
    {
        
        // 确定Item的改变不会影响RecyclerView的宽高
        viewDataBinding.rvNominateView.setHasFixedSize(true);
        viewDataBinding.rvNominateView
            .setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProviderNominateAdapter();
        adapter.addHeaderView(getHeaderView());
        viewDataBinding.rvNominateView.setAdapter(adapter);
        viewDataBinding.refreshLayout
            .setRefreshHeader(new ClassicsHeader(requireContext()));
        viewDataBinding.refreshLayout
            .setRefreshFooter(new ClassicsFooter(getContext()));
        viewDataBinding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            viewModel.tryToRefresh();
        });
        viewDataBinding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            viewModel.loadMore();
        });
        setLoadSir(viewDataBinding.refreshLayout);
        showLoading();
        viewModel.initModel();
        
    }

    /**
     * 配置头部banner
     * */
    private View getHeaderView()
    {
        ViewDataBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
            R.layout.home_item_banner_view,
            viewDataBinding.rvNominateView,
            false);
        View view = binding.getRoot();
        return view;
    }
    
    @Override
    public int getLayoutId()
    {
        return R.layout.home_fragment_nominate;
    }
    
    @Override
    public int getBindingVariable()
    {
        return 0;
    }
    
    @Override
    protected NominateViewModel getViewModel()
    {
        
        return new ViewModelProvider(this).get(NominateViewModel.class);
    }
    
    @Override
    protected void onRetryBtnClick()
    {
        
    }
    
    @Override
    public void onLoadMoreFailure(String message)
    {
        viewDataBinding.refreshLayout.finishLoadMore(false);
    }
    
    @Override
    public void onLoadMoreEmpty()
    {
        viewDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
    }
    
    @Override
    public void onDataLoadFinish(ArrayList<BaseCustomViewModel> viewModels,
        boolean isFirstPage)
    {
        if (isFirstPage)
        {
            adapter.setNewData(viewModels);
            showContent();
            viewDataBinding.refreshLayout.finishRefresh(true);
        }
        else
        {
            adapter.addData(viewModels);
            showContent();
            viewDataBinding.refreshLayout.finishLoadMore(true);
        }
        
    }
}
